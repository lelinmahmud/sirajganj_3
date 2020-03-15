package com.smarifrahman.sirajganj_3.ui.opinion;

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
import com.smarifrahman.sirajganj_3.R;
import com.smarifrahman.sirajganj_3.ui.news.NewsAdapter;
import com.smarifrahman.sirajganj_3.ui.news.RecyclerViewItemClickListener;
import com.smarifrahman.sirajganj_3.ui.news.model.News;

import java.util.List;

public class OpinionAdapter extends RecyclerView.Adapter<OpinionAdapter.ViewHolder> {

    private Context context;

    public OpinionAdapter(Context context) {
        this.context = context;

    }

    @NonNull
    @Override
    public OpinionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.opinion_item_view, parent, false);

        return new OpinionAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OpinionAdapter.ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 10;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView userName, title, description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.user_name);
            title = itemView.findViewById(R.id.opinion_subject_tv);
            description = itemView.findViewById(R.id.user_opinion_tv);


        }


    }
}
