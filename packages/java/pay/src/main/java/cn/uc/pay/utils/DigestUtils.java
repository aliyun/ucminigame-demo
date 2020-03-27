package cn.uc.pay.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class DigestUtils {

    public static String md5(String text) {
        try {
            if (text == null) { return null; }
            return md5(text.getBytes("utf-8"));
        } catch (Exception e) {
        }
        return null;
    }

    public static String md5(byte[] textBytes) {
        try {
            if (textBytes == null) { return null; }
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(textBytes);
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);

            // Now we need to zero pad it if you actually want the full 32
            // chars.
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (Exception e) {
        }
        return null;
    }
}
