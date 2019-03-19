package com.rss.rssreader.pojo;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

@Root(name = "image", strict = false)
public class RssImage implements Serializable {
    @Element (name = "url")
    private String url;

    public RssImage(String url) {
        this.url = url;
    }

    public RssImage() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
