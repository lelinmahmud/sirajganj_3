package com.sirajganj3.app.ui.news;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sirajganj3.app.R;
import com.sirajganj3.app.ui.news.model.News;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private static final String TAG = "NewsAdapter";
    private Context context;
    private RecyclerViewItemClickListener rvl;
    private List<News> newsList;

    public NewsAdapter(Context context, RecyclerViewItemClickListener rvl, List<News> newsList) {
        this.context = context;
        this.rvl = rvl;
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_item_view, parent, false);

        return new ViewHolder(view, rvl);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
        holder.title.setText("" + newsList.get(position).getContent());
        Glide.with(context).load(newsList.get(position).getFeaturedImage()).into(holder.newsImage);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView newsImage;
        TextView title;
        TextView details_btn;
        RecyclerViewItemClickListener rvl;

        public ViewHolder(@NonNull View itemView, RecyclerViewItemClickListener rvl) {
            super(itemView);
            newsImage = itemView.findViewById(R.id.iv_news);
            title = itemView.findViewById(R.id.tv_news_title);
            details_btn = itemView.findViewById(R.id.tv_details);
            this.rvl = rvl;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            rvl.didPressed(newsList.get(getAdapterPosition()).getId());
            Log.d(TAG, "onClick: "+getAdapterPosition());
        }
    }

}
