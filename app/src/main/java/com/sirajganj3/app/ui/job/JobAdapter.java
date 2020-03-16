package com.sirajganj3.app.ui.job;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sirajganj3.app.R;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.ViewHolder> {

    private Context context;

    public JobAdapter(Context context) {
        this.context = context;

    }

    @NonNull
    @Override
    public JobAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.jobs_item_view, parent, false);

        return new JobAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobAdapter.ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 10;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        }


    }
}
