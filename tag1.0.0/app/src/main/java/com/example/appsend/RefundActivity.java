package com.example.appsend;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RefundActivity extends Activity implements OnClickListener{
	private EditText text2;
	private EditText text3;
	private EditText text4;
	private EditText text5;
	private EditText text6;
	private EditText text7;
	private EditText text8;
	private EditText text9;
	private EditText text10;
	private EditText text11;
	private EditText text12;
	private Button bnt1;
	private Button clear;
	private String RefundTxnNo = "";
	private ImageButton back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_refund);
		findViews();
		TextView view = (TextView) findViewById(android.R.id.title);
        view.setGravity(Gravity.CENTER);
        back = (ImageButton) findViewById(R.id.back_img);
        back.setOnClickListener(new Button.OnClickListener(){  
	        @Override  
	        public void onClick(View arg0){  
	        	 finish();
	        }   
        });
		Intent data = getIntent();  
        if (null != data) { 
        	String PayMode = data.getStringExtra("PayMode");
        	String MultData = data.getStringExtra("MultData");
        	String ChannelAmt = data.getStringExtra("ChannelAmt");
        	text4.setText(PayMode);
        	text5.setText(MultData);
        	text2.setText(ChannelAmt);
        }
	}
	public void findViews(){
		text2 = (EditText)findViewById(R.id.text2);
		text3 = (EditText)findViewById(R.id.text3);
		text4 = (EditText)findViewById(R.id.text4);
		text5 = (EditText)findViewById(R.id.text5);
		text6 = (EditText)findViewById(R.id.text6);
		text7 = (EditText)findViewById(R.id.text7);
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String TxnReqTime = format.format(new Date());
		text7.setText(TxnReqTime);
		text8 = (EditText)findViewById(R.id.text8);
		text9 = (EditText)findViewById(R.id.text9);
		text10 = (EditText)findViewById(R.id.text10);
		text11 = (EditText)findViewById(R.id.text11);
		text12 = (EditText)findViewById(R.id.text12);
		bnt1 = (Button)findViewById(R.id.print_text_ce);
		clear = (Button)findViewById(R.id.clear);
		bnt1.setOnClickListener(this);
		clear.setOnClickListener(this);
		text11.setText((new Date()).getTime()+"");
		text12.setText("103");
	}
	
	

	@Override
	public void onClick(View v) {
		RefundTxnNo = text11.getText().toString();
		String TxnAmt = text2.getText().toString();
		String CurrencyCode = text3.getText().toString();
		String PayMode = text4.getText().toString();
		String OrgMultData = text5.getText().toString();
		String CashierID = text6.getText().toString();
		String TxnReqTime = text7.getText().toString();
		String TxnLongDesc = text8.getText().toString();
		String TxnShortDesc = text9.getText().toString();
		String ItemDetail = text10.getText().toString();
		String txnType = text12.getText().toString();
		switch (v.getId()) {
		case R.id.print_text_ce: 
				Uri uri = Uri.parse("sssoft://sssoft.uri.activity/payTrans?TxnType="+txnType+
						"&RefundTxnNo="+RefundTxnNo+
						"&RefundAmt="+TxnAmt+
						"&CurrencyCode="+CurrencyCode+
						"&TxnReqTime="+TxnReqTime+
						"&OrgMultData="+OrgMultData+
						"&OrgTxnNo="+OrgMultData+
						"&PayMode="+PayMode+
						"&CashierID="+CashierID+
						"&TxnLongDesc="+TxnLongDesc+
						"&TxnShortDesc="+TxnShortDesc+
						"&ItemDetail="+ItemDetail);  
				Intent intent = new Intent("android.sssoft.schemeurl.activity");  
				intent.setData(uri);  
				startActivityForResult(intent, 30);
				break;
		case R.id.clear: 
			text2.setText("");
			text3.setText("");
			text4.setText("");
			text5.setText("");
			text6.setText("");
			text8.setText("");
			text9.setText("");
			text10.setText("");
			text11.setText("");
		break;
		default:
			break;
		}
	}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	Intent intent=new Intent(RefundActivity.this,Main2Activity.class);
    	intent.putExtra("RespCode", data==null?"":data.getStringExtra("RespCode"));
		intent.putExtra("RespDesc", data==null?"":data.getStringExtra("RespDesc"));
		intent.putExtra("TxnAnsTime", data==null?"":data.getStringExtra("TxnAnsTime"));
		intent.putExtra("ChannelID", data==null?"":data.getStringExtra("ChannelID"));
		intent.putExtra("ChannelName", data==null?"":data.getStringExtra("ChannelName"));
		intent.putExtra("ChannelTxnNo", data==null?"":data.getStringExtra("ChannelTxnNo"));
		intent.putExtra("MerchantName", data==null?"":data.getStringExtra("MerchantName"));
		intent.putExtra("MerchantID", data==null?"":data.getStringExtra("MerchantID"));
		intent.putExtra("TerminalID", data==null?"":data.getStringExtra("TerminalID"));
		intent.putExtra("PayerID", data==null?"":data.getStringExtra("PayerID"));
		intent.putExtra("BatchNo", data==null?"":data.getStringExtra("BatchNo"));
		intent.putExtra("PlatformTxnNo", data==null?"":data.getStringExtra("PlatformTxnNo"));
		intent.putExtra("ChannelAmt",data.getDoubleExtra("ChannelAmt",0.00));
		intent.putExtra("ChannelTxnRrn", data==null?"":data.getStringExtra("ChannelTxnRrn"));
		intent.putExtra("TxnType","103");
		intent.putExtra("OrderNo", data.getStringExtra("OrderNo"));
		startActivity(intent);
    }
}
