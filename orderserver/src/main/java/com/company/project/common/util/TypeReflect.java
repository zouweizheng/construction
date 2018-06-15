package com.company.project.common.util;

/**
 * Created by ZouWeizheng on 2017-11-04.
 */
public class TypeReflect {

    public static String getOrderTypeReflect(String orderType){
        switch (orderType){
            case "con" : return "ConstructionService";
            case "bill" : return "BillService";
            case "op" : return "OperationService";
        }
        return null;
    }

    public static String getOrderConfigInfoReflect(String orderType){
        switch (orderType){
            case "con" : return "ConstructionConfigInfo";
            case "bill" : return "BillConfigInfo";
            case "op" : return "OperationConfigInfo";
        }
        return null;
    }

    public static String getOrderPojoReflect(String orderType){
        switch (orderType){
            case "con" : return "ConstructionPojo";
            case "bill" : return "BillPojo";
            case "op" : return "OperationPojo";
        }
        return null;
    }
}
