package com.nfjd.util;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;

public class DateTypeTran
{
	public static JSONObject MapToJSONObject(Map<String,String> map)
	{
		Set entries = map.entrySet();
		JSONObject jsonObject=new JSONObject();
		if(entries != null) {
		Iterator<Map.Entry<String, String>> iterator = entries.iterator();
		while(iterator.hasNext()) {
		Map.Entry entry =iterator.next();
		Object key = entry.getKey();
		Object value = entry.getValue();
		jsonObject.put(entry.getKey().toString(), entry.getValue().toString());
		}
		}
		return jsonObject;
	}

	public static String getNowTime(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date date=new java.util.Date();
		String str=sdf.format(date);
		return str;
	}

}
