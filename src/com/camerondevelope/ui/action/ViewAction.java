package com.camerondevelope.ui.action;

import com.camerondevelope.common.LogString;
import com.camerondevelope.common.StringUtil;
import com.camerondevelope.facebook.Facebook;
import com.camerondevelope.ui.action.include.RequireFBLogingAction;
import com.google.appengine.repackaged.com.google.common.collect.Lists;
import com.restfb.Connection;
import com.restfb.types.User;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: loyd
 * Date: Nov 21, 2010
 * Time: 3:26:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class ViewAction extends RequireFBLogingAction {

    private static final Logger log = Logger.getLogger(ViewAction.class.getName());

    private String fullname;
    private String friends;

    public String executeAfter() {
        setFullname(getFBFullname());
        setFriends(getFBFriends());
        return SUCCESS;
    }

    private String getFBFullname() {
        try {
            Facebook facebook = new Facebook(getFacebookLoginCookie());
            User user = facebook.getClient().fetchObject(getFacebookLoginCookie().getUid(), User.class);
            return user.getName();

        } catch (Exception e) {
            log.severe(LogString.e("Unable to read user information with fblc="+getFacebookLoginCookie(), e));
            return "";
        }
    }

    private String getFBFriends() {
        try {
            Facebook facebook = new Facebook(getFacebookLoginCookie());
            Connection<User> connectionFriends = facebook.getClient().fetchConnection(getFacebookLoginCookie().getUid()+"/friends", User.class);

            List<String> names = Lists.newLinkedList();
            for (User friend : connectionFriends.getData()) {
                names.add(friend.getName());
            }
            return StringUtil.implode(",", names);

        } catch (Exception e) {
            log.severe(LogString.e("Unable to read user information with fblc="+getFacebookLoginCookie(), e));
            return "";
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
}
