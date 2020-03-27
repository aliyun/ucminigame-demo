package cn.uc.pay.utils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Base64Utils {

    public static String base64Encode(byte[] bytes) {
        try {
            byte[] value = Base64.getEncoder().encode(bytes);
            return new String(value, "utf-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static String base64Encode(String str) {
        try {
            byte[] bytes = str.getBytes("utf-8");
            return base64Encode(bytes);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static byte[] base64Decode(String str) {
        try {
            return Base64.getDecoder().decode(str.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static String base64DecodeStr(String str) {
        try {
            byte[] value = Base64.getDecoder().decode(str.getBytes("utf-8"));
            String retString = new String(value, "utf-8");
            return retString;
        } catch (Exception e) {
            return null;
        }
    }
}
