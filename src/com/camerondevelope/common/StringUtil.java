package com.camerondevelope.common;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: loyd
 * Date: Nov 20, 2010
 * Time: 1:17:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class StringUtil {
    
    public static boolean isEmpty(final String string) {
        if (string == null || string.length() == 0) {
            return true;
        }
        return false;
    }

    public static String toHex(byte[] bytes) {
        BigInteger bi = new BigInteger(1, bytes);
        return String.format("%0" + (bytes.length << 1) + "x", bi);
    }

    public static String implode(String delim, List<String> strings) {

        if (strings == null || strings.size() <= 0) {
            return "";
        }

        boolean start = true;
        StringBuilder sb = new StringBuilder();
        for (String str : strings) {
            if (start) {
                start = false;
            } else {
                sb.append(delim);
            }
            sb.append(str);
        }
        return sb.toString();
    }
}
