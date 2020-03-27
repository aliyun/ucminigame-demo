package cn.uc.pay.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class URLEncodeUtils {

    public static String encode(String source, String charset) {
        if (StringUtils.isEmpty(source)) {
            return "";
        }
        String str = "";
        try {
            str = URLEncoder.encode(source, charset);
        } catch (UnsupportedEncodingException e) {
        }
        return str;
    }

    public static String encode(String source) {
        return encode(source, "utf-8");
    }

    public static String decode(String source, String charset) {
        if (StringUtils.isEmpty(source)) {
            return "";
        }
        String str = "";
        try {
            str = URLDecoder.decode(source, charset);
        } catch (UnsupportedEncodingException e) {
        }
        return str;
    }

    public static String decode(String source) {
        return decode(source, "utf-8");
    }

}
