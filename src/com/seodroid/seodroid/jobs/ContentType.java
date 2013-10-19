package com.seodroid.seodroid.jobs;

import com.seodroid.seodroid.system.Base;
import com.seodroid.seodroid.system.IResult;


public class ContentType
    extends Base
    implements IResult

    {

    public String getGrabUrl(){
      return link.getURI().toString();
    }

    public String getName() {
        return "Content Type";
    }

    public String getIconName(){
        return "ip.png";
    }

    public String getResult() throws Exception {
        return   String.valueOf(link.getHttpResponse().getEntity().getContentType().getValue());
    }
}
