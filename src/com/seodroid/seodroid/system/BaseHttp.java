package com.seodroid.seodroid.system;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;

import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;



public abstract class BaseHttp extends  Base {

    public  abstract String getGrabUrl();

    protected String getRequest(String uri) throws Exception{


        DefaultHttpClient httpClient = new EasyHttpClient();

        HttpGet httpGet = new HttpGet(uri);
        HttpResponse httpResponse = httpClient.execute(httpGet);

        HttpEntity httpEntity = httpResponse.getEntity();

        return inputStreamToString(httpEntity.getContent());

    }



    protected String inputStreamToString(InputStream is) throws Exception{
        String line = "";
        StringBuilder total = new StringBuilder();

        // Wrap a BufferedReader around the InputStream
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));

        // Read response until the end
        while ((line = rd.readLine()) != null) {
            total.append(line);
        }

        // Return full string
        return total.toString();
    }
}
