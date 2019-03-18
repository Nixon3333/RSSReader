package com.rss.rssreader.pojo;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

@Root (name = "item", strict = false)
public class RssItem implements Serializable {

    @Element (name = "title")
    String title;
    @Element (name = "description")
    String description;
    @Element (name = "pubDate")
    String pubDate;

    public RssItem(String title, String description, String pubDate) {
        this.title = title;
        this.description = description;
        this.pubDate = pubDate;
    }

    public RssItem() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }
}
