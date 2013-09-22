package com.seoman.seoman.ui;


public class ResultItem {

    public String name;
    public String value;
    public String resourceFilePath;


    public ResultItem(String name, String value, String resourceFilePath)
    {
        this.name = name;
        this.value = value;

        this.resourceFilePath = resourceFilePath;
    }

    @Override
    public String toString()
    {
        return this.name;
    }
}
