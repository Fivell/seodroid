package com.seodroid.seodroid.ui;


public class ResultItem {

    public String name;
    public String value;
    public String resourceFilePath;
    public String url;


    public ResultItem(String name, String value, String resourceFilePath, String url)
    {
        this.name = name;
        this.value = value;
        this.url= url;
        this.resourceFilePath = resourceFilePath;
    }

    public  String getUrl(){
        return this.url;
    }
    @Override
    public String toString()
    {
        return this.name;
    }
}
