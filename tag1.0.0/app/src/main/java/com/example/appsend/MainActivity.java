package com.example.appsend;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.TestJava.ConfirmDialog;
import com.sssoft.drivers.pos.aidl.Card;
import com.sssoft.drivers.pos.aidl.Scanner;
import com.sssoft.drivers.pos.aidl.ScannerListener;
import com.sssoft.drivers.pos.aidl.XposDriverInc;
import com.sssoft.drivers.pos.service.AIDLService;
import com.sssoft.drivers.pos.service.XposDriverService;

@SuppressLint("NewApi")
public class MainActivity extends Activity implements OnClickListener{

    private static final String TAG = "MainActivity";
    private Button sale;
	private Button refund;
	private Button cancel;
	private Button query;
	private Button admin, inquiry;
	private Button settle,inq_all_tran,reprint;
	private Button bGetDevice;
	private Button bIsScan;
	private ImageButton back;
	private static Context mContext;
	private Scanner scanner;
	private Card card;
	private String flag = "1";
	XposDriverInc xposDriverInc;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViews();
		back = (ImageButton) findViewById(R.id.back_img);
        back.setOnClickListener(new Button.OnClickListener(){  
	        @Override  
	        public void onClick(View arg0){  
	        	 finish();
	        }   
        });
        mContext = this;

		SharedPreferences sharedPreferences = getSharedPreferences("MyShared", Context.MODE_PRIVATE);
		Log.d(TAG, "onCreate: " + sharedPreferences.getString("Key",""));
//		sharedPreferences.edit().putString("Key", "zhaolh").commit();

		Log.d(TAG, "onCreate: " + sharedPreferences.getString("Key",""));

		AIDLService.init(mContext,"CCB");
        xposDriverInc = XposDriverInc.Stub.asInterface(new XposDriverService());

//		DeviceHelper.getInstance().init(mContext);
//		DeviceHelper.getInstance().bindService();
        //ServiceUtil.startServiceConnect(mContext);
//        Intent intent = new Intent();  
//	    intent.setAction("com.icbc.smartpos.device_service");
//	    intent.setPackage("com.icbc.smartpos.deviceservice");
//	    bindService(intent, A90Connection, Context.BIND_AUTO_CREATE);
	}
	
//	public ServiceConnection A90Connection = new ServiceConnection(){
//        
//        @Override
//        public void onServiceDisconnected(ComponentName name){
//        	deviceServiceA90 = null;
//        }
//        
//        @Override
//        public void onServiceConnected(ComponentName name, IBinder service)
//        {
//        	deviceServiceA90 = com.icbc.smartpos.deviceservice.aidl.IDeviceService.Stub.asInterface(service);
//        }
//    };
    public void findViews(){

		sale = (Button)findViewById(R.id.sale);
		refund = (Button)findViewById(R.id.refund);
		cancel = (Button)findViewById(R.id.cancel);
		query = (Button)findViewById(R.id.query);
		admin = (Button) findViewById(R.id.admin);
		inquiry = (Button) findViewById(R.id.inquiry);
		settle = (Button) findViewById(R.id.settle);
		inq_all_tran = (Button) findViewById(R.id.inq_all_tran);
		reprint = (Button) findViewById(R.id.reprint);
		bGetDevice = (Button) findViewById(R.id.getDevice);
        bIsScan = (Button) findViewById(R.id.is_scan);

		sale.setOnClickListener(this);
		refund.setOnClickListener(this);
		cancel.setOnClickListener(this);
		query.setOnClickListener(this);
		admin.setOnClickListener(this);
		inquiry.setOnClickListener(this);
		settle.setOnClickListener(this);
		inq_all_tran.setOnClickListener(this);
		reprint.setOnClickListener(this);
		bGetDevice.setOnClickListener(this);

        bIsScan.setOnClickListener(this);


	}
	@Override 
	 public void onDestroy() { 
//		 if(ServiceUtil.getXposDriverService()!=null)
//		 unbindService(ServiceUtil.mserviceConnection);
		 super.onDestroy();
	 }

	/**
	 * @param v
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.sale: 
			Intent intent=new Intent(MainActivity.this,SaleActivity.class);
			startActivity(intent);
			break;
		case R.id.refund: 
			Intent intent1=new Intent(MainActivity.this,RefundActivity.class);
			startActivity(intent1);
			break;
		case R.id.cancel: 
			Intent intent2=new Intent(MainActivity.this,CancelActivity.class);
			startActivity(intent2);
			break;
		case R.id.admin:
			Uri uri_admin = Uri.parse("sssoft://sssoft.uri.activity/payTrans?TxnType=105");
			intent = new Intent("android.sssoft.schemeurl.activity");
			intent.setData(uri_admin);
			startActivity(intent);
			break;
		case R.id.inquiry:

			Uri uri_inq = Uri.parse("sssoft://sssoft.uri.activity/payTrans?TxnType=105");
			intent = new Intent("android.sssoft.schemeurl.activity");
			intent.setData(uri_inq);
			startActivity(intent);
			break;
		case R.id.settle:

			Uri uri_settle = Uri.parse("sssoft://sssoft.uri.activity/payTrans?TxnType=105");
			intent = new Intent("android.sssoft.schemeurl.activity");
			intent.setData(uri_settle);
			startActivity(intent);
			break;
		case R.id.inq_all_tran:

			Uri uri_inq_all_tran = Uri.parse("sssoft://sssoft.uri.activity/payTrans?TxnType=105");
			intent = new Intent("android.sssoft.schemeurl.activity");
			intent.setData(uri_inq_all_tran);
			startActivity(intent);
			break;
		case R.id.reprint:

			Uri uri_reprint = Uri.parse("sssoft://sssoft.uri.activity/payTrans?TxnType=105");
			intent = new Intent("android.sssoft.schemeurl.activity");
			intent.setData(uri_reprint);
			startActivity(intent);
			break;
		case R.id.query:

			Intent intent3=new Intent(MainActivity.this,QueryActivity.class);
			startActivity(intent3);
//			try {
//				card = ServiceUtil.getXposDriverService().getCard();
//				card.read(30, new CardListener.Stub() {
//					
//					@Override
//					public void onReadResult(String[] arg0) throws RemoteException {
//						Log.i("cheny", arg0[0]+arg0[1]);
//						
//					}
//					
//					@Override
//					public void onFinish() throws RemoteException {
//						Log.i("cheny", "onFinish");
//						
//					}
//					
//					@Override
//					public void onError(String arg0, String arg1) throws RemoteException {
//						// TODO Auto-generated method stub
//						
//					}
//				});
//			} catch (RemoteException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			//scanner();
			
//			try {
//				DownLoadUtil down = new DownLoadUtil(mContext);
//				down.startDownLoad();
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

			break;
            case R.id.getDevice:
                String strBuf = new String();
               // strBuf = getTelephonyManager();
				strBuf = "12333";
				ConfirmDialog.showComfirmDialog(this, "Help", strBuf);

                Toast.makeText(mContext, strBuf, Toast.LENGTH_LONG).show();
                break;

            case R.id.is_scan:

                try {
                    Scanner scanner = xposDriverInc.getScanner();
                    scanner.startScan(2, 30, new ScannerListener.Stub() {
                        @Override
                        public void onScanResult(String barcode) throws RemoteException {
                            Log.d(TAG, "onScanResult: "+barcode);
                        }

                        @Override
                        public void onError(String code, String detail) throws RemoteException {
                            Log.d(TAG, "onError: "+detail);

                        }

                        @Override
                        public void onFinish() throws RemoteException {

                            Log.d(TAG, "onScanResult: onFinish");
                        }
                    });
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            	/*new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						Bundle bundle = new Bundle();
						bundle.putLong( "timeout", 30);
						bundle.putInt( "payType", 0);
						bundle.putString("title", "请快点扫码或条形码...亲");
						try {
							DeviceHelper deviceHelper = DeviceHelper.getInstance();
							IScanner iScanner = deviceHelper.getScanner(1);
							iScanner.startScan(bundle, new OnScanListener.Stub() {
								@Override
								public void onSuccess(Bundle bundle) throws RemoteException {
									Toast.makeText(MainActivity.this, bundle.getString("barcode"), Toast.LENGTH_SHORT).show();
								}

								@Override
								public void onError(int i, String s) throws RemoteException {
									Toast.makeText(MainActivity.this, i+s, Toast.LENGTH_SHORT).show();
								}

								@Override
								public void onTimeout() throws RemoteException {
									Toast.makeText(MainActivity.this, "Timeout", Toast.LENGTH_SHORT).show();
								}

								@Override
								public void onCancel() throws RemoteException {
									Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_SHORT).show();
								}
							});

						} catch (RemoteException e) {
							e.printStackTrace();
						}

					}
				}).start();*/
                break;
		default:
			break;
		}
	}
	public void scanner(){


	}
//	public void setPrintText(){
//		Bundle formatSC = new Bundle();
//		formatSC.putString("font", "small");
//		formatSC.putString("align", "center");
//		Bundle formatNC = new Bundle();
//		formatNC.putString("font", "normal");
//		formatNC.putString("align", "center");
//		Bundle formatNL = new Bundle();
//		formatNL.putString("font", "normal");
//		formatNL.putString("align", "left");
//		Bundle formatNR = new Bundle();
//		formatNR.putString("font", "normal");
//		formatNR.putString("align", "right");
//		Bundle formatLC = new Bundle();
//		formatLC.putString("font", "large");
//		formatLC.putString("align", "center");
//		Bundle formatLL = new Bundle();
//		formatLL.putString("font", "large");
//		formatLL.putString("align", "left");
//		try {
//			Printer printer = ServiceUtil.getXposDriverService().getPrinter();
//			if(flag.equals("1")){
//				printer.addText(formatLC, "�̻���");
//			}else{
//				printer.addText(formatLC, "�û���");
//			}
//			printer.addText(formatSC, "--------------------------------------");
//
//			printer.addText(formatNC, "����XXXXX�̻�");
//			
//			printer.addText(formatLC, "����"+":0.01");
//			
//			printer.addText(formatSC, "���׳ɹ�");
//
//			printer.addText(formatSC, "---------------------------------------");
//			
//			printer.addText(formatNL, "֧���˺�:1234567890123456789");
//			
//			printer.addText(formatNL, "֧����ʽ:���п�");
//			
//			printer.addText(formatNL, "����ʱ��:2017/12/22 14:29:59");
//			
//			printer.addText(formatNL, "�̻����:121212121212121");
//			
//			printer.addText(formatNL, "�ն˱��:12345678");
//			
//			printer.addText(formatNL, "����Ա��:01");
//			
//
//			printer.addText(formatSC, "---------------------------------------");
//			
//			if(flag.equals("1")){
//				printer.addText(formatNL, "�û�ǩ��:");
//				printer.addText(formatLC, "           ");
//				printer.addText(formatLC, "           ");
//				printer.addText(formatSC, "--------------------------------------");
//			}
//			
//			printer.addText(formatNC, "�̻���ɨ���˿���ѯ����");
//			
//			printer.addBarCode(formatNC, "88345837489753485");
//			
//			printer.addText(formatNC, "88345837489753485");
//			
//			printer.paperSkip(3);
//    		printer.startPrinter(new PrinterListener.Stub() {
//				@Override
//				public void onFinish() throws RemoteException {
//					 Message msg = printHandler.obtainMessage();
//					 if(flag.equals("1")){
//           				 msg.what = 0; 
//					 }else{
//						 msg.what = 2; 
//					 }
//					 printHandler.sendMessage(msg);
//				}
//				@Override
//				public void onError(String code, String detail) throws RemoteException {
//					 Message msg = printHandler.obtainMessage();
//      				 msg.what = 1;
//      				 msg.obj = detail;
//      				 printHandler.sendMessage(msg);
//      			}
//			});
//		} catch (Exception e) {
//			
//			Message msg = printHandler.obtainMessage();
//			msg.obj = "��ӡʧ�ܣ�"+e.getLocalizedMessage();
//			msg.what = 3; 
//			printHandler.sendMessage(msg);
//		}
//	}
//  	public Handler printHandler = new Handler(Looper.getMainLooper(),new Callback() {
//		@Override
//		public boolean handleMessage(Message msg) {
//		    if (msg.what==0) {
//				 AlertDialog.Builder StopDialog =new AlertDialog.Builder(mContext,AlertDialog.THEME_HOLO_LIGHT);//����һ�����������
//				 StopDialog.setCancelable(false);
//				 StopDialog.setPositiveButton("ȡ��", new DialogInterface.OnClickListener() {  
//	             public void onClick(DialogInterface dialog, int which) {     
//	            	 
//	             }  
//	             });
//	             StopDialog.setNegativeButton("��ӡ�ڶ���",new DialogInterface.OnClickListener() {                       
//	                 public void onClick(DialogInterface dialog, int which) {  
//	                	 	 flag="2";
//	                		 setPrintText();
//	                 }
//	             });
//	             Dialog a = StopDialog.create();
//		  	     a.getWindow().addFlags(5);
//		  	     a.show();
//		  	     a.getWindow().setType(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
//			}
//		    if (msg.what==1) {
//		    	 AlertDialog.Builder StopDialog =new AlertDialog.Builder(mContext,AlertDialog.THEME_HOLO_LIGHT);//����һ�����������
//				 StopDialog.setCancelable(false);
//				 StopDialog.setMessage((String)msg.obj);
//	             StopDialog.setPositiveButton("ȷ��",new DialogInterface.OnClickListener() {                       
//	                 public void onClick(DialogInterface dialog, int which) {  
//	                	 try {
//	                		setPrintText();
//						 } catch (Exception e) {
//							
//						 }
//	                 }
//	             });
//	             StopDialog.setNegativeButton("ȡ��",new DialogInterface.OnClickListener() {                       
//	                 public void onClick(DialogInterface dialog, int which) {  
//	                	 
//	                 }
//	             });
//	             Dialog a = StopDialog.create();
//		  	     a.getWindow().addFlags(5);
//		  	     a.show();
//		  	     a.getWindow().setType(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
//			}
//		    if (msg.what==2 || msg.what==3){
//		    	 if(msg.what==3)
//		    		 Toast.makeText(mContext, (String)msg.obj, Toast.LENGTH_LONG).show();
//		    	
//			}
//			return false;
//		}
//	});

    /**
     * @return
     */
    public String getTelephonyManager() {
	    String result =null;
        @SuppressLint("ServiceCast")
		TelephonyManager tm = (TelephonyManager ) this.getSystemService(Context.TELEPHONY_SERVICE);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n==========\nTelephonyManager\n")
                    .append("getDataState()=").append(tm.getDataState()).append("\n")
                    .append("getDeviceID()=").append(tm.getDeviceId()).append("\n")
                    .append("getLineNumber()").append(tm.getLine1Number()).append("\n")
                    .append("getSimSerialNumber=").append(tm.getSimSerialNumber()).append("\n")
                    .append("getSubscriberId=").append(tm.getSubscriberId()).append("\n")
                    .append("isNetworkRoaming()=").append(tm.isNetworkRoaming()).append("\n")
                    .append("getDeviceSoftwareVersion()=").append(tm.getDeviceSoftwareVersion()).append("\n");

        Log.i(TAG, "getTelephonyManager: "+stringBuilder.toString() );
        return stringBuilder.toString();
    }
}
