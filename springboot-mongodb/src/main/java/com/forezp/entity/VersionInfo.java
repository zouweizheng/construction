package com.forezp.entity;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by zou on 2018/2/6.
 */
public class VersionInfo {

    private String version;
    private String url;
    private String describe;
    private Date createTime;
    private String pojoInfo = "VersionInfo";

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPojoInfo() {
        return pojoInfo;
    }

    public void setPojoInfo(String pojoInfo) {
        this.pojoInfo = pojoInfo;
    }
}
