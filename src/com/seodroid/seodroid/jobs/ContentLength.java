package com.seodroid.seodroid.jobs;

import com.seodroid.seodroid.Utils;
import com.seodroid.seodroid.system.Base;
import com.seodroid.seodroid.system.IResult;

import java.io.ByteArrayOutputStream;



public class ContentLength extends Base
        implements IResult

{


    public String getName(){
        return "Content length";
    }

    public String getGrabUrl(){
        return link.getURI().toString();
    }

    public String getIconName(){
        return "ip.png";
    }


    public String getResult() throws Exception{

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        link.getHttpResponse().getEntity().writeTo(baos);
        return Utils.readableFileSize(baos.toByteArray().length);

    }


}
