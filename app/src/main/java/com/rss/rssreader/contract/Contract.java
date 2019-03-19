package com.rss.rssreader.contract;


import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.rss.rssreader.utils.Callback;
import com.rss.rssreader.pojo.Rss;

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
