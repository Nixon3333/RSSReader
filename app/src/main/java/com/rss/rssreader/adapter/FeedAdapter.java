package com.rss.rssreader.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rss.rssreader.R;
import com.rss.rssreader.pojo.RSS;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedViewHolder> {

    private RSS rss;

    public FeedAdapter(RSS rss) {
        this.rss = rss;
    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        return new FeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder feedViewHolder, int i) {
        feedViewHolder.tvTitle.setText(rss.getChannel().getItems().get(i).getTitle());
        feedViewHolder.tvDate.setText(rss.getChannel().getItems().get(i).getPubDate());
        feedViewHolder.tvContent.setText(rss.getChannel().getItems().get(i).getDescription());
    }

    @Override
    public int getItemCount() {
        return rss.getChannel().getItems().size();
    }

    public class FeedViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTitle, tvDate, tvContent;

        public FeedViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvContent = itemView.findViewById(R.id.tvContent);
        }
    }
}

