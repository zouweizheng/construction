package com.company.project.core.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by LiuJinming on 2017/1/3.
 */
public class GenerateSeriesID {

    public static String createId(String beforeString) {
        int randomNum = (int)(Math.random()*(99-10+1))+10;
        SimpleDateFormat formatter = new SimpleDateFormat("yyMMddHHmmss");
        String formatStr = formatter.format(new Date())+String.valueOf(randomNum);
        formatStr = beforeString + formatStr;
        return formatStr;
    }

}
