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

public class QueryActivity extends Activity implements OnClickListener{
	private static final String TAG = "QueryActivity";
	private EditText text1;
	private EditText text2;
	private EditText text3;
	private EditText text4;
	private EditText text5;
	private Button bnt1;
	private Button clear;
	private ImageButton back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_query);
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
	}
	public void findViews(){
		text2 = (EditText)findViewById(R.id.text2);
		text3 = (EditText)findViewById(R.id.text3);
		text4 = (EditText)findViewById(R.id.text4);
		text5 = (EditText)findViewById(R.id.text5);
		text1 = (EditText)findViewById(R.id.text1);
		bnt1 = (Button)findViewById(R.id.print_text_ce);
		clear = (Button)findViewById(R.id.clear);
		bnt1.setOnClickListener(this);
		clear.setOnClickListener(this);
	}
	
	

	@Override
	public void onClick(View v) {
		String TxnAmt = text1.getText().toString();
		String payMode = text2.getText().toString();
		String CurrencyCode = text3.getText().toString();
		String QueryType = text4.getText().toString();
		String OrgTxnNo = text5.getText().toString();
		switch (v.getId()) {
		case R.id.print_text_ce: 
				Uri uri = Uri.parse("sssoft://sssoft.uri.activity/payTrans?TxnType=104&TxnAmt="+TxnAmt+"&CurrencyCode="+CurrencyCode+"&PayMode="+payMode+"&OrgTxnType="+QueryType+"&OrgMerchantTxnNo="+OrgTxnNo);
            Intent intent = new Intent("android.sssoft.schemeurl.activity");
				intent.setData(uri);
				startActivityForResult(intent, 20);
				break;
		case R.id.clear: 
			text2.setText("");
			text3.setText("");
			text4.setText("");
			text5.setText("");
			text1.setText("");
		break;
		default:
			break;
		}
	}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	Intent intent=new Intent(QueryActivity.this,Main2Activity.class);
		String channelTxnNo = data==null?"":data.getStringExtra("ChannelTxnNo");
		String channelTxnRrn = data==null?"":data.getStringExtra("ChannelTxnRrn");
    	intent.putExtra("RespCode", data==null?"":data.getStringExtra("RespCode"));
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
		intent.putExtra("TxnType","104");
		startActivity(intent);
    }
}
