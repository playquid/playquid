package com.camerondevelope.ui.action;

import com.camerondevelope.ui.action.include.AcceptFBLoginAction;

import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: loyd
 * Date: Nov 19, 2010
 * Time: 9:29:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class HomeAction extends AcceptFBLoginAction {

    private static final Logger log = Logger.getLogger(HomeAction.class.getName());

    public HomeAction() {}

    public String execute() {
        addJSUrl("https://connect.facebook.net/en_US/all.js", true);
        addJSFile("facebook");
        return SUCCESS;
    }

}
