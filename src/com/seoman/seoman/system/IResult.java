package com.seoman.seoman.system;


import java.net.URI;

public interface IResult {
   public String getResult() throws Exception;
   public void setUri(URI iri);
   public String getName();
   public String getIconName();
}
