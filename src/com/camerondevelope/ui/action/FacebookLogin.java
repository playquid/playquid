package com.camerondevelope.ui.action;

import com.camerondevelope.common.StringUtil;
import com.camerondevelope.config.Configuration;
import com.camerondevelope.facebook.FacebookLoginCookie;
import com.google.appengine.repackaged.com.google.common.collect.Lists;
import com.opensymphony.xwork2.ActionSupport;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;
import org.apache.struts2.interceptor.CookiesAware;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: loyd
 * Date: Nov 19, 2010
 * Time: 9:29:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class FacebookLogin extends ActionSupport implements CookiesAware {

    private static final Logger log = Logger.getLogger(FacebookLogin.class.getName());

    private String fullname;
    private String friends;
    private FacebookLoginCookie facebookLoginCookie;
    private FacebookClient facebookClient;

    public FacebookLogin() {}

    public String execute() {
        setFullname(getFBFullname());
        setFriends(getFBFriends());
        return "success";
    }

    private String getFBFullname() {
        try {
            User user = facebookClient.fetchObject(facebookLoginCookie.getUid(), User.class);
            return user.getName();

        } catch (Exception e) {
            log.severe("Unable to read user information with fblc="+facebookLoginCookie);
            return "";
        }
    }

    private String getFBFriends() {
        try {
            Connection<User> connectionFriends = facebookClient.fetchConnection(facebookLoginCookie.getUid()+"/friends", User.class);

            List<String> names = Lists.newLinkedList();
            for (User friend : connectionFriends.getData()) {
                names.add(friend.getName());
            }
            return StringUtil.implode(",", names);

        } catch (Exception e) {
            log.severe("Unable to read user information with fblc="+facebookLoginCookie);
            return "";
        }
    }

    private boolean validateFBLogin() {
        return facebookLoginCookie.isValid();
    }

    @Override
    public void setCookiesMap(Map<String, String> cookie) {
        facebookLoginCookie = new FacebookLoginCookie(cookie);
        if (facebookLoginCookie.isValid()) {
            facebookClient = new DefaultFacebookClient(facebookLoginCookie.getAccessToken());
        }
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getFriends() {
        return friends;
    }

    public void setFriends(String friends) {
        this.friends = friends;
    }

    public String getFbAppId() {
        return Configuration.getInstance().getApplicationId();
    }
}
