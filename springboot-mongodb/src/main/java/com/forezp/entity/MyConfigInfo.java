package com.forezp.entity;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;


public class MyConfigInfo {

    @Id
    private String id;
    private String enName;
    private String chName;
    private String secEnName;
    private String secChName;
    private String thirEnName;
    private String thirChName;
    private int sortNum;
    private List<MyConfigInfo> configInfoList;

    public MyConfigInfo() {}

    public MyConfigInfo(String enName, String chName, int sortNum) {
        this.enName = enName;
        this.chName = chName;
        this.sortNum = sortNum;
    }

    @Override
    public String toString() {
        return "ConfigInfo{" +
                "id='" + id + '\'' +
                ", enName='" + enName + '\'' +
                ", chName='" + chName + '\'' +
                ", secEnName='" + secEnName + '\'' +
                ", secChName='" + secChName + '\'' +
                ", thirEnName='" + thirEnName + '\'' +
                ", thirChName='" + thirChName + '\'' +
                ", sortNum=" + sortNum +
                ", configInfoList=" + configInfoList +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getChName() {
        return chName;
    }

    public void setChName(String chName) {
        this.chName = chName;
    }

    public String getSecEnName() {
        return secEnName;
    }

    public void setSecEnName(String secEnName) {
        this.secEnName = secEnName;
    }

    public String getSecChName() {
        return secChName;
    }

    public void setSecChName(String secChName) {
        this.secChName = secChName;
    }

    public String getThirEnName() {
        return thirEnName;
    }

    public void setThirEnName(String thirEnName) {
        this.thirEnName = thirEnName;
    }

    public String getThirChName() {
        return thirChName;
    }

    public void setThirChName(String thirChName) {
        this.thirChName = thirChName;
    }

    public int getSortNum() {
        return sortNum;
    }

    public void setSortNum(int sortNum) {
        this.sortNum = sortNum;
    }

    public List<MyConfigInfo> getConfigInfoList() {
        return configInfoList;
    }

    public void setConfigInfoList(List<MyConfigInfo> configInfoList) {
        this.configInfoList = configInfoList;
    }
    public void addConfigInfoList(MyConfigInfo configInfo) {
        if(null==this.configInfoList) this.configInfoList = new ArrayList<>();
        this.configInfoList.add(configInfo);
    }

}