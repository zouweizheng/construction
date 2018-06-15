package com.gprinter.sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.ArrayUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.net.http.AndroidHttpClient;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.gprint.common.Code;
import com.gprint.common.MyApplication;
import com.gprinter.aidl.GpService;
import com.gprinter.command.EscCommand;
import com.gprinter.command.EscCommand.ENABLE;
import com.gprinter.command.EscCommand.FONT;
import com.gprinter.command.EscCommand.HRI_POSITION;
import com.gprinter.command.EscCommand.JUSTIFICATION;
import com.gprinter.command.GpCom;
import com.gprinter.command.TscCommand;
import com.gprinter.command.TscCommand.BARCODETYPE;
import com.gprinter.command.TscCommand.BITMAP_MODE;
import com.gprinter.command.TscCommand.DIRECTION;
import com.gprinter.command.TscCommand.EEC;
import com.gprinter.command.TscCommand.FONTMUL;
import com.gprinter.command.TscCommand.FONTTYPE;
import com.gprinter.command.TscCommand.MIRROR;
import com.gprinter.command.TscCommand.READABEL;
import com.gprinter.command.TscCommand.ROTATION;
import com.gprinter.io.GpDevice;
import com.gprinter.service.GpPrintService;

import com.loopj.android.http.*;
import com.sample.R;

public class MainActivity extends Activity implements OnClickListener{
    private GpService mGpService = null;
    public static final String CONNECT_STATUS = "connect.status";
    private static final String DEBUG_TAG = "MainActivity";
	protected static final int SHOW_RESPONSE = 0;
	
//	protected static final int USER_LOGIN = 1;
    private PrinterServiceConnection conn = null;
    private int mPrinterIndex = 0;
    private int mTotalCopies = 0;
    
    /** 鐢ㄦ埛鍚� */
	private EditText userName;
	/** 瀵嗙爜 */
	private EditText passWord;
	/** 楠岃瘉鐮� */
	private EditText verifyCode;
	private ImageView iv_showCode;
	private String realCode;
	
	/** 鐧诲綍 */
	private Button btnLogin;
	
	private WebView webview; 
	
	//缓冲条
	private ProgressDialog progressDialog;

	//新版本apk下载url
	private String new_downloadUrl;
//	private ILogin iLogin;

    class PrinterServiceConnection implements ServiceConnection {
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i("ServiceConnection", "onServiceDisconnected() called");
            mGpService = null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mGpService = GpService.Stub.asInterface(service);
        }
    }

    ;

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        Log.e(DEBUG_TAG, "onResume");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        
        //判断当前应用是否在栈底
//        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {  
//            finish();  
//            return;  
//        }  
        
        //判断是否有版本号更新
        newVersion();

        
        setContentView(R.layout.login);
        
        //用于下载新apk
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();   
        StrictMode.setThreadPolicy(policy);
//        
        initView();
        
        Log.e(DEBUG_TAG, "onCreate");
//        startService();
        connection();
        
        
        //登陆验证
        loginCheck();
    }
    
   
    
    //检测版本更新
    private void newVersion() {
    	//获取当前apk版本号
    	Context context = getApplicationContext();
    	PackageManager pm = context.getPackageManager();//context为当前Activity上下文 
    	PackageInfo pi = null;
		try {
			pi = pm.getPackageInfo(context.getPackageName(), 0);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	final String version = pi.versionName;
    	
    	//获取最新版本号
	    AsyncHttpClient client = new AsyncHttpClient();
    	String userAgent = ((MyApplication)getApplication()).getUserAgent();

    	client.addHeader("user-agent", userAgent);
    	client.get("http://47.97.207.192:8036/version/getNewsVersionInfo", new JsonHttpResponseHandler() {
    		@Override
    		public void onSuccess(int statusCode, Header[] headers, org.json.JSONObject  response) {
				String new_version = null ;
				String new_describe = null;
    			try {
    				new_version = response.getString("version");
    				new_downloadUrl = response.getString("url");
    				new_describe = response.getString("describe");
					
					
					if(version.equals(new_version)) {
						return;
					}else {

						 //版本更新弹出框
					    AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
					     	   .setTitle("版本升级")
					     	   .setMessage(new_describe)
					     	   .setNegativeButton("取消", new DialogInterface.OnClickListener() {
					     	       @Override
					     	       public void onClick(DialogInterface dialog, int which) {
					     	       }
					     	   })
					     	   .setPositiveButton("确定", new DialogInterface.OnClickListener() {
					     	       @Override
					     	       public void onClick(DialogInterface dialog, int which) {
					     	       		dialog.dismiss();
//					     	    	    String url = "https://e.gmcc.net/vpn/CitrixReceiver_3.1.apk";
					    	        	getNewApk(new_downloadUrl);
					     	       }
					     	   })
					     	   .create();
					    dialog.show();
					}
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    	    }
    		
			@Override
    		public void onFailure(int statusCode, Header[] headers,Throwable error,org.json.JSONObject responseBody) {
                Log.d("Error----> ", ""+statusCode+" ------ "+ responseBody);
            	Toast.makeText(getApplicationContext(), "调用接口失败",Toast.LENGTH_SHORT).show(); 
            }
    	   
    	});

    }
    
    
    
 
    //下载apk
    private void getNewApk(String downloadUrl) {
    	try {  
    		progressDialog = new ProgressDialog(MainActivity.this);
	        progressDialog.setMessage("正在下载中，请稍候...");  
	        progressDialog.show();  
	        	
            URL url = new URL(downloadUrl);  
            //打开连接  
            URLConnection conn = url.openConnection();  
            //打开输入流  
            InputStream is = conn.getInputStream();  
            //获得长度  
            int contentLength = conn.getContentLength();  
//            Log.e(TAG, "contentLength = " + contentLength);  
            //创建文件夹 MyDownLoad，在存储卡下  
            String dirName = Environment.getExternalStorageDirectory().getAbsolutePath();  
            
            //判断是否有对sd卡操作权限以及是否可以创建文件
//            File sd = Environment.getExternalStorageDirectory();  
//            boolean can_write = sd.canWrite();  
//            File file=new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/text.txt");
//            file.createNewFile();
            
//            File file = new File(dirName);  
//            
//            //不存在创建  
//            if (!file.exists()) {  
//                file.mkdir();  
//            }  
            //获取当前apk版本号
        	Context context = getApplicationContext();
        	PackageManager pm = context.getPackageManager();//context为当前Activity上下文 
        	PackageInfo pi = null;
    		try {
    			pi = pm.getPackageInfo(context.getPackageName(), 0);
    		} catch (NameNotFoundException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	final String version = pi.versionName;
        	
            //下载后的文件名  
            String fileName = dirName + "/锋晟建筑"+version+".apk";  
            File file1 = new File(fileName);  
            if (file1.exists()) {  
                file1.delete();  
            }  
            //创建字节流  
            byte[] bs = new byte[1024];  
            int len;  
            OutputStream os = new FileOutputStream(fileName);  
            //写数据  
            while ((len = is.read(bs)) != -1) {  
                os.write(bs, 0, len);  
            }  
            //完成后关闭流  
//            Log.e(TAG, "download-finish");  
            os.close();  
            is.close();  
            
            //打开下载的apk文件
            Intent intent = new Intent();
            File file = new File(fileName);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//设置标记
            intent.setAction(Intent.ACTION_VIEW);//动作，查看
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");//设置类型
            getApplicationContext().startActivity(intent);
            
            progressDialog.dismiss();
//            Toast.makeText(getApplicationContext(),  "已下载至:"+dirName,Toast.LENGTH_SHORT).show();
        } catch (Exception e) {  
            e.printStackTrace();  
            progressDialog.dismiss();
            Toast.makeText(getApplicationContext(),  "版本更新失败",Toast.LENGTH_SHORT).show();
        }  
    }
    
    //登陆验证
    private void loginCheck(){
		String userName=null;
		String passWord=null;
		String verifyCode=null;
		
			//获取用户名,密码和验证码
			SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("userinfo",getApplicationContext().MODE_PRIVATE);  
	        String name = sharedPreferences.getString("name","");  
	        String pass = sharedPreferences.getString("pass","");  

	        //初始打开的时候
	        if(name.length()==0 || pass.length()==0) {
	        	return;
	        }else {
				autoLogin(name,pass);
	        }
	}
    
    //自动登陆操作
    private void autoLogin(final String userName,final String passWord) {
 
    		progressDialog = ProgressDialog.show(MainActivity.this, "", "自动登陆中，请稍后……");
    		
    		AsyncHttpClient client = new AsyncHttpClient();
    		
    		webview = (WebView) this.findViewById(R.id.id_wv_ua);  
            // 得到WebSettings对象  
            WebSettings settings = webview.getSettings();  
            // 如果访问的页面中有JavaScript，则WebView必须设置支持JavaScript，否则显示空白页面  
            webview.getSettings().setJavaScriptEnabled(true);  
            // 获取到UserAgentString  
            String userAgent = settings.getUserAgentString(); 
            //保存userAgent 
	    	((MyApplication)getApplication()).setUserAgent(userAgent);
	    	
            client.addHeader("user-agent", userAgent);
			String userId = userName+"@gd.cmcc";
			String url = "http://120.79.146.218:10079/conserver/token/encryptDesToken?userId="+userId+"&password="+passWord;
	    	client.get(url, new JsonHttpResponseHandler() {            
				@Override  
			    public void onSuccess(int statusCode, Header[] headers, org.json.JSONObject  response) {  
					System.out.println(response);

					String message = null;
					String data = null;
					try {
						message = response.getString("message");
						data = response.getString("data");
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
	    			
	    			
	    			if(message.equals("SUCCESS")) {
	    				//保存token 
    	    	    	((MyApplication)getApplication()).setToken(data);
    	    	    	progressDialog.dismiss();
    	    	    	Toast.makeText(getApplicationContext(),  "登录成功",Toast.LENGTH_SHORT).show();
    	    	    	enterMainMenuActivity();
 	
	    			}else {
	    				progressDialog.dismiss();
	    				Toast.makeText(getApplicationContext(),  "用户名或密码错误",Toast.LENGTH_SHORT).show();
						return;
	    			}
			    }  
			    @Override  
			    public void onFailure(int statusCode, Header[] headers,Throwable error,org.json.JSONObject responseBody) {
			    	progressDialog.dismiss();
			    	Toast.makeText(getApplicationContext(),  "接口调用失败",Toast.LENGTH_SHORT).show();
			    	Log.d("Error----> ", ""+statusCode+" ------ "+ error);
			    }  
	    	});
    }



	private Handler handler = new Handler() {
    	@Override
    	public void handleMessage(Message msg) {
	    	super.handleMessage(msg);
	    	switch (msg.what) {
		    	case SHOW_RESPONSE:
		    		
		    	  String response = (String) msg.obj;
		//    	  textView_response.setText(response);
		    	  break;
		    	 
		    	default:
		    	  break;
	    	}            
        }
    	 
    };

    private void initView() {
		userName = (EditText) findViewById(R.id.username);
		passWord = (EditText) findViewById(R.id.password);
		verifyCode = (EditText) findViewById(R.id.verifyCode);
		iv_showCode = (ImageView) findViewById(R.id.verify_pic);
		
	
		btnLogin = (Button) findViewById(R.id.confirm_login);
		
		btnLogin.setOnClickListener(this);
		
		iv_showCode.setImageBitmap(Code.getInstance().createBitmap());
		realCode = Code.getInstance().getCode();
		iv_showCode.setOnClickListener(this); 
		
		
    }
    
    public void onClick(View v) {
		if (v.equals(btnLogin)) {
			String userNameTxt = userName.getText().toString();
		    String passWordTxt = passWord.getText().toString();
		    String verifyCodeTxt = verifyCode.getText().toString();
		    
		    final String userNameSave=userNameTxt;
		    final String passWordSave=passWordTxt;
		    
		    if (TextUtils.isEmpty(userNameTxt) || TextUtils.isEmpty(passWordTxt)) {
//				CommonUI.showToast(context, "鐢ㄦ埛鍚嶆垨瀵嗙爜涓嶈兘涓虹┖锛�");
				Toast.makeText(getApplicationContext(), "用户名或密码不能为空",Toast.LENGTH_SHORT).show(); 
				return;
			}     
		    if(!TextUtils.isEmpty(verifyCodeTxt)) {
		    	if(verifyCodeTxt.equals(realCode)) {
		    		progressDialog = ProgressDialog.show(MainActivity.this, "", "登陆中，请稍后……");
		    		
		    		AsyncHttpClient client = new AsyncHttpClient();
		    		
		    		webview = (WebView) this.findViewById(R.id.id_wv_ua);  
		            // 得到WebSettings对象  
		            WebSettings settings = webview.getSettings();  
		            // 如果访问的页面中有JavaScript，则WebView必须设置支持JavaScript，否则显示空白页面  
		            webview.getSettings().setJavaScriptEnabled(true);  
		            // 获取到UserAgentString  
		            String userAgent = settings.getUserAgentString(); 
		            //保存userAgent 
	    	    	((MyApplication)getApplication()).setUserAgent(userAgent);
	    	    	
		            client.addHeader("user-agent", userAgent);
					String userId = userNameTxt+"@gd.cmcc";
					String url = "http://120.79.146.218:10079/conserver/token/encryptDesToken?userId="+userId+"&password="+passWordTxt;
//					String url = "http://localhost:10079/conserver/token/encryptDesToken?userId="+userId+"&password="+passWordTxt;
			    	client.get(url, new JsonHttpResponseHandler() {            
//			    		@Override
//	    	    		public void onSuccess(int statusCode, Header[] headers,  byte[]  response) {
//	    	    	       // Handle resulting parsed JSON response here
//	    	    			String result=new String(response);
//	    	    			String newResult1 = result.substring(1, result.length());
//	    	    			String newResult2 = newResult1.substring(0, newResult1.length()-1);
//	    	    			
//	    	    			if(result.length() !=0 ) {
//	    	    				//保存token 
//		    	    	    	((MyApplication)getApplication()).setToken(newResult2);
//		    	    	    	Toast.makeText(getApplicationContext(),  "登录成功",Toast.LENGTH_SHORT).show();
//		    	    	    	enterMainMenuActivity();
//	    	    			}else {
//	    	    				Toast.makeText(getApplicationContext(),  "用户名或密码错误",Toast.LENGTH_SHORT).show();
//	    						return;
//	    	    			}
//	    	    	    }
//
//
//						@Override
//	    	    		public void onFailure(int statusCode, Header[] headers,  byte[] responseBody, Throwable error) {
//	    	                Log.d("Error----> ", ""+statusCode+" ------ "+ responseBody);
//	    	                String s2=new String(responseBody);
////	    	            	Toast.makeText(getApplicationContext(), errorResponse,Toast.LENGTH_SHORT).show(); 
//	    	            }


						@Override  
					    public void onSuccess(int statusCode, Header[] headers, org.json.JSONObject  response) {  
							System.out.println(response);

							String message = null;
							String data = null;
							try {
								message = response.getString("message");
								data = response.getString("data");
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
	    	    			
	    	    			
	    	    			if(message.equals("SUCCESS")) {
//	    	    				String newResult1 = result.substring(1, result.length());
//		    	    			String newResult2 = newResult1.substring(0, newResult1.length()-1);
	    	    				//保存token 
		    	    	    	((MyApplication)getApplication()).setToken(data);
		    	    	    	progressDialog.dismiss();
		    	    	    	Toast.makeText(getApplicationContext(),  "登录成功",Toast.LENGTH_SHORT).show();
		    	    	    	enterMainMenuActivity();
		    	    	    
		    	    	    	
		    	    	    	//存储密码和用户名
		    	    	    	SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("userinfo",getApplicationContext().MODE_PRIVATE);  
		    	    	        Editor editor = sharedPreferences.edit();  
		    	    	        editor.putString("name",userNameSave);  
		    	    	        editor.putString("pass",passWordSave);  
		    	    	        editor.commit(); 
	    	    			}else {
	    	    				progressDialog.dismiss();
	    	    				Toast.makeText(getApplicationContext(),  "用户名或密码错误",Toast.LENGTH_SHORT).show();
	    						return;
	    	    			}
					    }  
					    @Override  
					    public void onFailure(int statusCode, Header[] headers,Throwable error,org.json.JSONObject responseBody) {
					    	progressDialog.dismiss();
					    	Toast.makeText(getApplicationContext(),  "接口调用失败",Toast.LENGTH_SHORT).show();
					    	Log.d("Error----> ", ""+statusCode+" ------ "+ error);
					    }  
			    	});
		    	}else {
		    		progressDialog.dismiss();
		    		Toast.makeText(getApplicationContext(), "验证码错误",Toast.LENGTH_SHORT).show(); 
		    		iv_showCode.setImageBitmap(Code.getInstance().createBitmap());
					realCode = Code.getInstance().getCode();
					return;
		    	}
				
			}
		    else {
		    	Toast.makeText(getApplicationContext(), "验证码不能为空",Toast.LENGTH_SHORT).show(); 
				return;
		    }
		}
		else if(v.equals(iv_showCode)) {
			iv_showCode.setImageBitmap(Code.getInstance().createBitmap());
			realCode = Code.getInstance().getCode();
		}
	}

//	private void enterMainMenuActivity() {
//		Intent intent=new Intent(MainActivity.this, MainMenuActivity.class);
//		startActivity(intent);
//	}
//	
	private void enterMainMenuActivity() {
		Intent it = new Intent(MainActivity.this,MainMenuActivity.class);
		startActivity(it);
	}


    
  
    private void connection() {
        conn = new PrinterServiceConnection();
//        Intent intent = new Intent("com.gprinter.aidl.GpPrintService");
        Intent intent = new Intent(this, GpPrintService.class);
        bindService(intent, conn, Context.BIND_AUTO_CREATE); // bindService
    }

    public boolean[] getConnectState() {
        boolean[] state = new boolean[GpPrintService.MAX_PRINTER_CNT];
        for (int i = 0; i < GpPrintService.MAX_PRINTER_CNT; i++) {
            state[i] = false;
        }
        for (int i = 0; i < GpPrintService.MAX_PRINTER_CNT; i++) {
            try {
                if (mGpService.getPrinterConnectStatus(i) == GpDevice.STATE_CONNECTED) {
                    state[i] = true;
                }
            } catch (RemoteException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return state;
    }

    public void openPortDialogueClicked(View view) {
        Log.d(DEBUG_TAG, "openPortConfigurationDialog ");
        Intent intent = new Intent(this,
                PrinterConnectDialog.class);
        boolean[] state = getConnectState();
        intent.putExtra(CONNECT_STATUS, state);
        this.startActivity(intent);
    }

    public void printTestPageClicked(View view) {
        try {
            int rel = mGpService.printeTestPage(mPrinterIndex); //
            Log.i("ServiceConnection", "rel " + rel);
            GpCom.ERROR_CODE r = GpCom.ERROR_CODE.values()[rel];
            if (r != GpCom.ERROR_CODE.SUCCESS) {
                Toast.makeText(getApplicationContext(), GpCom.getErrorText(r),
                        Toast.LENGTH_SHORT).show();
            }
        } catch (RemoteException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public void getPrinterStatusClicked(View view) {
        try {
            mTotalCopies = 0;
            int status = mGpService.queryPrinterStatus(mPrinterIndex, 500);
            String str = new String();
            if (status == GpCom.STATE_NO_ERR) {
                str = "鎵撳嵃鏈烘甯�";
            } else {
                str = "鎵撳嵃鏈� ";
                if ((byte) (status & GpCom.STATE_OFFLINE) > 0) {
                    str += "鑴辨満";
                }
                if ((byte) (status & GpCom.STATE_PAPER_ERR) > 0) {
                    str += "缂虹焊";
                }
                if ((byte) (status & GpCom.STATE_COVER_OPEN) > 0) {
                    str += "鎵撳嵃鏈哄紑鐩�";
                }
                if ((byte) (status & GpCom.STATE_ERR_OCCURS) > 0) {
                    str += "鎵撳嵃鏈哄嚭閿�";
                }
                if ((byte) (status & GpCom.STATE_TIMES_OUT) > 0) {
                    str += "鏌ヨ瓒呮椂";
                }
            }
            Toast.makeText(getApplicationContext(),
                    "鎵撳嵃鏈猴細" + mPrinterIndex + " 鐘舵�侊細" + str, Toast.LENGTH_SHORT).show();
        } catch (RemoteException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public void getPrinterCommandTypeClicked(View view) {
        try {
            int type = mGpService.getPrinterCommandType(mPrinterIndex);
            if (type == GpCom.ESC_COMMAND) {
                Toast.makeText(getApplicationContext(), "鎵撳嵃鏈轰娇鐢‥SC鍛戒护",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "鎵撳嵃鏈轰娇鐢═SC鍛戒护",
                        Toast.LENGTH_SHORT).show();
            }
        } catch (RemoteException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    void sendReceipt() {
        EscCommand esc = new EscCommand();
        esc.addPrintAndFeedLines((byte) 3);
        esc.addSelectCodePage(EscCommand.CODEPAGE.UYGUR);
        esc.addCancelKanjiMode();
        esc.addSelectJustification(JUSTIFICATION.CENTER);//璁剧疆鎵撳嵃灞呬腑
        esc.addSelectPrintModes(FONT.FONTA, ENABLE.OFF, ENABLE.ON, ENABLE.ON, ENABLE.OFF);//璁剧疆涓哄�嶉珮鍊嶅
        esc.addText("Sample\n");   //  鎵撳嵃鏂囧瓧
        esc.addPrintAndLineFeed();
        List<Byte> by = new ArrayList<Byte>();
//        List<Byte> by = new ArrayList<Byte>();

        for (byte i = (byte) 0x80; i <= (byte) 0xff; i++) {
            by.add(i);
        }

        byte[] bs = new byte[by.size()];
        int i = 0;
        for (byte b : by) {
            bs[i++] = b;
        }
        String str = Base64.encodeToString(bs, Base64.DEFAULT);
        int rel;
        try {
            rel = mGpService.sendEscCommand(mPrinterIndex, str);
            GpCom.ERROR_CODE r = GpCom.ERROR_CODE.values()[rel];
            if (r != GpCom.ERROR_CODE.SUCCESS) {
                Toast.makeText(getApplicationContext(), GpCom.getErrorText(r),
                        Toast.LENGTH_SHORT).show();
            }
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        /*鎵撳嵃鏂囧瓧*/
        esc.addSelectPrintModes(FONT.FONTA, ENABLE.OFF, ENABLE.OFF, ENABLE.OFF, ENABLE.OFF);//鍙栨秷鍊嶉珮鍊嶅
        esc.addSelectJustification(JUSTIFICATION.LEFT);//璁剧疆鎵撳嵃宸﹀榻�
        esc.addText("Print text\n");   //  鎵撳嵃鏂囧瓧
        esc.addText("Welcome to use Gprinter!\n");   //  鎵撳嵃鏂囧瓧
        esc.addText("浣犲ソ鍚�", "utf-8");   //  鎵撳嵃鏂囧瓧

		/*鎵撳嵃绻佷綋涓枃  闇�瑕佹墦鍗版満鏀寔绻佷綋瀛楀簱*/
        String message = Util.SimToTra("浣冲崥绁ㄦ嵁鎵撳嵃鏈篭n");
        //	esc.addText(message,"BIG5");
        esc.addText(message, "GB2312");
        esc.addPrintAndLineFeed();

		/*鎵撳嵃鍥剧墖*/
        esc.addText("Print bitmap!\n");   //  鎵撳嵃鏂囧瓧
        Bitmap b = BitmapFactory.decodeResource(getResources(),
                R.drawable.gprinter);
        esc.addRastBitImage(b, b.getWidth(), 0);   //鎵撳嵃鍥剧墖

		/*鎵撳嵃涓�缁存潯鐮�*/
        esc.addText("Print code128\n");   //  鎵撳嵃鏂囧瓧
        esc.addSelectPrintingPositionForHRICharacters(HRI_POSITION.BELOW);//璁剧疆鏉＄爜鍙瘑鍒瓧绗︿綅缃湪鏉＄爜涓嬫柟
        esc.addSetBarcodeHeight((byte) 60); //璁剧疆鏉＄爜楂樺害涓�60鐐�
//        esc.addCODE128("Gprinter");  //鎵撳嵃Code128鐮�
//        esc.addPrintAndLineFeed();
        // 璁� 缃� 鏉� 鐮� 鍗� 鍏� 瀹� 搴� 涓� 1 鐐�
        esc.addSetBarcodeWidth((byte)1);
        // 鎵� 鍗� Code128 鐮�
        esc.addCODE128(esc.genCode128("123456789"));
//        esc.addCODE128(esc.genCodeC("youare666"));
        esc.addCODE128(esc.genCodeB("Gprinter"));
        esc.addPrintAndLineFeed();

		/*QRCode鍛戒护鎵撳嵃
			姝ゅ懡浠ゅ彧鍦ㄦ敮鎸丵RCode鍛戒护鎵撳嵃鐨勬満鍨嬫墠鑳戒娇鐢ㄣ��
			鍦ㄤ笉鏀寔浜岀淮鐮佹寚浠ゆ墦鍗扮殑鏈哄瀷涓婏紝鍒欓渶瑕佸彂閫佷簩缁存潯鐮佸浘鐗�
		*/
		esc.addText("Print QRcode\n");   //  鎵撳嵃鏂囧瓧
		esc.addSelectErrorCorrectionLevelForQRCode((byte)0x31); //璁剧疆绾犻敊绛夌骇
		esc.addSelectSizeOfModuleForQRCode((byte)6);//璁剧疆qrcode妯″潡澶у皬
		esc.addStoreQRCodeData("www.gprinter.com.cn");//璁剧疆qrcode鍐呭
		esc.addPrintQRCode();//鎵撳嵃QRCode
		esc.addPrintAndLineFeed();
		
		/*鎵撳嵃鏂囧瓧*/
        esc.addSelectJustification(JUSTIFICATION.CENTER);//璁剧疆鎵撳嵃宸﹀榻�
        esc.addText("Completed!\r\n");   //  鎵撳嵃缁撴潫
        esc.addPrintAndFeedLines((byte) 8);

        Vector<Byte> datas = esc.getCommand(); //鍙戦�佹暟鎹�
        Byte[] Bytes = datas.toArray(new Byte[datas.size()]);
        byte[] bytes = ArrayUtils.toPrimitive(Bytes);
        String sss = Base64.encodeToString(bytes, Base64.DEFAULT);
        int rs;
        try {
            rs = mGpService.sendEscCommand(mPrinterIndex, sss);
            GpCom.ERROR_CODE r = GpCom.ERROR_CODE.values()[rs];
            if (r != GpCom.ERROR_CODE.SUCCESS) {
                Toast.makeText(getApplicationContext(), GpCom.getErrorText(r),
                        Toast.LENGTH_SHORT).show();
            }
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    void sendReceipt3() {
        EscCommand esc = new EscCommand();
        esc.addText("1234567890\n");   //  鎵撳嵃鏂囧瓧
        Vector<Byte> datas = esc.getCommand(); //鍙戦�佹暟鎹�
        Byte[] Bytes = datas.toArray(new Byte[datas.size()]);
        byte[] bytes = ArrayUtils.toPrimitive(Bytes);
        String str = Base64.encodeToString(bytes, Base64.DEFAULT);
        int rel;
        try {
            rel = mGpService.sendEscCommand(mPrinterIndex, str);
            GpCom.ERROR_CODE r = GpCom.ERROR_CODE.values()[rel];
            if (r != GpCom.ERROR_CODE.SUCCESS) {
                Toast.makeText(getApplicationContext(), GpCom.getErrorText(r),
                        Toast.LENGTH_SHORT).show();
            }
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    void sendReceipt(int i) {
        EscCommand esc = new EscCommand();
        esc.addPrintAndFeedLines((byte) 3);
        esc.addSelectJustification(JUSTIFICATION.CENTER);//璁剧疆鎵撳嵃灞呬腑
        esc.addSelectPrintModes(FONT.FONTA, ENABLE.OFF, ENABLE.ON, ENABLE.ON, ENABLE.OFF);//璁剧疆涓哄�嶉珮鍊嶅
        esc.addText("Sample\n");   //  鎵撳嵃鏂囧瓧
        esc.addPrintAndLineFeed();

		/*鎵撳嵃鏂囧瓧*/
        esc.addSelectPrintModes(FONT.FONTA, ENABLE.OFF, ENABLE.OFF, ENABLE.OFF, ENABLE.OFF);//鍙栨秷鍊嶉珮鍊嶅
        esc.addSelectJustification(JUSTIFICATION.LEFT);//璁剧疆鎵撳嵃宸﹀榻�
        esc.addText("Print text\n");   //  鎵撳嵃鏂囧瓧
        esc.addText("Welcome to use Gprinter!\n");   //  鎵撳嵃鏂囧瓧
		
		/*鎵撳嵃绻佷綋涓枃  闇�瑕佹墦鍗版満鏀寔绻佷綋瀛楀簱*/
        String message = Util.SimToTra("浣冲崥绁ㄦ嵁鎵撳嵃鏈篭n");
        //	esc.addText(message,"BIG5");
        esc.addText(message, "GB2312");
        esc.addPrintAndLineFeed();
		
		/*鎵撳嵃鍥剧墖*/
        esc.addText("Print bitmap!\n");   //  鎵撳嵃鏂囧瓧
        Bitmap b = BitmapFactory.decodeResource(getResources(),
                R.drawable.gprinter);
        esc.addRastBitImage(b, b.getWidth(), 0);   //鎵撳嵃鍥剧墖
	
		/*鎵撳嵃涓�缁存潯鐮�*/
        esc.addText("Print code128\n");   //  鎵撳嵃鏂囧瓧
        esc.addSelectPrintingPositionForHRICharacters(HRI_POSITION.BELOW);//璁剧疆鏉＄爜鍙瘑鍒瓧绗︿綅缃湪鏉＄爜涓嬫柟
        esc.addSetBarcodeHeight((byte) 60); //璁剧疆鏉＄爜楂樺害涓�60鐐�
        esc.addCODE128("Gprinter");  //鎵撳嵃Code128鐮�
        esc.addPrintAndLineFeed();
	
		/*QRCode鍛戒护鎵撳嵃
			姝ゅ懡浠ゅ彧鍦ㄦ敮鎸丵RCode鍛戒护鎵撳嵃鐨勬満鍨嬫墠鑳戒娇鐢ㄣ��
			鍦ㄤ笉鏀寔浜岀淮鐮佹寚浠ゆ墦鍗扮殑鏈哄瀷涓婏紝鍒欓渶瑕佸彂閫佷簩缁存潯鐮佸浘鐗�
		*/
        esc.addText("Print QRcode\n");   //  鎵撳嵃鏂囧瓧
        esc.addSelectErrorCorrectionLevelForQRCode((byte) 0x31); //璁剧疆绾犻敊绛夌骇
        esc.addSelectSizeOfModuleForQRCode((byte) 3);//璁剧疆qrcode妯″潡澶у皬
        esc.addStoreQRCodeData("www.gprinter.com.cn");//璁剧疆qrcode鍐呭
        esc.addPrintQRCode();//鎵撳嵃QRCode
        esc.addPrintAndLineFeed();

        esc.addText("绗� " + i + " 浠絓n");   //  鎵撳嵃鏂囧瓧
		/*鎵撳嵃鏂囧瓧*/
        esc.addSelectJustification(JUSTIFICATION.CENTER);//璁剧疆鎵撳嵃宸﹀榻�
        esc.addText("Completed!\r\n");   //  鎵撳嵃缁撴潫
        esc.addPrintAndFeedLines((byte) 8);

        Vector<Byte> datas = esc.getCommand(); //鍙戦�佹暟鎹�
        Byte[] Bytes = datas.toArray(new Byte[datas.size()]);
        byte[] bytes = ArrayUtils.toPrimitive(Bytes);
        String str = Base64.encodeToString(bytes, Base64.DEFAULT);
        int rel;
        try {
            rel = mGpService.sendEscCommand(mPrinterIndex, str);
            GpCom.ERROR_CODE r = GpCom.ERROR_CODE.values()[rel];
            if (r != GpCom.ERROR_CODE.SUCCESS) {
                Toast.makeText(getApplicationContext(), GpCom.getErrorText(r),
                        Toast.LENGTH_SHORT).show();
            }
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    void sendReceiptBmp(int i) {
        EscCommand esc = new EscCommand();
		/*鎵撳嵃鍥剧墖*/
        esc.addText("Print bitmap!\n");   //  鎵撳嵃鏂囧瓧
        Bitmap b = BitmapFactory.decodeResource(getResources(),
                R.drawable.test);
        esc.addRastBitImage(b, 384, 0);   //鎵撳嵃鍥剧墖
        esc.addText("绗� " + i + " 浠絓n");   //  鎵撳嵃鏂囧瓧

        Vector<Byte> datas = esc.getCommand(); //鍙戦�佹暟鎹�
        Byte[] Bytes = datas.toArray(new Byte[datas.size()]);
        byte[] bytes = ArrayUtils.toPrimitive(Bytes);
        String str = Base64.encodeToString(bytes, Base64.DEFAULT);
        int rel;
        try {
            rel = mGpService.sendEscCommand(mPrinterIndex, str);
            GpCom.ERROR_CODE r = GpCom.ERROR_CODE.values()[rel];
            if (r != GpCom.ERROR_CODE.SUCCESS) {
                Toast.makeText(getApplicationContext(), GpCom.getErrorText(r),
                        Toast.LENGTH_SHORT).show();
            }
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    void sendLabel() {
        TscCommand tsc = new TscCommand();
        tsc.addSize(60, 60); //璁剧疆鏍囩灏哄锛屾寜鐓у疄闄呭昂瀵歌缃�
        tsc.addGap(0);           //璁剧疆鏍囩闂撮殭锛屾寜鐓у疄闄呭昂瀵歌缃紝濡傛灉涓烘棤闂撮殭绾稿垯璁剧疆涓�0
        tsc.addDirection(DIRECTION.BACKWARD, MIRROR.NORMAL);//璁剧疆鎵撳嵃鏂瑰悜
        tsc.addReference(0, 0);//璁剧疆鍘熺偣鍧愭爣
        tsc.addTear(ENABLE.ON); //鎾曠焊妯″紡寮�鍚�
        tsc.addCls();// 娓呴櫎鎵撳嵃缂撳啿鍖�
        //缁樺埗绠�浣撲腑鏂�
        tsc.addText(20, 20, FONTTYPE.SIMPLIFIED_CHINESE, ROTATION.ROTATION_0, FONTMUL.MUL_1, FONTMUL.MUL_1, "Welcome to use Gprinter!");
        //缁樺埗鍥剧墖
        Bitmap b = BitmapFactory.decodeResource(getResources(),
                R.drawable.gprinter);
        tsc.addBitmap(20, 50, BITMAP_MODE.OVERWRITE, b.getWidth() * 2, b);

        tsc.addQRCode(250, 80, EEC.LEVEL_L, 5, ROTATION.ROTATION_0, " www.gprinter.com.cn");
        //缁樺埗涓�缁存潯鐮�
        tsc.add1DBarcode(20, 250, BARCODETYPE.CODE128, 100, READABEL.EANBEL, ROTATION.ROTATION_0, "Gprinter");
        tsc.addPrint(1, 1); // 鎵撳嵃鏍囩
        tsc.addSound(2, 100); //鎵撳嵃鏍囩鍚� 铚傞福鍣ㄥ搷
        Vector<Byte> datas = tsc.getCommand(); //鍙戦�佹暟鎹�
        Byte[] Bytes = datas.toArray(new Byte[datas.size()]);
        byte[] bytes = ArrayUtils.toPrimitive(Bytes);
        String str = Base64.encodeToString(bytes, Base64.DEFAULT);
        int rel;
        try {
            rel = mGpService.sendTscCommand(mPrinterIndex, str);
            GpCom.ERROR_CODE r = GpCom.ERROR_CODE.values()[rel];
            if (r != GpCom.ERROR_CODE.SUCCESS) {
                Toast.makeText(getApplicationContext(), GpCom.getErrorText(r),
                        Toast.LENGTH_SHORT).show();
            }
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void printReceiptClicked(View view) {
        try {
            int type = mGpService.getPrinterCommandType(mPrinterIndex);
            if (type == GpCom.ESC_COMMAND) {
                int status = mGpService.queryPrinterStatus(mPrinterIndex, 500);
                if (status == GpCom.STATE_NO_ERR) {
                    sendReceipt();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "鎵撳嵃鏈洪敊璇紒", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (RemoteException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public void printLabelClicked(View view) {
        try {
            int type = mGpService.getPrinterCommandType(mPrinterIndex);
            if (type == GpCom.TSC_COMMAND) {
                int status = mGpService.queryPrinterStatus(mPrinterIndex, 500);
                if (status == GpCom.STATE_NO_ERR) {
                    sendLabel();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "鎵撳嵃鏈洪敊璇紒", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (RemoteException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public void printTestClicked(View view) {
        try {
            int type = mGpService.getPrinterCommandType(mPrinterIndex);
            if (type == GpCom.ESC_COMMAND) {
                EditText etCopies = (EditText) findViewById(R.id.etPrintCopies);
                String str = etCopies.getText().toString();
                int copies = 0;
                if (!str.equals("")) {
                    copies = Integer.parseInt(str);
                }
                mTotalCopies += copies;
//                for (int i = 0; i < copies; i++) {
//                    sendReceipt();
//                }
                sendReceipt();
            }
        } catch (RemoteException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        Log.e(DEBUG_TAG, "onDestroy");
        super.onDestroy();
        if (conn != null) {
            unbindService(conn); // unBindService
        }
    }

}
