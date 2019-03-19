package com.rss.rssreader.contract;


import android.content.Context;

import com.rss.rssreader.pojo.Rss;
import com.rss.rssreader.utils.Callback;

public interface Contract {

    interface View {
        void showFeed(Rss rss);
        void init();
        void hideProgressBar();
        void showError();
    }

    interface Presenter {
        void getFeed(String baseUrl);
        void start();
        void showError(Context context);
    }

    interface Model {
        void getFeed(String baseUrl, Callback<Rss> callback);
    }
}
