package com.rss.rssreader.model;

import android.util.Log;

import com.rss.rssreader.pojo.Rss;
import com.rss.rssreader.utils.Callback;
import com.rss.rssreader.contract.Contract;
import com.rss.rssreader.retrofit.RetrofitAPI;
import com.rss.rssreader.retrofit.RetrofitClient;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class Model implements Contract.Model {

    @Override
    public void getFeed(String baseUrl, Callback<Rss> callback) {


        Retrofit retrofit = RetrofitClient.getClient(baseUrl);
        // https://lenta.ru/rss/news/
        // https://news.yandex.ru/auto.rss/

        RetrofitAPI api = retrofit.create(RetrofitAPI.class);

        Observable<Rss> observable = api.getFeed();

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Rss>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Rss rss) {
                        callback.returnResult(rss);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.returnError(e.getMessage());
                        Log.d("RetroErr", e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        observable.unsubscribeOn(AndroidSchedulers.mainThread());
    }
}
