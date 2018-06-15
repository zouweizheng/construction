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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.ArrayUtils;
import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.json.JSONException;

import com.alibaba.fastjson.JSONObject;
import com.gprint.common.BottomMenu;
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

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class OrderDetailActivity extends Activity implements OnClickListener{
   		private GpService mGpService = null;
	  	public static final String CONNECT_STATUS = "connect.status";
	    private static final String DEBUG_TAG = "MainActivity";
	    
	    private PrinterServiceConnection conn = null;
	    private int mPrinterIndex = 0;
	    private int mTotalCopies = 0;
	
		/**底部菜单栏*/
		private RadioGroup radioGroup;
		private RadioButton radio0;
		private RadioButton radio1;
		private RadioButton radio2;
		
		//褰撳墠搴曢儴鐘舵�佹爮閫変腑鎯呭喌
		private int radioChecked = 0;
		
		private BottomMenu menuWindow;
		
		
		private String orderId;   //订单ID
		private String taskId;    //taskID
		private EditText editText11;
		private EditText editText12;   //车牌号码
//		private EditText editText22;   //联系人
//		private EditText editText32;   //电话号码
		private EditText editText42;   //所属公司
		private EditText editText52;   //工程项目
		private EditText editText61;   
		private EditText editText62;   //费用类别
		private EditText editText71;
		private EditText editText72;   //车辆类型
		private EditText editText81;
		private EditText editText82;   //工作类型
		private EditText editText171;
		private EditText editText172;   //第二工作类型
//		private EditText editText92;   //来源项目
		private EditText editText102;   //目的项目
		
		private LinearLayout beginTime; //开始时间
		private EditText editText22;
		private LinearLayout endTime; //结束时间
		private EditText editText32;
		
		private EditText editText112;  //数量
		private EditText editText122;  //单价
		private EditText editText132;  //金额
		private String taskCreateTime;//创建日期
		private int printNum;     //重复打印次数
		private ImageView img142;
		private ImageView img152;
		
		private Button claimButton;
		private Button agreeButton;
		private Button printButton;
		
		
		//当前选中的配置项
		private String feeType;
		private String mechineType;
		private String workType;
		private String secondWorkType;
		private String fee_type_chose=null;
		private String mechine_type_chose=null;
		private String work_type_chose=null;
		private String second_work_type_chose=null;
		
		//判断订单为待签收或待办理
		private String orderType;
		
		//缓冲条
		private ProgressDialog progressDialog;
		
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
	        setContentView(R.layout.order_detail);
	        
	        //当前页获取订单ID
	        Intent intent = getIntent();
	        orderId = intent.getStringExtra("orderId");
	        taskId = intent.getStringExtra("taskId");
	        orderType = intent.getStringExtra("listType");
	        initView();
	        connection();
	    }
	    
	    private void initView() {
	    	//底部菜单栏
			radio0 = (RadioButton) findViewById(R.id.radio0);
			radio1 = (RadioButton) findViewById(R.id.radio1);
			radio2 = (RadioButton) findViewById(R.id.radio2);
			
			editText11 = (EditText) findViewById(R.id.editText11);
	    	editText12 = (EditText) findViewById(R.id.editText12);
//	    	editText22 = (EditText) findViewById(R.id.editText22);
//	    	editText32 = (EditText) findViewById(R.id.editText32);
//	    	editText42 = (EditText) findViewById(R.id.editText42);
	    	editText52 = (EditText) findViewById(R.id.editText52);
	    	editText61 = (EditText) findViewById(R.id.editText61);
	    	editText62 = (EditText) findViewById(R.id.editText62);
	    	editText71 = (EditText) findViewById(R.id.editText71);
	    	editText72 = (EditText) findViewById(R.id.editText72);
	    	editText81 = (EditText) findViewById(R.id.editText81);
	    	editText82 = (EditText) findViewById(R.id.editText82);
//	    	editText92 = (EditText) findViewById(R.id.editText92);
	    	editText102 = (EditText) findViewById(R.id.editText102);
	    	
	    	beginTime = (LinearLayout) findViewById(R.id.beginTime);
	    	editText22 = (EditText) findViewById(R.id.editText22);
	    	endTime = (LinearLayout) findViewById(R.id.endTime);
	    	editText32 = (EditText) findViewById(R.id.editText32);
	    	
	        editText112 = (EditText) findViewById(R.id.editText112);
	    	editText122 = (EditText) findViewById(R.id.editText122);
	    	editText132 = (EditText) findViewById(R.id.editText132);
	    	editText171 = (EditText) findViewById(R.id.editText171);
	    	editText172 = (EditText) findViewById(R.id.editText172);
	    	img142 = (ImageView) findViewById(R.id.image142);
	    	
	    	img152 = (ImageView) findViewById(R.id.image152);

	        agreeButton = (Button) findViewById(R.id.button162);
	    	claimButton = (Button) findViewById(R.id.button172);
	    	printButton = (Button) findViewById(R.id.button182);
	    	
	    	radio0.setOnClickListener(this);
	    	radio1.setOnClickListener(this);
	    	radio2.setOnClickListener(this);
	    	
	        img142.setOnClickListener(this);
	        img152.setOnClickListener(this);
	        agreeButton.setOnClickListener(this);
	        claimButton.setOnClickListener(this);
	        printButton.setOnClickListener(this);
	        
	        progressDialog = ProgressDialog.show(OrderDetailActivity.this, "", "获取订单信息中，请稍后……");  
	        //获取工单详情
	        AsyncHttpClient client = new AsyncHttpClient();
	    	String userAgent = ((MyApplication)getApplication()).getUserAgent();
	    	String token = ((MyApplication)getApplication()).getToken();
	    	
	    	client.addHeader("user-agent", userAgent);
	    	String url = "http://120.79.146.218:10079/conserver/querry/getorderdetail?orderType=con&token="+token+"&taskId="+taskId+"&orderId="+orderId;
	    	client.get(url, new JsonHttpResponseHandler() {
	    		@Override
	    		public void onSuccess(int statusCode, Header[] headers, org.json.JSONObject  response) {
//	    			super.onSuccess(statusCode,headers, response);
	    			String str = response.toString();
	    			
	    			//转成字符串
					String jobStr = response.toString();
					//转成com.alibaba.fastjson.JSONObject格式的Object
					JSONObject myJsonObject = JSONObject.parseObject(jobStr);
	    			
					com.alibaba.fastjson.JSONObject data =myJsonObject.getJSONObject("data");
					com.alibaba.fastjson.JSONObject orderPojo = data.getJSONObject("orderPojo");
					com.alibaba.fastjson.JSONObject taskInfo = data.getJSONObject("taskInfo");
					com.alibaba.fastjson.JSONObject conOrder = orderPojo.getJSONObject("conOrder");

//					Boolean isAgree = taskInfo.containsKey("assignee");
					if(orderType.equals("groupWait")) {
						claimButton.setVisibility(View.VISIBLE);
						agreeButton.setVisibility(View.GONE);
					}else if(orderType.equals("waitPerson")) {
						claimButton.setVisibility(View.GONE);
						agreeButton.setVisibility(View.VISIBLE);
					}else if(orderType.equals("finishPerson")) {
						claimButton.setVisibility(View.GONE);
						agreeButton.setVisibility(View.GONE);
					}
					
	    			if(conOrder.get("motorcadeId").toString().equals("")) {
	    				editText11.setVisibility(View.GONE);
	    				editText12.setVisibility(View.GONE);
	    			}else {
	    				editText11.setVisibility(View.VISIBLE);
	    				editText12.setVisibility(View.VISIBLE);
	    				editText12.setText(conOrder.get("motorcadeId").toString());	
	    			}
	    			
	    			
	    			if(conOrder.get("startTime") == null || conOrder.get("startTime").toString().equals("")) {
	    				beginTime.setVisibility(View.GONE);
	    				endTime.setVisibility(View.GONE);
	    			}else {
	    				beginTime.setVisibility(View.VISIBLE);
	    				endTime.setVisibility(View.VISIBLE);
	    				String startTimeStamp = conOrder.get("startTime").toString();
	    				String endTimeStamp = conOrder.get("endTime").toString();

	    				
	    				long startTimeInt = conOrder.getLong("startTime");
	    				long endTimeInt = conOrder.getLong("endTime");
	    				
//	    				int startTimeInt = (int)Double.parseDouble(startTimeStamp);
//	    				int endTimeInt = (int)Double.parseDouble(endTimeStamp);
	    				
	    				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    				String startDate = sdf.format(new Date(startTimeInt));  
	    				String endDate = sdf.format(new Date(endTimeInt));  
	    				
	    				editText22.setText(startDate);
	    				editText32.setText(endDate);
	    				
	    				
	    			}
	    			editText52.setText(conOrder.get("projectName").toString());
	    			editText62.setText(conOrder.get("feeType").toString());
	    			editText72.setText(conOrder.get("machineType").toString());
	    			editText82.setText(conOrder.get("workType").toString());
//	    			editText92.setText(conOrder.get("motorcadeName").toString());
	    			editText102.setText(conOrder.get("destinationName").toString());
	    			editText112.setText(conOrder.get("num").toString());
	    			editText122.setText(conOrder.get("unitPrice").toString()+" "+conOrder.get("unitWord").toString());
//	    			editText122.setText(conOrder.get("unitPrice").toString());
	    			editText132.setText(conOrder.get("money").toString());
	    			editText172.setText(conOrder.get("feeSecondType").toString());
	    			taskCreateTime = taskInfo.get("taskCreateTime").toString();
	    			printNum =  (int) conOrder.get("printNum");
	    		
//	    			Bitmap bitmap1 = GetNetPicture.getHttpBitmap("http://www.yxbone.com:10023/fileupload/upload/showPic?picturePath="+conOrder.get("firstPic").toString()); //从网上取图片
	    			Bitmap bitmap1 = GetNetPicture.getHttpBitmap("https://gdfsc-construction.oss-cn-shenzhen.aliyuncs.com/"+conOrder.get("firstPic").toString()); //从网上取图片
	    	        img142.setImageBitmap(bitmap1); //设置Bitmap
	    	        
	    	        Bitmap bitmap2 = GetNetPicture.getHttpBitmap("https://gdfsc-construction.oss-cn-shenzhen.aliyuncs.com/"+conOrder.get("secondlyPic").toString()); //从网上取图片
	    	        img152.setImageBitmap(bitmap2); //设置Bitmap
	    	        
	    	        feeType = conOrder.get("feeType").toString();
	    	        getFeeName(feeType);
	    	        mechineType = conOrder.get("machineType").toString();
	    	        workType = conOrder.get("workType").toString();
	    	        secondWorkType = conOrder.get("feeSecondType").toString();

	    	        
	    	        progressDialog.dismiss();
	    	      
	    	    }
	    		
				@Override
	    		public void onFailure(int statusCode, Header[] headers,Throwable error,org.json.JSONObject responseBody) {
					progressDialog.dismiss();
	                Log.d("Error----> ", ""+statusCode+" ------ "+ responseBody);
	            	Toast.makeText(getApplicationContext(), "调用接口失败",Toast.LENGTH_SHORT).show(); 
	            }
	    	   
	    	});
	       
	    }
	    
	    //获取费用类型的类名
	    private void getFeeName(final String feeType) {
	    	
	    	//获取配置项
	        AsyncHttpClient client = new AsyncHttpClient();
	    	String userAgent = ((MyApplication)getApplication()).getUserAgent();

	    	client.addHeader("user-agent", userAgent);
	    	String url = "http://47.97.207.192:8036/query/findByEnNames?enNames=feeType";
	    	client.get(url, new JsonHttpResponseHandler() {
	    		@Override
	    		public void onSuccess(int statusCode, Header[] headers, org.json.JSONObject  response) {
					org.json.JSONArray childEnum;
					String nextItemId;
					String nextItemName;
					try {
						childEnum = response.getJSONArray("configInfoList");

						nextItemName = response.getString("nextItemName");
						if(childEnum.length()>0) {
				    		for(int i=0;i<(childEnum.length());i++) {
				    			org.json.JSONObject childEnum_item;
				    			String childEnum_value;
				    			try {
									childEnum_item = childEnum.getJSONObject(i);
									childEnum_value = childEnum_item.getString("chName");
									if(childEnum_value.equals(feeType)) {
										fee_type_chose = childEnum_item.getString("enName");
										String dd = fee_type_chose;
								    	editText61.setText(nextItemName+":");
						    	        getMechineName(mechineType);
									}
									
									
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
				    		}
				    		
				    		
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
	    
	    //获取车辆类型的类名
	    private void getMechineName(final String mechineType) {
	    	
	    	//获取配置项
	        AsyncHttpClient client = new AsyncHttpClient();
	    	String userAgent = ((MyApplication)getApplication()).getUserAgent();
	    	String a = fee_type_chose;
	    	client.addHeader("user-agent", userAgent);
	    	String url = "http://47.97.207.192:8036/query/findByEnNames?enNames=feeType,"+fee_type_chose;
	    	client.get(url, new JsonHttpResponseHandler() {
	    		@Override
	    		public void onSuccess(int statusCode, Header[] headers, org.json.JSONObject  response) {
					org.json.JSONArray childEnum;
					String nextItemId;
					String nextItemName;
					try {
						childEnum = response.getJSONArray("configInfoList");

						nextItemName = response.getString("nextItemName");
						if(childEnum.length()>0) {
				    		for(int i=0;i<(childEnum.length());i++) {
				    			org.json.JSONObject childEnum_item;
				    			String childEnum_value;
				    			try {
									childEnum_item = childEnum.getJSONObject(i);
									childEnum_value = childEnum_item.getString("chName");
									if(childEnum_value.equals(mechineType)) {
										mechine_type_chose = childEnum_item.getString("enName");
								    	editText71.setText(nextItemName+":");
						    	        getWorkName(workType);
									}
									
									
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
				    		}
				    		
				    		
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
	    
	  //获取工作类型的类名
	    private void getWorkName(final String workType) {
	    	
	    	//获取配置项
	        AsyncHttpClient client = new AsyncHttpClient();
	    	String userAgent = ((MyApplication)getApplication()).getUserAgent();

	    	client.addHeader("user-agent", userAgent);
	    	String url = "http://47.97.207.192:8036/query/findByEnNames?enNames=feeType,"+fee_type_chose+","+mechine_type_chose;
	    	client.get(url, new JsonHttpResponseHandler() {
	    		@Override
	    		public void onSuccess(int statusCode, Header[] headers, org.json.JSONObject  response) {
					org.json.JSONArray childEnum;
					String nextItemId;
					String nextItemName;
					try {
						childEnum = response.getJSONArray("configInfoList");

						nextItemName = response.getString("nextItemName");
						nextItemId = response.getString("nextItemId");
						if(nextItemId.equals("price")) {
							editText81.setVisibility(View.GONE);
							editText82.setVisibility(View.GONE);
							editText81.setText("");
							editText171.setVisibility(View.GONE);
							editText172.setVisibility(View.GONE);
							editText171.setText("");
						}else {
							if(childEnum.length()>0) {
					    		for(int i=0;i<(childEnum.length());i++) {
					    			org.json.JSONObject childEnum_item;
					    			String childEnum_value;
					    			try {
										childEnum_item = childEnum.getJSONObject(i);
										childEnum_value = childEnum_item.getString("chName");
										if(childEnum_value.equals(workType)) {
											work_type_chose = childEnum_item.getString("enName");
									    	editText81.setText(nextItemName+":");
							    	        getSecondWorkName(secondWorkType);
										}
										
										
									} catch (JSONException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
					    		}
					    		
					    		
					    	}
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
	    
	  //获取第二工作类型的类名
	    private void getSecondWorkName(final String secondWorkType) {
	    	
	    	//获取配置项
	        AsyncHttpClient client = new AsyncHttpClient();
	    	String userAgent = ((MyApplication)getApplication()).getUserAgent();

	    	client.addHeader("user-agent", userAgent);
	    	String url = "http://47.97.207.192:8036/query/findByEnNames?enNames=feeType,"+fee_type_chose+","+mechine_type_chose+","+work_type_chose;
	    	client.get(url, new JsonHttpResponseHandler() {
	    		@Override
	    		public void onSuccess(int statusCode, Header[] headers, org.json.JSONObject  response) {
					org.json.JSONArray childEnum;
					String nextItemId;
					String nextItemName;
					try {
						childEnum = response.getJSONArray("configInfoList");

						nextItemName = response.getString("nextItemName");
						nextItemId = response.getString("nextItemId");
						if(nextItemId.equals("price")) {
							editText171.setVisibility(View.GONE);
							editText172.setVisibility(View.GONE);
							editText171.setText("");
						}else {
							if(childEnum.length()>0) {
					    		for(int i=0;i<(childEnum.length());i++) {
					    			org.json.JSONObject childEnum_item;
					    			String childEnum_value;
					    			try {
										childEnum_item = childEnum.getJSONObject(i);
										childEnum_value = childEnum_item.getString("chName");
//										mechine_type_chose = childEnum_item.getString("enName");
										if(childEnum_value.equals(secondWorkType)) {
									    	editText171.setText(nextItemName+":");
										}
										
										
									} catch (JSONException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
					    		}
					    		
					    		
					    	}
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
	    
	   
	    public void onClick(View v) {
	    	if (v.equals(radio0)) {
				if(radioChecked != 0) {
					checkCreateNew();
	
					radioChecked = 0;
					unCheckOrderList();
					unCheckPersonCenter();
				}
				enterMainMenuActivity();
			}
			else if(v.equals(radio1)) {
				if(radioChecked != 1) {
					checkOrderList();
	
					radioChecked = 1;
					unCheckCreateNew();
					unCheckPersonCenter();
				}
				//弹出底部菜单
				menuWindow = new BottomMenu(OrderDetailActivity.this, clickListener,"1");  
			    menuWindow.show(); 
			}
			else if(v.equals(radio2)) {
				if(radioChecked != 2) {
					checkPersonCenter();
	//				layout1.setVisibility(View.GONE);
	//				layout2.setVisibility(View.GONE);
	//				layout3.setVisibility(View.VISIBLE);
					
					radioChecked = 2;
					unCheckCreateNew();
					unCheckOrderList();
				}
				menuWindow = new BottomMenu(OrderDetailActivity.this, clickListener,"2");  
			    menuWindow.show(); 
			    
			}
			else if(v.equals(img142)) {
	    		//大图预览
	    		final AlertDialog dialog = new AlertDialog.Builder(this).create();
	            ImageView imgView = getView("1");
	            dialog.setView(imgView);
	            dialog.show();
	            
	         // 点击图片消失
	            imgView.setOnClickListener(new View.OnClickListener() {
	                @Override
	                public void onClick(View v) {
	                    // TODO Auto-generated method stub
	                    dialog.dismiss();
	                }
	            });
	    	}
	    	else if(v.equals(img152)) {
	    		final AlertDialog dialog = new AlertDialog.Builder(this).create();
	            ImageView imgView = getView("2");
	            dialog.setView(imgView);
	            dialog.show();
	            
	         // 点击图片消失
	            imgView.setOnClickListener(new View.OnClickListener() {
	                @Override
	                public void onClick(View v) {
	                    // TODO Auto-generated method stub
	                    dialog.dismiss();
	                }
	            });
	    	}
	    	else if (v.equals(agreeButton)) {
				
				//审核通过操作
				AsyncHttpClient client = new AsyncHttpClient();
				RequestParams params = new RequestParams();
				String userAgent = ((MyApplication)getApplication()).getUserAgent();
		    	String token = ((MyApplication)getApplication()).getToken();
		    	client.addHeader("user-agent", userAgent);
		    	client.addHeader("ContentType", "application/json")	; 
		    	
		    	JSONObject submitObject = new JSONObject();
		    	JSONObject variables = new JSONObject();
		    	
		    	StringEntity stringEntity = null; 
		    	 
		    	variables.put("message","1");                
				submitObject.put("taskId", taskId);
				submitObject.put("variables", variables);
				try {
					stringEntity = new StringEntity(submitObject.toString(),"utf-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}                   

		    	String url = "http://120.79.146.218:10079/conserver/process/commit?token="+token+"&orderType=con";
    	    	client.post(getBaseContext(),url,stringEntity,"application/json",new JsonHttpResponseHandler() {
	    		@Override
	    		public void onSuccess(int statusCode, Header[] headers, org.json.JSONObject  response) {
	    	       // Handle resulting parsed JSON response here
	    	    	System.out.println(response);

						try {
							org.json.JSONObject data = response.getJSONObject("data");
							String orderId = data.getString("orderId");
							if(data != null) {
								Toast.makeText(getApplicationContext(), "审核通过，订单号为："+orderId,Toast.LENGTH_SHORT).show();
								
								//回到工单列表
								 new Thread() {
							            @Override
							            public void run() {
							                super.run();
							                try {
												Thread.sleep(2000);
											} catch (InterruptedException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}//休眠3秒
											enterOrderListActivity("waitPerson");
//							                enterMainMenuActivity();
							            }
							    }.start();
							}else {
								Toast.makeText(getApplicationContext(), "审核失败",Toast.LENGTH_SHORT).show();
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
	    	else if (v.equals(claimButton)) {
				
				//签收通过操作
				AsyncHttpClient client = new AsyncHttpClient();
				RequestParams params = new RequestParams();
				String userAgent = ((MyApplication)getApplication()).getUserAgent();
		    	String token = ((MyApplication)getApplication()).getToken();
		    	client.addHeader("user-agent", userAgent);
		    	client.addHeader("ContentType", "application/json")	; 
		    	
		    	JSONObject submitObject = new JSONObject();
		    	JSONObject variables = new JSONObject();
		    	
		    	StringEntity stringEntity = null; 
		    	 
		    	variables.put("message","1");                
				submitObject.put("taskId", taskId);
				submitObject.put("variables", variables);
				try {
					stringEntity = new StringEntity(submitObject.toString(),"utf-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}                   

		    	String url = "http://120.79.146.218:10079/conserver/process/claim?token="+token+"&orderType=con";
    	    	client.post(getBaseContext(),url,stringEntity,"application/json",new JsonHttpResponseHandler() {
	    		@Override
	    		public void onSuccess(int statusCode, Header[] headers, org.json.JSONObject  response) {
	    	       // Handle resulting parsed JSON response here
	    	    	System.out.println(response);

						try {
//							org.json.JSONObject data = response.getJSONObject("data");
//							String orderId = data.getString("orderId");
							String message = response.getString("message");
							if(message.equals("SUCCESS")) {
								Toast.makeText(getApplicationContext(), "签收成功，订单号为："+orderId,Toast.LENGTH_SHORT).show();
								
								//回到工单列表
								 new Thread() {
							            @Override
							            public void run() {
							                super.run();
							                try {
												Thread.sleep(2000);
											} catch (InterruptedException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}//休眠3秒
											enterOrderListActivity("groupWait");
//							                enterMainMenuActivity();
							            }
							    }.start();
							}else {
								Toast.makeText(getApplicationContext(), "签收失败",Toast.LENGTH_SHORT).show();
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
	    	else if(v.equals(printButton)) {
				printTestClicked(v,orderId);
	    	}
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
	                Toast.makeText(OrderDetailActivity.this, "menu21", Toast.LENGTH_SHORT).show();  
	                break;  
	            case R.id.btn22:  
	                Toast.makeText(OrderDetailActivity.this, "menu22", Toast.LENGTH_SHORT).show();  
	                break; 
	            case R.id.btn23:  
	                Toast.makeText(OrderDetailActivity.this, "menu23", Toast.LENGTH_SHORT).show();  
	                break;  
	            case R.id.btn24:  
//	                Toast.makeText(MainMenuActivity.this, "menu24", Toast.LENGTH_SHORT).show();  
	            	enterMainActivity();
	                break; 
	            default:  
	                break;  
	            }  
	            }  
	        }; 
	        
	        
	        //连接打印机
		    private void connection() {
		        conn = new PrinterServiceConnection();
//		        Intent intent = new Intent("com.gprinter.aidl.GpPrintService");
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

		    }
		    
		    void sendReceipt(String orderId) {
		        EscCommand esc = new EscCommand();
//		        esc.addPrintAndFeedLines((byte) 3);
//		        esc.addSelectCodePage(EscCommand.CODEPAGE.UYGUR);
//		        esc.addCancelKanjiMode();
		        esc.addSelectJustification(JUSTIFICATION.CENTER);//璁剧疆鎵撳嵃灞呬腑
		        esc.addSelectPrintModes(FONT.FONTA, ENABLE.OFF, ENABLE.ON, ENABLE.ON, ENABLE.OFF);//璁剧疆涓哄�嶉珮鍊嶅
		        esc.addText("锋晟建筑单据\n");   //  鎵撳嵃鏂囧瓧
		        esc.addPrintAndLineFeed();

		        /*鎵撳嵃鏂囧瓧*/
		        esc.addSelectPrintModes(FONT.FONTA, ENABLE.OFF, ENABLE.OFF, ENABLE.OFF, ENABLE.OFF);//鍙栨秷鍊嶉珮鍊嶅
		        esc.addSelectJustification(JUSTIFICATION.LEFT);//璁剧疆鎵撳嵃宸﹀榻�
//		        esc.addText("Print text\n");   //  鎵撳嵃鏂囧瓧
//		        esc.addText("Welcome to use Gprinter!\n");   //  鎵撳嵃鏂囧瓧
//		        esc.addText("这是中文\n", "utf8");   //  鎵撳嵃鏂囧瓧
//		        esc.addText("这是中文\n", "utf-8");   //  鎵撳嵃鏂囧瓧
		        String number =editText12.getText().toString();
//		        String people = editText22.getText().toString();
//		        String phone = editText32.getText().toString();
//		        String company =editText42.getText().toString();
		        String project = editText52.getText().toString();
		        String feeType = editText62.getText().toString();
		        String machineType = editText72.getText().toString();
		        String workType = editText82.getText().toString();
		        String secondWorkType = editText172.getText().toString();
//		        String sourceProject = editText92.getText().toString();
		        String destinationName = editText102.getText().toString();
		        String num = editText112.getText().toString();
		        String unitPrice = editText122.getText().toString();
		        String money  = editText132.getText().toString();
		        
		        if(printNum>=0) {
		        	esc.addText("重复打印单:第"+(printNum+1)+"次\n");
		        	addPrintNum();
		        }
		        if(!number.equals("")) {
		        	esc.addText("车牌号码:"+number+"\n");       //  车牌号码
		        }
		        
//		        esc.addText("联系人:"+people+"\n");        //  联系人
//		        esc.addText("电话号码:"+phone+"\n");       //  电话号码
//		        esc.addText("所属公司:"+company+"\n");       //  所属公司
		        esc.addText("工程项目:"+project+"\n");   //  工程项目
		        esc.addText("费用类别:"+feeType+"\n");   //  费用类别
		        esc.addText(editText71.getText().toString()+machineType+"\n");      //  运输类型
		        if(editText81.getText().toString().length()!=0) {
			        esc.addText(editText81.getText().toString()+workType+"\n");   //  工作方法	
		        }
		        if(editText171.getText().toString().length()!=0) {
			        esc.addText(editText171.getText().toString()+secondWorkType+"\n");   //  工作方法	
		        }
//		        esc.addText("来源项目:"+sourceProject+"\n");   //  来源项目
		        esc.addText("目的项目:"+destinationName+"\n");   // 目的项目
		        if(!editText22.getText().toString().equals("")) {
		        	 esc.addText("开始时间:"+editText22.getText().toString()+"\n");           // 开始时间
		        	 esc.addText("结束时间:"+editText32.getText().toString()+"\n");           // 开始时间
    			}
		        esc.addText("数量:"+num+"\n");           // 数量
		        esc.addText("单价:"+unitPrice+"\n");          //  单价
		        esc.addText("金额:"+money+"元\n");          //  金额
		        esc.addText("创建日期:"+taskCreateTime+"\n");
		        esc.addPrintAndLineFeed();
//				/*鎵撳嵃绻佷綋涓枃  闇�瑕佹墦鍗版満鏀寔绻佷綋瀛楀簱*/
//		        String message = Util.SimToTra("浣冲崥绁ㄦ嵁鎵撳嵃鏈篭n");
//		        //	esc.addText(message,"BIG5");
//		        esc.addText(message, "GB2312");
//		        esc.addPrintAndLineFeed();
	//
//				/*鎵撳嵃鍥剧墖*/
//		        esc.addText("Print bitmap!\n");   //  鎵撳嵃鏂囧瓧
//		        Bitmap b = BitmapFactory.decodeResource(getResources(),
//		                R.drawable.gprinter);
//		        esc.addRastBitImage(b, b.getWidth(), 0);   //鎵撳嵃鍥剧墖

				/*鎵撳嵃涓�缁存潯鐮�*/
//		        esc.addText("条形码：\n");   //  鎵撳嵃鏂囧瓧
		        esc.addSelectPrintingPositionForHRICharacters(HRI_POSITION.BELOW);//璁剧疆鏉＄爜鍙瘑鍒瓧绗︿綅缃湪鏉＄爜涓嬫柟
		        esc.addSetBarcodeHeight((byte) 60); //璁剧疆鏉＄爜楂樺害涓�60鐐�
//		        esc.addCODE128("Gprinter");  //鎵撳嵃Code128鐮�
//		        esc.addCODE128(orderId);  //鎵撳嵃Code128鐮�
//		        esc.addPrintAndLineFeed();
//		        // 璁� 缃� 鏉� 鐮� 鍗� 鍏� 瀹� 搴� 涓� 1 鐐�
		        esc.addSetBarcodeWidth((byte)1);
//		        // 鎵� 鍗� Code128 鐮�
//		        esc.addCODE128(esc.genCode128("123456789"));
//		        esc.addCODE128(esc.genCodeC("youare666"));
//		        esc.addCODE128(esc.genCodeB("Gprinter"));
		        esc.addCODE128(esc.genCodeB(orderId));
		        esc.addPrintAndLineFeed();

				/*QRCode鍛戒护鎵撳嵃
					姝ゅ懡浠ゅ彧鍦ㄦ敮鎸丵RCode鍛戒护鎵撳嵃鐨勬満鍨嬫墠鑳戒娇鐢ㄣ��
					鍦ㄤ笉鏀寔浜岀淮鐮佹寚浠ゆ墦鍗扮殑鏈哄瀷涓婏紝鍒欓渶瑕佸彂閫佷簩缁存潯鐮佸浘鐗�
				*/
//				esc.addText("二维码：\n");   //  鎵撳嵃鏂囧瓧
		        esc.addSelectJustification(JUSTIFICATION.CENTER);//璁剧疆鎵撳嵃宸﹀榻�
				esc.addSelectErrorCorrectionLevelForQRCode((byte)0x31); //璁剧疆绾犻敊绛夌骇
				esc.addSelectSizeOfModuleForQRCode((byte)8);//璁剧疆qrcode妯″潡澶у皬
				esc.addStoreQRCodeData(orderId);//璁剧疆qrcode鍐呭
				esc.addPrintQRCode();//鎵撳嵃QRCode
				esc.addPrintAndLineFeed();
				
//				/*鎵撳嵃鏂囧瓧*/
//		        esc.addSelectJustification(JUSTIFICATION.CENTER);//璁剧疆鎵撳嵃宸﹀榻�
//		        esc.addText("Completed!\r\n");   //  鎵撳嵃缁撴潫
//		        esc.addPrintAndFeedLines((byte) 8);
				/*鎵撳嵃鏂囧瓧*/
		        esc.addSelectJustification(JUSTIFICATION.RIGHT);//璁剧疆鎵撳嵃宸﹀榻�
//		        esc.addText("Completed!\r\n");   //  鎵撳嵃缁撴潫
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
		        
//		        //打印完成后刷新本页
		        enterOrderDetailActivity();
		    }
		    
	   //重复打印单操作	    
	   private void addPrintNum() {
		    	//重复打印单次数增加1
		        AsyncHttpClient client = new AsyncHttpClient();
		    	String userAgent = ((MyApplication)getApplication()).getUserAgent();
		    	String token = ((MyApplication)getApplication()).getToken();
		    	
		    	client.addHeader("user-agent", userAgent);
		    	String url = "http://120.79.146.218:10079/conserver/con/printNumIncrease?orderId="+orderId+"&token="+token;
		    	client.get(url, new JsonHttpResponseHandler() {
		    		@Override
		    		public void onSuccess(int statusCode, Header[] headers, org.json.JSONObject  response) {
		    		   Toast.makeText(getApplicationContext(), "单据打印成功",Toast.LENGTH_SHORT).show();
		    	    }
		    		
					@Override
		    		public void onFailure(int statusCode, Header[] headers,Throwable error,org.json.JSONObject responseBody) {
		                Log.d("Error----> ", ""+statusCode+" ------ "+ responseBody);
		            	Toast.makeText(getApplicationContext(), "调用接口失败",Toast.LENGTH_SHORT).show(); 
		            }
		    	   
		    	});
		}
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
	    

	    private ImageView getView(String imgNo) {
	    	ImageView imgView = new ImageView(this);
	    	imgView.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT));
	    	if(imgNo.equals("1")) {
	    		imgView.setImageDrawable(img142.getDrawable());
	    	}else {
	    		imgView.setImageDrawable(img152.getDrawable());
	    	}
	        
	        return imgView;
	    }

		 
		 private void enterMainActivity() {
				Intent it = new Intent(OrderDetailActivity.this,MainActivity.class);
				startActivity(it);
			}
	    private void enterMainMenuActivity() {
			Intent it = new Intent(OrderDetailActivity.this,MainMenuActivity.class);
			startActivity(it);
		}
	    
	    private void enterOrderDetailActivity() {
			Intent it = new Intent(OrderDetailActivity.this,OrderDetailActivity.class);
			it.putExtra("orderId", orderId);
			it.putExtra("taskId", taskId);
			it.putExtra("listType", orderType);
			startActivity(it);
		}
	    
	    private void enterOrderListActivity(String listType) {
			Intent it = new Intent(OrderDetailActivity.this,OrderListActivity.class);
			it.putExtra("listType", listType);
			startActivity(it);
		}
	    
}
