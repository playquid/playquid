package com.camerondevelope.common;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by IntelliJ IDEA.
 * User: loyd
 * Date: Nov 21, 2010
 * Time: 2:05:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class LogString {
    public static String e(String log, Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        return log+"\n"+sw.toString();
    }
}
