package com.seodroid.seodroid.jobs;

import com.seodroid.seodroid.system.Base;
import com.seodroid.seodroid.system.IResult;

import java.net.InetAddress;


public class Ip
        extends Base
        implements IResult {


    public String getGrabUrl(){
        return  "http://ping.nmonitoring.com/?ip="+getUri().getHost()+"&pingsub=1&ln=en";
    }

    public String getName() {
        return "IP";
    }

    public String getIconName(){
        return "ip.png";
    }

    public String getResult() throws Exception {
        return InetAddress.getByName(uri.getHost()).getHostAddress();
    }
}
