package com.rss.rssreader.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rss.rssreader.R;
import com.rss.rssreader.pojo.Rss;
import com.rss.rssreader.pojo.RssItem;
import com.rss.rssreader.utils.OnItemClickListener;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedViewHolder> {

    private Rss rss;
    private OnItemClickListener listener;

    public FeedAdapter(Rss rss, OnItemClickListener listener) {
        this.rss = rss;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        return new FeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder feedViewHolder, int i) {
        feedViewHolder.bind(rss.getChannel().getItems().get(i), listener);
    }

    @Override
    public int getItemCount() {
        return rss.getChannel().getItems().size();
    }

    public class FeedViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTitle, tvDate, tvContent;
        public ImageView rssImage;

        FeedViewHolder(@NonNull View itemView) {
            super(itemView);


            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvContent = itemView.findViewById(R.id.tvContent);
            rssImage = itemView.findViewById(R.id.rssImage);
        }

        void bind(RssItem rssItem, OnItemClickListener listener) {
            tvTitle.setText(rssItem.getTitle());
            tvDate.setText(rssItem.getPubDate());
            tvContent.setText(rssItem.getDescription());

            itemView.setOnClickListener(view -> listener.onItemClick(rssItem));
        }
    }
}

