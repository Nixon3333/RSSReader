package com.rss.rssreader.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.rss.rssreader.R;
import com.rss.rssreader.adapter.FeedAdapter;
import com.rss.rssreader.contract.Contract;
import com.rss.rssreader.pojo.Rss;
import com.rss.rssreader.pojo.RssItem;
import com.rss.rssreader.presenter.Presenter;

public class FeedActivity extends AppCompatActivity implements Contract.View {

    RecyclerView recyclerView;
    ImageView rssImage;
    ProgressBar pbFeed;
    SwipeRefreshLayout swipeContainer;
    Contract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        presenter = new Presenter(this);
        presenter.start();

    }

    @Override
    public void showFeed(Rss rss) {
        FeedAdapter adapter = new FeedAdapter(rss, this::openUrl);
        recyclerView.setAdapter(adapter);

        Glide.with(getApplicationContext()).load(rss.getChannel().getImage().getUrl())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        rssImage.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        rssImage.setVisibility(View.VISIBLE);
                        return false;
                    }
                })
                .into(rssImage);

        getSupportActionBar().setTitle(rss.getChannel().getTitle());
    }

    //Open url in browser
    private void openUrl(RssItem rssItem) {
        String url = rssItem.getLink();
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    @Override
    public void init() {
        String rssUrl = getIntent().getStringExtra("rssUrl");
        Log.d("rssUrl", checkUrl(rssUrl));
        pbFeed = findViewById(R.id.pbFeed);
        rssImage = findViewById(R.id.rssImage);
        swipeContainer = findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(() -> presenter.getFeed(checkUrl(rssUrl)));
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        presenter.getFeed(checkUrl(rssUrl));
    }

    //Add '/' if need
    private String checkUrl(String rssUrl) {
        String temp = rssUrl;
        if (!rssUrl.endsWith("/"))
            rssUrl += "/";
        if (!rssUrl.startsWith("http://") | (!rssUrl.startsWith("https://")))
            temp = "http://" + rssUrl;
        return temp;
    }

    @Override
    public void hideProgressBar() {
        pbFeed.setVisibility(View.GONE);
        swipeContainer.setRefreshing(false);
    }

    @Override
    public void showError() {
        presenter.showError(this);
    }
}
