package com.rss.rssreader.contract;

import com.rss.rssreader.utils.Callback;
import com.rss.rssreader.pojo.RSS;

public interface Contract {

    interface View {
        void showFeed(RSS rss);
        void init();
    }

    interface Presenter {
        void getFeed(String baseUrl);
        void start();
    }

    interface Model {
        void getFeed(String baseUrl, Callback<RSS> callback);
    }
}
