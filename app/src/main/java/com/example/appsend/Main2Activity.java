package com.example.appsend;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class Main2Activity extends Activity implements OnClickListener{
	private Button btnRefund;
	private Button btnVoidSale;
	private Button btnQueryTxn;
	private Button btnVoidAuth;
	private Button btnAddAuth;
	private Button btnAuthComp;
	private Button btnVoidComp;
	private TextView info1;
	private TextView info2;
	private TextView info3;
	private TextView info4;
	private TextView info5;
	private TextView info6;
	private TextView info7;
	private TextView info8;
	private TextView info9;
	private String MultData="";
	private String ChannelAmt="";
	private String PayMode="";
	private ImageButton back;
	private String mReqContent = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);
		findViews();
		TextView view = (TextView) findViewById(android.R.id.title);
        view.setGravity(Gravity.CENTER);
		Intent data = getIntent();
		mReqContent = "";
		StringBuilder stringBuilder = new StringBuilder();
		StringBuilder stringBuilderNew = new StringBuilder();
		JSONObject jsonObject = new JSONObject();

        if (null != data) {
        	if(!data.getStringExtra("RespCode").equals("00")){
        		btnRefund.setVisibility(View.GONE);
        		btnVoidSale.setVisibility(View.GONE);
        		btnVoidAuth.setVisibility(View.GONE);
        		btnAddAuth.setVisibility(View.GONE);
        		btnAuthComp.setVisibility(View.GONE);
        		btnVoidComp.setVisibility(View.GONE);
        	}
    		Bundle bundle = data.getExtras();
			for (String key : bundle.keySet()) {
				stringBuilder.append(key).append(":").append(bundle.get(key)).append("\r\n");

				try {
					jsonObject.put(key, bundle.get(key));
					if ("MerchantTxnNo".equals(key)) {
						jsonObject.put("OrgMerchantTxnNo", bundle.get(key));
					}
					if ("MerchantOrderNo".equals(key)) {
						jsonObject.put("OrgMerchantOrderNo", bundle.get(key));
					}
					if ("TxnType".equals(key)) {
						jsonObject.put("OrgTxnType", bundle.get(key));
					}
					if ("MultData".equals(key)) {
						jsonObject.put("OrgMultData", bundle.get(key));
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			if (jsonObject != null) {
				mReqContent = jsonObject.toString();
			}

			info1.setText("[" + data.getStringExtra("RespCode") + "]" + data.getStringExtra("RespDesc"));
			info2.setText(stringBuilder.toString());

			back = (ImageButton) findViewById(R.id.back_img);
            back.setOnClickListener(new Button.OnClickListener(){  
    	        @Override  
    	        public void onClick(View arg0){  
    	        	 finish();
    	        }   
            });
        }
	}
	public void findViews(){

		btnRefund = (Button)findViewById(R.id.btnRefund);
		btnVoidSale = (Button)findViewById(R.id.btnVoidSale);
		btnQueryTxn = (Button)findViewById(R.id.btnQueryTxn);
		btnVoidAuth = (Button)findViewById(R.id.btnVoidAuth);
		btnAddAuth = (Button)findViewById(R.id.btnAddAuth);
		btnAuthComp = (Button)findViewById(R.id.btnAuthComp);
		btnVoidComp = (Button)findViewById(R.id.btnVoidComp);

		info1 = (TextView)findViewById(R.id.info1);
		info2 = (TextView)findViewById(R.id.info2);
		info2.setMovementMethod(ScrollingMovementMethod.getInstance());

		info3 = (TextView)findViewById(R.id.info3);
		info4 = (TextView)findViewById(R.id.info4);
		info5 = (TextView)findViewById(R.id.info5);
		info6 = (TextView)findViewById(R.id.info6);
		info7 = (TextView)findViewById(R.id.info7);
		info8 = (TextView)findViewById(R.id.info8);
		info9 = (TextView)findViewById(R.id.info9);

		btnRefund.setOnClickListener(this);
		btnVoidSale.setOnClickListener(this);
		btnQueryTxn.setOnClickListener(this);
		btnVoidAuth.setOnClickListener(this);
		btnAddAuth.setOnClickListener(this);
		btnAuthComp.setOnClickListener(this);
		btnVoidComp.setOnClickListener(this);
	}
	
	

	@Override
	public void onClick(View v) {
		Intent intent=new Intent(Main2Activity.this,SaleActivity.class);
		JSONObject jsonTmp = new JSONObject();
		switch (v.getId()) {
		case R.id.btnRefund:
			try {
				jsonTmp = new JSONObject(mReqContent);
				jsonTmp.put("TxnType", "103");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			intent.putExtra("reqContent", jsonTmp.toString());
			startActivity(intent);
			break;
		case R.id.btnVoidSale:
			try {
				jsonTmp = new JSONObject(mReqContent);
				jsonTmp.put("TxnType", "102");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			intent.putExtra("reqContent", jsonTmp.toString());
			startActivity(intent);
			break;
		case R.id.btnQueryTxn:
			try {
				jsonTmp = new JSONObject(mReqContent);
				jsonTmp.put("TxnType", "104");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			intent.putExtra("reqContent", jsonTmp.toString());
			startActivity(intent);
			break;
		case R.id.btnVoidAuth:
			try {
				jsonTmp = new JSONObject(mReqContent);
				jsonTmp.put("TxnType", "107");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			intent.putExtra("reqContent", jsonTmp.toString());
			startActivity(intent);
			break;
		case R.id.btnAddAuth:
			try {
				jsonTmp = new JSONObject(mReqContent);
				jsonTmp.put("TxnType", "118");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			intent.putExtra("reqContent", jsonTmp.toString());
			startActivity(intent);
			break;
		case R.id.btnAuthComp:
			try {
				jsonTmp = new JSONObject(mReqContent);
				jsonTmp.put("TxnType", "108");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			intent.putExtra("reqContent", jsonTmp.toString());
			startActivity(intent);
			break;
		case R.id.btnVoidComp:
			try {
				jsonTmp = new JSONObject(mReqContent);
				jsonTmp.put("TxnType", "109");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			intent.putExtra("reqContent", jsonTmp.toString());
			startActivity(intent);
			break;
		default:
			break;
		}
	}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    		
    }
}
