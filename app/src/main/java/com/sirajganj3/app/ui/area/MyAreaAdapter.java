package com.sirajganj3.app.ui.area;

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
import com.sirajganj3.app.ui.area.models.AreaInfo;
import com.sirajganj3.app.ui.opinion.OpinionAdapter;

import java.util.List;

public class MyAreaAdapter extends RecyclerView.Adapter<MyAreaAdapter.ViewHolder> {
    private static final String TAG = "MyAreaAdapter";
    private Context context;
    private List<AreaInfo> areaInfos;
    private RecyclerViewItemClickListener listener;

    public MyAreaAdapter(Context context, List<AreaInfo> areaInfos, RecyclerViewItemClickListener listener) {
        this.context = context;
        this.areaInfos = areaInfos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyAreaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.my_area_item_view, parent, false);

        return new MyAreaAdapter.ViewHolder(view,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAreaAdapter.ViewHolder holder, int position) {
        holder.title.setText(areaInfos.get(position).getTitle());
        holder.content.setText(areaInfos.get(position).getContent());
        Glide.with(context).load(areaInfos.get(position).getImage()).into(holder.p_image);

    }

    @Override
    public int getItemCount() {
        return areaInfos.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        TextView content;
        TextView details;
        ImageView p_image;
        RecyclerViewItemClickListener listener;

        public ViewHolder(@NonNull View itemView,RecyclerViewItemClickListener listener) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
             content=itemView.findViewById(R.id.tv_news_title);
            details=itemView.findViewById(R.id.tv_details);
            p_image=itemView.findViewById(R.id.iv_news);
            this.listener=listener;
            
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            listener.didPressed(areaInfos.get(getAdapterPosition()).getId());
            Log.e(TAG, "onClick: "+getAdapterPosition());

        }
    }
}
