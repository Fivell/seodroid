package com.seodroid.seodroid;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 9/26/13
 * Time: 12:23 AM
 * To change this template use File | Settings | File Templates.
 */
public class Utils {


    public static String readableFileSize(long size) {
        if(size <= 0) return "0";
        final String[] units = new String[] { "B", "KB", "MB", "GB", "TB" };
        int digitGroups = (int) (Math.log10(size)/Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size/Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }

    public static String toString(InputStream is) throws Exception{
        String line = "";
        StringBuilder total = new StringBuilder();

        // Wrap a BufferedReader around the InputStream
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));

        // Read response until the end
        while ((line = rd.readLine()) != null) {
            total.append(line);
        }

        // Return full string
        return total.toString();
    }


}
