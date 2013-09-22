package com.seoman.seoman.jobs;

import com.seoman.seoman.system.BaseHttp;
import com.seoman.seoman.system.IResult;

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

    public String getUrl(){
        return "http://www.dmoz.org/search/?q=";
    }

    public String getName(){
         return  "DMOZ";
     }

     public String getResult() throws Exception{
         String host = getUri().getHost();
         String htmlDocument =     getRequest(getUrl() + host);
         Pattern pattern = Pattern.compile(REGEX);
         Matcher matcher = pattern.matcher(htmlDocument);

         String dmozHost;
         while(matcher.find()){
             try {
                dmozHost = URI.create(matcher.group(1)).getHost();

             }catch (Exception e){
                 continue;
             }

             if (host.equals(dmozHost)){
                 return "Y";
             }
         }
         return  "N";

     }

}
