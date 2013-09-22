package com.seoman.seoman.system;

import org.json.JSONObject;

public abstract  class BaseJson extends  BaseHttp {

    public  abstract String getJsonKey();
    public  abstract String getUrl();

    public String getResult() throws Exception {
        return  getJsonElementFromUri(getUrl() + uri.toString(), getJsonKey() );

    }
    protected String getJsonElementFromUri(String uri, String key) throws Exception{
        try{
            return new JSONObject(getRequest(uri)).getString(key);
        }catch (org.json.JSONException e){
            return "0";
        }
    }

}
