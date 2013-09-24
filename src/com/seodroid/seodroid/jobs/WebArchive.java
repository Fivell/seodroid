package com.seodroid.seodroid.jobs;
import com.seodroid.seodroid.system.BaseHttp;
import com.seodroid.seodroid.system.IResult;

import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class WebArchive extends BaseHttp
        implements IResult {


    private static final String REGEX =  "going all the way back to <a.*?>(.*?)</a>";


    public  String getGrabUrl(){
       return  "http://web.archive.org/web/*/"+  getUri().getHost();
    }

    public String getIconName() {
        return "web-archive.png";
    }

    public String getName() {
        return "Web Archive";
    }
    public String getResult() throws Exception {

        String htmlDocument =     getRequest(getGrabUrl());
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(htmlDocument);

        return matcher.find() ? matcher.group(1) : "n/a";
    }

}

