package com.sirajganj3.app.ui.goodwork;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sirajganj3.app.R;
import com.sirajganj3.app.ui.area.MyAreaAdapter;

public class GoodWorkAdapter extends RecyclerView.Adapter<GoodWorkAdapter.ViewHolder> {

    private Context context;

    public GoodWorkAdapter(Context context) {
        this.context = context;

    }

    @NonNull
    @Override
    public GoodWorkAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.good_work_item_view, parent, false);
        return new GoodWorkAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GoodWorkAdapter.ViewHolder holder, int position) {
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
