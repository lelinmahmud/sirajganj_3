package com.sirajganj3.app.ui.opinion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sirajganj3.app.R;

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
