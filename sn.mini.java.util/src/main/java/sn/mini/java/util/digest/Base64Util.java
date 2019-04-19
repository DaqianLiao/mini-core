package sn.mini.java.util.digest;

import java.nio.ByteBuffer;

/**
 * sn.mini.java.util.digest.java
 * @author XChao
 */
public class Base64Util {

    /**
     * Base64编码
     * @param data 编码数据
     * @return 编码结果
     */
    public static String encode(String data) {
        return java.util.Base64.getEncoder().encodeToString(data.getBytes());
    }

    /**
     * Base64编码
     * @param data 编码数据
     * @return 编码结果
     */
    public static byte[] encodeToByte(String data) {
        return java.util.Base64.getEncoder().encode(data.getBytes());
    }

    /**
     * Base64编码
     * @param data 编码数据
     * @return 编码结果
     */
    public static String encode(byte[] data) {
        return java.util.Base64.getEncoder().encodeToString(data);
    }

    /**
     * Base64编码
     * @param data 编码数据
     * @return 编码结果
     */
    public static byte[] encodeToByte(byte[] data) {
        return java.util.Base64.getEncoder().encode(data);
    }

    /**
     * Base64编码
     * @param data 编码数据
     * @return 编码结果
     */
    public static ByteBuffer encode(ByteBuffer data) {
        return java.util.Base64.getEncoder().encode(data);
    }

    /**
     * Base64编码
     * @param data 编码数据
     * @return 编码结果
     */
    public static int encode(byte[] data, byte[] dest) {
        return java.util.Base64.getEncoder().encode(data, dest);
    }

    /**
     * Base64解码
     * @param data 编码数据
     * @return 编码结果
     */
    public static String decode(String data) {
        return new String(java.util.Base64.getDecoder().decode(data));
    }

    /**
     * Base64解码
     * @param data 编码数据
     * @return 编码结果
     */
    public static byte[] decodeToByte(String data) {
        return java.util.Base64.getDecoder().decode(data);
    }

    /**
     * Base64解码
     * @param data 编码数据
     * @return 编码结果
     */
    public static String decode(byte[] data) {
        return new String(java.util.Base64.getDecoder().decode(data));
    }

    /**
     * Base64解码
     * @param data 编码数据
     * @return 编码结果
     */
    public static byte[] decodeToByte(byte[] data) {
        return java.util.Base64.getDecoder().decode(data);
    }

    /**
     * Base64解码
     * @param data 编码数据
     * @return 编码结果
     */
    public static ByteBuffer decode(ByteBuffer data) {
        return java.util.Base64.getDecoder().decode(data);
    }

    /**
     * Base64解码
     * @param data 编码数据
     * @param dest 编码参数
     * @return 编码结果
     */
    public static int decode(byte[] data, byte[] dest) {
        return java.util.Base64.getDecoder().decode(data, dest);
    }

    /**
     * URLBase64编码
     * @param data 编码数据
     * @return 编码结果
     */
    public static String urlEncode(String data) {
        return java.util.Base64.getUrlEncoder().encodeToString(data.getBytes());
    }

    /**
     * URLBase64编码
     * @param data 编码数据
     * @return 编码结果
     */
    public static byte[] urlEncodeToByte(String data) {
        return java.util.Base64.getUrlEncoder().encode(data.getBytes());
    }

    /**
     * URLBase64编码
     * @param data 编码数据
     * @return 编码结果
     */
    public static String urlEncode(byte[] data) {
        return java.util.Base64.getUrlEncoder().encodeToString(data);
    }

    /**
     * URLBase64编码
     * @param data 编码数据
     * @return 编码结果
     */
    public static byte[] urlEncodeToByte(byte[] data) {
        return java.util.Base64.getUrlEncoder().encode(data);
    }

    /**
     * URLBase64编码
     * @param data 编码数据
     * @return 编码结果
     */
    public static ByteBuffer urlEncode(ByteBuffer data) {
        return java.util.Base64.getUrlEncoder().encode(data);
    }

    /**
     * URLBase64编码
     * @param data 编码数据
     * @return 编码结果
     */
    public static int urlEncode(byte[] data, byte[] dest) {
        return java.util.Base64.getUrlEncoder().encode(data, dest);
    }

    /**
     * URLBase64解码
     * @param data 解码数据
     * @return 解码结果
     */
    public static String urlDecode(String data) {
        return new String(java.util.Base64.getUrlDecoder().decode(data));
    }

    /**
     * URLBase64解码
     * @param data 解码数据
     * @return 解码结果
     */
    public static byte[] urlDecodeToByte(String data) {
        return java.util.Base64.getUrlDecoder().decode(data);
    }

    /**
     * URLBase64解码
     * @param data 解码数据
     * @return 解码结果
     */
    public static String urlDecode(byte[] data) {
        return new String(java.util.Base64.getUrlDecoder().decode(data));
    }

    /**
     * URLBase64解码
     * @param data 解码数据
     * @return 解码结果
     */
    public static byte[] urlDecodeToByte(byte[] data) {
        return java.util.Base64.getUrlDecoder().decode(data);
    }

    /**
     * URLBase64解码
     * @param data 解码数据
     * @return 解码结果
     */
    public static ByteBuffer urlDecode(ByteBuffer data) {
        return java.util.Base64.getUrlDecoder().decode(data);
    }

    /**
     * URLBase64解码
     * @param data 解码数据
     * @param dest 编码参数
     * @return 解码结果
     */
    public static int urlDecode(byte[] data, byte[] dest) {
        return java.util.Base64.getUrlDecoder().decode(data, dest);
    }
}
