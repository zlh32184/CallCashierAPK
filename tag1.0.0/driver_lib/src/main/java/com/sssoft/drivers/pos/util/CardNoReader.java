package com.sssoft.drivers.pos.util;

import java.util.List;

import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;

import com.zacloud.deviceservice.aidl.CheckCardListener;
import com.zacloud.deviceservice.aidl.IPBOC;
import com.zacloud.deviceservice.aidl.PBOCHandler;

/**
 * Created by XC on 2018/2/26.
 */

public class CardNoReader {
    private static String TAG = "CardNoReader";
    private static CardNoReader instance = null;
    private static IPBOC Ipboc;
    
    public static synchronized CardNoReader getInstance(IPBOC ipboc) {
        Log.i(TAG, "CardNoReader.getInstance()");

        if (instance == null) {
            instance = new CardNoReader();
            Ipboc = ipboc;
        }
        return instance;
    }

    public void readCardNo(int timeOut, ReadCardListener readCardListener) {
        Log.i(TAG, "readCardNo() executed, timeout="+timeOut);
        final ReadCardListener readCardNoListener;
        final CheckCardListener checkCardListener;

        if(readCardListener == null){
            Log.i(TAG, "readCardListener is null");
            return;
        }

        readCardNoListener = readCardListener;

        if(timeOut <= 0 || timeOut > 300){
            timeOut = 60;
        }

        Bundle checkCardBdl = new Bundle();
        checkCardBdl.putBoolean("supportMagCard", true);
        checkCardBdl.putBoolean("supportICCard", true);
        checkCardBdl.putBoolean("supportRFCard", true);

        checkCard(checkCardBdl, timeOut, new CheckCardListener.Stub() {

            @Override
            public void onCardSwiped(Bundle track) throws RemoteException {
                Log.i(TAG, "onCardSwiped(), PAN=" + track.getString("PAN"));
                stopCheckCard();
                readCardNoListener.onSuccess(track.getString("PAN"));
            }

            @Override
            public void onCardPowerUp() throws RemoteException {
                Log.i(TAG, "onCardPowerUp()");

                stopCheckCard();
                startPBOCProcess(0); //插卡
            }

            @Override
            public void onCardActivate() throws RemoteException {
                Log.i(TAG, "onCardActivate()");

                stopCheckCard();
                startPBOCProcess(1); //挥卡
            }

            @Override
            public void onTimeout() throws RemoteException {
                Log.i(TAG, "onTimeout()");

                stopCheckCard();
                readCardNoListener.onError(5, "读卡超时");
            }

            @Override
            public void onError(int error, String message) throws RemoteException {
                Log.i(TAG, "onError(), error=" + error + ", message=" + message);

                stopCheckCard();
                readCardNoListener.onError(error, message);
            }

            private void startPBOCProcess(int slotType) {
                Bundle pbocBdl = new Bundle();
                pbocBdl.putInt("cardType", slotType); //0-插卡，1-挥卡

                long amt = 0;
                pbocBdl.putLong("authAmount", amt);
                pbocBdl.putBoolean("isSupportQ", true);
                pbocBdl.putBoolean("isSupportEC", false);
                pbocBdl.putBoolean("isSupportSM", true);
                pbocBdl.putBoolean("isQPBOCForceOnline", true);
                pbocBdl.putBoolean("isSupportPBOCFirst", false);
                pbocBdl.putString("merchantName", "");
                pbocBdl.putString("merchantId", "");
                pbocBdl.putString("terminalId", "");

                //启动简易流程(仅需读卡号)
                startPBOC(12, pbocBdl, new PBOCHandler.Stub() {
                    @Override
                    public void onRequestAmount() throws RemoteException {
                        return;
                    }

                    @Override
                    public void onSelectApplication(List<String> appList) throws RemoteException {
                        return;
                    }

                    @Override
                    public void onConfirmCardInfo(Bundle info) throws RemoteException {
                        readCardNoListener.onSuccess(info.getString("PAN"));
                        abortPBOC();
                    }

                    @Override
                    public void onRequestInputPIN(boolean isOnlinePin, int retryTimes) throws RemoteException {
//                        readCardNoListener.onError(100, "流程异常1");
                        abortPBOC();
                    }

                    @Override
                    public void onConfirmCertInfo(String certType, String certInfo) throws RemoteException {
//                        readCardNoListener.onError(100, "流程异常2");
                        abortPBOC();
                    }

                    @Override
                    public void onRequestOnlineProcess(Bundle aaResult) throws RemoteException {
//                        readCardNoListener.onError(100, "流程异常3");
                        abortPBOC();
                    }

                    @Override
                    public void onTransactionResult(int result, Bundle data) throws RemoteException {
                        readCardNoListener.onError(result, data.getString("ERROR"));
                        abortPBOC();
                    }
                });
            }
        } );

    }

    /**
     * stopReadCard: 取消卡号读取
     */
    public void stopReadCard() {
        Log.i(TAG, "stopReadCard() executed");

        stopCheckCard();
    }


    private void checkCard(Bundle bdl, int timeOut, CheckCardListener checkCardListener) {
        Log.i(TAG, "checkCard() executed");

        try {
        	Ipboc.checkCard(bdl, timeOut, checkCardListener);
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    private void stopCheckCard() {
        Log.i(TAG, "stopCheckCard() executed");

        try {
        	Ipboc.stopCheckCard();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    private void startPBOC(int transType, Bundle bdl, PBOCHandler pbocHandler) {
        Log.i(TAG, "startPBOC() executed");

        try {
        	Ipboc.startPBOC(transType, bdl, pbocHandler);
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    private void abortPBOC() {
        Log.i(TAG, "abortPBOC() executed");

        try {
        	Ipboc.abortPBOC();
        }catch (Exception e) {
            e.printStackTrace();
        } 
    }
}
