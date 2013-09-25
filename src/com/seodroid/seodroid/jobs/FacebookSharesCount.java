package com.seodroid.seodroid.jobs;

import com.seodroid.seodroid.system.BaseJson;
import com.seodroid.seodroid.system.IResult;


public class FacebookSharesCount  extends BaseJson
        implements IResult {

    private static final String apiUrl =  "http://graph.facebook.com/?id=";
    private static final String jsonKey = "shares";

    @Override
    public String getResult() throws Exception {
        return  getJsonElementFromUri(getGrabUrl(), jsonKey );

    }

    public String getGrabUrl(){
        return apiUrl +  getUri().toString();
    }

    public String getJsonKey(){
        return jsonKey;
    }

    public String getIconName(){
        return "facebook.png";
    }
    @Override
    public String getName() {
       return  "Facebook Shares";
    }
}
