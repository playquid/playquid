package com.camerondevelope.common;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: loyd
 * Date: Nov 20, 2010
 * Time: 1:26:37 AM
 * To change this template use File | Settings | File Templates.
 */
public class Md5Test {
    @Test
    public void testCompute() throws Exception {
        assertEquals("4d25f99b3add9499661b45dc5d74b1a9", Md5.compute("testist"));
    }
}
