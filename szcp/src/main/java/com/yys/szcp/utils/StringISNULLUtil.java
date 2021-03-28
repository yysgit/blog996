package com.yys.szcp.utils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Describe:
 * -------------------
 * User: yangyongsheng
 * Date: 2019/07/04 10:47:16
 * Email: 1095737364@qq.com
 */
public class StringISNULLUtil {
    public static String mapToString(Object object){
        if(object!=null&&!"".equals(object)&& !"null".equals(object)){
            return object.toString();
        }else{
            return null;
        }
    }




    public static Integer mapToInteger(Object object){
        if(object!=null&&!"".equals(object)&& !"null".equals(object)){
            return Integer.valueOf(object.toString());
        }else{
            return null;
        }
    }
    public static Double mapToDouble(Object object){
        if(object!=null&&!"".equals(object)&& !"null".equals(object)){
            return Double.valueOf(object.toString());
        }else{
            return null;
        }
    }
    public static BigDecimal mapToBigDecimal(Object object){
        if(object!=null&&!"".equals(object)&& !"null".equals(object)){
            return new  BigDecimal(object.toString());
        }else{
            return null;
        }
    }

    public static Date mapToDateyyyyMMddHHmmss(Object object){
        if(object!=null&&!"".equals(object)&& !"null".equals(object)){
            String time = "2017-11-30T10:41:44.651Z";
            time = time.replace("Z", " UTC");//UTC是本地时间
            SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date d = null;
            try {
                d = format.parse(time);
            } catch (ParseException e) {

                e.printStackTrace();
            }
            return d;
        }else{
            return null;
        }
    }



}
