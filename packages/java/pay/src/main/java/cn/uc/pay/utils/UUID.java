package cn.uc.pay.utils;

import org.apache.commons.lang3.StringUtils;

public class UUID {

    public static String id() {
        String uuid = java.util.UUID.randomUUID().toString();
        uuid = StringUtils.remove(uuid, '-');
        return uuid;
    }

    public static byte[] bytes() {
        return id().getBytes();
    }

    public static String part5() {
        return id().substring(20);
    }

    public static String part1_2() {
        return id().substring(0, 12);
    }
}
