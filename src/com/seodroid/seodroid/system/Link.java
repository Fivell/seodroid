package com.seodroid.seodroid.system;

import com.seodroid.seodroid.Utils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;



public class Link {

    private URI uri;
    private String ip;
    private HttpResponse httpResponse;

    private final Object ipLock = new Object();
    private final Object httpLock = new Object();

    public Link(String url) {
        this.uri = java.net.URI.create(url);

    }

    public java.net.URI getURI() {
        return this.uri;
    }

    public String  getHttpBody() throws Exception{
         return Utils.toString(getHttpResponse().getEntity().getContent());
    }



    public HttpResponse getHttpResponse() throws Exception {
        if (httpResponse == null) {
            synchronized (httpLock) {
                if (httpResponse == null) {
                    DefaultHttpClient httpClient = new EasyHttpClient();

                    HttpGet httpGet = new HttpGet(uri);
                    httpResponse = httpClient.execute(httpGet);
                }
            }
        }

        return httpResponse;


    }

    public String getIP() throws UnknownHostException {
        if (this.ip == null) {
            synchronized (ipLock) {
                if (this.ip == null) {
                    this.ip = InetAddress.getByName(this.uri.getHost()).getHostAddress();
                }
            }
        }
        return this.ip;

    }

}
