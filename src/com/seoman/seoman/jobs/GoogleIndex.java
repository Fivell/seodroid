package com.seoman.seoman.jobs;
import com.seoman.seoman.system.BaseHttp;
import com.seoman.seoman.system.IResult;

import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class GoogleIndex extends BaseHttp
        implements IResult {

    //https://www.google.com/search?q=site:ru.ru
    private static final String REGEX =  "<div id=\"resultStats\">(.*?)<";


    public  String getUrl(){
        return  "http://www.google.com/search?q=site:";
    }

    public String getIconName() {
        return "google.png";
    }

    public String getName() {
        return "Google Site Index";
    }
    public String getResult() throws Exception {

        String htmlDocument =     getRequest(getUrl() + getUri().getHost());
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(htmlDocument);
        String result = "n/a";
        if (matcher.find()){
           result =  matcher.group(1).replaceAll( "[^\\d]", "" );
        }
        return  result;

    }


}

