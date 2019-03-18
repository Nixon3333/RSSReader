package com.rss.rssreader.presenter;

import com.rss.rssreader.utils.Callback;
import com.rss.rssreader.contract.Contract;
import com.rss.rssreader.model.Model;
import com.rss.rssreader.pojo.RSS;

public class Presenter implements Contract.Presenter {

    private Contract.View view;
    private Contract.Model model;

    public Presenter(Contract.View view) {
        this.view = view;
        this.model = new Model();
    }

    @Override
    public void getFeed(String baseUrl) {
        model.getFeed(baseUrl, new Callback<RSS>() {
            @Override
            public void returnResult(RSS rss) {
                view.showFeed(rss);
            }

            @Override
            public void returnError(String message) {

            }
        });
    }

    @Override
    public void start() {
        view.init();
    }
}
