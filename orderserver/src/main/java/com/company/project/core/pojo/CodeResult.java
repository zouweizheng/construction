package com.company.project.core.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Administrator on 2016/11/18.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CodeResult<T> {
    private Integer code=200;

    private String message="成 功";
    
    private T body;

    public T getBody()
	{
		return body;
	}

	public void setBody(T body)
	{
		this.body = body;
	}

	public Integer getErrcode() {
        return code;
    }

    public void setErrcode(Integer errcode) {
        this.code = errcode;
    }

    public String getErrmsg() {
        return message;
    }

    public void setErrmsg(String errmsg) {
        this.message = errmsg;
    }

    @Override
    public String toString() {
        return "ApiResult{" +
                "errcode='" + code + '\'' +
                ", errmsg='" + message + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
