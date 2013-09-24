package com.seodroid.seodroid.system;


import java.net.URI;

public interface IResult {
   public String getResult() throws Exception;
   public void setUri(URI iri);
   public String getName();
   public String getIconName();
   public String getGrabUrl();
   public String getBrowserUrl();
}
