package com.rss.rssreader.presenter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.rss.rssreader.contract.Contract;
import com.rss.rssreader.model.Model;
import com.rss.rssreader.pojo.Rss;
import com.rss.rssreader.utils.Callback;

public class Presenter implements Contract.Presenter {

    private Contract.View view;
    private Contract.Model model;

    public Presenter(Contract.View view) {
        this.view = view;
        this.model = new Model();
    }

    //Send request
    @Override
    public void getFeed(String baseUrl) {
        model.getFeed(baseUrl, new Callback<Rss>() {
            @Override
            public void returnResult(Rss rss) {
                view.hideProgressBar();
                view.showFeed(rss);
            }

            @Override
            public void returnError(String message) {
                view.hideProgressBar();
                view.showError();
            }
        });
    }

    @Override
    public void start() {
        view.init();
    }

    @Override
    public void showError(Context context) {
        showErrorDialog(context);
    }

    private void showErrorDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Ошибка!")
                .setMessage("Сервер не отвечает. Проверьте соединение и RSS-адрес")
                .setCancelable(false)
                .setNegativeButton("Ок",
                        (dialog, id) -> {
                            dialog.cancel();
                            ((Activity) context).finish();
                        });
        AlertDialog alert = builder.create();
        if (!((Activity) context).isFinishing())
            alert.show();
    }
}
