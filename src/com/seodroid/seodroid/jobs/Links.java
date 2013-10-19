package com.seodroid.seodroid.jobs;


import com.seodroid.seodroid.system.BaseHttp;
import com.seodroid.seodroid.system.IResult;

import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.util.Patterns;

public class Links extends BaseHttp
        implements IResult {


    private static final String REGEX_A_TAG =  "(<[\\s]*a([^>]*?)>)([\\s\\S]*?)(<[\\s*]?\\/[\\s*]?a[\\s]*?>)";
    private static final  String   REGEX_A_ATTRIBUTES = "(href+?)=['\\\"]([^\\\"]+?)['\\\"]";

    private Pattern aPattern =  Pattern.compile(REGEX_A_TAG, Pattern.CASE_INSENSITIVE);
    private Pattern attrPattern = Pattern.compile(REGEX_A_ATTRIBUTES, Pattern.CASE_INSENSITIVE);

    private int innerLinksCount = 0;
    private int outerLinksCount = 0;

    public String getGrabUrl(){
        return  getUri().toString();
    }

    public  String getName(){
        return "Page links (In/Out)";
    }

    public String getIconName(){
        return "ip.png";
    }

    public String getResult() throws  Exception{
        String content = link.getHttpBody();
        Matcher aMatcher = aPattern.matcher(content);
        String domain = link.getURI().getHost().toLowerCase();
        String[] skippedProtocols =  new String[]{ "afs:","cid:","file:", "ftp:", "mailto:",
                                                   "mid:", "news:", "x-exec:", "javascript:",
                                                   "magnet:", "irc:"};
        allAnchors: while (aMatcher.find()) {
            String aTagAttributes = aMatcher.group(2);
            Matcher attrMatcher = attrPattern.matcher(aTagAttributes);
             if(attrMatcher.find()){
                //skip ?
                String href =  attrMatcher.group(2).toLowerCase().trim();
                for(String p : skippedProtocols){
                    if(href.startsWith(p)){
                        continue allAnchors;
                    }
                }
                //out link?
                if(Patterns.WEB_URL.matcher(href).matches()){
                    if (new URI(href).getHost().toLowerCase().equals(domain)){
                        innerLinksCount++;
                    }else{
                        outerLinksCount++;
                    }
                }else{
                    innerLinksCount++;
                }


            }else{
               String  href = "";
                 //debug me
             }


        }
        return String.valueOf(innerLinksCount) + "/" + String.valueOf(outerLinksCount);

    }




}
