package com.nfjd.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Administrator on 2016/11/18.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResult<T> {
    private Integer errcode=0;

    private String errmsg="成 功";
    
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
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    @Override
    public String toString() {
        return "ApiResult{" +
                "errcode='" + errcode + '\'' +
                ", errmsg='" + errmsg + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
