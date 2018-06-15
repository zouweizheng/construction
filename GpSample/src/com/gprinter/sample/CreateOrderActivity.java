package com.gprinter.sample;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.ArrayUtils;
import org.apache.http.Header;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import com.gprint.common.GetNetPicture;
import com.gprint.common.MyApplication;
import com.gprint.common.PictureUtil;
import com.gprinter.aidl.GpService;
import com.gprinter.command.EscCommand;
import com.gprinter.command.GpCom;
import com.gprinter.command.EscCommand.ENABLE;
import com.gprinter.command.EscCommand.FONT;
import com.gprinter.command.EscCommand.HRI_POSITION;
import com.gprinter.command.EscCommand.JUSTIFICATION;
import com.gprinter.io.GpDevice;
import com.gprinter.sample.MainActivity.PrinterServiceConnection;
import com.gprinter.service.GpPrintService;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.sample.R;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class CreateOrderActivity extends Activity implements OnClickListener{
    	private GpService mGpService = null;
	  	public static final String CONNECT_STATUS = "connect.status";
	    private static final String DEBUG_TAG = "MainActivity";
	    
	    private PrinterServiceConnection conn = null;
	    private int mPrinterIndex = 0;
	    private int mTotalCopies = 0;
	  
	  
		private EditText editText12;   //车牌号码
		private EditText editText22;   //联系人
		private EditText editText32;   //电话号码
		private EditText editText42;   //所属公司
		private Spinner spinner5;
		private Spinner spinner6;
		private Spinner spinner7;
		private Spinner spinner8;
		private Spinner spinner9;
		private Spinner spinner10;
		private EditText editText112;  //数量
		private EditText editText122;  //单价
		private EditText editText132;  //金额
		private ImageView img141;
		private Button button142;
		private ImageView img151;
		private Button button152;
		private List<String> data_list;
		private List<String> data_list5;
		private List<String> data_list6;
		private List<String> data_list7;
		private List<String> data_list8;
		private List<String> data_list9;
		private List<String> data_list10;
		private ArrayAdapter<String> arr_adapter;
		
		private Button saveButton;
		private Button connectButton;
		private Button printButton;

		private File currentImageFile1 = null;
		private File currentImageFile2 = null;
		String srcPath1="";
		String srcPath2="";
		String srcNetPath1 = "";
		String srcNetPath2 = "";
		Uri ul;
		
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


		
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
//	        setContentView(R.layout.activity_main);
	        setContentView(R.layout.create_order);
	        initView();
	        connection();
	    }
	    
	    private void initView() {
	    	editText12 = (EditText) findViewById(R.id.editText12);
//	    	editText22 = (EditText) findViewById(R.id.editText22);
//	    	editText32 = (EditText) findViewById(R.id.editText32);
//	    	editText42 = (EditText) findViewById(R.id.editText42);
	        spinner5 = (Spinner) findViewById(R.id.spinner5);
	        spinner6 = (Spinner) findViewById(R.id.spinner6);
	        spinner7 = (Spinner) findViewById(R.id.spinner7);
	        spinner8 = (Spinner) findViewById(R.id.spinner8);
//	        spinner9 = (Spinner) findViewById(R.id.spinner9);
	        spinner10 = (Spinner) findViewById(R.id.spinner10);
	        editText112 = (EditText) findViewById(R.id.editText112);
	    	editText122 = (EditText) findViewById(R.id.editText122);
	    	editText132 = (EditText) findViewById(R.id.editText132);
	    	img141 = (ImageView) findViewById(R.id.image141);
	    	button142 = (Button) findViewById(R.id.button142);
	    	img151 = (ImageView) findViewById(R.id.image151);
	    	button152 = (Button) findViewById(R.id.button152);
	        
	        saveButton = (Button) findViewById(R.id.button162);
//	        connectButton = (Button) findViewById(R.id.button172);
//	        printButton = (Button) findViewById(R.id.button182);
	    
	        //数据
	        data_list5 = new ArrayList<String>();
	        data_list5.add("万科A项目");
	        data_list5.add("万科B项目");
	        data_list5.add("万科C项目");
	        
	        //适配器
	        arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list5);
	        //设置样式
	        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	        //加载适配器
	        spinner5.setAdapter(arr_adapter);
	        
	    	//获取配置项
	        data_list =  (List<String>) getConfigItem();
	       
	        
	        //数据
	        data_list6 = new ArrayList<String>();
	        data_list6.add("机械费");
	        data_list6.add("设备费");
	        arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list6);
	        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	        spinner6.setAdapter(arr_adapter);
	        spinner6.setOnItemSelectedListener(new OnItemSelectedListener() {  
				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
					// TODO Auto-generated method stub
					String feeType = (String) spinner6.getSelectedItem();  
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}  
	        });  
	        

	        data_list7 = new ArrayList<String>();
	        data_list7.add("卡车");
	        data_list7.add("货车");
	        arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list7);
	        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	        spinner7.setAdapter(arr_adapter);
	        
	        data_list8 = new ArrayList<String>();
	        data_list8.add("内运");
	        data_list8.add("外运");
	        arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list8);
	        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	        spinner8.setAdapter(arr_adapter);
	        
	        arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list5);
	        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	        spinner9.setAdapter(arr_adapter);
	        
	        arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list5);
	        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	        spinner10.setAdapter(arr_adapter);
			
	        saveButton.setOnClickListener(this);
	        button142.setOnClickListener(this);
	        button152.setOnClickListener(this);
//	        connectButton.setOnClickListener(this);
//	        printButton.setOnClickListener(this);

	    }
	    
	    private JSONObject getConfigItem() {
	    	//获取费用类型
	        AsyncHttpClient client = new AsyncHttpClient();
	    	String userAgent = ((MyApplication)getApplication()).getUserAgent();

	    	client.addHeader("user-agent", userAgent);
	    	String url = "http://120.79.146.218:8093/getConfigItem/construction1/myfee?userId=yangying";
	    	client.get(url, new JsonHttpResponseHandler() {
	    		@Override
	    		public void onSuccess(int statusCode, Header[] headers, org.json.JSONObject  response) {
//	    			super.onSuccess(statusCode,headers, response);
	    			String str = response.toString();
	    			
	    			//转成字符串
//					String jobStr = response.toString();
					//转成com.alibaba.fastjson.JSONObject格式的Object
//					JSONObject myJsonObject = JSONObject.parseObject(jobStr);
	    			
					JSONObject body;
					JSONObject configItem;
					try {
						body = response.getJSONObject("body");
						configItem = body.getJSONObject("configItem");
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
			JSONObject configItem = null;
			return configItem;
	    }
	    
	    public void onClick(final View v) {
			if (v.equals(saveButton)) {
				String stringData = editText12.getText().toString()+";"+spinner5.getSelectedItem().toString()+";"+
						spinner6.getSelectedItem().toString()+";"+spinner7.getSelectedItem().toString()+";"+spinner8.getSelectedItem().toString()+";"+
						spinner9.getSelectedItem().toString()+";"+spinner10.getSelectedItem().toString()+";"+editText112.getText().toString()+";"
						+editText122.getText().toString()+";"+editText132.getText().toString();
				Toast.makeText(getApplicationContext(), stringData,Toast.LENGTH_SHORT).show(); 
				
//				getimage(srcPath2);
				
				
				//提交工单数据
				AsyncHttpClient client = new AsyncHttpClient();
				RequestParams params = new RequestParams();
				String userAgent = ((MyApplication)getApplication()).getUserAgent();
		    	String token = ((MyApplication)getApplication()).getToken();
		    	client.addHeader("user-agent", userAgent);
		    	client.addHeader("ContentType", "application/json")	; 
		    	
		    	JSONObject submitObject = new JSONObject();
		    	JSONObject conOrderObject = new JSONObject();
		    	
		    	StringEntity stringEntity = null; 
		    	 
		    	try {
					conOrderObject.put("motorcadeId",editText12.getText().toString());                 //车牌号码
			    	conOrderObject.put("projectName",(String) spinner5.getSelectedItem());             //工程项目
			    	conOrderObject.put("feeType",(String) spinner6.getSelectedItem());                 //费用类别
			    	conOrderObject.put("machineType",(String) spinner7.getSelectedItem());             //运输类型
			    	conOrderObject.put("workType",(String) spinner8.getSelectedItem());                //工作方法
			    	conOrderObject.put("destinationName",(String) spinner9.getSelectedItem());         //目的项目
			    	conOrderObject.put("num",editText112.getText().toString());                        //数量
			    	conOrderObject.put("unitPrice",editText122.getText().toString());                  //单价
			    	conOrderObject.put("money",editText132.getText().toString());                      //金额
			    	conOrderObject.put("firstPic", srcNetPath1);                                        //车头照片
			    	conOrderObject.put("secondlyPic", srcNetPath2);                                    //车尾照片
			    	submitObject.put("conOrder", conOrderObject);

					try {
						stringEntity = new StringEntity(submitObject.toString(),"utf-8");
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
		    	} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}                   

		    	String url = "http://120.79.146.218:10079/conserver/con/insertOrder?token="+token;
    	    	client.post(getBaseContext(),url,stringEntity,"application/json",new JsonHttpResponseHandler() {
	    		@Override
	    		public void onSuccess(int statusCode, Header[] headers, org.json.JSONObject  response) {
	    	       // Handle resulting parsed JSON response here
	    	    	System.out.println(response);

						try {
							JSONObject data = response.getJSONObject("body");
							String orderId = data.getString("orderId");
							if(data != null) {
								Toast.makeText(getApplicationContext(), "提交成功，订单号为："+orderId,Toast.LENGTH_SHORT).show();
								
								//提交后打印
								printTestClicked(v,orderId);
								
//								//回到主菜单
//								 new Thread() {
//							            @Override
//							            public void run() {
//							                super.run();
//							                try {
//												Thread.sleep(3000);
//											} catch (InterruptedException e) {
//												// TODO Auto-generated catch block
//												e.printStackTrace();
//											}//休眠3秒
//							                enterMainMenuActivity();
//							            }
//							    }.start();
							}else {
								Toast.makeText(getApplicationContext(), "提交失败",Toast.LENGTH_SHORT).show();
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}   	    	
	    	    }
	    		@Override
	    		public void onFailure(int statusCode, Header[] headers,Throwable error,org.json.JSONObject responseBody) {
	                Log.d("Error----> ", ""+statusCode+" ------ "+ responseBody);
//	            	Toast.makeText(getApplicationContext(), errorResponse,Toast.LENGTH_SHORT).show(); 
	            }
	    		
	    	});
			
			}
			else if(v.equals(button142)) {
//				Intent it_loc=new Intent();
//                it_loc.setType("image/*");
////                it_loc.setAction(Intent.ACTION_GET_CONTENT);
//                it_loc.setAction(Intent.ACTION_PICK);
//                startActivityForResult(it_loc,2);
				
				//拍照按钮的监听方法
                String dirName="pictures";//目录中文件夹名称
                File dir = new File(Environment.getExternalStorageDirectory(),dirName);
                if(dir.exists()){
                    dir.mkdirs();
                }
                String fileName=System.currentTimeMillis()+".jpg";//目录中文件名称
                currentImageFile1 = new File(dir,fileName);
                //获取拍照保存的照片的路径
                srcPath1=Environment.getExternalStorageDirectory().toString()+"/"+dirName+"/"+fileName;//文件的路径，根据根目录，文件夹名称，文件名称拼接而成
//                String file_str = Environment.getExternalStorageDirectory().getPath();
//                File file_go = new File(file_str + "/my_camera/file.jpg");
                //设置Intent拍照动作
                Intent it_cam = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //设置存储方式外部存储，设置存储路径
                it_cam.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(currentImageFile1));//涉及到存储的时候一定要注意权限的添加
                startActivityForResult(it_cam,1);
			}
			else if(v.equals(button152)) {
				//拍照按钮的监听方法
                String dirName="pictures";//目录中文件夹名称
                File dir = new File(Environment.getExternalStorageDirectory(),dirName);
                if(dir.exists()){
                    dir.mkdirs();
                }
                String fileName=System.currentTimeMillis()+".jpg";//目录中文件名称
                currentImageFile2 = new File(dir,fileName);
                //获取拍照保存的照片的路径
                srcPath2=Environment.getExternalStorageDirectory().toString()+"/"+dirName+"/"+fileName;//文件的路径，根据根目录，文件夹名称，文件名称拼接而成
//                String file_str = Environment.getExternalStorageDirectory().getPath();
//                File file_go = new File(file_str + "/my_camera/file.jpg");
                //设置Intent拍照动作
                Intent it_cam = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //设置存储方式外部存储，设置存储路径
                it_cam.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(currentImageFile2));//涉及到存储的时候一定要注意权限的添加
                startActivityForResult(it_cam,2);
               
			}
//			else if(v.equals(connectButton)) {
//				//连接打印机
//				openPortDialogueClicked(v);
//			}else if(v.equals(printButton)) {
//				//打印
//				printTestClicked(v);
//			}
		}
	    
	    private void uploadPic(final String picNo) {
	    	String srcPath = null;
	    	if(picNo.equals("1")) {
	    		srcPath = srcPath1;
	    	}else if(picNo.equals("2")) {
	    		srcPath = srcPath2;
	    	}
	    	
	    	 //上传图片
            File file = new File(srcPath);
			long len = file.length();
			
			final String pic_path = file.getPath();
			//调用压缩图片的方法，返回压缩后的图片path
			final String compressImage = PictureUtil.compressImage(pic_path, pic_path, 30);
			final File compressedPic = new File(compressImage);
			long len2 = compressedPic.length();
			
			AsyncHttpClient client = new AsyncHttpClient();
			RequestParams params = new RequestParams();
			client.addHeader("ContentType", "multipart/form-data")	; 
		
			try {
				params.put("file", compressedPic);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	    	client.post("http://www.yxbone.com:10023/fileupload/upload/uploadPicture", params,new JsonHttpResponseHandler() {
	    		@Override
	    		public void onSuccess(int statusCode, Header[] headers,  org.json.JSONObject  response) {
	    	       // Handle resulting parsed JSON response here
	    			System.out.println(response);
	    			try {
						String body = response.getString("body");
						if(picNo.equals("1")) {
							srcNetPath1 = body;
				    	}else if(picNo.equals("2")) {
				    		srcNetPath2 = body;
				    	}
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    	    	
//	    	    	Toast.makeText(getApplicationContext(),  response,Toast.LENGTH_SHORT).show();
	    	    }
	    		@Override
	    		public void onFailure(int statusCode, Header[] headers, Throwable error,org.json.JSONObject responseBody) {
	                Log.d("Error----> ", ""+statusCode+" ------ "+ responseBody);
//	            	Toast.makeText(getApplicationContext(), errorResponse,Toast.LENGTH_SHORT).show(); 
	            }
	    		
	    	});
	    }
	    //连接打印机
	    private void connection() {
	        conn = new PrinterServiceConnection();
//	        Intent intent = new Intent("com.gprinter.aidl.GpPrintService");
	        Intent intent = new Intent(this, GpPrintService.class);
	        bindService(intent, conn, Context.BIND_AUTO_CREATE); // bindService
	    }
	    
	    public void openPortDialogueClicked(View view) {
	        Log.d(DEBUG_TAG, "openPortConfigurationDialog ");
	        Intent intent = new Intent(this,
	                PrinterConnectDialog.class);
	        boolean[] state = getConnectState();
	        intent.putExtra(CONNECT_STATUS, state);
	        this.startActivity(intent);
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
	    
	    //打印
	    public void printTestClicked(View view,String orderId) {
	    	//获取打印机连接状态
	    	boolean[] state = getConnectState();
	    	if(!state[0] && !state[1] && !state[2]) {
	    		//连接打印机
				openPortDialogueClicked(view);
	    	}else {
	    		sendReceipt(orderId);
	    	}
//	        try {
//	            int type = mGpService.getPrinterCommandType(mPrinterIndex);
//	            if (type == GpCom.ESC_COMMAND) {
//	                EditText etCopies = (EditText) findViewById(R.id.etPrintCopies);
//	                String str = etCopies.getText().toString();
//	                int copies = 0;
//	                if (!str.equals("")) {
//	                    copies = Integer.parseInt(str);
//	                }
//	                mTotalCopies += copies;
////	                for (int i = 0; i < copies; i++) {
////	                    sendReceipt();
////	                }
//	                sendReceipt();
//	            }
//	        } catch (RemoteException e1) {
//	            // TODO Auto-generated catch block
//	            e1.printStackTrace();
//	        }
	    }
	    
	    void sendReceipt(String orderId) {
	        EscCommand esc = new EscCommand();
//	        esc.addPrintAndFeedLines((byte) 3);
//	        esc.addSelectCodePage(EscCommand.CODEPAGE.UYGUR);
//	        esc.addCancelKanjiMode();
//	        esc.addSelectJustification(JUSTIFICATION.CENTER);//璁剧疆鎵撳嵃灞呬腑
//	        esc.addSelectPrintModes(FONT.FONTA, ENABLE.OFF, ENABLE.ON, ENABLE.ON, ENABLE.OFF);//璁剧疆涓哄�嶉珮鍊嶅
	        esc.addText("Sample\n");   //  鎵撳嵃鏂囧瓧
//	        esc.addPrintAndLineFeed();
//	        List<Byte> by = new ArrayList<Byte>();
////	        List<Byte> by = new ArrayList<Byte>();
//
//	        for (byte i = (byte) 0x80; i <= (byte) 0xff; i++) {
//	            by.add(i);
//	        }
//
//	        byte[] bs = new byte[by.size()];
//	        int i = 0;
//	        for (byte b : by) {
//	            bs[i++] = b;
//	        }
//	        String str = Base64.encodeToString(bs, Base64.DEFAULT);
//	        int rel;
//	        try {
//	            rel = mGpService.sendEscCommand(mPrinterIndex, str);
//	            GpCom.ERROR_CODE r = GpCom.ERROR_CODE.values()[rel];
//	            if (r != GpCom.ERROR_CODE.SUCCESS) {
//	                Toast.makeText(getApplicationContext(), GpCom.getErrorText(r),
//	                        Toast.LENGTH_SHORT).show();
//	            }
//	        } catch (RemoteException e) {
//	            // TODO Auto-generated catch block
//	            e.printStackTrace();
//	        }

	        /*鎵撳嵃鏂囧瓧*/
	        esc.addSelectPrintModes(FONT.FONTA, ENABLE.OFF, ENABLE.OFF, ENABLE.OFF, ENABLE.OFF);//鍙栨秷鍊嶉珮鍊嶅
	        esc.addSelectJustification(JUSTIFICATION.LEFT);//璁剧疆鎵撳嵃宸﹀榻�
	        esc.addText("Print text\n");   //  鎵撳嵃鏂囧瓧
	        esc.addText("Welcome to use Gprinter!\n");   //  鎵撳嵃鏂囧瓧
//	        esc.addText("这是中文\n", "utf8");   //  鎵撳嵃鏂囧瓧
//	        esc.addText("这是中文\n", "utf-8");   //  鎵撳嵃鏂囧瓧
	        String number =editText12.getText().toString();
//	        String people = editText22.getText().toString();
//	        String phone = editText32.getText().toString();
//	        String company =editText42.getText().toString();
	        String project = (String) spinner5.getSelectedItem();
	        String feeType = (String) spinner6.getSelectedItem();
	        String machineType = (String) spinner7.getSelectedItem();
	        String workType = (String) spinner8.getSelectedItem();
	        String sourceProject = (String) spinner8.getSelectedItem();
	        String destinationName = (String) spinner9.getSelectedItem();
	        String num = editText112.getText().toString();
	        String unitPrice = editText122.getText().toString();
	        String money  = editText132.getText().toString();
	        
	        esc.addText("车牌号码:"+number+"\n");       //  车牌号码
//	        esc.addText("联系人:"+people+"\n");        //  联系人
//	        esc.addText("电话号码:"+phone+"\n");       //  电话号码
//	        esc.addText("所属公司:"+company+"\n");       //  所属公司
	        esc.addText("工程项目:"+project+"\n");   //  工程项目
	        esc.addText("费用类别:"+feeType+"\n");   //  费用类别
	        esc.addText("类型:"+machineType+"\n");      //  运输类型
	        esc.addText("工作方法:"+workType+"\n");   //  工作方法
	        esc.addText("来源项目:"+sourceProject+"\n");   //  来源项目
	        esc.addText("目的项目:"+destinationName+"\n");   // 目的项目
	        esc.addText("数量:"+num+"\n");           // 数量
	        esc.addText("单价:"+unitPrice+"\n");          //  单价
	        esc.addText("金额:"+money+"\n");          //  金额

//			/*鎵撳嵃绻佷綋涓枃  闇�瑕佹墦鍗版満鏀寔绻佷綋瀛楀簱*/
//	        String message = Util.SimToTra("浣冲崥绁ㄦ嵁鎵撳嵃鏈篭n");
//	        //	esc.addText(message,"BIG5");
//	        esc.addText(message, "GB2312");
//	        esc.addPrintAndLineFeed();
//
//			/*鎵撳嵃鍥剧墖*/
//	        esc.addText("Print bitmap!\n");   //  鎵撳嵃鏂囧瓧
//	        Bitmap b = BitmapFactory.decodeResource(getResources(),
//	                R.drawable.gprinter);
//	        esc.addRastBitImage(b, b.getWidth(), 0);   //鎵撳嵃鍥剧墖

			/*鎵撳嵃涓�缁存潯鐮�*/
	        esc.addText("Print code128\n");   //  鎵撳嵃鏂囧瓧
	        esc.addSelectPrintingPositionForHRICharacters(HRI_POSITION.BELOW);//璁剧疆鏉＄爜鍙瘑鍒瓧绗︿綅缃湪鏉＄爜涓嬫柟
	        esc.addSetBarcodeHeight((byte) 60); //璁剧疆鏉＄爜楂樺害涓�60鐐�
//	        esc.addCODE128("Gprinter");  //鎵撳嵃Code128鐮�
//	        esc.addCODE128(orderId);  //鎵撳嵃Code128鐮�
//	        esc.addPrintAndLineFeed();
//	        // 璁� 缃� 鏉� 鐮� 鍗� 鍏� 瀹� 搴� 涓� 1 鐐�
	        esc.addSetBarcodeWidth((byte)1);
//	        // 鎵� 鍗� Code128 鐮�
//	        esc.addCODE128(esc.genCode128("123456789"));
//	        esc.addCODE128(esc.genCodeC("youare666"));
//	        esc.addCODE128(esc.genCodeB("Gprinter"));
	        esc.addCODE128(esc.genCodeB(orderId));
	        esc.addPrintAndLineFeed();

			/*QRCode鍛戒护鎵撳嵃
				姝ゅ懡浠ゅ彧鍦ㄦ敮鎸丵RCode鍛戒护鎵撳嵃鐨勬満鍨嬫墠鑳戒娇鐢ㄣ��
				鍦ㄤ笉鏀寔浜岀淮鐮佹寚浠ゆ墦鍗扮殑鏈哄瀷涓婏紝鍒欓渶瑕佸彂閫佷簩缁存潯鐮佸浘鐗�
			*/
			esc.addText("Print QRcode\n");   //  鎵撳嵃鏂囧瓧
			esc.addSelectErrorCorrectionLevelForQRCode((byte)0x31); //璁剧疆绾犻敊绛夌骇
			esc.addSelectSizeOfModuleForQRCode((byte)6);//璁剧疆qrcode妯″潡澶у皬
			esc.addStoreQRCodeData(orderId);//璁剧疆qrcode鍐呭
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
	        
//	        //打印完成后返回菜单页
	        enterMainMenuActivity();
	    }
	    
	    protected void onActivityResult(int requestCode,int resultCode,Intent data){
//	    	if(data == null) {
//	    		return;
//	    	}else {
	    		switch (requestCode){
	            case 2:
	            	//ul即为图片文件
	                ul = Uri.fromFile(currentImageFile2);
//	                img151.setImageURI(ul);
	            	img151.setImageURI(Uri.fromFile(currentImageFile2));
   
	                Toast.makeText(CreateOrderActivity.this,srcPath2, Toast.LENGTH_SHORT).show();
	                
	                //上传图片
	                uploadPic("2");
	                break;
	            case 1:
//	                //根据android.net.Uri获取图片路径的方法
//	                Uri uri1 = data.getData();
//	                img141.setImageURI(uri1);
//	                ContentResolver cr1 = this.getContentResolver();
//	                Cursor c1 = cr1.query(uri1, null, null, null, null);
//	                c1.moveToFirst();
//	                //这是获取的图片保存在sdcard中的位置
////	                srcPath = c.getString(c.getColumnIndex("_data"));//获取文件的保存路径，这里和拍照的文件格式一样，统一了路径
//	                int colunm_index1 = c1.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//	                srcPath1 = c1.getString(colunm_index1);
//	  
////	                File file = new File(srcPath);
////	                if(file.exists()){
////	                	Bitmap bm = BitmapFactory.decodeFile(srcPath);
////	                	img151.setImageBitmap(bm);
////	                }
//	                //最后不管是本地的还是拍照的，都取得了图片的路径，可以根据图片的路径来进行上传图片
//	    	        //此处验证并显示取得的图片的路径									
//	    	        Toast.makeText(CreateOrderActivity.this,srcPath1, Toast.LENGTH_SHORT).show();
//	                break;
	            	//ul即为图片文件
	                ul = Uri.fromFile(currentImageFile1);
//	                img151.setImageURI(ul);
	            	img141.setImageURI(Uri.fromFile(currentImageFile1));
   
	                Toast.makeText(CreateOrderActivity.this,srcPath1, Toast.LENGTH_SHORT).show();
	                
	                //上传图片
	                uploadPic("1");
	                break;
	        }
//	    	}
	        
	       
	    }

	 
	    
	    
	    private void enterMainMenuActivity() {
			Intent it = new Intent(CreateOrderActivity.this,MainMenuActivity.class);
			startActivity(it);
		}
	    
}
