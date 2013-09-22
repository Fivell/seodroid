package com.seoman.seoman.jobs;

import com.seoman.seoman.system.Base;
import com.seoman.seoman.system.IResult;

import java.net.InetAddress;


public class Ip
        extends Base
        implements IResult {

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
