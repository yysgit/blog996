package com.yys.szcp.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;

public class EasyEduUtil {

    /**
     * 字符串使用MD5加密处理,可用于密码加密
     *
     * @param msg 明文
     * @return 加密之后的明文, 加密失败则返回一个空字符串
     */
    public static String md5(String msg) {
        if (null == msg) {
            return null;
        }
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] out = md.digest(msg.getBytes());
            String str = Base64.encodeBase64String(out);
            return str;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 获取一个salt值，每次调用该方法都会获得一个不同的值
     * 该值为一个随机8个字符的字符串,随机范围为：数字、字母(区分大小写)、*、#、%、_
     */
    public static String getSalt() {
        String str = "12345*67890qwertyuiopa#sdfghjklzxcvbnmQWERTYU%IOPMNBVCXZ_ASDFGHJKL";
        String salt = "";
        for (int i = 0; i < 8; i++) {
            salt += str.charAt((int) (Math.random() * str.length()));
        }
        return salt;
    }

    /**
     * 将给定的java对象转换成json格式字符串
     */
    public static String objToJson(Object object) {
        String json = "{}";
        if (null == object) {
            return json;
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            json = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {

        }
        return json;
    }


}
