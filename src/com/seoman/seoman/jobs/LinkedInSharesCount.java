package com.seoman.seoman.jobs;

import com.seoman.seoman.system.BaseJson;
import com.seoman.seoman.system.IResult;


public class LinkedInSharesCount extends BaseJson
        implements IResult {

    private static final String apiUrl =  "http://www.linkedin.com/countserv/count/share?format=json&url=";

    public String getUrl(){
        return apiUrl;
    }

    @Override
    public String getJsonKey() {
        return "count";
    }

    public String getIconName(){
        return "linkedin.png";
    }

    @Override
    public String getName() {
        return  "LinkedIn Shares";
    }
}
