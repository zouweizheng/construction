package com.company.project.core.util;

/**
 * Created by ZouWeizheng on 2017-11-04.
 */
public class MapReflect {

    public static String getOrderConfigReflect(String mapName){
        switch (mapName){
            case "ConstructionMapper" : return "ConstructionConfigInfo";
            case "BillMapper" : return "BillConfigInfo";
            case "OperationMapper": return "OperationConfigInfo";
        }
        return null;
    }
}
