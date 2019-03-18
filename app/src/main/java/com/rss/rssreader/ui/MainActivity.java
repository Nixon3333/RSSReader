package com.rss.rssreader.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rss.rssreader.R;
import com.rss.rssreader.adapter.FeedAdapter;
import com.rss.rssreader.contract.Contract;
import com.rss.rssreader.pojo.RSS;
import com.rss.rssreader.presenter.Presenter;

public class MainActivity extends AppCompatActivity implements Contract.View{

    RecyclerView recyclerView;
    Contract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new Presenter(this);
        presenter.start();
        
    }

    @Override
    public void showFeed(RSS rss) {
        FeedAdapter adapter = new FeedAdapter(rss);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void init() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        presenter.getFeed("https://lenta.ru/rss/news/");
    }
}
