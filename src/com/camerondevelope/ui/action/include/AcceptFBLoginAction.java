package com.camerondevelope.ui.action.include;

import com.camerondevelope.config.Configuration;
import com.camerondevelope.facebook.FacebookLoginCookie;
import org.apache.struts2.interceptor.CookiesAware;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: loyd
 * Date: Nov 21, 2010
 * Time: 1:31:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class AcceptFBLoginAction extends AppAction implements CookiesAware {

    private FacebookLoginCookie facebookLoginCookie;

    @Override
    public void setCookiesMap(Map<String, String> cookie) {
        facebookLoginCookie = new FacebookLoginCookie(cookie);
    }    

    public boolean isFBLoggedIn() {
        return facebookLoginCookie.isValid();
    }

    public FacebookLoginCookie getFacebookLoginCookie() {
        return facebookLoginCookie;
    }

    public String getFBAppId() {
        return Configuration.getInstance().getApplicationId();
    }
}
