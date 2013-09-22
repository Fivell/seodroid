
package com.seoman.seoman.jobs;

import com.seoman.seoman.system.Base;
import com.seoman.seoman.system.IResult;

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

        return getRawResult(uri.getHost());

    }

    private String getRawResult(String domain) throws Exception {
        int result = 0;
        String url = "http://data.alexa.com/data?cli=10&url=" + domain;


        URLConnection conn = new URL(url).openConnection();
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


        return Integer.toString(result);
    }

}