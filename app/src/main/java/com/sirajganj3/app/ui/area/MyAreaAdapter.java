package com.sirajganj3.app.ui.area;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sirajganj3.app.R;
import com.sirajganj3.app.ui.opinion.OpinionAdapter;

public class MyAreaAdapter extends RecyclerView.Adapter<MyAreaAdapter.ViewHolder> {

    private Context context;

    public MyAreaAdapter(Context context) {
        this.context = context;

    }

    @NonNull
    @Override
    public MyAreaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.my_area_item_view, parent, false);

        return new MyAreaAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAreaAdapter.ViewHolder holder, int position) {
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
