package com.camerondevelope.facebook;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;

/**
 * Created by IntelliJ IDEA.
 * User: loyd
 * Date: Nov 21, 2010
 * Time: 1:35:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class Facebook {
    private FacebookClient facebookClient;

    public Facebook(FacebookLoginCookie facebookLoginCookie) {
        if (facebookLoginCookie.isValid()) {
            facebookClient = new DefaultFacebookClient(facebookLoginCookie.getAccessToken());
        }
    }

    public FacebookClient getClient() {
        return facebookClient;
    }

}
