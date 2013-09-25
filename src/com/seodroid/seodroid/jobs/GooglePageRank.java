package com.seodroid.seodroid.jobs;


import com.seodroid.seodroid.google.JenkinsHash;
import com.seodroid.seodroid.system.Base;
import com.seodroid.seodroid.system.IResult;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


public class GooglePageRank
        extends Base
        implements IResult {


    private JenkinsHash jenkinsHash;

    public String getIconName() {
        return "google-rank.png";
    }

    public GooglePageRank() {

        this.jenkinsHash = new JenkinsHash();
    }


    public String getResult() throws Exception {

        return getRawResult();

    }

    public String getGrabUrl(){

        String domain = getUri().getHost();
        long hash = jenkinsHash.hash(("info:" + domain).getBytes());

        //Append a 6 in front of the hashing value.
        String url = "http://toolbarqueries.google.com/tbr?client=navclient-auto&hl=en&"
                + "ch=6" + hash + "&ie=UTF-8&oe=UTF-8&features=Rank&q=info:" + domain;
        return  url;
    }

    public String getName() {
        return "Google PageRank";
    }

    private String getRawResult() throws Exception {


        String result = "0";


        URLConnection conn = new URL(getGrabUrl()).openConnection();

        BufferedReader br = new BufferedReader(new InputStreamReader(
                conn.getInputStream()));

        String input;
        while ((input = br.readLine()) != null) {
            // What GooglePageRank returned? Example : Rank_1:1:9, PR = 9
            result = input.substring(input.lastIndexOf(":") + 1);
        }


        return result;
    }


}

