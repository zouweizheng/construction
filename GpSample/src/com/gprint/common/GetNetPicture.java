package com.gprint.common;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;

public class GetNetPicture {
	 public static Bitmap getHttpBitmap(String url) {
	    	URL myFileUrl = null;
	    	Bitmap bitmap = null;
	    	try {
//	    	Log.d(TAG, url);
	    	myFileUrl = new URL(url);
	    	} catch (MalformedURLException e) {
	    	e.printStackTrace();
	    	}
	    	try {
	    	HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
//	    	conn.setConnectTimeout(0);
	    	conn.setDoInput(true);
	    	StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    	StrictMode.setThreadPolicy(policy);
	    	try {
	    		conn.connect();
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
	    	InputStream is = conn.getInputStream();
	    	bitmap = BitmapFactory.decodeStream(is);
	    	is.close();
	    	} catch (IOException e) {
	    	e.printStackTrace();
	    	}
	    	return bitmap;
	    }
}
