package com.rss.rssreader.pojo;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.List;

@Root (name = "channel", strict = false)
public class Channel implements Serializable{

    @Element (name = "title")
    String title;
    @Element (name = "description")
    String description;
    @ElementList (name = "item", inline = true)
    List<RssItem> items;
    @Element
    RssImage image;

    public Channel(String title, String description, List<RssItem> items, RssImage image) {
        this.title = title;
        this.description = description;
        this.items = items;
        this.image = image;
    }

    public RssImage getImage() {
        return image;
    }

    public void setImage(RssImage image) {
        this.image = image;
    }

    public List<RssItem> getItems() {
        return items;
    }

    public void setItems(List<RssItem> items) {
        this.items = items;
    }

    public Channel() {
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

    @Override
    public String
    toString() {
        return "Channel{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
