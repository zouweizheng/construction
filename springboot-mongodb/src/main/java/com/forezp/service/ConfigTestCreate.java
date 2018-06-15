package com.forezp.service;

import com.forezp.entity.ConfigInfo;
import jdk.nashorn.internal.runtime.regexp.joni.Config;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/4.
 */
@Service
public class ConfigTestCreate {

    /**
     * 返回Fee的类
     * @return
     */
    public ConfigInfo FeeInfo(){
        ConfigInfo configInfo = new ConfigInfo();
        configInfo.setNextItemId("feeType");
        configInfo.setNextItemName("费用类别");
        configInfo.setEnName("feeType");
        configInfo.setChName("费用类别");
        List<ConfigInfo> configInfoList = new ArrayList<>();
        configInfoList.add(CarInfo());
        configInfoList.add(MechanicalInfo());
        configInfoList.add(PersonInfo());
        configInfoList.add(OtherInfo());
        configInfoList.add(PurchaseInfo());
        configInfo.setConfigInfoList(configInfoList);
        return configInfo;
    }

    /**
     * 返回车辆运输费ne
     * @return
     */
    public ConfigInfo CarInfo(){
        ConfigInfo configInfo = new ConfigInfo();
        configInfo.setNextItemId("mechineType");
        configInfo.setNextItemName("车辆类型");
        configInfo.setEnName("carTranslate");
        configInfo.setChName("车辆运输");
        List<ConfigInfo> configInfoList = new ArrayList<>();
        configInfoList.add(CarWorkType("maDanB","孖担车（标准型）"));
        configInfoList.add(CarWorkType("maDanZ","孖担车（重载前孖后孖）"));
        configInfoList.add(CarWorkType("nongYC","农用车"));
        configInfoList.add(CarWorkType("tuolaji","拖拉机"));
        configInfoList.add(CarWorkType("chache","叉车（台班）"));
        configInfoList.add(CarWorkType("diaoche","吊车"));
        configInfo.setConfigInfoList(configInfoList);
        return configInfo;
    }

    /**
     * 返回机械费
     * @return
     */
    public ConfigInfo MechanicalInfo(){
        ConfigInfo configInfo = new ConfigInfo();
        configInfo.setNextItemId("mechineType");
        configInfo.setNextItemName("机械类型");
        configInfo.setEnName("mechanical");
        configInfo.setChName("机械费");
        List<ConfigInfo> configInfoList = new ArrayList<>();
        configInfoList.add(MechineWorkType("PC200","PC200"));
        configInfoList.add(MechineWorkType("PC350","PC350"));
        configInfoList.add(MechineWorkType("HD820","HD820"));
        configInfoList.add(MechineWorkType("CAD320D","CAD320D"));
        configInfo.setConfigInfoList(configInfoList);
        return configInfo;
    }

    /**
     * 返回人工费
     * @return
     */
    public ConfigInfo PersonInfo(){
        ConfigInfo configInfo = new ConfigInfo();
        configInfo.setEnName("person");
        configInfo.setChName("人工费");
        List<ConfigInfo> configInfoList = new ArrayList<>();
        return configInfo;
    }

    /**
     * 返回其他费用
     * @return
     */
    public ConfigInfo OtherInfo(){
        ConfigInfo configInfo = new ConfigInfo();
        configInfo.setEnName("other");
        configInfo.setChName("其他费用");
        List<ConfigInfo> configInfoList = new ArrayList<>();
        return configInfo;
    }

    /**
     * 返回采购费用
     * @return
     */
    public ConfigInfo PurchaseInfo(){
        ConfigInfo configInfo = new ConfigInfo();
        configInfo.setEnName("purchase");
        configInfo.setChName("采购费用");
        List<ConfigInfo> configInfoList = new ArrayList<>();
        return configInfo;
    }

    /**
     * 机械费工作类型
     * @return
     */
    public ConfigInfo MechineWorkType(String enName , String chName){
        ConfigInfo configInfo = new ConfigInfo();
        configInfo.setNextItemId("workType");
        configInfo.setNextItemName("工作类型");
        configInfo.setEnName(enName);
        configInfo.setChName(chName);
        List<ConfigInfo> configInfoList = new ArrayList<>();
        configInfoList.add(oidType());
        configInfoList.add(setPriceType("shift","台班"));
        configInfoList.add(setPriceType("dapao","打炮破碎"));
        configInfoList.add(setPriceType("dangou","单钩破碎"));
        configInfoList.add(setPriceType("madan","装车（孖担车）"));
        configInfoList.add(setPriceType("nongyong","装车（农用车）"));
        configInfo.setConfigInfoList(configInfoList);
        return configInfo;
    }

    /**
     * 运输费工作类型
     * @return
     */
    public ConfigInfo CarWorkType(String enName , String chName){
        ConfigInfo configInfo = new ConfigInfo();
        configInfo.setNextItemId("workType");
        configInfo.setNextItemName("工作类型");
        configInfo.setEnName(enName);
        configInfo.setChName(chName);
        List<ConfigInfo> configInfoList = new ArrayList<>();
        configInfoList.add(oidType());
        configInfoList.add(setPriceType("shift","台班"));
        configInfoList.add(setPriceType("outTranslate","外运（车）"));
        configInfoList.add(setPriceType("outsideTurn","中转（场外）"));
        configInfoList.add(setPriceType("insideTurn","中转（场内）"));
        configInfo.setConfigInfoList(configInfoList);
        return configInfo;
    }

    /**
     *柴油类型
     * @return
     */
    public ConfigInfo oidType(){
        ConfigInfo configInfo = new ConfigInfo();
        configInfo.setNextItemId("secondWorkType");
        configInfo.setNextItemName("柴油工作类型");
        configInfo.setEnName("oid");
        configInfo.setChName("柴油台班");
        List<ConfigInfo> configInfoList = new ArrayList<>();
        configInfoList.add(setPriceType("oidMoney","柴油费"));
        configInfoList.add(setPriceType("oidShift","柴油用班"));
        configInfoList.add(unitWord());
        configInfo.setConfigInfoList(configInfoList);
        return configInfo;
    }

    /**
     *设置价格
     * @return
     */
    public ConfigInfo setPriceType(String enName , String chName){
        ConfigInfo configInfo = new ConfigInfo();
        configInfo.setNextItemId("price");
        configInfo.setNextItemName("价格");
        configInfo.setEnName(enName);
        configInfo.setChName(chName);
        List<ConfigInfo> configInfoList = new ArrayList<>();
        configInfoList.add(unitPrice());
        configInfoList.add(unitWord());
        configInfo.setConfigInfoList(configInfoList);
        return configInfo;
    }

    /**
     *最终价格
     * @return
     */
    public ConfigInfo unitPrice(){
        ConfigInfo configInfo = new ConfigInfo();
        configInfo.setEnName("unitPrice");
        configInfo.setChName("50");
        List<ConfigInfo> configInfoList = new ArrayList<>();
        return configInfo;
    }

    /**
     *最终价格
     * @return
     */
    public ConfigInfo unitWord(){
        ConfigInfo configInfo = new ConfigInfo();
        configInfo.setEnName("unitWord");
        configInfo.setChName("天/次");
        List<ConfigInfo> configInfoList = new ArrayList<>();
        return configInfo;
    }

}
