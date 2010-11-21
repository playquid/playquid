package com.camerondevelope.callbacks;

import org.json.JSONObject;

/**
 * Created by IntelliJ IDEA.
 * User: loyd
 * Date: Nov 20, 2010
 * Time: 1:48:23 AM
 * To change this template use File | Settings | File Templates.
 */
interface SuccessFailureCallback {
    public void onSuccess(JSONObject jsonObject);
    public void onFailure();
}
