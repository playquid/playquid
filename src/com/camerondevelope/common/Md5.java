package com.camerondevelope.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by IntelliJ IDEA.
 * User: loyd
 * Date: Nov 20, 2010
 * Time: 1:19:35 AM
 * To change this template use File | Settings | File Templates.
 */
public class Md5 {

    public static String compute(final String md5) {
        try {

            byte[] bytesOfMessage = md5.getBytes("UTF-8");

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] thedigest = md.digest(bytesOfMessage);

            return StringUtil.toHex(thedigest);

        } catch (NoSuchAlgorithmException e) {
            // TODO: Add logging
            return "NOSUCHALG";
        } catch (UnsupportedEncodingException e) {
            // TODO: Add logging
            return "UNSUPPORTENC";
        }
    }
}
