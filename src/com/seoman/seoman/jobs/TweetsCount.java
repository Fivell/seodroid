package com.seoman.seoman.jobs;



import com.seoman.seoman.system.BaseJson;
import com.seoman.seoman.system.IResult;


public class TweetsCount extends BaseJson
        implements IResult {

    private static final String apiUrl =  "https://cdn.api.twitter.com/1/urls/count.json?url=";
    private static final String jsonKey = "count";

    public String getUrl(){
       return apiUrl;
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
