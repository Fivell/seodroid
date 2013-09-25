package com.seodroid.seodroid.jobs;
import com.seodroid.seodroid.system.BaseHttp;
import com.seodroid.seodroid.system.IResult;

import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class GoogleIndex extends BaseHttp
        implements IResult {


    private static final String REGEX =  "<div id=\"resultStats\">(.*?)<";


    public  String getGrabUrl(){
        return  "https://www.google.com/search?q=site:"+getUri().getHost();
    }

    public String getName() {
        return "Google Site Index";
    }

    public String getIconName() {
        return "google.png";
    }


    public String getResult() throws Exception {

        String htmlDocument =     getRequest(getGrabUrl());
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(htmlDocument);
        String result = "n/a";
        if (matcher.find()){
           result =  matcher.group(1).replaceAll( "[^\\d]", "" );
           if (result == ""){
               result =  "0";
           }
        }
        return  result;

    }


}

