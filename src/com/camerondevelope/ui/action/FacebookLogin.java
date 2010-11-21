package com.camerondevelope.ui.action;

import com.camerondevelope.common.StringUtil;
import com.camerondevelope.facebook.FacebookLoginCookie;
import com.camerondevelope.facebook.graphapi.FBGraphType;
import com.camerondevelope.facebook.graphapi.ReadRest;
import com.google.appengine.repackaged.com.google.common.collect.Lists;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.CookiesAware;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: loyd
 * Date: Nov 19, 2010
 * Time: 9:29:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class FacebookLogin extends ActionSupport implements CookiesAware {
    private String fullname;
    private String friends;
    private FacebookLoginCookie facebookLoginCookie;

    public FacebookLogin() {}

    public String execute() {
        setFullname(getFBFullname());
        setFriends(getFBFriends());
        return "success";
    }

    private String getFBFullname() {
        if (!validateFBLogin()) {
            return "";
        }
        try {
            JSONObject profile = ReadRest.getInstance().read(facebookLoginCookie.getUid());
            return profile.getString("name");
        } catch (Exception e) {
            // TODO: Logging
            return "";
        }
    }

    private String getFBFriends() {
        if (!validateFBLogin()) {
            return "";
        }
        try {
            // TODO: All this Json nonsense into a facebook thing
            JSONObject friends = ReadRest.getInstance().read(facebookLoginCookie, facebookLoginCookie.getUid(), FBGraphType.FRIENDS);
            JSONArray friendArray = friends.getJSONArray("data");
            List<String> friendsStrings = Lists.newLinkedList();
            int len = friendArray.length();
            for (int i = 0; i < len; i++) {
                JSONObject jsonObject = friendArray.getJSONObject(i);
                friendsStrings.add(jsonObject.getString("name"));
            }
            return StringUtil.implode(",", friendsStrings);
        } catch (Exception e) {
            // TODO: Logging
            return "";
        }
    }

    private boolean validateFBLogin() {
        return facebookLoginCookie.isValid();
    }

    @Override
    public void setCookiesMap(Map<String, String> cookie) {
        facebookLoginCookie = new FacebookLoginCookie(cookie);        
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
        return FacebookLoginCookie.APPLICATION_ID;
    }
}
