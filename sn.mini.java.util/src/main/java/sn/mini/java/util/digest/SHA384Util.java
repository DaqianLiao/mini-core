package sn.mini.java.util.digest;

import java.io.InputStream;

/**
 * com.cfinal.util.digest.SHA384Util.java
 * @author XChao
 */
public class SHA384Util {
    private static final DigestUtil instance = new DigestUtil("SHA-384");

    /**
     * SHA384加密
     * @param data
     * @return
     */
    public static byte[] encodeToByte(String data) {
        return instance.encodeToByte(data);
    }

    /**
     * SHA384加密
     * @param data
     * @return
     */
    public static String encode(String data) {
        return instance.encode(data);
    }

    /**
     * SHA384加密
     * @param data
     * @return
     */
    public static byte[] encodeToByte(byte[] data) {
        return instance.encodeToByte(data);
    }

    /**
     * SHA384加密
     * @param data
     * @return
     */
    public static String encode(byte[] data) {
        return instance.encode(data);
    }

    /**
     * SHA384加密
     * @param data
     * @return
     */
    public static byte[] encodeToByte(InputStream data) {
        return instance.encodeToByte(data);
    }

    /**
     * SHA384加密
     * @param data
     * @return
     */
    public static String encode(InputStream data) {
        return instance.encode(data);
    }

}
