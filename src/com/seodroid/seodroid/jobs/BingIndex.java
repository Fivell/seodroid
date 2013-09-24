package com.seodroid.seodroid.jobs;

import com.seodroid.seodroid.system.BaseHttp;
import com.seodroid.seodroid.system.IResult;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class BingIndex  extends BaseHttp
        implements IResult {

    //https://www.google.com/search?q=site:ru.ru
    private static final String REGEX =  "<span class=\"sb_count\" id=\"count\">(.*?)<";


    public  String getGrabUrl(){
        return  "http://www.bing.com/search?q=site:"+  getUri().getHost();
    }

    public String getIconName() {
        return "bing.png";
    }

    public String getName() {
        return "Bing Site Index";
    }
    public String getResult() throws Exception {

        String htmlDocument =     getRequest(getGrabUrl());
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(htmlDocument);
        String result = "n/a";
        if (matcher.find()){
            result =  matcher.group(1).replaceAll( "[^\\d]", "" );
        }
        return  result;

    }


}

