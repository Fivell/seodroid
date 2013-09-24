package com.seodroid.seodroid.jobs;



import com.seodroid.seodroid.system.BaseJson;
import com.seodroid.seodroid.system.IResult;


public class TweetsCount extends BaseJson
        implements IResult {

    private static final String apiUrl =  "http://cdn.api.twitter.com/1/urls/count.json?url=";
    private static final String jsonKey = "count";

    public String getGrabUrl(){
       return apiUrl + getUri().toString();
    }


    public String getIconName(){
        return "twitter.png";
    }
    @Override
    public String getJsonKey(){
        return jsonKey;
    }

    @Override
    public String getName() {
        return "Tweets count";
    }




}
