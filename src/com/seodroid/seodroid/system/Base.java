package com.seodroid.seodroid.system;


import java.net.URI;
import java.net.UnknownHostException;

public abstract class Base implements
        IResult{

    protected Link link;

    public void setLink(Link link){
        this.link = link;
    }

    public String getBrowserUrl(){
        return  getGrabUrl();
    }

    public String getIP() throws UnknownHostException{
       return link.getIP();
    }

    public URI getUri() {
        return link.getURI();
    }



}
