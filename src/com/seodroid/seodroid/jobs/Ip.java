package com.seodroid.seodroid.jobs;

import com.seodroid.seodroid.system.Base;
import com.seodroid.seodroid.system.IResult;

import java.net.InetAddress;
import java.net.URI;


public class Ip
        extends Base
        implements IResult {



    public String getGrabUrl(){
        try {
            return  "http://geoip.flagfox.net/?ip="+ getResult();
        }catch (Exception e){
            return null;
        }
    }



    public String getName() {
        return "IP";
    }

    public String getIconName(){
        return "ip.png";
    }

    public String getResult() throws Exception {
        return  link.getIP();
    }
}
