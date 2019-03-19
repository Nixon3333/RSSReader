package com.rss.rssreader.retrofit;

import com.rss.rssreader.pojo.Rss;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface RetrofitAPI {

    @GET(".")
    Observable<Rss> getFeed();
}
