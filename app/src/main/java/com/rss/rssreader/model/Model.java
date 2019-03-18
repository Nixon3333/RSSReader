package com.rss.rssreader.model;

import com.rss.rssreader.utils.Callback;
import com.rss.rssreader.contract.Contract;
import com.rss.rssreader.pojo.RSS;
import com.rss.rssreader.retrofit.RetrofitAPI;
import com.rss.rssreader.retrofit.RetrofitClient;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class Model implements Contract.Model {

    String title = "Hello";

    @Override
    public void getFeed(String baseUrl, Callback<RSS> callback) {


        Retrofit retrofit = RetrofitClient.getClient(baseUrl);
        // https://lenta.ru/rss/news/
        // https://news.yandex.ru/auto.rss/

        RetrofitAPI api = retrofit.create(RetrofitAPI.class);

        Observable<RSS> observable = api.getFeed();

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RSS>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RSS rss) {
                        callback.returnResult(rss);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }



        /*call.enqueue(new Callback<RSS>() {
            @Override
            public void onResponse(Call<RSS> call, Response<RSS> response) {
                Log.d("RetrofitResp", response.body().getChannel().getTitle());
                title = response.body().getChannel().getTitle();
            }

            @Override
            public void onFailure(Call<RSS> call, Throwable t) {
                Log.d("RetrofitErr", t.toString());

            }
        });
        return title;*/

}
