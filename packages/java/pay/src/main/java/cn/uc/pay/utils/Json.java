package cn.uc.pay.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;
import java.util.Map;

public class Json {

    public static final SerializeConfig SERIALIZE_CONFIG = new SerializeConfig();

    static {
        SERIALIZE_CONFIG.setPropertyNamingStrategy(PropertyNamingStrategy.SnakeCase);
    }

    public static <T> List<T> getDataFieldArray(JSONObject data, String field, Class<T> clazz) {
        if (data == null || !data.containsKey(field)) {
            return null;
        }
        JSONArray arr = data.getJSONArray(field);
        return arr.toJavaList(clazz);
    }

    public static <T> T getDataFieldObject(JSONObject data, String field, Class<T> clazz) {
        if (data == null || !data.containsKey(field)) {
            return null;
        }
        JSONObject arr = data.getJSONObject(field);
        return arr.toJavaObject(clazz);
    }

    public static JSONObject toJSONObject(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        return JSONObject.parseObject(bytes, JSONObject.class);
    }

    public static <T> List<T> listFrom(String text, Class<T> clazz) {
        return JSONArray.parseArray(text, clazz);
    }

    public static <T> T from(byte[] bytes, Class<T> clazz) {
        return JSONObject.parseObject(bytes, clazz);
    }

    public static <T> T from(String text, TypeReference<T> type) {
        return JSONObject.parseObject(text, type);
    }

    public static <T> T from(String text, Class<T> clazz) {
        return JSONObject.parseObject(text, clazz);
    }

    public static Map<String, Object> from(String text) {
        return JSONObject.parseObject(text);
    }

    public static String to(Object object) {
        if (object == null) {
            return null;
        }
        return JSONObject.toJSONString(object, SERIALIZE_CONFIG, SerializerFeature.WriteDateUseDateFormat);
    }

    public static String to(Object object, boolean stringIgnore) {
        if (object == null) {
            return null;
        }
        if (object instanceof String && stringIgnore) {
            return (String)object;
        }
        return JSONObject.toJSONString(object, SERIALIZE_CONFIG, SerializerFeature.WriteDateUseDateFormat);
    }

    public static byte[] toBytes(Object object) {
        return JSONObject.toJSONBytes(object, SERIALIZE_CONFIG);
    }
}
