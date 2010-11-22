package com.camerondevelope.ui.action.include;

/**
 * Created by IntelliJ IDEA.
 * User: loyd
 * Date: Nov 21, 2010
 * Time: 3:25:44 PM
 * To change this template use File | Settings | File Templates.
 */
abstract public class RequireFBLogingAction extends AcceptFBLoginAction {

    public static final String NOT_LOGGED_IN = "notloggedin";

    @Override
    public String execute() {
        if (!isFBLoggedIn()) {
            return NOT_LOGGED_IN;
        }
        return executeAfter();
    }

    abstract protected String executeAfter();
}
