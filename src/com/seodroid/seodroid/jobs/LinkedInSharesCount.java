package com.seodroid.seodroid.jobs;

import com.seodroid.seodroid.system.BaseJson;
import com.seodroid.seodroid.system.IResult;


public class LinkedInSharesCount extends BaseJson
        implements IResult {

    private static final String apiUrl =  "http://www.linkedin.com/countserv/count/share?format=json&url=";

    public String getGrabUrl(){
        return apiUrl + getUri().toString();
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
