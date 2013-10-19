package com.seodroid.seodroid.system;


import com.seodroid.seodroid.Utils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;

import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;



public abstract class BaseHttp extends  Base {

    public  abstract String getGrabUrl();

    protected String contentFromGetRequest(String uri) throws Exception{


        DefaultHttpClient httpClient = new EasyHttpClient();

        HttpGet httpGet = new HttpGet(uri);
        HttpResponse httpResponse = httpClient.execute(httpGet);

        HttpEntity httpEntity = httpResponse.getEntity();

        return Utils.toString(httpEntity.getContent());

    }




}
