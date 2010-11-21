package com.camerondevelope.facebook.graphapi;

import com.camerondevelope.facebook.FacebookLoginCookie;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by IntelliJ IDEA.
 * User: loyd
 * Date: Nov 20, 2010
 * Time: 1:41:10 AM
 * To change this template use File | Settings | File Templates.
 */
public class ReadRest {

    //https://graph.facebook.com/ID/CONNECTION_TYPE
    private static final String FACEBOOK_URL = "https://graph.facebook.com/%s";
    private static final String FACEBOOK_URL_ACCESS = FACEBOOK_URL+"/%s?access_token=%s";

    /**
     * Singleton pattern by Bill Pugh
     * http://en.wikipedia.org/wiki/Singleton_pattern#The_solution_of_Bill_Pugh
     */
    private ReadRest() {}
    private static class SingletonHolder {
        public static final ReadRest INSTANCE = new ReadRest();
    }
    public static ReadRest getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private JSONObject read(URL url) {
        StringBuilder sb = null;

        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();

        } catch (IOException e) {
            // TODO:Logging
        }

        if (sb == null) {
            return null;
        }

        try {
            return new JSONObject(sb.toString());
        } catch (JSONException e) {
            // TODO: Logging
        }

        return null;
    }

    public JSONObject read(String id) {
        try {
            URL url = new URL(String.format(FACEBOOK_URL, id));
            return read(url);
        } catch (MalformedURLException e) {
            //TODO:LogERror
            return null;
        }
    }

    public JSONObject read(FacebookLoginCookie facebookLoginCookie, String id, FBGraphType type) {
        if (!facebookLoginCookie.isValid()) {
            return null;
        }
        
        try {
            URL url = new URL(String.format(FACEBOOK_URL_ACCESS, id, type.toString(), facebookLoginCookie.getAccessToken()));
            return read(url);
        } catch (MalformedURLException e) {
            //TODO:LogERror
            return null;
        }
    }


}
