package com.camerondevelope.facebook;

import com.camerondevelope.common.StringUtil;
import com.google.appengine.repackaged.com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: loyd
 * Date: Nov 20, 2010
 * Time: 12:48:01 AM
 * To change this template use File | Settings | File Templates.
 */
public class FacebookLoginCookie {

    // TODO: Add to config
    //*DEV:*/ public static final String APPLICATION_ID = "166675046698226";
    //*DEV:*/ public static final String APPLICATION_SECRET = "62b5f154211b517cc62f9d24ae1d9945";
    /*PROD:*/ public static final String APPLICATION_ID = "133294296724238";
    /*PROD:*/ public static final String APPLICATION_SECRET = "df444fffb20ce2376778322e7539094b";

    private final String accessToken;
    private final String secret;
    private final String sessionKey;
    private final String sig;
    private final String uid;

    private final Map<String, String> fbsMap;

    public FacebookLoginCookie(Map<String, String> cookie) {

        String payload = "";

        final String fbs = cookie.get("fbs_"+APPLICATION_ID);

        if (fbs == null) {
            accessToken = null;
            secret = null;
            sessionKey = null;
            sig = null;
            uid = null;
            fbsMap = null;
        } else {

            // In map is a hashmap inside this function, the final map is only
            // set once we are confident that the payload is good
            Map<String, String> inMap = Maps.newHashMap();

            for (final String keyVal : fbs.split("&")) {
                String[] keyValStrings = keyVal.split("=", 2);
                if (keyValStrings.length == 2) {
                    if (!String.valueOf(keyValStrings[0]).equals("sig")) {
                        payload += keyVal;
                    }
                    inMap.put(keyValStrings[0], keyValStrings[1]);
                }
            }

            if (payloadGood(payload+APPLICATION_SECRET, inMap.get("sig"))) {
                fbsMap = inMap;
            } else {
                fbsMap = Maps.newHashMap();
            }
            accessToken = fbsMap.get("access_token");
            secret = fbsMap.get("secret");
            sessionKey = fbsMap.get("session_key");
            sig = fbsMap.get("sig");
            uid = fbsMap.get("uid");
        }
    }

    private boolean payloadGood(final String payload, final String sig) {
        if (StringUtil.isEmpty(payload) || StringUtil.isEmpty(sig)) {
            return false;
        }
        //return sig.equals(Md5.compute(payload));
        return true;//TODO:Figure out what is going on here
    }
    
    public String getAccessToken() {
        return accessToken;
    }

    public String getSecret() {
        return secret;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public String getSig() {
        return sig;
    }

    public String getUid() {
        return uid;
    }

    public Map<String, String> getFbsMap() {
        return fbsMap;
    }

    @Override
    public String toString() {
        return "FacebookLoginCookie{" +
                "uid='" + uid + '\'' +
                ", sig='" + sig + '\'' +
                ", sessionKey='" + sessionKey + '\'' +
                ", secret='" + secret + '\'' +
                ", accessToken='" + accessToken + '\'' +
                '}';
    }

    public boolean isValid() {
        return fbsMap != null;
    }
}
