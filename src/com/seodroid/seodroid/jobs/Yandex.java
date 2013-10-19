package com.seodroid.seodroid.jobs;


import com.seodroid.seodroid.system.BaseHttp;
import com.seodroid.seodroid.system.IResult;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Yandex extends BaseHttp
        implements IResult {


    private static  final String REGEX = "<tcy rang=\"(\\d*)\" value=\"(\\d*)\"";


    public String getGrabUrl(){
        return "http://bar-navig.yandex.ru/u?show=1&url="+link.getURI().toString();
    }

    public String getIconName(){
        return   "ip.png";
    }

    public  String getResult() throws  Exception{
        String content = contentFromGetRequest(getGrabUrl());
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(content);
        String result = "n/a";
        if (matcher.find()){
            result =  matcher.group(2).replaceAll( "[^\\d]", "" );
        }
        return  result;

    }

    public String getName(){
        return "Yandex CY";
    }


}
