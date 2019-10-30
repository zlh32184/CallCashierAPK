package com.example.appsend;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.sssoft.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SaleActivity extends Activity implements OnClickListener{
	private static final String TAG ="SaleActivity" ;
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
	private EditText text13;
	private EditText text14;
	private EditText text15;
	private Button bnt1;
	private Button bnt4;
	private Button clear;
	private String MerchantTxnNo = "";
	private String payMode;
	private ImageButton back;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sale);
		findViews();
		text2.setText("0.01");
		text4.setText("1");
		text6.setText("2003");
		TextView view = (TextView) findViewById(android.R.id.title);
        view.setGravity(Gravity.CENTER);
        back = (ImageButton) findViewById(R.id.back_img);
        back.setOnClickListener(new Button.OnClickListener(){  
	        @Override  
	        public void onClick(View arg0){  
	        	 finish();
	        }   
        });

		SharedPreferences sharedPreferences = getSharedPreferences("MyShared", MODE_PRIVATE);
		sharedPreferences.edit().putString("Key", "zhaolh 2").commit();

		Log.d(TAG, "onCreate: " + sharedPreferences.getString("Key",""));
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
		text13 = (EditText)findViewById(R.id.text13);
		text14 = (EditText)findViewById(R.id.text14);
		text15 = (EditText)findViewById(R.id.text15);
		bnt1 = (Button)findViewById(R.id.print_text_ce);
		bnt4 = (Button)findViewById(R.id.print_qrcode_ce);
		clear = (Button)findViewById(R.id.clear);
		bnt1.setOnClickListener(this);
		bnt4.setOnClickListener(this);
		clear.setOnClickListener(this);
		text11.setText((new Date()).getTime()+"");
		text12.setText("101");
	}
	
	

	@Override
	public void onClick(View v) {
		JSONObject json = new JSONObject();
		json.put("Key1", "测试");
		json.put("Value1", "1111111111111");
		MerchantTxnNo = text11.getText().toString();
		String TxnAmt = text2.getText().toString();
		String CurrencyCode = text3.getText().toString();
		payMode = text4.getText().toString();
		String PermitDisctAmt = text5.getText().toString();
		String CashierID = text6.getText().toString();
		String TxnReqTime = text7.getText().toString();
		String TxnLongDesc = text8.getText().toString();
		String TxnShortDesc = text9.getText().toString();
		String ItemDetail = text10.getText().toString();
		String txnType = text12.getText().toString();
		
		String OrgAuthCode = text13.getText().toString();
		String OrgTxnDate = text14.getText().toString();
		String OrgMTxnNo = text15.getText().toString();
		switch (v.getId()) {
		case R.id.print_text_ce: 
				Uri uri = Uri.parse("sssoft://sssoft.uri.activity/payTrans?TxnType="+txnType+
							"&MerchantTxnNo="+MerchantTxnNo+
							"&MerCode="+"10040000000099"+
							"&TxnAmt="+TxnAmt+
							"&CurrencyCode="+CurrencyCode+
							"&CashierID=" + CashierID+
							"&TxnReqTime="+	TxnReqTime+
							"&PayMode="+payMode+
							"&TxnLongDesc="+TxnLongDesc+
							"&TxnShortDesc="+TxnShortDesc+
							"&PermitDisctAmt="+PermitDisctAmt+
							"&ItemDetail="+ItemDetail+
							"&OrgAuthCode="+OrgAuthCode+
							"&OrgTxnDate="+OrgTxnDate+
							"&OrgMTxnNo="+OrgMTxnNo+
							"&QRCode="+OrgMTxnNo+
							"&AddInfo="+json.toString());  
				Intent intent = new Intent("android.sssoft.schemeurl.activity");
				intent.setData(uri);
				startActivityForResult(intent, 10);
				break;
		case R.id.print_qrcode_ce: 
			break;
		case R.id.clear: 
//			text2.setText("");
//			text3.setText("");
//			text4.setText("");
//			text5.setText("");
//			text6.setText("");
//			text8.setText("");
//			text9.setText("");
//			text10.setText("");
//			text11.setText("");
//			text13.setText("");
//			text14.setText("");
//			text15.setText("");
//			Uri uri2 = Uri.parse("mobile://mobile.uri.activity/sale?ChannelID=6&MerchantTxnNo="+MerchantTxnNo+"&TxnAmt="+TxnAmt+"&CurrencyCode="+CurrencyCode+
//					"&TxnReqTime="+TxnReqTime+"&SmType=0&CashierID="+CashierID+"&TxnLongDesc="+TxnLongDesc+"&TxnShortDesc="+TxnShortDesc+"&PermitDisctAmt="+PermitDisctAmt+"&ItemDetail="+ItemDetail+"&AddInfo="+json.toString());  
//			Intent intent2 = new Intent(Intent.ACTION_VIEW);
//			intent2.setData(uri2);  
//			startActivityForResult(intent2, 10);
			Uri uri2 = Uri.parse("mobile://mobile.uri.activity/query?OrgPlatformTxnNo=6&OrgTxnNo="+MerchantTxnNo+"&TxnAmt="+TxnAmt+"&CurrencyCode="+CurrencyCode+
					"&TxnReqTime="+TxnReqTime+"&SmType=0&CashierID="+CashierID+"&QueryType=5&ChannelID=6");  
			Intent intent2 = new Intent(Intent.ACTION_VIEW);
			intent2.setData(uri2);  
			startActivityForResult(intent2, 10);
		break;
		default:
			break;
		}
	}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    		Intent intent=new Intent(SaleActivity.this,Main2Activity.class);
    		String channelTxnNo = data==null?"":data.getStringExtra("ChannelTxnNo");
    		String channelTxnRrn = data==null?"":data.getStringExtra("ChannelTxnRrn");
        	intent.putExtra("RespCode", data==null?"":data.getStringExtra("RespCode"));
        	intent.putExtra("Sign", data==null?"":data.getStringExtra("Sign"));
    		intent.putExtra("RespDesc", data==null?"":data.getStringExtra("RespDesc"));
    		intent.putExtra("TxnAnsTime", data==null?"":data.getStringExtra("TxnAnsTime"));
    		intent.putExtra("ChannelID", data==null?"":data.getStringExtra("ChannelID"));
    		intent.putExtra("ChannelName", data==null?"":data.getStringExtra("ChannelName"));
    		intent.putExtra("ChannelTxnNo", channelTxnNo);
    		intent.putExtra("MerchantName", data==null?"":data.getStringExtra("MerchantName"));
    		intent.putExtra("MerchantID", data==null?"":data.getStringExtra("MerchantID"));
    		intent.putExtra("TerminalID", data==null?"":data.getStringExtra("TerminalID"));
    		intent.putExtra("PayerID", data==null?"":data.getStringExtra("PayerID"));
    		intent.putExtra("BatchNo", data==null?"":data.getStringExtra("BatchNo"));
			intent.putExtra("TotalAmt", data.getDoubleExtra("TotalAmt",0.00));
			intent.putExtra("AuthCode", data==null?"":data.getStringExtra("AuthCode"));
			intent.putExtra("IncomeAmt", data.getDoubleExtra("IncomeAmt",0.00));
			intent.putExtra("InvoiceAmt", data.getDoubleExtra("InvoiceAmt",0.00));
			intent.putExtra("PointAmt", data.getDoubleExtra("PointAmt",0.00));
			intent.putExtra("MerchantDisctAmt", data.getDoubleExtra("MerchantDisctAmt",0.00));
			intent.putExtra("ChannelDisctAmt", data.getDoubleExtra("ChannelDisctAmt",0.00));
			intent.putExtra("ChannelTxnRrn", channelTxnRrn);
			intent.putExtra("PlatformTxnNo", data.getStringExtra("PlatformTxnNo"));
			intent.putExtra("MultData", data.getStringExtra("MultData"));
			intent.putExtra("OrderNo", data.getStringExtra("OrderNo"));

			intent.putExtra("TxnType","101");
			intent.putExtra("PayMode",payMode);

			startActivity(intent);
    }
}
