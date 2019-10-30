package com.example.download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.json.sssoft.JSONObject;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.appsend.MainActivity;
import com.example.appsend.R;



@SuppressLint({ "NewApi", "UseValueOf" })
public class DownLoadUtil2 {
	 /*-----------------------更新模块开始-----------------------------*/
	 private final String TAG = this.getClass().getName();
	 private final int UPDATA_NONEED = 0;
	 private final int UPDATA_CLIENT = 1;
	 private final int GET_UNDATAINFO_ERROR = 2;
	 private final int SDCARD_NOMOUNTED = 3;
	 private final int DOWN_ERROR = 4;
	 private final int HTTP_ERROR = 5;
	 private final int DOWN_ING = 10;
	 private final int DOWN_DONE= 11;
	
	 private UpdataInfo info;
	 private NotificationManager mNotificationManager=null;
	 private Notification mNotification;
	 private int fileSize =0 ;
	 private int downLoadFileSize = 0;
	 private  PendingIntent  pIntent;
	 private Intent updateIntent;
	 private Intent azIntent;
	 File file = null;  
	 private String[] oldVersions = new String[3]; //0：驱动apk  1：移动支付apk 2：收银台 
	 private List<FileApp> fileApps = new ArrayList<FileApp>();
	 private String appCode = "";
	 private String appName = "";
	 private String MOBILEPACK = "com.sssoft.mobile.activity";
	 private String DRIVERPACK = "com.sssoft.drivers.pos.service";
	 private List<Integer> listDif = new ArrayList<Integer>();
	 private Context mContext;
	 private String imei;
	 public DownLoadUtil2(Context context){
		 mContext = context;
		 TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
		 imei = tm.getDeviceId();
	 }
	 
	 public void startDownLoad()  throws Exception{
		 SuccinctProgress.showSuccinctProgress(mContext, "正在检查更新...", false, false);
		 fileApps = new ArrayList<FileApp>();
		 listDif = new ArrayList<Integer>();
		 getVersionName();
		 
		 CheckVersionTask cv = new CheckVersionTask();
		 new Thread(cv).start();
	 }
	 private void getVersionName() throws Exception {
			//getPackageName()是你当前类的包名，0代表是获取版本信息  
		PackageManager packageManager = mContext.getPackageManager();
		PackageInfo packInfo = packageManager.getPackageInfo(DRIVERPACK,0);
		oldVersions[0] = packInfo.versionName;//驱动apk版本
		packInfo = packageManager.getPackageInfo(MOBILEPACK,0);
	    oldVersions[1] = packInfo.versionName;;//移动支付
	    packInfo = packageManager.getPackageInfo(mContext.getPackageName(),0);
	    oldVersions[2] = packInfo.versionName;//收银台
	}
   public class CheckVersionTask implements Runnable {
		InputStream is;
		public void run() {
			try {
				String path = "http://192.168.0.219:8080/pos-ws/api";
//				URL url = new URL(path);
//				HttpURLConnection conn = (HttpURLConnection) url
//						.openConnection();
//				conn.setConnectTimeout(5000);
//				conn.setRequestMethod("GET"); 
//                int responseCode = conn.getResponseCode(); 
//                if (responseCode == 200) { 
//                   // 从服务器获得一个输入流 
//               	is = conn.getInputStream(); 
//              } 
				JSONObject postData = new JSONObject();
	    		postData.put("TxnType", "901");
	    		postData.put("AccessID", "10000001");
	    		postData.put("IMEI", imei);
	    		postData.put("FileName", "app-update/HI98-APP/version-cashier.xml");
	    		postData.put("TxnReqTime", DateUtil.dateToStr(new Date(), "yyyy/MM/dd HH:mm:ss"));
	    		String sign = SignVerifyUtil.sign(postData, "1wcyz5wmr6rq23c353xe");
	    		postData.put("Sign", sign);
	    		
	    		HttpPost httpPost = new HttpPost(path); 
	    		//设定直接post的数据内容
    			HttpEntity entity = new StringEntity(postData.toString(),"UTF-8");
    			httpPost.setEntity(entity);
    			DefaultHttpClient client = null;
	    		try{
	    			client = new DefaultHttpClient();
	    			//设置连接等待超时时间
	    			client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,30*1000);
	    			//设置读取等待超时时间
	    			client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,30*1000);
	                HttpResponse httpResponse = client.execute(httpPost);  
	                HttpEntity httpEntity = httpResponse.getEntity();
	                if (httpEntity != null){
	                	file = new File(Environment.getExternalStorageDirectory().getPath()+"/Download", "version-cashier.xml");
	                	is = httpEntity.getContent();
	                	OutputStream os = new FileOutputStream(file);
	        		    int bytesRead = 0;
	        		    byte[] buffer = new byte[8*1024];
	        		    while ((bytesRead = is.read(buffer, 0, 8*1024)) != -1) {
	        			   os.write(buffer, 0, bytesRead);
	        		    }
	        		    os.close();
	        		    is.close();
//	                	if(httpEntity.isStreaming()){
//	                		is = httpEntity.getContent();
//	                	}else{
//	                		String respContent = EntityUtils.toString(httpEntity,"UTF-8");
//	                		Message msg = new Message();
//							msg.what = HTTP_ERROR;
//							msg.obj = respContent;
//							handler.sendMessage(msg);
//	                	}
	                }else{
	                	Message msg = new Message();
						msg.what = HTTP_ERROR;
						msg.obj = "查询失败";
						handler.sendMessage(msg);
	                }
	            }finally {
	                //关闭连接，释放资源 
	            	if(client != null)
	            		client.getConnectionManager().shutdown();  
	            }
	    		info = UpdataInfoParser.getUpdataInfo(file);
				fileApps = info.getFileapps();
				for (int i = 0; i < fileApps.size(); i++) {
					FileApp fileApp = fileApps.get(i);
					if (!oldVersions[i].equals("") && !oldVersions[i].equals(fileApp.getVersion())) {
						listDif.add(i);
					}
				}
				if (listDif.size()==0) {
					Log.i(TAG, "版本号相同");
					Message msg = new Message();
					msg.what = UPDATA_NONEED;
					handler.sendMessage(msg);
					// LoginMain();
				} else {
					Log.i(TAG, "版本号不相同 ");
					Message msg = new Message();
					msg.what = UPDATA_CLIENT;
					handler.sendMessage(msg);
				}
			} catch (Exception e) {
				Log.i("cheny", e.getLocalizedMessage());
				Message msg = new Message();
				msg.what = GET_UNDATAINFO_ERROR;
				handler.sendMessage(msg);
				e.printStackTrace();
			}
		}
	}
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			
			super.handleMessage(msg);
			switch (msg.what) {
			case UPDATA_NONEED:
				SuccinctProgress.dismiss();
				Toast.makeText(mContext, "不需要更新",
						Toast.LENGTH_SHORT).show();
				break;
			case UPDATA_CLIENT:
				SuccinctProgress.dismiss();
				 //对话框通知用户升级程序   
				showUpdataDialog();
				break;
			case GET_UNDATAINFO_ERROR:
				SuccinctProgress.dismiss();
				//服务器超时   
	            Toast.makeText(mContext, "获取服务器更新信息失败", 1).show(); 
				break;
			case HTTP_ERROR:
				SuccinctProgress.dismiss();
				//通信失败
				String val = (String)msg.obj;
				try {
					if(val.contains("RespCode")){
						JSONObject rspStr = new JSONObject(val);
						String RespCode = rspStr.getString("RespCode");
						String RespDesc = rspStr.getString("RespDesc");
						Toast.makeText(mContext, RespCode+":"+RespDesc, 1).show(); 
					}else{
						Toast.makeText(mContext, val, 1).show(); 
					}
				} catch (Exception e) {
					Toast.makeText(mContext, e.getLocalizedMessage(), 1).show(); 
				}
				break;
			case DOWN_ERROR:
				SuccinctProgress.dismiss();
				//下载apk失败  
	            Toast.makeText(mContext, "下载新版本失败", 1).show(); 
				break;
			case DOWN_ING:
				SuccinctProgress.setMessage("正在下载"+appName+":"+downLoadFileSize+"B");
//				int result = downLoadFileSize * 100 / fileSize;
//				Log.i("cheny", result+"result");
//		        mNotification.contentView.setTextViewText(R.id.content_view_text1, result + "%");
//		        mNotification.contentView.setProgressBar(R.id.content_view_progress, fileSize, downLoadFileSize, false);
//		        mNotificationManager.notify(0, mNotification);  
				break;
			case DOWN_DONE:
				SuccinctProgress.dismiss();
				//下载完成
//				int result1 = downLoadFileSize * 100 / fileSize;
//		        mNotification.contentView.setTextViewText(R.id.content_view_text1, result1 + "%");
//		        mNotification.contentView.setProgressBar(R.id.content_view_progress, fileSize, downLoadFileSize, false);
		        Toast.makeText(mContext, "下载完成,正在启动安装", 1).show(); 
//		        
//		        
//				azIntent = new Intent(Intent.ACTION_VIEW);  
//			    //执行的数据类型  
//				azIntent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");  
//			    pIntent = PendingIntent.getActivity(mContext, 0, azIntent, 0);
//			    mNotification.defaults = Notification.DEFAULT_SOUND;//铃声提醒   
//			    mNotification.contentView.setTextViewText(R.id.content_view_text1, "下载完成,点击安装.");
//			    mNotification.contentIntent=pIntent;
//		        mNotificationManager.notify(0, mNotification);   
//		      //停止服务  
//               mContext.stopService(updateIntent); 
               
               installApk(file);
				break;
			}
		}
	};
	/* 
	 *  
	 * 弹出对话框通知用户更新程序  
	 *  
	 * 弹出对话框的步骤： 
	 *  1.创建alertDialog的builder.   
	 *  2.要给builder设置属性, 对话框的内容,样式,按钮 
	 *  3.通过builder 创建一个对话框 
	 *  4.对话框show()出来   
	 */  
	protected void showUpdataDialog() {
		String message = "";
		for (int i = 0; i < listDif.size(); i++) {
			FileApp fileApp = fileApps.get(listDif.get(i));
			message = message +(i+1)+fileApp.getAppName()+"\n";
		}
		AlertDialog.Builder builer = new Builder(mContext,ProgressDialog.THEME_DEVICE_DEFAULT_LIGHT);
		builer.setTitle("版本升级");
		builer.setMessage(message+"检测到最新版本，请及时更新！");
		 //当点确定按钮时从服务器上下载 新的apk 然后安装   װ
		builer.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				//notificationInit();
				downLoadApk(fileApps.get(listDif.get(0)));
			}
		});
		builer.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				//do sth
			}
		});
		AlertDialog dialog = builer.create();
		dialog.show();
	}
	/* 
	 * 从服务器中下载APK 
	 */  
	protected void downLoadApk(FileApp fileApp) {  
		appCode = fileApp.getAppCode();
		appName = fileApp.getAppName();
	    SuccinctProgress.showSuccinctProgress(mContext, "正在下载"+appName+":"+downLoadFileSize+"B", false, false);
	    new Thread(){  
	        @Override  
	        public void run() {  
	            try {  
	                if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
	                	InputStream is = null;
//	        			URL url = new URL("http://192.168.0.219:8080/xpos-app/apk/"+appCode);
//	    				HttpURLConnection conn = (HttpURLConnection) url
//	    						.openConnection();
//	        			conn.setConnectTimeout(5000);
//	        			//获取到文件的大小 
//	        			pd.setMax(conn.getContentLength());
	        			JSONObject postData = new JSONObject();
	    	    		postData.put("TxnType", "901");
	    	    		postData.put("AccessID", "10000001");
	    	    		postData.put("IMEI", imei);
	    	    		postData.put("FileName", "app-update/HI98-APP/"+appCode);
	    	    		postData.put("TxnReqTime", DateUtil.dateToStr(new Date(), "yyyy/MM/dd HH:mm:ss"));
	    	    		String sign = SignVerifyUtil.sign(postData, "1wcyz5wmr6rq23c353xe");
	    	    		postData.put("Sign", sign);
	    	    		
	    	    		HttpPost httpPost = new HttpPost("http://192.168.0.219:8080/pos-ws/api"); 
	    	    		//设定直接post的数据内容
	        			HttpEntity entity = new StringEntity(postData.toString(),"UTF-8");
	        			httpPost.setEntity(entity);
	        			//httpPost.setHeader("Accept-Encoding", "identity");  
	        			DefaultHttpClient client = null;
	    	    		try{
	    	    			client = new DefaultHttpClient();
	    	    			//设置连接等待超时时间
	    	    			client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,30*1000);
	    	    			//设置读取等待超时时间
	    	    			client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,30*1000);
	    	                HttpResponse httpResponse = client.execute(httpPost);  
	    	                HttpEntity httpEntity = httpResponse.getEntity();
	    	                if (httpEntity != null){
	    	                	Log.i("cheny",httpEntity.isStreaming()==true?"true":"no");
	    	                	if(httpEntity.isStreaming()){
	    	                		int downloadCount = 0;  
	    		        			String fielName = appCode.split("\\.")[0]+new Date().getTime()+".apk";
	    		        			file = new File(Environment.getExternalStorageDirectory().getPath()+"/Download", fielName);
	    	                		is = httpEntity.getContent();
	    	                		//httpEntity.getContentLength();
	    	                		OutputStream os = new FileOutputStream(file);
	    		        		    int bytesRead = 0;
	    		        		    int total = 0;
	    		        		    byte[] buffer = new byte[1024];
	    		        		    while ((bytesRead = is.read(buffer, 0, 1024)) != -1) {
	    		        			   os.write(buffer, 0, bytesRead);
	    		        				total+= bytesRead;
	    		        				//获取当前下载量
	    		        				downLoadFileSize = total;
	    		        				sendMsg(DOWN_ING);
	    		        		    }
	    		        		    os.close();
	    		        		    is.close();
	    	                	}else{
	    	                		String respContent = EntityUtils.toString(httpEntity,"UTF-8");
	    	                		Message msg = new Message();
	    							msg.what = HTTP_ERROR;
	    							msg.obj = respContent;
	    							handler.sendMessage(msg);
	    	                	}
	    	                }else{
	    	                	Message msg = new Message();
	    						msg.what = HTTP_ERROR;
	    						msg.obj = "下载apk失败";
	    						handler.sendMessage(msg);
	    	                }
	    	            }finally {
	    	                //关闭连接，释放资源 
	    	            	if(client != null)
	    	            		client.getConnectionManager().shutdown();  
	    	            }
	        			sendMsg(DOWN_DONE);// 通知下载完成
		                listDif.remove(0);
	        		}
	            } catch (Exception e) {  
	            	sendMsg(DOWN_ERROR);
	                e.printStackTrace();  
	            }  
	        }}.start();  
	}  
	private void sendMsg(int flag) {
		  Message msg = new Message();
		  msg.what = flag;
		  handler.sendMessage(msg);
	}
	//安装apk   
	protected void installApk(File file) {  
	    Intent intent = new Intent();  
	    //执行动作  
	    intent.setAction(Intent.ACTION_VIEW); 
	    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	    //执行的数据类型  
	    intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");  
	    mContext.startActivity(intent);
	    try {
			Thread.sleep(3000);
			if(listDif.size()!=0)
	    		downLoadApk(fileApps.get(listDif.get(0)));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}  
	
//	private void notificationInit(){
//		  //通知栏内显示下载进度条
//		  updateIntent=new Intent(mContext,MainActivity.class);//点击进度条，进入程序
//		  pIntent=PendingIntent.getActivity(mContext, 0, updateIntent, 0);
//
//		  mNotificationManager=(NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
//		  mNotification=new Notification();
//		  mNotification.icon=R.drawable.ic_launcher;
//		  mNotification.tickerText="开始下载";
//		  mNotification.contentView=new RemoteViews(mContext.getPackageName(),R.layout.content_view);//通知栏中进度布局
//		  mNotification.contentIntent=pIntent;
//	 }
   
   /*-----------------------更新模块结束-----------------------------*/
}
