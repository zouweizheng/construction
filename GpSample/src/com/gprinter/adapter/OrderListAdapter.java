package com.gprinter.adapter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.gprinter.sample.OrderDetailActivity;
import com.sample.R;

import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

public class OrderListAdapter extends BaseAdapter {
	protected static final String ORDER_DETAIL = null;
	public JSONArray list;
	private Context context;
	
	private String listType;

	
	private LayoutInflater mInflater;
	public OrderListAdapter(com.alibaba.fastjson.JSONArray orderList,Context context,String listType) {
		super();
		this.listType = listType;
		this.list = orderList;
		this.context = context;
		mInflater = LayoutInflater.from(context);  
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
//		robdetail = (IRobdetail) new BCTool().newProxyInstance(new RobdetailBC());
		ViewHolder vh = null;
		if(convertView == null){
			vh = new ViewHolder();
			convertView = mInflater.inflate(R.layout.order_list_item,null);
//			vh.tv = (TextView) convertView.findViewById(R.id.textView1);
			convertView.setTag(vh);
		}else{
			vh = (ViewHolder) convertView.getTag();
		}
		com.alibaba.fastjson.JSONObject jObject = list.getJSONObject(position);  
        
       /* TextView title = (TextView) convertView.findViewById(R.id.textView1);//閹电偓鐓囨稉顏呭付娴狅拷  
        title.setText(jObject.get("consignNo").toString());//缂佹瑨顕氶幒褌娆㈢拋鍓х枂閺佺増宓�(閺佺増宓佹禒搴ㄦ肠閸氬牏琚稉顓熸降)  
*/        
				com.alibaba.fastjson.JSONObject orderPojo = jObject.getJSONObject("orderPojo");
		        com.alibaba.fastjson.JSONObject taskInfo = jObject.getJSONObject("taskInfo");
		        com.alibaba.fastjson.JSONObject conOrder = orderPojo.getJSONObject("conOrder");

				//璁㈠崟orderItem
				LinearLayout orderItem = (LinearLayout) convertView.findViewById(R.id.order_list_item_todetail);
				EditText tit1 = (EditText) convertView.findViewById(R.id.order_list_item_tit1);
				EditText content1 = (EditText) convertView.findViewById(R.id.order_list_item_content1);
				EditText tit2 = (EditText) convertView.findViewById(R.id.order_list_item_tit2);
				EditText content2 = (EditText) convertView.findViewById(R.id.order_list_item_content2);
				EditText tit3 = (EditText) convertView.findViewById(R.id.order_list_item_tit3);
				EditText content3 = (EditText) convertView.findViewById(R.id.order_list_item_content3);
				EditText tit4 = (EditText) convertView.findViewById(R.id.order_list_item_tit4);
				EditText content4 = (EditText) convertView.findViewById(R.id.order_list_item_content4);
				
				com.alibaba.fastjson.JSONObject tagObject = new com.alibaba.fastjson.JSONObject();
				tagObject.put("orderId",jObject.get("orderId").toString());
				tagObject.put("taskId",taskInfo.get("taskId").toString());
				orderItem.setTag(tagObject);
				tit1.setTag(tagObject);
				content1.setTag(tagObject);
				tit2.setTag(tagObject);
				content2.setTag(tagObject);
				tit3.setTag(tagObject);
				content3.setTag(tagObject);
				tit4.setTag(tagObject);
				content4.setTag(tagObject);
				
		        EditText motorcadeId = (EditText) convertView.findViewById(R.id.order_list_item_content1);
		        motorcadeId.setText(conOrder.get("orderId").toString());

		       
		        EditText type = (EditText) convertView.findViewById(R.id.order_list_item_content2);
		        type.setText(conOrder.get("feeType").toString());

		        EditText price = (EditText) convertView.findViewById(R.id.order_list_item_content3);
		        price.setText(conOrder.get("money").toString());

		        EditText status = (EditText) convertView.findViewById(R.id.order_list_item_content4);
		        if(conOrder.get("orderStatus").toString().equals("verify")) {
		        	status.setText("审批环节");

		        }else if(conOrder.get("orderStatus").toString().equals("construction")){
		        	status.setText("已确认");
		        }
//		        status.setText(conOrder.get("orderStatus").toString());
		        
		        

		        //缁橪inearLayout娣诲姞鍗曞嚮浜嬩欢
		        orderItem.setOnClickListener(new View.OnClickListener() {
			            @Override
			            public void onClick(View v) {

			            	//鑾峰彇tagObject
			            	com.alibaba.fastjson.JSONObject getTagObject = (com.alibaba.fastjson.JSONObject) v.getTag();
				           	String orderId = getTagObject.get("orderId").toString();
				           	String taskId =  getTagObject.get("taskId").toString();

				            //璺宠浆鍒拌鍗曡鎯�
				           	enterOrderDetailActivity(orderId,taskId,listType);
			            }
			      });
		        tit1.setOnClickListener(new View.OnClickListener() {
		            @Override
		            public void onClick(View v) {

		            	//鑾峰彇tagObject
		            	 com.alibaba.fastjson.JSONObject getTagObject = (com.alibaba.fastjson.JSONObject) v.getTag();
			           	String orderId = getTagObject.get("orderId").toString();
			           	String taskId =  getTagObject.get("taskId").toString();

			            //璺宠浆鍒拌鍗曡鎯�
			           	enterOrderDetailActivity(orderId,taskId,listType);
		            }
		      });
		        content1.setOnClickListener(new View.OnClickListener() {
		            @Override
		            public void onClick(View v) {

		            	//鑾峰彇tagObject
		            	 com.alibaba.fastjson.JSONObject getTagObject = (com.alibaba.fastjson.JSONObject) v.getTag();
			           	String orderId = getTagObject.get("orderId").toString();
			           	String taskId =  getTagObject.get("taskId").toString();

			            //璺宠浆鍒拌鍗曡鎯�
			           	enterOrderDetailActivity(orderId,taskId,listType);
		            }
		      });
		        tit2.setOnClickListener(new View.OnClickListener() {
		            @Override
		            public void onClick(View v) {

		            	//鑾峰彇tagObject
		            	 com.alibaba.fastjson.JSONObject getTagObject = (com.alibaba.fastjson.JSONObject) v.getTag();
			           	String orderId = getTagObject.get("orderId").toString();
			           	String taskId =  getTagObject.get("taskId").toString();

			            //璺宠浆鍒拌鍗曡鎯�
			           	enterOrderDetailActivity(orderId,taskId,listType);
		            }
		      });
		        content2.setOnClickListener(new View.OnClickListener() {
		            @Override
		            public void onClick(View v) {

		            	//鑾峰彇tagObject
		            	 com.alibaba.fastjson.JSONObject getTagObject = (com.alibaba.fastjson.JSONObject) v.getTag();
			           	String orderId = getTagObject.get("orderId").toString();
			           	String taskId =  getTagObject.get("taskId").toString();

			            //璺宠浆鍒拌鍗曡鎯�
			           	enterOrderDetailActivity(orderId,taskId,listType);
		            }
		      });
		        tit3.setOnClickListener(new View.OnClickListener() {
		            @Override
		            public void onClick(View v) {

		            	//鑾峰彇tagObject
		            	 com.alibaba.fastjson.JSONObject getTagObject = (com.alibaba.fastjson.JSONObject) v.getTag();
			           	String orderId = getTagObject.get("orderId").toString();
			           	String taskId =  getTagObject.get("taskId").toString();

			            //璺宠浆鍒拌鍗曡鎯�
			           	enterOrderDetailActivity(orderId,taskId,listType);
		            }
		      });
		        content3.setOnClickListener(new View.OnClickListener() {
		            @Override
		            public void onClick(View v) {

		            	//鑾峰彇tagObject
		            	 com.alibaba.fastjson.JSONObject getTagObject = (com.alibaba.fastjson.JSONObject) v.getTag();
			           	String orderId = getTagObject.get("orderId").toString();
			           	String taskId =  getTagObject.get("taskId").toString();

			            //璺宠浆鍒拌鍗曡鎯�
			           	enterOrderDetailActivity(orderId,taskId,listType);
		            }
		      });
		        tit4.setOnClickListener(new View.OnClickListener() {
		            @Override
		            public void onClick(View v) {

		            	//鑾峰彇tagObject
		            	 com.alibaba.fastjson.JSONObject getTagObject = (com.alibaba.fastjson.JSONObject) v.getTag();
			           	String orderId = getTagObject.get("orderId").toString();
			           	String taskId =  getTagObject.get("taskId").toString();

			            //璺宠浆鍒拌鍗曡鎯�
			           	enterOrderDetailActivity(orderId,taskId,listType);
		            }
		      });
		        content4.setOnClickListener(new View.OnClickListener() {
		            @Override
		            public void onClick(View v) {

		            	//鑾峰彇tagObject
		            	 com.alibaba.fastjson.JSONObject getTagObject = (com.alibaba.fastjson.JSONObject) v.getTag();
			           	String orderId = getTagObject.get("orderId").toString();
			           	String taskId =  getTagObject.get("taskId").toString();

			            //璺宠浆鍒拌鍗曡鎯�
			           	enterOrderDetailActivity(orderId,taskId,listType);
		            }
		      });
		       

				return convertView;
			}

			protected void startActivity(Intent intent) {
				// TODO Auto-generated method stub

			}

			public class ViewHolder{
				public TextView tv;
			}

			private void enterOrderDetailActivity(String orderId,String taskId,String listType) {
				Intent it = new Intent(context, OrderDetailActivity.class);
				it.putExtra("orderId", orderId);
				it.putExtra("taskId", taskId);
				it.putExtra("listType", listType);
				context.startActivity(it);
			}
}
