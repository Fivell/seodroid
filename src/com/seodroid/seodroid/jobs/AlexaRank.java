
package com.seodroid.seodroid.jobs;

import com.seodroid.seodroid.system.Base;
import com.seodroid.seodroid.system.IResult;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class AlexaRank
        extends Base
        implements IResult {

    public String getName() {
        return "Alexa Rank";
    }

    public String getIconName() {
        return "alexa.png";
    }

    public String getResult() throws Exception {

        return getRawResult();

    }

    public  String getBrowserUrl(){
        return  "http://www.alexa.com/siteinfo/"   + getUri().getHost();
    }

    public  String getGrabUrl(){
       return  "http://data.alexa.com/data?cli=10&url=" + getUri().getHost();
    }

    private String getRawResult() throws Exception {
        int result = 0;
        URLConnection conn = new URL(getGrabUrl()).openConnection();
        InputStream is = conn.getInputStream();
        DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = dBuilder.parse(is);
        Element element = doc.getDocumentElement();
        NodeList nodeList = element.getElementsByTagName("POPULARITY");
        if (nodeList.getLength() > 0) {
            Element elementAttribute = (Element) nodeList.item(0);
            String ranking = elementAttribute.getAttribute("TEXT");
            if (!"".equals(ranking)) {
                result = Integer.valueOf(ranking);
            }
        }
        return String.valueOf(result);
    }

}