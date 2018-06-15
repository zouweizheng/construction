package com.gprinter.sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.regex.Pattern;

import org.apache.commons.lang.ArrayUtils;
import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.google.zxing.client.android.CaptureActivity;
import com.gprint.common.BottomMenu;
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
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.sample.R;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainMenuActivity extends Activity implements OnClickListener{
//		private GpService mGpService = null;
//		public static final String CONNECT_STATUS = "connect.status";
//		 private PrinterServiceConnection conn = null;
//		private int mPrinterIndex = 0;
//		private int mTotalCopies = 0;

		//涓嬪崟鐣岄潰
		private LinearLayout layout1;
		
			//宸ュ湴鍑哄叆鍗曚笅鍗�
			private Button button11;
		
		//鍗曟嵁鍒楄〃鐣岄潰
		private LinearLayout layout2;
			//宸ュ湴鍑哄叆鍗曞崟鎹垪琛�
			private Button button21;
		
		//涓汉涓績鐣岄潰
		private LinearLayout layout3;
			//閫�鍑虹櫥闄�
			private Button button34;
		
		
		/**搴曢儴鐘舵�佹爮*/
		private RadioGroup radioGroup;
		private RadioButton radio0;
		private RadioButton radio1;
		private RadioButton radio2;
		
		//褰撳墠搴曢儴鐘舵�佹爮閫変腑鎯呭喌
		private int radioChecked = 0;
		
		//下单逻辑
		private GpService mGpService = null;
	  	public static final String CONNECT_STATUS = "connect.status";
	    private static final String DEBUG_TAG = "MainActivity";
	    
	    private PrinterServiceConnection conn = null;
	    private int mPrinterIndex = 0;
	    private int mTotalCopies = 0;
	  
	    private LinearLayout carNo;
		private EditText editText12;   //车牌号码
//		private EditText editText22;   //联系人
//		private EditText editText32;   //电话号码
		private EditText editText42;   //所属公司
		private Spinner spinner5;
		private EditText editText61;
		private Spinner spinner6;
		private EditText editText71;
		private Spinner spinner7;
		private EditText editText81;
		private Spinner spinner8;
//		private EditText editText91;
//		private Spinner spinner9;
		private EditText editText101;
		private Spinner spinner10;
		private EditText editText171;
		private Spinner spinner17;
		
		private LinearLayout beginTime;  //开始时间
		private EditText editText21;
		private EditText editText22;
		private LinearLayout endTime;    //结束时间
		private EditText editText31;
		private EditText editText32;
		
		private EditText editText112;  //数量
		private EditText editText122;  //单价
		private EditText editText132;  //金额
		private ImageView img141;
		private Button button142;
		private ImageView img151;
		private Button button152;
		private List<String> data_list5;
		private List<String> data_list6;
		private List<String> data_list7;
		private List<String> data_list8;
		private List<String> data_list9;
		private List<String> data_list10;
		private List<String> data_list11;
		private List<String> data_list17;
		private ArrayAdapter<String> arr_adapter;
		
		private String taskCreateTime;  //订单创建时间
		
		private Button scanButton;
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
		
		//渲染配置项数据
		private org.json.JSONArray feeType_list;
		private String fee_type_chose_chName;
		private String fee_type_chose_enName;
//		private org.json.JSONArray mechine_type_list;
		private String mechine_type_nextItemName;
		private String mechine_type_chose_chName;
		private String mechine_type_chose_enName;
//		private org.json.JSONArray work_type_list;
		private String work_type_nextItemName;
		private String work_type_chose_chName;
		private String work_type_chose_enName;
		private String second_work_type_nextItemName;
		private String second_work_type_chose_chName;
		private String second_work_type_chose_enName;
		
		 private final static int SCAN_CODE = 3;  
		 private String carId;
		 
		 private BottomMenu menuWindow;
		 
			private Calendar cal; 
		    private int year,month,day; 
		    private Calendar mCalendar;
		    
		    //上传图片到oss
		    private TextView tv,detail;
		    private Button camerabutton,playbutton,selectvideo;
		    private ProgressBar pb;
		    private String path,objectname;
		    private EditText filename;

		
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
		
		//下单逻辑end
		
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
//	        setContentView(R.layout.activity_main);
	        setContentView(R.layout.main_menu);
	        //当前页获取carId
	        Intent intent = getIntent();
	        carId = intent.getStringExtra("carId");
	        
	        initView();
	        
	       
	        //	startService();
	        //下单逻辑
	        connection();
	    }
	    
	    private void initView() {
	    	//下单逻辑
	    	carNo = (LinearLayout) findViewById(R.id.carNo);
	    	editText12 = (EditText) findViewById(R.id.editText12);
//	    	editText22 = (EditText) findViewById(R.id.editText22);
//	    	editText32 = (EditText) findViewById(R.id.editText32);
//	    	editText42 = (EditText) findViewById(R.id.editText42);
	        spinner5 = (Spinner) findViewById(R.id.spinner5);
	        editText61 = (EditText) findViewById(R.id.editText61);
	        spinner6 = (Spinner) findViewById(R.id.spinner6);
	        editText71 = (EditText) findViewById(R.id.editText71);
	        spinner7 = (Spinner) findViewById(R.id.spinner7);
	        editText81 = (EditText) findViewById(R.id.editText81);
	        spinner8 = (Spinner) findViewById(R.id.spinner8);
//	        editText91 = (EditText) findViewById(R.id.editText91);
//	        spinner9 = (Spinner) findViewById(R.id.spinner9);
	        editText101 = (EditText) findViewById(R.id.editText101);
	        spinner10 = (Spinner) findViewById(R.id.spinner10);
	        editText171 = (EditText) findViewById(R.id.editText171);
	        spinner17 = (Spinner) findViewById(R.id.spinner17);
	        
	        beginTime = (LinearLayout) findViewById(R.id.beginTime);
	        editText22 = (EditText) findViewById(R.id.editText22);
	        endTime = (LinearLayout) findViewById(R.id.endTime);
	        editText32 = (EditText) findViewById(R.id.editText32);
	        
	        editText112 = (EditText) findViewById(R.id.editText112);
	        editText112.setText("1");
	    	editText122 = (EditText) findViewById(R.id.editText122);
	    	editText132 = (EditText) findViewById(R.id.editText132);
	    	img141 = (ImageView) findViewById(R.id.image141);
	    	button142 = (Button) findViewById(R.id.button142);
	    	img151 = (ImageView) findViewById(R.id.image151);
	    	button152 = (Button) findViewById(R.id.button152);
	        
	    	scanButton = (Button) findViewById(R.id.button13);
	        saveButton = (Button) findViewById(R.id.button162);
	        
	        //上传图片到oss
	        pb = (ProgressBar) findViewById(R.id.progressBar1);
	        
	        //隐藏下拉框配置项
	        editText71.setVisibility(View.GONE);
	        editText81.setVisibility(View.GONE);
	        editText171.setVisibility(View.GONE);
	        spinner7.setVisibility(View.GONE);
	        spinner8.setVisibility(View.GONE);
	        spinner17.setVisibility(View.GONE);
	        
	        if(carId == null || carId.length()==0) {
	        	editText12.setText("");
	        }else if(carId.length()!=0) {
	        	editText12.setText(carId);
	        }
	        
	        //数据
	        data_list5 = new ArrayList<String>();
	    	//适配器
	        arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list5);
	        //设置样式
	        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

	        
	        //获取目的项目
	        AsyncHttpClient clientDesc = new AsyncHttpClient();
	    	String userAgentDesc = ((MyApplication)getApplication()).getUserAgent();
	    	String tokenDesc = ((MyApplication)getApplication()).getToken();
	    	
	    	clientDesc.addHeader("user-agent", userAgentDesc);
	    	String urlDesc = "http://120.79.146.218:10079/conserver/user/getUserByGroupId?groupId=project&token="+tokenDesc;
	    	clientDesc.get(urlDesc, new JsonHttpResponseHandler() {
	    		@Override
	    		public void onSuccess(int statusCode, Header[] headers, org.json.JSONObject  response) {
					org.json.JSONArray descList;
					try {
						descList = response.getJSONArray("data");
						if(descList.length()>0) {
				    		for(int i=0;i<(descList.length());i++) {
				    			JSONObject childEnum_item;
				    			String childEnum_value;
				    			try {
				    				childEnum_item = descList.getJSONObject(i);
									childEnum_value = childEnum_item.getString("userName");
									data_list5.add(childEnum_value);
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
				    		}
				    	}
				        //加载适配器
				        spinner5.setAdapter(arr_adapter);
//				        spinner9.setAdapter(arr_adapter);
				        spinner10.setAdapter(arr_adapter);

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

//	        data_list5.add("万科A项目");
//	        data_list5.add("万科B项目");
//	        data_list5.add("万科C项目");
	        

	        
	    	//获取配置项
	        AsyncHttpClient client = new AsyncHttpClient();
	    	String userAgent = ((MyApplication)getApplication()).getUserAgent();
	    	String token = ((MyApplication)getApplication()).getToken();
	    	
	    	client.addHeader("user-agent", userAgent);
	    	String url = "http://47.97.207.192:8036/query/findByEnNames?enNames=feeType";
	    	client.get(url, new JsonHttpResponseHandler() {
	    		@Override
	    		public void onSuccess(int statusCode, Header[] headers, org.json.JSONObject  response) {
					org.json.JSONArray childEnum;
					String nextItemId;
					try {
						childEnum = response.getJSONArray("configInfoList");
						feeType_list = childEnum;
						nextItemId = response.getString("nextItemId");
						editText61.setText(response.getString("nextItemName")+":");
						setFeeType(feeType_list,nextItemId);
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

//	        data_list7 = new ArrayList<String>();
//	        data_list7.add("卡车");
//	        data_list7.add("货车");
//	        arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list7);
//	        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//	        spinner7.setAdapter(arr_adapter);
	        
//	        data_list8 = new ArrayList<String>();
//	        data_list8.add("内运");
//	        data_list8.add("外运");
//	        arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list8);
//	        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//	        spinner8.setAdapter(arr_adapter);
	        
	       
	        editText22.setOnClickListener(this);
	        editText32.setOnClickListener(this);
	        scanButton.setOnClickListener(this);
	        saveButton.setOnClickListener(this);
	        button142.setOnClickListener(this);
	        button152.setOnClickListener(this);
	        
	        //根据数量和单价动态计算金额
	        editText112.addTextChangedListener(new TextWatcher() {
	        	@Override
	        	public void onTextChanged(CharSequence s, int start, int before, int count) {
	        		String str1 = editText112.getText().toString();
	        		String str2 = editText122.getText().toString();
	        		String[] unitPriceArr = str2.split(" ");
	        		String  unitPrice = unitPriceArr[0];
	        		if(unitPrice.length()==0) {
	        			unitPrice = "0";
	        		}if(str1.length()==0) {
	        			str1 = "0";
	        		} 

	        		if(str1.equals("-")) {
	        			Toast.makeText(getApplicationContext(),  "请输入大于0的整数",Toast.LENGTH_SHORT).show();
	                	str1="1";
	        		}
	        		if(str1.equals("0")) {
	        			Toast.makeText(getApplicationContext(),  "请输入大于0的整数",Toast.LENGTH_SHORT).show();
	                	str1="1";
	        		}
	        		//判断数量框是否输入整数
	        		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  
	                boolean isInteger =  pattern.matcher(str1).matches();  
	                if(!isInteger) {
	                	Toast.makeText(getApplicationContext(),  "请输入整数",Toast.LENGTH_SHORT).show();
	                	str1="0";
	                }
	                 
	        		int int1 = Integer.parseInt(str1);
	        		int int2 = Integer.parseInt(unitPrice);
	        		int result = int1*int2;
	        		editText132.setText(Integer.toString(result));
	        	}

				@Override
				public void afterTextChanged(Editable arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
					// TODO Auto-generated method stub
					
				}

	        });
	        
	        editText122.addTextChangedListener(new TextWatcher() {
	        	@Override
	        	public void onTextChanged(CharSequence s, int start, int before, int count) {
	        		String str1 = editText112.getText().toString();
	        		String str2 = editText122.getText().toString();
	        		String[] unitPriceArr = str2.split(" ");
	        		String  unitPrice = unitPriceArr[0];
	        		if(str1.length()==0) {
	        			str1 = "0";
	        		}else if(unitPrice.length()==0) {
	        			unitPrice = "0";
	        		}
	        		
	        		if(str1.equals("-")) {
	        			Toast.makeText(getApplicationContext(),  "单价请输入大于0的数",Toast.LENGTH_SHORT).show();
	                	str1="1";
	        		}
	        		if(str1.equals("0")) {
	        			Toast.makeText(getApplicationContext(),  "单价请输入大于0的数",Toast.LENGTH_SHORT).show();
	                	str1="1";
	        		}
	        		int int1 = Integer.parseInt(str1);
	        		int int2 = Integer.parseInt(unitPrice);
	        		int result = int1*int2;
	        		editText132.setText(Integer.toString(result));
	        	}

				@Override
				public void afterTextChanged(Editable arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
					// TODO Auto-generated method stub
					
				}

	        });
	    	//下单逻辑end
	        
	    	layout1 = (LinearLayout) findViewById(R.id.create_order_panel);
	    	layout2 = (LinearLayout) findViewById(R.id.order_list_panel);
	    	layout3 = (LinearLayout) findViewById(R.id.person_center_panel);
	    	
//	    	layout1.setVisibility(View.VISIBLE);
	    	layout2.setVisibility(View.GONE);
	    	layout3.setVisibility(View.GONE);
	    	
	    	//搴曢儴瀵艰埅
			radio0 = (RadioButton) findViewById(R.id.radio0);
			radio1 = (RadioButton) findViewById(R.id.radio1);
			radio2 = (RadioButton) findViewById(R.id.radio2);
	    	
//	    	button11 = (Button) findViewById(R.id.create_order_panel_button1);
	    	button21 = (Button) findViewById(R.id.order_list_panel_button1);
	    	button34 = (Button) findViewById(R.id.person_center_panel_button4);
	    	
	    	radio0.setOnClickListener(this);
	    	radio1.setOnClickListener(this);
	    	radio2.setOnClickListener(this);
	    	
//	    	button11.setOnClickListener(this);
	    	button21.setOnClickListener(this);
	    	button34.setOnClickListener(this);
	    	
	    	String str2 = ((MyApplication)getApplication()).getToken();
	    	Toast.makeText(getApplicationContext(),  "token为:"+str2,Toast.LENGTH_SHORT).show();

	    }
	    
	    //获取下一关联配置项的list
	    private void nextConfigList(String nextUrl,final String nextType) {
	    	//获取配置项
	        AsyncHttpClient client = new AsyncHttpClient();
	    	String userAgent = ((MyApplication)getApplication()).getUserAgent();

	    	client.addHeader("user-agent", userAgent);
	    	String url = nextUrl;
	    	client.get(url, new JsonHttpResponseHandler() {
	    		@Override
	    		public void onSuccess(int statusCode, Header[] headers, org.json.JSONObject  response) {
					org.json.JSONArray nextList;
					String nextItemId;
					try {
						
						//获取车辆类型列表
						if(nextType.equals("mechineType")) {
								nextList = response.getJSONArray("configInfoList");
								nextItemId = response.getString("nextItemId");
								editText71.setVisibility(View.VISIBLE);
								editText71.setText(response.getString("nextItemName")+":");
								mechine_type_nextItemName = response.getString("nextItemName");
								spinner7.setVisibility(View.VISIBLE);
								setMechineType(nextList,nextItemId);						
						}
						//获取工作类型列表
						else if(nextType.equals("workType")) {
							nextList = response.getJSONArray("configInfoList");
							nextItemId = response.getString("nextItemId");
							editText81.setVisibility(View.VISIBLE);
							editText81.setText(response.getString("nextItemName")+":");
							work_type_nextItemName = response.getString("nextItemName");
							spinner8.setVisibility(View.VISIBLE);
							setWorkType(nextList,nextItemId);						
						}
						//获取第二工作类型列表
						else if(nextType.equals("secondWorkType")) {
							nextList = response.getJSONArray("configInfoList");
							nextItemId = response.getString("nextItemId");
							nextList = response.getJSONArray("configInfoList");
							nextItemId = response.getString("nextItemId");
							editText171.setVisibility(View.VISIBLE);
							editText171.setText(response.getString("nextItemName")+":");
							second_work_type_nextItemName = response.getString("nextItemName");
							spinner17.setVisibility(View.VISIBLE);
							setSecondWorkType(nextList,nextItemId);						
						}
						//获取规格单价
						else if(nextType.equals("price")) {
							nextList = response.getJSONArray("configInfoList");
							nextItemId = response.getString("nextItemId");							
							setPrice(nextList,nextItemId);						
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
	    
	    //渲染费用类型数据
	    private void setFeeType(final org.json.JSONArray childEnum_list,final String nextItemId) {
	    	data_list6 = new ArrayList<String>();

	    	if(childEnum_list.length()>0) {
	    		for(int i=0;i<(childEnum_list.length());i++) {
	    			JSONObject childEnum_item;
	    			String childEnum_value;
	    			try {
						childEnum_item = childEnum_list.getJSONObject(i);
						childEnum_value = childEnum_item.getString("chName");
						data_list6.add(childEnum_value);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    		}
	    		
	    		
	    	}
		    arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list6);
		    arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		    spinner6.setAdapter(arr_adapter);
	        spinner6.setOnItemSelectedListener(new OnItemSelectedListener() {  
	        	  
	            @Override  
	            public void onItemSelected(AdapterView<?> parent, View view,  
	                    int position, long id) {  
	                //拿到被选择项的值  
	            	fee_type_chose_chName = (String) spinner6.getSelectedItem();  
	                
	            	for(int i=0;i<(childEnum_list.length());i++) {
		    			JSONObject childEnum_item;
		    			String childEnum_value;
		    			try {
							childEnum_item = childEnum_list.getJSONObject(i);
							childEnum_value = childEnum_item.getString("chName");
							if(childEnum_value.equals(fee_type_chose_chName)) {
								fee_type_chose_enName = childEnum_item.getString("enName");
								if(fee_type_chose_enName.equals("carTranslate")) {
									carNo.setVisibility(View.GONE);
								}else {
									carNo.setVisibility(View.VISIBLE);
								}
								nextConfigList("http://47.97.207.192:8036/query/findByEnNames?enNames=feeType,"+fee_type_chose_enName,"mechineType");
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		    		}
	            	
	            }  
	  
	            @Override  
	            public void onNothingSelected(AdapterView<?> parent) {  
	                // TODO Auto-generated method stub  
	                  
	            }  
	        });  
	    }
	    
	   
	  //渲染车辆类型数据
	    private void setMechineType(final org.json.JSONArray childEnum_list,final String nextItemId) {
	    	data_list7 = new ArrayList<String>();

	    	if(childEnum_list.length()>0) {
	    		for(int i=0;i<(childEnum_list.length());i++) {
	    			JSONObject childEnum_item;
	    			String childEnum_value;
	    			try {
						childEnum_item = childEnum_list.getJSONObject(i);
						childEnum_value = childEnum_item.getString("chName");
						data_list7.add(childEnum_value);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    		}
	    		
	    		
	    	}
		    arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list7);
		    arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		    spinner7.setAdapter(arr_adapter);
	        spinner7.setOnItemSelectedListener(new OnItemSelectedListener() {  
	        	  
	            @Override  
	            public void onItemSelected(AdapterView<?> parent, View view,  
	                    int position, long id) {  
	                //拿到被选择项的值  
	            	mechine_type_chose_chName = (String) spinner7.getSelectedItem();  
	                
	            	for(int i=0;i<(childEnum_list.length());i++) {
		    			JSONObject childEnum_item;
		    			String childEnum_value;
		    			try {
							childEnum_item = childEnum_list.getJSONObject(i);
							childEnum_value = childEnum_item.getString("chName");
							if(childEnum_value.equals(mechine_type_chose_chName)) {
								mechine_type_chose_enName = childEnum_item.getString("enName");
								nextConfigList("http://47.97.207.192:8036/query/findByEnNames?enNames=feeType,"+fee_type_chose_enName+","+mechine_type_chose_enName,"workType");
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		    		}
	            	
	            }  
	  
	            @Override  
	            public void onNothingSelected(AdapterView<?> parent) {  
	                // TODO Auto-generated method stub  
	                  
	            }  
	        });  
	    }
	    
	  //渲染工作类型数据
	    private void setWorkType(final org.json.JSONArray childEnum_list,final String nextItemId) {
	    	data_list8 = new ArrayList<String>();

	    	if(childEnum_list.length()>0) {
	    		for(int i=0;i<(childEnum_list.length());i++) {
	    			JSONObject childEnum_item;
	    			String childEnum_value;
	    			try {
						childEnum_item = childEnum_list.getJSONObject(i);
						childEnum_value = childEnum_item.getString("chName");
						data_list8.add(childEnum_value);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    		}
	    		
	    		
	    	}
		    arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list8);
		    arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		    spinner8.setAdapter(arr_adapter);
	        spinner8.setOnItemSelectedListener(new OnItemSelectedListener() {  
	        	  
	            @Override  
	            public void onItemSelected(AdapterView<?> parent, View view,  
	                    int position, long id) {  
	                //拿到被选择项的值  
	            	work_type_chose_chName = (String) spinner8.getSelectedItem();  
	                
	            	for(int i=0;i<(childEnum_list.length());i++) {
		    			JSONObject childEnum_item;
		    			String childEnum_value;
		    			try {
							childEnum_item = childEnum_list.getJSONObject(i);
							childEnum_value = childEnum_item.getString("chName");
							if(childEnum_value.equals(work_type_chose_chName)) {
								work_type_chose_enName = childEnum_item.getString("enName");
								//没有工作类型
								if(nextItemId.equals("price")) {
									editText81.setVisibility(View.GONE);
								    spinner8.setVisibility(View.GONE);
									editText171.setVisibility(View.GONE);
								    spinner17.setVisibility(View.GONE);
								    work_type_nextItemName = "";
						    		nextConfigList("http://47.97.207.192:8036/query/findByEnNames?enNames=feeType,"+fee_type_chose_enName,"price");
						    	}
								//有工作类型
								else {
									if(!work_type_chose_enName.equals("shift")) {
										editText22.setText("");
										editText32.setText("");
										
										beginTime.setVisibility(View.GONE);
										endTime.setVisibility(View.GONE);
									}else {
										mCalendar = Calendar.getInstance();
								        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
										editText22.setText(format.format(mCalendar.getTime()));
										editText32.setText(format.format(mCalendar.getTime()));
										
								        beginTime.setVisibility(View.VISIBLE);
										endTime.setVisibility(View.VISIBLE);
									}
									nextConfigList("http://47.97.207.192:8036/query/findByEnNames?enNames=feeType,"+fee_type_chose_enName+","+mechine_type_chose_enName+","+work_type_chose_enName,"secondWorkType");	
						    	}
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		    		}
	            	
	            }  
	  
	            @Override  
	            public void onNothingSelected(AdapterView<?> parent) {  
	                // TODO Auto-generated method stub  
	                  
	            }  
	        });  
	    }
	    //渲染第二工作类型数据
	    private void setSecondWorkType(final org.json.JSONArray childEnum_list,final String nextItemId) {
	    	
	    	data_list17 = new ArrayList<String>();

	    	if(childEnum_list.length()>0) {
	    		for(int i=0;i<(childEnum_list.length());i++) {
	    			JSONObject childEnum_item;
	    			String childEnum_value;
	    			try {
						childEnum_item = childEnum_list.getJSONObject(i);
						childEnum_value = childEnum_item.getString("chName");
						data_list17.add(childEnum_value);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    		}
	    		
	    		
	    	}
		    arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list17);
		    arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		    spinner17.setAdapter(arr_adapter);
	        spinner17.setOnItemSelectedListener(new OnItemSelectedListener() {  
	        	  
	            @Override  
	            public void onItemSelected(AdapterView<?> parent, View view,  
	                    int position, long id) {  
	                //拿到被选择项的值  
	            	second_work_type_chose_chName = (String) spinner17.getSelectedItem();  
	                
	            	for(int i=0;i<(childEnum_list.length());i++) {
		    			JSONObject childEnum_item;
		    			String childEnum_value;
		    			try {
							childEnum_item = childEnum_list.getJSONObject(i);
							childEnum_value = childEnum_item.getString("chName");
							if(childEnum_value.equals(second_work_type_chose_chName)) {
								second_work_type_chose_enName = childEnum_item.getString("enName");
								//没有第二工作类型
								if(nextItemId.equals("price")) {
									editText171.setVisibility(View.GONE);
								    spinner17.setVisibility(View.GONE);
								    second_work_type_nextItemName = "";
						    		nextConfigList("http://47.97.207.192:8036/query/findByEnNames?enNames=feeType,"+fee_type_chose_enName+","+mechine_type_chose_enName+","+work_type_chose_enName,"price");
						    	}
								//有第二工作类型
								else {
									nextConfigList("http://47.97.207.192:8036/query/findByEnNames?enNames=feeType,"+fee_type_chose_enName+","+mechine_type_chose_enName+","+work_type_chose_enName+","+second_work_type_chose_enName,"price");	
						    	}
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		    		}
	            	
	            }  
	  
	            @Override  
	            public void onNothingSelected(AdapterView<?> parent) {  
	                // TODO Auto-generated method stub  
	                  
	            }  
	        });  
	    }
	    
	    //渲染规格和单价数据
	    private void setPrice(final org.json.JSONArray childEnum_list,final String nextItemId) {
	    	data_list11 = new ArrayList<String>();
	    	
	    	String unitPrice = null;
	    	String unitWord = null;

	    	if(childEnum_list.length()>0) {
	    		for(int i=0;i<(childEnum_list.length());i++) {
	    			JSONObject childEnum_item;
	    			String childEnum_value;
	    			try {
						childEnum_item = childEnum_list.getJSONObject(i);
						childEnum_value = childEnum_item.getString("chName");
						data_list11.add(childEnum_value);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    		}
	    		
	    		
	    	}
		    
	    	if(data_list11 != null && data_list11.size() >= 0){
	    		unitPrice = data_list11.get(0);
	    		unitWord = data_list11.get(1);
	    		editText122.setText(unitPrice+" "+unitWord);
	    	}
	    	
	    }
	    
	    public void onClick(final View v) {
			if (v.equals(radio0)) {
				if(radioChecked != 0) {
					checkCreateNew();

					layout1.setVisibility(View.VISIBLE);
					layout2.setVisibility(View.GONE);
					layout3.setVisibility(View.GONE);
					radioChecked = 0;
					unCheckOrderList();
					unCheckPersonCenter();
				}
			}
			else if(v.equals(radio1)) {
				if(radioChecked != 1) {
					checkOrderList();
//					layout1.setVisibility(View.GONE);
//					layout2.setVisibility(View.VISIBLE);
//					layout3.setVisibility(View.GONE);
				    
					radioChecked = 1;
					unCheckCreateNew();
					unCheckPersonCenter();
				}
				//弹出底部菜单
				menuWindow = new BottomMenu(MainMenuActivity.this, clickListener,"1");  
			    menuWindow.show(); 
			}
			else if(v.equals(radio2)) {
				if(radioChecked != 2) {
					checkPersonCenter();
//					layout1.setVisibility(View.GONE);
//					layout2.setVisibility(View.GONE);
//					layout3.setVisibility(View.VISIBLE);
					
					radioChecked = 2;
					unCheckCreateNew();
					unCheckOrderList();
				}
				menuWindow = new BottomMenu(MainMenuActivity.this, clickListener,"2");  
			    menuWindow.show(); 
			    
			}
			else if(v.equals(button11)) {
				enterCreateOrderActivity();
			}
			else if(v.equals(button21)) {
//				enterOrderListActivity();
			}
			else if(v.equals(button34)) {
				enterMainActivity();
			}
			//开始时间选择
			else if(v.equals(editText22)) {
				//获取当前日期  
				cal=Calendar.getInstance();  
		        year=cal.get(Calendar.YEAR);       //获取年月日时分秒    
		        Log.i("wxy","year"+year);  
		        month=cal.get(Calendar.MONTH);   //获取到的月份是从0开始计数  
		        day=cal.get(Calendar.DAY_OF_MONTH);  
		        
		        
				 OnDateSetListener listener=new OnDateSetListener() {  
						@Override
						public void onDateSet(DatePicker arg0, int year, int month, int day) {
							// TODO Auto-generated method stub
							editText22.setText(year+"-"+(++month)+"-"+day); 
							
							mCalendar = Calendar.getInstance();
					        TimePickerDialog dialog = new TimePickerDialog(MainMenuActivity.this, new TimePickerDialog.OnTimeSetListener() {
								@Override
								public void onTimeSet(TimePicker arg0, int arg1, int arg2) {
									// TODO Auto-generated method stub
									mCalendar.set(Calendar.HOUR, arg1);
					                mCalendar.set(Calendar.MINUTE, arg2);
					                
					                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
					                try {
										editText22.setText(format.format(format.parse(editText22.getText()+" "+arg1+":"+arg2)));
									} catch (ParseException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
					        }, mCalendar.get(Calendar.HOUR), mCalendar.get(Calendar.MINUTE), true);
					        dialog.show();
						}  
		            };  
		            DatePickerDialog dialog=new DatePickerDialog(MainMenuActivity.this, 0,listener,year,month,day);//后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月  
		            dialog.show();  
			}
			//结束时间选择
			else if(v.equals(editText32)) {
				//获取当前日期  
				cal=Calendar.getInstance();  
		        year=cal.get(Calendar.YEAR);       //获取年月日时分秒    
		        Log.i("wxy","year"+year);  
		        month=cal.get(Calendar.MONTH);   //获取到的月份是从0开始计数  
		        day=cal.get(Calendar.DAY_OF_MONTH);  
		        
		        
				 OnDateSetListener listener=new OnDateSetListener() {  
						@Override
						public void onDateSet(DatePicker arg0, int year, int month, int day) {
							// TODO Auto-generated method stub
							editText32.setText(year+"-"+(++month)+"-"+day); 
							
							mCalendar = Calendar.getInstance();
					        TimePickerDialog dialog = new TimePickerDialog(MainMenuActivity.this, new TimePickerDialog.OnTimeSetListener() {
								@Override
								public void onTimeSet(TimePicker arg0, int arg1, int arg2) {
									// TODO Auto-generated method stub
									mCalendar.set(Calendar.HOUR, arg1);
					                mCalendar.set(Calendar.MINUTE, arg2);
					                
					                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
					                try {
										editText32.setText(format.format(format.parse(editText32.getText()+" "+arg1+":"+arg2)));
									} catch (ParseException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
					        }, mCalendar.get(Calendar.HOUR), mCalendar.get(Calendar.MINUTE), true);
					        dialog.show();
						}  
		            };  
		            DatePickerDialog dialog=new DatePickerDialog(MainMenuActivity.this, 0,listener,year,month,day);//后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月  
		            dialog.show();  
			}
			//下单逻辑
			else if(v.equals(scanButton)) {
				 startActivityForResult(new Intent(this, CaptureActivity.class),SCAN_CODE); 
			}
			else if (v.equals(saveButton)) {
				String stringData = editText12.getText().toString()+";"+spinner5.getSelectedItem().toString()+";"+
						spinner6.getSelectedItem().toString()+";"+spinner7.getSelectedItem().toString()+";"+spinner8.getSelectedItem().toString()+";"+
					spinner10.getSelectedItem().toString()+";"+editText112.getText().toString()+";"
						+editText122.getText().toString()+";"+editText132.getText().toString();
				Toast.makeText(getApplicationContext(), stringData,Toast.LENGTH_SHORT).show(); 
				
				String[] unitPriceArr = editText122.getText().toString().split(" ");
        		String  unitPrice = unitPriceArr[0];
        		String  unitWord = unitPriceArr[1];
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
			    	conOrderObject.put("machineType",(String) spinner7.getSelectedItem());             //车辆类型
			    	conOrderObject.put("workType",(String) spinner8.getSelectedItem());                //工作类型
			    	conOrderObject.put("feeSecondType",(String) spinner17.getSelectedItem());          //第二工作类型
//			    	conOrderObject.put("motorcadeName",(String) spinner9.getSelectedItem());         //来源项目
			    	conOrderObject.put("destinationName",(String) spinner10.getSelectedItem());         //目的项目
			    	if(!editText22.getText().toString().equals("")) {
			    		conOrderObject.put("startTime",editText22.getText().toString());
			    	}
			    	if(!editText32.getText().toString().equals("")) {
			    		conOrderObject.put("endTime",editText32.getText().toString());
			    	}
			    	if(editText112.getText().toString().equals("")) {
			    		conOrderObject.put("num",1);  
			    	}else {
			    		conOrderObject.put("num",Integer.parseInt(editText112.getText().toString()));                        //数量
			    	}
			    	conOrderObject.put("unitPrice",unitPrice);                  					   //单价
			    	conOrderObject.put("unitWord",unitWord);                  					   //规格
			    	conOrderObject.put("money",editText132.getText().toString());                      //金额
			    	conOrderObject.put("firstPic", srcNetPath1);                                        //车头照片
			    	conOrderObject.put("secondlyPic", srcNetPath2);                                    //车尾照片
			    
			    	
			    	//发起人
			    	SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("userinfo",getApplicationContext().MODE_PRIVATE);  
			        String name = sharedPreferences.getString("name","");  
			        String createPersonName = name+"@gd.cmcc";
			        conOrderObject.put("createPersonName", createPersonName); 
			    	
			    	if(srcNetPath1.equals("")||srcNetPath2.equals("")) {
			    		Toast.makeText(getApplicationContext(), "请上传图片，否则无法提交",Toast.LENGTH_SHORT).show();
			    		return;
			    	}
			    	
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
								
								JSONObject taskInfo = data.getJSONObject("taskInfo");
								taskCreateTime = taskInfo.getString("taskCreateTime").toString();
								
								//防止重复提交
								saveButton.setEnabled(false);
								saveButton.setBackgroundColor(Color.parseColor("#cccccc"));
//								Toast.makeText(getApplicationContext(), "不能重复提交！请五秒后再尝试"+orderId,Toast.LENGTH_SHORT).show();
						        Handler handler = new Handler();
						        handler.postDelayed(new Runnable() {
						            @Override
						            public void run() {
						               /**
						                *要执行的操作
						                */
						            	saveButton.setEnabled(true); 
						            	saveButton.setBackgroundColor(Color.parseColor("#03549e"));
						            }
						        }, 5000);
						        
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
			/*else if(v.equals(button142)) {
				//获取oss配置信息
				AsyncHttpClient client = new AsyncHttpClient();
		    	StringEntity stringEntity = null; 
				String url = "http://120.79.146.218:7080/";
    	    	client.post(getBaseContext(),url,stringEntity,"application/json",new JsonHttpResponseHandler() {
		    		@Override
		    		public void onSuccess(int statusCode, Header[] headers, org.json.JSONObject  response) {
		    	       // Handle resulting parsed JSON response here
		    	    	System.out.println(response);
		                String fileName=System.currentTimeMillis()+".jpg";//目录中文件名称
		    	    	String AccessKeyId= null;
		    	    	String AccessKeySecret = null;
		    	    	String SecurityToken= null;
						try {
							AccessKeyId = response.getString("AccessKeyId");
							AccessKeySecret = response.getString("AccessKeySecret");
							SecurityToken = response.getString("SecurityToken");
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		    	    	beginupload(fileName,AccessKeyId,AccessKeySecret,SecurityToken);
		    	    }
		    		@Override
		    		public void onFailure(int statusCode, Header[] headers,Throwable error,org.json.JSONObject responseBody) {
		                Log.d("Error----> ", ""+statusCode+" ------ "+ responseBody);
	
		            }
	    		});
			}*/
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
			//下单逻辑end
		}
	    
	    
	    //监听底部弹出菜单的选择项事件
	    private OnClickListener  clickListener = new OnClickListener(){  
	    	   
	        public void onClick(View v) {  
	            switch (v.getId()) {  
	            case R.id.btn1:  
//	                Toast.makeText(MainMenuActivity.this, "menu1", Toast.LENGTH_SHORT).show();  
	                enterOrderListActivity("groupWait");
	                break;  
	            case R.id.btn2:  
	            	enterOrderListActivity("waitPerson");
	                break;  
	            case R.id.btn3:  
	            	enterOrderListActivity("finishPerson");
	                break;  
	            case R.id.btn21:  
	                Toast.makeText(MainMenuActivity.this, "menu21", Toast.LENGTH_SHORT).show();  
	                break;  
	            case R.id.btn22:  
	                Toast.makeText(MainMenuActivity.this, "menu22", Toast.LENGTH_SHORT).show();  
	                break; 
	            case R.id.btn23:  
	                Toast.makeText(MainMenuActivity.this, "menu23", Toast.LENGTH_SHORT).show();  
	                break;  
	            case R.id.btn24:  
//	                Toast.makeText(MainMenuActivity.this, "menu24", Toast.LENGTH_SHORT).show();  
	            	enterMainActivity();
	            	
	            	//清除存储的密码和用户名
	    	    	SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("userinfo",getApplicationContext().MODE_PRIVATE);  
	    	        Editor editor = sharedPreferences.edit();  
	    	        editor.putString("name","");  
	    	        editor.putString("pass","");  
	    	        editor.commit(); 
	                break; 
	            default:  
	                break;  
	            }  
	            }  
	        };  
	    //搴曢儴瀵艰埅閫変腑
	    private void checkCreateNew() {
	    	Drawable top0 = getResources().getDrawable(R.drawable.bottom_create_new_select);
			radio0.setCompoundDrawablesWithIntrinsicBounds(null, top0 , null, null);
			radio0.setTextColor(Color.parseColor("#55cedc"));  
	    }
	    
	    private void checkOrderList() {
	    	Drawable top1 = getResources().getDrawable(R.drawable.bottom_order_list_select);
			radio1.setCompoundDrawablesWithIntrinsicBounds(null, top1 , null, null);
			radio1.setTextColor(Color.parseColor("#55cedc"));  
	    }
	    
	    private void checkPersonCenter() {
	    	Drawable top2 = getResources().getDrawable(R.drawable.bottom_person_center_select);
			radio2.setCompoundDrawablesWithIntrinsicBounds(null, top2 , null, null);
			radio2.setTextColor(Color.parseColor("#55cedc"));  
	    }
	    
	    //搴曢儴瀵艰埅鍘婚櫎閫変腑
	    private void unCheckCreateNew() {
	    	Drawable top0 = getResources().getDrawable(R.drawable.bottom_create_new_img);
			radio0.setCompoundDrawablesWithIntrinsicBounds(null, top0 , null, null);
			radio0.setTextColor(Color.parseColor("#444444"));  
	    }
	    
	    private void unCheckOrderList() {
	    	Drawable top1 = getResources().getDrawable(R.drawable.bottom_order_list_img);
			radio1.setCompoundDrawablesWithIntrinsicBounds(null, top1 , null, null);
			radio1.setTextColor(Color.parseColor("#444444"));  
	    }
	    
	    private void unCheckPersonCenter() {
	    	Drawable top2 = getResources().getDrawable(R.drawable.bottom_person_center_img);
			radio2.setCompoundDrawablesWithIntrinsicBounds(null, top2 , null, null);
			radio2.setTextColor(Color.parseColor("#444444"));  
	    }
	    
	    
	    
	    private void enterCreateOrderActivity() {
			Intent it = new Intent(MainMenuActivity.this,CreateOrderActivity.class);
			startActivity(it);
		}
	    
	    private void enterOrderListActivity(String listType) {
			Intent it = new Intent(MainMenuActivity.this,OrderListActivity.class);
			it.putExtra("listType", listType);
			startActivity(it);
		}
	    
	    private void enterMainActivity() {
			Intent it = new Intent(MainMenuActivity.this,MainActivity.class);
			startActivity(it);
		}
	    
	    //下单逻辑
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
			
			/*AsyncHttpClient client = new AsyncHttpClient();
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
	    		
	    	});*/
			//获取oss配置信息
			/*AsyncHttpClient client = new AsyncHttpClient();
	    	StringEntity stringEntity = null; 
			String url = "http://120.79.146.218:7080/";
	    	client.post(getBaseContext(),url,stringEntity,"application/json",new JsonHttpResponseHandler() {
	    		@Override
	    		public void onSuccess(int statusCode, Header[] headers, org.json.JSONObject  response) {
	    	       // Handle resulting parsed JSON response here
	    	    	System.out.println(response);
	                String fileName=System.currentTimeMillis()+".jpg";//目录中文件名称
	    	    	String AccessKeyId= null;
	    	    	String AccessKeySecret = null;
	    	    	String SecurityToken= null;
					try {
//						AccessKeyId = response.getString("AccessKeyId");
						AccessKeyId = "LTAIqnxvP0tzhj2Z";
						
//						AccessKeySecret = response.getString("AccessKeySecret");
						AccessKeySecret ="8hK2PU2SXzKgm13k5zaoqkJnTvoUEL";
						SecurityToken = response.getString("SecurityToken");
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    	    	beginupload(fileName,AccessKeyId,AccessKeySecret,SecurityToken,compressImage,picNo);
	    	    }
	    		@Override
	    		public void onFailure(int statusCode, Header[] headers,Throwable error,org.json.JSONObject responseBody) {
	                Log.d("Error----> ", ""+statusCode+" ------ "+ responseBody);

	            }
    		});*/
	    	 String fileName=System.currentTimeMillis()+".jpg";//目录中文件名称
	    	 String AccessKeyId = "LTAIqnxvP0tzhj2Z";
	    	 String AccessKeySecret ="8hK2PU2SXzKgm13k5zaoqkJnTvoUEL";
	    	 String SecurityToken= null;
	    	beginupload(fileName,AccessKeyId,AccessKeySecret,SecurityToken,compressImage,picNo);
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
	        esc.addSelectJustification(JUSTIFICATION.CENTER);//璁剧疆鎵撳嵃灞呬腑
	        esc.addSelectPrintModes(FONT.FONTA, ENABLE.OFF, ENABLE.ON, ENABLE.ON, ENABLE.OFF);//璁剧疆涓哄�嶉珮鍊嶅
	        esc.addText("锋晟建筑单据\n");   //  鎵撳嵃鏂囧瓧
	        esc.addPrintAndLineFeed();
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
//	        esc.addText("Print text\n");   //  鎵撳嵃鏂囧瓧
//	        esc.addText("Welcome to use Gprinter!\n");   //  鎵撳嵃鏂囧瓧
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
	        String secondWorkType = (String) spinner17.getSelectedItem();
//	        String sourceProject = (String) spinner9.getSelectedItem();
	        String destinationName = (String) spinner10.getSelectedItem();
	        String num = editText112.getText().toString();
	        String unitPrice = editText122.getText().toString();
	        String money  = editText132.getText().toString();
	        
	        esc.addText("车牌号码:"+number+"\n");       //  车牌号码
//	        esc.addText("联系人:"+people+"\n");        //  联系人
//	        esc.addText("电话号码:"+phone+"\n");       //  电话号码
//	        esc.addText("所属公司:"+company+"\n");       //  所属公司
	        esc.addText("工程项目:"+project+"\n");   //  工程项目
	        esc.addText("费用类别:"+feeType+"\n");   //  费用类别
	        esc.addText(mechine_type_nextItemName+":"+machineType+"\n");      //  运输类型
	        if(work_type_nextItemName.length()!=0) {
		        esc.addText(work_type_nextItemName+":"+workType+"\n");   //  工作方法
	        }
	        if(second_work_type_nextItemName.length()!=0) {
		        esc.addText(second_work_type_nextItemName+":"+secondWorkType+"\n");   //  第二工作类型
	        }
//	        esc.addText("来源项目:"+sourceProject+"\n");   //  来源项目
	        esc.addText("目的项目:"+destinationName+"\n");   // 目的项目
	        if(!editText22.getText().toString().equals("")) {
	        	esc.addText("开始时间:"+editText22.getText().toString()+"\n");   // 开始时间
	    	}
	    	if(!editText32.getText().toString().equals("")) {
	    		esc.addText("结束时间:"+editText32.getText().toString()+"\n");   // 开始时间
	    	}
	        esc.addText("数量:"+num+"\n");           // 数量
	        esc.addText("单价:"+unitPrice+"\n");          //  单价
	        esc.addText("金额:"+money+"元"+"\n");          //  金额
	        esc.addText("创建日期:"+taskCreateTime+"\n");
	        esc.addPrintAndLineFeed();
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
//	        esc.addText("条形码：\n");   //  鎵撳嵃鏂囧瓧
	        esc.addSelectPrintingPositionForHRICharacters(HRI_POSITION.BELOW);//璁剧疆鏉＄爜鍙瘑鍒瓧绗︿綅缃湪鏉＄爜涓嬫柟
	        esc.addSetBarcodeHeight((byte) 60); //璁剧疆鏉＄爜楂樺害涓�60鐐�
//	        esc.addCODE128(orderId);  //鎵撳嵃Code128鐮�
//	        esc.addPrintAndLineFeed();
	        // 璁� 缃� 鏉� 鐮� 鍗� 鍏� 瀹� 搴� 涓� 1 鐐�
	        esc.addSetBarcodeWidth((byte)1);
//	        // 鎵� 鍗� Code128 鐮�
//	        esc.addCODE128(esc.genCode128("6666"));
//	        esc.addCODE128(esc.genCodeC("gggg"));
//	        esc.addCODE128(esc.genCodeB("gprinter"));
//	        esc.addCODE128(esc.genCodeB("con2018"));
//	        esc.addCODE128(esc.genCodeB("con20180105"));
//	        esc.addCODE128(esc.genCodeB("con2018010508"));         
//	        esc.addCODE128(esc.genCodeB("con20180105085"));        //14
//	        esc.addCODE128(esc.genCodeB("20180105085222"));        //14
//	        esc.addCODE128(esc.genCodeB("con201801050850"));
//	        esc.addCODE128(esc.genCodeB("con2018010508500212"));
	        esc.addCODE128(esc.genCodeB(orderId));
//	        esc.addCODE128(esc.genCodeB("'"+orderId+"'"));
	        esc.addPrintAndLineFeed();

			/*QRCode鍛戒护鎵撳嵃
				姝ゅ懡浠ゅ彧鍦ㄦ敮鎸丵RCode鍛戒护鎵撳嵃鐨勬満鍨嬫墠鑳戒娇鐢ㄣ��
				鍦ㄤ笉鏀寔浜岀淮鐮佹寚浠ゆ墦鍗扮殑鏈哄瀷涓婏紝鍒欓渶瑕佸彂閫佷簩缁存潯鐮佸浘鐗�
			*/
//			esc.addText("二维码：\n");   //  鎵撳嵃鏂囧瓧
	        esc.addSelectJustification(JUSTIFICATION.CENTER);//璁剧疆鎵撳嵃宸﹀榻�
			esc.addSelectErrorCorrectionLevelForQRCode((byte)0x31); //璁剧疆绾犻敊绛夌骇
			esc.addSelectSizeOfModuleForQRCode((byte)8);//璁剧疆qrcode妯″潡澶у皬
			esc.addStoreQRCodeData(orderId);//璁剧疆qrcode鍐呭
			esc.addPrintQRCode();//鎵撳嵃QRCode
			esc.addPrintAndLineFeed();
			
			/*鎵撳嵃鏂囧瓧*/
	        esc.addSelectJustification(JUSTIFICATION.RIGHT);//璁剧疆鎵撳嵃宸﹀榻�
//	        esc.addText("Completed!\r\n");   //  鎵撳嵃缁撴潫
	        esc.addText("签名:                     "+"\n");
	        esc.addPrintAndLineFeed();
	        
	        //获取当前时间
	        Date date = new Date();
	        DateFormat df1 = DateFormat.getDateInstance();//日期格式，精确到日

	        esc.addText("日期:"+df1.format(date)+"        \n");
	        
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
//	        enterMainMenuActivity();
	    }
	    
	    protected void onActivityResult(int requestCode,int resultCode,Intent data){
//	    	if(data == null) {
//	    		return;
//	    	}else {
	    		switch (requestCode){
	    		case 3:
	    			System.out.println("66");
	    			break;
	            case 2:
	            	//ul即为图片文件
	                ul = Uri.fromFile(currentImageFile2);
//	                img151.setImageURI(ul);
	            	img151.setImageURI(Uri.fromFile(currentImageFile2));
   
	                Toast.makeText(MainMenuActivity.this,srcPath2, Toast.LENGTH_SHORT).show();
	                
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
   
	                Toast.makeText(MainMenuActivity.this,srcPath1, Toast.LENGTH_SHORT).show();
	                
	                //上传图片
	                uploadPic("1");
	                break;
	        }
//	    	}
	        
	       
	    }
	    //下单逻辑end
	    
	    //上传图片到oss操作逻辑
	    public void beginupload(String fileName,String AccessKeyId,String AccessKeySecret,String SecurityToken,String picPath,final String picNo){
	    	//通过填写文件名形成objectname,通过这个名字指定上传和下载的文件
	        objectname=fileName;
	        if(objectname==null||objectname.equals("")){
	            Toast.makeText(MainMenuActivity.this, "文件名不能为空", 2000).show();
	            return;
	        }
	        
	      //填写自己的OSS外网域名
	      String endpoint = "http://oss-cn-shenzhen.aliyuncs.com";
	      //填写明文accessKeyId和accessKeySecret，加密官网有
	      OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider(AccessKeyId, AccessKeySecret);
	      OSS oss = new OSSClient(getApplicationContext(), endpoint, credentialProvider);
	      //获取当前时间戳字符串
	      SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd");    
	      final String datetime = tempDate.format(new java.util.Date());
	      
	      //下面3个参数依次为bucket名，Object名，上传文件路径
	      PutObjectRequest put = new PutObjectRequest("gdfsc-construction", datetime+"/"+objectname, picPath);
	      if(picPath==null||picPath.equals("")){
//	          detail.setText("请选择视频!!!!");
	          Toast.makeText(getApplicationContext(), "请选择图片!!!!",Toast.LENGTH_SHORT).show();
	          return;
	      }
//	      tv.setText("正在上传中....");
	      Toast.makeText(getApplicationContext(), "正在上传中....",Toast.LENGTH_SHORT).show();
	      pb.setVisibility(View.VISIBLE);
	      // 异步上传，可以设置进度回调
	      put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
	      public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
	             Log.d("PutObject", "currentSize: " + currentSize + " totalSize: " + totalSize);
	             }
	      });
	        @SuppressWarnings("rawtypes")
	        OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
	            @Override
	            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
	                Log.d("PutObject", "UploadSuccess");
	                //回调为子线程，所以去UI线程更新UI
	                runOnUiThread(new Runnable() {
	                @Override
	                    public void run() {
	                        // TODO Auto-generated method stub
//	                  tv.setText("UploadSuccess");
	                  Toast.makeText(getApplicationContext(), "UploadSuccess",Toast.LENGTH_SHORT).show();
	                  pb.setVisibility(ProgressBar.INVISIBLE);
	                  if(picNo.equals("1")) {
	                	  srcNetPath1 = datetime+"/"+objectname;
	                  }else {
	                	  srcNetPath2 = datetime+"/"+objectname;
	                  }
	                }
	                });
	            }
				@Override
				public void onFailure(PutObjectRequest arg0, ClientException arg1, ServiceException arg2) {
					PutObjectRequest arg00 = arg0;
					PutObjectRequest arg01= arg00;
					ClientException arg11 = arg1;
					
					 ServiceException arg22 = arg2;
					// TODO Auto-generated method stub
					 // 请求异常
	                runOnUiThread(new Runnable() {
	                    @Override
	                        public void run() {
	                            // TODO Auto-generated method stub
	                        pb.setVisibility(ProgressBar.INVISIBLE);
//	                        tv.setText("Uploadfile,localerror");
	                        Toast.makeText(getApplicationContext(), "Uploadfile,localerror",Toast.LENGTH_SHORT).show();
	                    }
	                    });
	                if (arg1 != null) {
	                    // 本地异常如网络异常等
	                	arg1.printStackTrace();
	                }
	                if (arg1 != null) {
	                    // 服务异常
//	                    tv.setText("Uploadfile,servererror");
	                    Log.e("ErrorCode", arg2.getErrorCode());
	                    Log.e("RequestId", arg2.getRequestId());
	                    Log.e("HostId", arg2.getHostId());
	                    Log.e("RawMessage", arg2.getRawMessage());
	                }
				}
	        });
	    }
	    
}
