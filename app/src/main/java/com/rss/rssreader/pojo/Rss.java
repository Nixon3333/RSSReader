package com.rss.rssreader.pojo;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root (strict = false)
public class Rss {

    @Attribute
    String version;

    @Element
    Channel channel;

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return "Rss{" +
                "version='" + version + '\'' +
                ", channel=" + channel +
                '}';
    }
}