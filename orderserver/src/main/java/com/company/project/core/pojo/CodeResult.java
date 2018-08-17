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

	public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
