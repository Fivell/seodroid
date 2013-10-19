package com.seodroid.seodroid.jobs;

import com.seodroid.seodroid.system.BaseHttp;
import com.seodroid.seodroid.system.IResult;

import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dmoz extends BaseHttp
        implements IResult {

    //https://www.google.com/search?q=site:ru.ru
    private static final String REGEX =  "<li class=\"\">\\s+<a href=\"([^>]+)\"";

    public String getIconName(){
        return  "dmoz.png";
    }

    public String getGrabUrl(){
        return "http://www.dmoz.org/search/?q="+ getUri().getHost();
    }

    public String getName(){
         return  "DMOZ";
     }

     public String getResult() throws Exception{

         String htmlDocument = contentFromGetRequest(getGrabUrl());
         Pattern pattern = Pattern.compile(REGEX);
         Matcher matcher = pattern.matcher(htmlDocument);
         String host = getUri().getHost();
         String dmozHost;
         while(matcher.find()){
             try {
                dmozHost = URI.create(matcher.group(1)).getHost();

             }catch (Exception e){
                 continue;
             }

             if (host.equals(dmozHost)){
                 return "Yes";
             }
         }
         return  "No";

     }

}
