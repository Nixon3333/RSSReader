package com.rss.rssreader.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
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
    AlertDialog dialog;
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
        Glide.with(getApplicationContext()).load(rss.getChannel().getImage().getUrl()).into(rssImage);
        getSupportActionBar().setTitle(rss.getChannel().getTitle());
    }

    private void openUrl(RssItem rssItem) {
        String url = rssItem.getLink();
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    @Override
    public void init() {
        String rssUrl = getIntent().getStringExtra("rssUrl");
        pbFeed = findViewById(R.id.pbFeed);
        rssImage = findViewById(R.id.rssImage);
        swipeContainer = findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(() -> presenter.getFeed(checkUrl(rssUrl)));
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        presenter.getFeed(checkUrl(rssUrl));
    }

    private String checkUrl(String rssUrl) {
        if (!rssUrl.endsWith("/"))
            rssUrl += "/";
        return rssUrl;
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
