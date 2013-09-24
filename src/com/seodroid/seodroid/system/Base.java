package com.seodroid.seodroid.system;


import java.net.URI;

public abstract class Base implements
        IResult{

    protected URI uri;

    public String getBrowserUrl(){
        return  getGrabUrl();
    }

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }

}
