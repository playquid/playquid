package com.camerondevelope.common;

import com.google.appengine.repackaged.com.google.common.collect.Lists;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: loyd
 * Date: Nov 20, 2010
 * Time: 1:23:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class StringUtilTest {
    @Test
    public void testIsEmpty() throws Exception {
        assertTrue(StringUtil.isEmpty(""));
        assertTrue(StringUtil.isEmpty(null));
        assertFalse(StringUtil.isEmpty("FALSE"));
    }

    @Test
    public void testImplode() {
        assertEquals("1,2,3", StringUtil.implode(",", Lists.newLinkedList("1",  "2", "3")));
        assertEquals("1", StringUtil.implode(",", Lists.newLinkedList("1")));
    }
}
