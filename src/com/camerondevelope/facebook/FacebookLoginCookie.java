package com.camerondevelope.facebook;

import com.camerondevelope.common.Md5;
import com.camerondevelope.common.StringUtil;
import com.camerondevelope.config.Configuration;
import com.google.appengine.repackaged.com.google.common.collect.Maps;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: loyd
 * Date: Nov 20, 2010
 * Time: 12:48:01 AM
 * To change this template use File | Settings | File Templates.
 */
public class FacebookLoginCookie {

    private static final Logger log = Logger.getLogger(FacebookLoginCookie.class.getName());

    private final String accessToken;
    private final String secret;
    private final String sessionKey;
    private final String sig;
    private final String uid;

    private final Map<String, String> fbsMap;

    public FacebookLoginCookie(Map<String, String> cookie) {

        final String applicationId = Configuration.getInstance().getApplicationId();
        final String applicationSecret = Configuration.getInstance().getApplicationSecret();

        String payload = "";

        final String fbs = cookie.get("fbs_"+applicationId);

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
                    try {
                        String key = keyValStrings[0];
                        String val = URLDecoder.decode(keyValStrings[1], "UTF-8");
                        if (!String.valueOf(key).equals("sig")) {
                            payload = payload + key + '=' + val;
                        }
                        inMap.put(key, val);
                    } catch (UnsupportedEncodingException e) {
                        log.severe("Cannot decode url utf8");
                    }
                }
            }

            if (payloadGood(payload+applicationSecret, inMap.get("sig"))) {
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

    /**
     * Computed based on algo from http://developers.facebook.com/docs/guides/web#login
     * @param payload
     * @param sig
     * @return
     */
    private boolean payloadGood(final String payload, final String sig) {
        if (StringUtil.isEmpty(payload) || StringUtil.isEmpty(sig)) {
            return false;
        }
        return sig.equals(Md5.compute(payload));
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
