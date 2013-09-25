package com.seodroid.seodroid.system;

import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;

public class Link {

    private URI uri;
    private String ip;

    private final Object lock = new Object();

    public Link(String url) {
        this.uri = java.net.URI.create(url);

    }

    public java.net.URI getURI() {
        return this.uri;
    }

    public String getIP() throws UnknownHostException {
        synchronized(lock) {
            if (this.ip == null) {
                this.ip = InetAddress.getByName(this.uri.getHost()).getHostAddress();
            }
        }
        return this.ip;

    }

}
