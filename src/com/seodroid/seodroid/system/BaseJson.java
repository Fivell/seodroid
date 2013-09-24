package com.seodroid.seodroid.system;

import org.json.JSONObject;

public abstract  class BaseJson extends  BaseHttp {

    public  abstract String getJsonKey();

    public String getResult() throws Exception {
        return  getJsonElementFromUri(getGrabUrl() , getJsonKey() );

    }
    protected String getJsonElementFromUri(String uri, String key) throws Exception{
        try{
            return new JSONObject(getRequest(uri)).getString(key);
        }catch (org.json.JSONException e){
            return "0";
        }
    }

}
