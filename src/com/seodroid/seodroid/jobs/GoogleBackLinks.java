package com.seodroid.seodroid.jobs;


public class GoogleBackLinks  extends  GoogleIndex{

    public  String getGrabUrl(){
        return  "http://www.google.com.et/search?hl=en&q=link:"+getUri().toString();
    }

    public String getName() {
        return "Google Back links";
    }

}
