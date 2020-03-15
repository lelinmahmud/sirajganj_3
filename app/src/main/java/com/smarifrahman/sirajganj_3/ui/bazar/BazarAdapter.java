package com.smarifrahman.sirajganj_3.ui.bazar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smarifrahman.sirajganj_3.R;
import com.smarifrahman.sirajganj_3.ui.opinion.OpinionAdapter;

public class BazarAdapter extends RecyclerView.Adapter<BazarAdapter.ViewHolder> {

    private Context context;

    public BazarAdapter(Context context) {
        this.context = context;

    }

    @NonNull
    @Override
    public BazarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.bazar_item_view, parent, false);

        return new BazarAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BazarAdapter.ViewHolder holder, int position) {
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
