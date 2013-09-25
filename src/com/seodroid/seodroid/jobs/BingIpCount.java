package com.seodroid.seodroid.jobs;

import java.net.UnknownHostException;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 9/24/13
 * Time: 10:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class BingIpCount extends BingIndex {



    public String getName() {
        return "Sites sharing the same IP";
    }

    public  String getGrabUrl(){
        try{
            return  "http://www.bing.com/search?q=ip:"+  getIP();
        } catch (UnknownHostException e){
            return null;
        }

    }
}
