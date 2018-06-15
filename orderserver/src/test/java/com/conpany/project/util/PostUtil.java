package com.conpany.project.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.company.project.core.pojo.ApiResult;
import com.company.project.core.pojo.TaskAndOrder;
import com.company.project.foundation.core.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


public class PostUtil<T>
{
	private static final Logger log = LoggerFactory.getLogger(PostUtil.class);

	/**
	 * post数据并返还
	 * @author ZouWeizheng
	 * modify_date 2017年1月6日
	 */
	public static ResponseEntity<ApiResult> postData(Map postData, String url)
	{
		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());
		JSONObject medalRule= new JSONObject();
		medalRule=DateTypeTran.MapToJSONObject(postData);
		HttpEntity<String> formEntity =new HttpEntity<String>(medalRule.toString(), headers);
		RestTemplate template = new RestTemplate();
		ResponseEntity<ApiResult> result =template.postForEntity(url, formEntity, ApiResult.class);
		//ResponseEntity<String> result =template.postForObject(url, formEntity, String.class);
		return result;
	}

	public static ResponseEntity<String> postData(Map postData, String url, String userName, String password)
	{
		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());
		JSONObject medalRule= new JSONObject();
		medalRule=DateTypeTran.MapToJSONObject(postData);
		HttpEntity<String> formEntity =new HttpEntity<String>(medalRule.toString(), headers);
		RestTemplate template = new RestTemplate();
		template.getInterceptors().add(
				new BasicAuthorizationInterceptor(userName, password));
		ResponseEntity<String> result =template.postForEntity(url, formEntity, String.class);
		//ResponseEntity<String> result =template.postForObject(url, formEntity, String.class);
		return result;
	}

	public static ResponseEntity<ApiResult> postData(String postData, String url)
	{
		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());
		HttpEntity<String> formEntity =new HttpEntity<String>(postData.toString(), headers);
		RestTemplate template = new RestTemplate();
		ResponseEntity<ApiResult> result =template.postForEntity(url, formEntity, ApiResult.class);
		//ResponseEntity<String> result =template.postForObject(url, formEntity, String.class);
		return result;
	}

	public static ResponseEntity<ApiResult> postData(JSONArray postData, String url)
	{
		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());
		HttpEntity<String> formEntity =new HttpEntity<String>(postData.toString(), headers);
		RestTemplate template = new RestTemplate();
		ResponseEntity<ApiResult> result =template.postForEntity(url, formEntity, ApiResult.class);
		//ResponseEntity<String> result =template.postForObject(url, formEntity, String.class);
		return result;
	}

	public static ResponseEntity<ApiResult> postData(JSONObject postData, String url)
	{
		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());
		JSONObject orderData= postData;
		HttpEntity<String> formEntity =new HttpEntity<String>(orderData.toString(), headers);
		RestTemplate template = new RestTemplate();
		ResponseEntity<ApiResult> result =template.postForEntity(url, formEntity, ApiResult.class);
		//ResponseEntity<String> result =template.postForObject(url, formEntity, String.class);
		return result;
	}

	public ApiResult<TaskAndOrder> postData(T postData, String url)
	{
		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());
		String jsonString = JSON.toJSONString(postData);
		HttpEntity<String> formEntity =new HttpEntity<String>(jsonString, headers);
		RestTemplate template = new RestTemplate();
		ResponseEntity<ApiResult> result =template.postForEntity(url, formEntity,ApiResult.class);
		//ResponseEntity<String> result =template.postForObject(url, formEntity, String.class);
		return result.getBody();
	}

	public static String postData(JSONObject postData, String url, String userName, String password)
	{
		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());
		JSONObject medalRule= postData;
		HttpEntity<String> formEntity =new HttpEntity<String>(medalRule.toString(), headers);
		RestTemplate template = new RestTemplate();
		template.getInterceptors().add(
				new BasicAuthorizationInterceptor(userName, password));
		String result =template.postForObject(url, formEntity, String.class);
		return result;
	}


}
