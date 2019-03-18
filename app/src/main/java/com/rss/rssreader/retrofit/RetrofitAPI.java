package com.rss.rssreader.retrofit;

import com.rss.rssreader.pojo.RSS;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitAPI {

    @GET(".")
    Observable<RSS> getFeed();
}
