package com.yys.szcp.utils;

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
}
