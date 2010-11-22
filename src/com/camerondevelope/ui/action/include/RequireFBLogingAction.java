package com.camerondevelope.ui.action.include;

import com.camerondevelope.ui.action.include.AcceptFBLoginAction;

/**
 * Created by IntelliJ IDEA.
 * User: loyd
 * Date: Nov 21, 2010
 * Time: 3:25:44 PM
 * To change this template use File | Settings | File Templates.
 */
abstract public class RequireFBLogingAction extends AcceptFBLoginAction {

    @Override
    public String execute() {
        if (!isFBLoggedIn()) {
            return "notloggedin";
        }
        return executeAfter();
    }

    abstract protected String executeAfter();
}
