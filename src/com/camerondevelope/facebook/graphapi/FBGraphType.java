package com.camerondevelope.facebook.graphapi;

/**
 * Created by IntelliJ IDEA.
 * User: loyd
 * Date: Nov 20, 2010
 * Time: 2:18:00 AM
 * To change this template use File | Settings | File Templates.
 */
public enum FBGraphType {
    FRIENDS("friends");

    private String s;
    FBGraphType(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return s;
    }
}
