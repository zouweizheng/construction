package com.gprinter.sample;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.lang.ArrayUtils;
import org.apache.http.Header;
import org.json.JSONException;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gprint.common.BottomMenu;
import com.gprint.common.MyApplication;
import com.gprinter.adapter.OrderListAdapter;
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

import com.sample.R;

import android.R.integer;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.loopj.android.http.*;


public class OrderListActivity extends Activity  implements OnClickListener{ 
	    
		/*顶部搜索栏*/
	    private LinearLayout search_nav;
	    private JSONArray searchListAll = new JSONArray();
	    
	    //存放搜索后匹配的列表项
		private JSONArray searchListGet = new JSONArray();
		private EditText search_text;
		private Button search_btn;
		
		/*底部分页栏*/
		private LinearLayout page_nav;
		private Button prevPage;
		private EditText thisPage;
		private Button nextPage;
		
		private Integer pageNowIndex = 1;
		private double pageWholeIndex;
		
		private JSONArray pageListGet = new JSONArray();
			
		/**底部菜单栏*/
		private RadioGroup radioGroup;
		private RadioButton radio0;
		private RadioButton radio1;
		private RadioButton radio2;
		
		//褰撳墠搴曢儴鐘舵�佹爮閫変腑鎯呭喌
		private int radioChecked = 0;
		
		 private BottomMenu menuWindow; 
	
		//单据列表不为空
		private ListView haveList;
		//单据列表为空
		private LinearLayout noneList;
		
		//获取单据列表
	  	private List<String> list;
	  	private ListView lv;
	  	OrderListAdapter adapter;
	  	private int count = 0;
	  	
	  	//单据列表类型
	  	private String listType;

	  	//缓冲条
		private ProgressDialog progressDialog;
	  	
        
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
//	        setContentView(R.layout.activity_main);
	        setContentView(R.layout.order_list);
	        
	        //获取单据列表类型
	        Intent intent = getIntent();
	        listType = intent.getStringExtra("listType");
	        
	        initView();
	  
	    }
	    
	    private void initView() {
	    	//顶部搜索栏
	    	search_nav = (LinearLayout) findViewById(R.id.search_nav);
	    	search_text = (EditText) findViewById(R.id.search_text);
	    	search_btn = (Button) findViewById(R.id.search_btn);
	    	search_btn.setOnClickListener(this);
	    	
	    	//底部分页栏
	    	page_nav = (LinearLayout) findViewById(R.id.page_nav);
	    	prevPage = (Button) findViewById(R.id.prev_page);
	    	thisPage = (EditText) findViewById(R.id.this_page);
	    	nextPage = (Button) findViewById(R.id.next_page);
	    	
	    	prevPage.setOnClickListener(this);
	    	nextPage.setOnClickListener(this);
	    	
	    	//底部菜单栏
			radio0 = (RadioButton) findViewById(R.id.radio0);
			radio1 = (RadioButton) findViewById(R.id.radio1);
			radio2 = (RadioButton) findViewById(R.id.radio2);
			
	    	noneList = (LinearLayout) findViewById(R.id.none_list);
	    	haveList = (ListView) findViewById(R.id.order_list);
	    	
	    	radio0.setOnClickListener(this);
	    	radio1.setOnClickListener(this);
	    	radio2.setOnClickListener(this);
	    	
	    	
//	    	showProgressDialog("获取列表数据中……");
	    	progressDialog = ProgressDialog.show(OrderListActivity.this, "", "加载中，请稍后……");  
	    	
	    	final JSONArray orderListGet = new JSONArray();  
	        final String mes1=null;
	    	AsyncHttpClient client = new AsyncHttpClient();
	    	String userAgent = ((MyApplication)getApplication()).getUserAgent();
	    	String token = ((MyApplication)getApplication()).getToken();
	    	
	    	
	    	client.addHeader("user-agent", userAgent);
	    	String url = null;
	    	if(listType.equals("groupWait")) {
	    		url="http://120.79.146.218:10079/conserver/querry/groupWait?orderType=con&token="+token;
	    		radio1.setText("本组待办");
	    	}else if(listType.equals("waitPerson")) {
	    		url="http://120.79.146.218:10079/conserver/querry/waitPerson?orderType=con&token="+token;
	    		radio1.setText("个人待办");
	    	}else if(listType.equals("finishPerson")) {
	    		url="http://120.79.146.218:10079/conserver/querry/finishPerson?orderType=con&token="+token;
	    		radio1.setText("个人已办");
	    	}
//	    	String url = "http://localhost:10079/conserver/querry/groupWait?orderType=con&token="+token;
	    	client.get(url, new JsonHttpResponseHandler() {
	    		@Override
	    		public void onSuccess(int statusCode, Header[] headers, org.json.JSONObject  response) {
	    	       // Handle resulting parsed JSON response here
//	    			super.onSuccess(statusCode,headers, response);
//	    			String str = response.toString();
	    	    	
	    			//新建jsonArray装载返回的json中的数组
	    			org.json.JSONArray orderList = new org.json.JSONArray();  
					try {
						orderList = response.getJSONArray("data");
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//按规格为10来分，计算可以分多少页
					pageWholeIndex = Math.ceil(orderList.length()/10);
					
					
					if((orderList).length()>0){
						  //遍历数组，获取其中每个org.json.JSONObject格式的Object
						  for(int i=0;i<(orderList.length());i++){
						    try {
								org.json.JSONObject job = orderList.getJSONObject(i);
								//转成字符串
								String jobStr = job.toString();
								//转成com.alibaba.fastjson.JSONObject格式的Object
								JSONObject myJsonObject = JSONObject.parseObject(jobStr);
								//加入com.alibaba.fastjson.JSONArray格式的Array中
								orderListGet.add(myJsonObject);
								
								//此全局list存放初始所有列表项
								searchListAll.add(myJsonObject);
								
								//此全局list存放用于分页的所有列表项
								pageListGet.add(myJsonObject);
								
//								String strr = orderListGet.toString();
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
						  }
						  
						  //初始第一页
						  int startIndex = 1;
						  JSONArray defaultList = sliceList(orderListGet,startIndex);
							
						  String orderListGetStr = orderListGet.toString();
						  setList(defaultList);	

						  progressDialog.dismiss();
					}else {
						progressDialog.dismiss();
						search_nav.setVisibility(View.GONE);
						noneList.setVisibility(View.VISIBLE);
						haveList.setVisibility(View.GONE);
					}
//					System.out.println("orderListGet:"+orderListGet);

//					System.out.println("orderListGetStr:"+orderListGetStr);

	    	    }
	    		
				@Override
	    		public void onFailure(int statusCode, Header[] headers,Throwable error,org.json.JSONObject responseBody) {
	                Log.d("Error----> ", ""+statusCode+" ------ "+ responseBody);
	                progressDialog.dismiss();
	            	Toast.makeText(getApplicationContext(), "调用接口失败",Toast.LENGTH_SHORT).show(); 
	            	
	            }
	    	   
	    	});
			
	    }
	    
	    //列表按规格为10进行分组
	    private JSONArray sliceList(JSONArray sliceList,Integer startIndex) {
	    	int start_index = (startIndex-1)*10;

	    	JSONArray list_group = new JSONArray();
	    	
	    	for(int i=start_index;i<start_index+10;i++) {
	    		//判断越界
	    		if(sliceList.size() <= i) {
	    			return list_group;
	    		}else {
	    			list_group.add(sliceList.getJSONObject(i));
	    		}
	    	}
	    	
		    return list_group;	
	    }
	    
	    public void onClick(final View v) {
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
				menuWindow = new BottomMenu(OrderListActivity.this, clickListener,"1");  
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
				menuWindow = new BottomMenu(OrderListActivity.this, clickListener,"2");  
			    menuWindow.show(); 
			    
			}
			//上一页
			else if(v.equals(prevPage)) {
				pageNowIndex--;
				if(pageNowIndex == 0 ) {
					pageNowIndex++;
					Toast.makeText(getApplicationContext(), "当前页为首页",Toast.LENGTH_SHORT).show();
					return;
				}
				setList(sliceList(pageListGet,pageNowIndex));
				thisPage.setText(pageNowIndex.toString());
			}
			//下一页
			else if(v.equals(nextPage)) {
				pageNowIndex++;
				if(pageNowIndex > pageWholeIndex ) {
					pageNowIndex--;
					Toast.makeText(getApplicationContext(), "当前页为尾页",Toast.LENGTH_SHORT).show();
					return;
				}
				setList(sliceList(pageListGet,pageNowIndex));
				thisPage.setText(pageNowIndex.toString());
			}
			//搜索功能
			else if(v.equals(search_btn)) {
				//查找操作后均把当前页置为1
				thisPage.setText("1");
				pageNowIndex = 1;
				
				//清空搜索匹配的结果
				searchListGet.clear();
				
				String searchContent = search_text.getText().toString();
				if(searchContent.length()!=0) {
					//筛选
					for(int i =0 ;i<searchListAll.size();i++) {
						JSONObject orderListItem = searchListAll.getJSONObject(i);
						JSONObject orderPojo = orderListItem.getJSONObject("orderPojo");
						com.alibaba.fastjson.JSONObject conOrder = orderPojo.getJSONObject("conOrder");
						String orderType = conOrder.get("orderId").toString();
						String projectName = conOrder.getString("projectName").toString();
						String feeType = conOrder.getString("feeType").toString();
						String motorcadeId = conOrder.getString("motorcadeId").toString();
						
						if(orderType.indexOf(searchContent)!=-1 || projectName.indexOf(searchContent)!=-1 || feeType.indexOf(searchContent)!=-1 || motorcadeId.indexOf(searchContent)!=-1){
							searchListGet.add(orderListItem);
						}else {
							continue;
						}
					}
					
					//实时更新总页数
					double j = searchListGet.size();
					double k = j/10;
					pageWholeIndex = Math.ceil(k);
					
					 //初始第一页
					 int startIndex = 1;
					 JSONArray defaultList = sliceList(searchListGet,startIndex);
	
					 pageListGet = searchListGet;
					 setList(defaultList);
						
				}else {
					//实时更新总页数
					double j = searchListGet.size();
					double k = j/10;
					pageWholeIndex = Math.ceil(k);
					
					//初始第一页
					 int startIndex = 1;
					 JSONArray defaultList = sliceList(searchListAll,startIndex);

					
					pageListGet = searchListAll;
					setList(defaultList);
				}
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
	                Toast.makeText(OrderListActivity.this, "menu21", Toast.LENGTH_SHORT).show();  
	                break;  
	            case R.id.btn22:  
	                Toast.makeText(OrderListActivity.this, "menu22", Toast.LENGTH_SHORT).show();  
	                break; 
	            case R.id.btn23:  
	                Toast.makeText(OrderListActivity.this, "menu23", Toast.LENGTH_SHORT).show();  
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
	    
		private void setList(JSONArray orderListGet1) {
    		//获取提醒消息列表
			lv = (ListView) findViewById(R.id.order_list);
			
			adapter = new OrderListAdapter(orderListGet1, this,listType);
			lv.setAdapter(adapter);
			
			//判断listView滚动的位置
			lv.setOnScrollListener(new AbsListView.OnScrollListener() {            
				@Override
	            public void onScrollStateChanged(AbsListView view, int scrollState) {
	                switch (scrollState) {
	                    // 当不滚动时
	                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
	                        // 判断滚动到底部
	                        if (lv.getLastVisiblePosition() == (lv.getCount() - 1)) {
	//                            L.e("滚动到底部");
	                        	  page_nav.setVisibility(View.VISIBLE);
//	                        	  search_nav.setVisibility(View.GONE);
	                        	  page_nav.setBackgroundColor(getResources().getColor(R.color.half_transparent));
	                        }
	                        // 判断滚动到顶部
	                        if (lv.getFirstVisiblePosition() == 0) {
	                        	 page_nav.setVisibility(View.GONE);
//	                        	 search_nav.setVisibility(View.VISIBLE);
//	                        	 search_nav.setBackgroundColor(getResources().getColor(R.color.half_transparent));
	                        }
	                        break;
	                }
	            }
 
	            @Override
	            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
	 
	            }
			}); 
			
			progressDialog.dismiss();

    	}
		
		
		
		private void enterMainMenuActivity() {
			Intent it = new Intent(OrderListActivity.this,MainMenuActivity.class);
			startActivity(it);
		}

		
		 private void enterOrderListActivity(String listType) {
				Intent it = new Intent(OrderListActivity.this,OrderListActivity.class);
				it.putExtra("listType", listType);
				startActivity(it);
			}
		 
		 private void enterMainActivity() {
				Intent it = new Intent(OrderListActivity.this,MainActivity.class);
				startActivity(it);
			}
	
}
