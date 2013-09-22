package com.seoman.seoman.system;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;

import org.apache.http.conn.ssl.SSLSocketFactory;
//import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;


public abstract class BaseHttp extends  Base {

    public  abstract String getUrl();

    protected String getRequest(String uri) throws Exception{


        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(uri);
        HttpResponse httpResponse = httpClient.execute(httpGet);

        HttpEntity httpEntity = httpResponse.getEntity();

        return inputStreamToString(httpEntity.getContent());

    }


//    public static DefaultHttpClient getHttpClient() {
//        try {
//            SSLSocketFactory sf = new SSLSocketFactory(new TrustStrategy(){
//                @Override
//                public boolean isTrusted(X509Certificate[] chain,
//                                         String authType) throws CertificateException {
//                    return true;
//                }
//            }, new AllowAllHostnameVerifier());
//
//            SchemeRegistry registry = new SchemeRegistry();
//            registry.register(new Scheme("https",8444, sf));
//            ClientConnectionManager ccm = new ThreadSafeClientConnManager(registry);
//            return new DefaultHttpClient(ccm);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new DefaultHttpClient();
//        }
//    }

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
