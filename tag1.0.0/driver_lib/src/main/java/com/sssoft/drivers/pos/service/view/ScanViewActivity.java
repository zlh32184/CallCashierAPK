package com.sssoft.drivers.pos.service.view;

import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.newland.SettingsManager;
import android.os.Bundle;
import android.os.Message;
import android.os.RemoteException;
import android.view.KeyEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.newland.mtype.common.Const;
import com.newland.mtype.log.DeviceLogger;
import com.newland.mtype.log.DeviceLoggerFactory;
import com.newland.mtype.module.common.scanner.ScannerListener;
import com.sssoft.drivers.pos.impl.newland.n9.NScannerSample;
import com.sssoft.drivers.pos.service.R;

public class ScanViewActivity extends Activity{
	
	private SurfaceView surfaceView;
	private Context context;
	private int timeout;
	private boolean isFinish=true;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.sacn_view);
		context=this;
		timeout = getIntent().getIntExtra("timeout", 60);
		init();
	}
	
	
	
	private void init() {
		surfaceView=(SurfaceView) findViewById(R.id.surfaceView);
		startScan();
	}
	
	private void startScan(){
		try{
			NScannerSample.scanner.initScanner(context,surfaceView,0x00);
			NScannerSample.scanner.startScan(timeout, TimeUnit.SECONDS, new ScannerListener() {

				@Override
				public void onResponse(String[] barcodes) {
					isFinish=true;
					try {
						NScannerSample.listener2.onScanResult(barcodes[0]);
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				@Override
				public void onFinish() {
					try {
						NScannerSample.listener2.onFinish();
						if(isFinish){
							finish();
						}
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			},true);
		}catch(Exception e){
			try {
				NScannerSample.listener2.onError("XX", "scan err :"+e.getLocalizedMessage());
				finish();
			} catch (RemoteException ex) {
				
			}
		}
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event){
		if (keyCode == KeyEvent.KEYCODE_BACK){//返回键监听
			NScannerSample.scanner.stopScan();
			try {
				NScannerSample.listener2.onFinish();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            finish();
         }
         return super.onKeyDown(keyCode, event);
		
	}
}
