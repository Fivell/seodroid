package com.seoman.seoman.jobs;

import com.seoman.seoman.system.BaseJson;
import com.seoman.seoman.system.IResult;


public class FacebookSharesCount  extends BaseJson
        implements IResult {

    private static final String apiUrl =  "http://graph.facebook.com/?id=";
    private static final String jsonKey = "shares";

    @Override
    public String getResult() throws Exception {
        return  getJsonElementFromUri(apiUrl + uri.toString(), jsonKey );

    }

    public String getUrl(){
        return apiUrl;
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
