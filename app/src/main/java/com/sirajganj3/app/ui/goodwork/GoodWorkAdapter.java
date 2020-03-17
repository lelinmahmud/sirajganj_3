package com.sirajganj3.app.ui.goodwork;

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
import com.sirajganj3.app.ui.area.MyAreaAdapter;
import com.sirajganj3.app.ui.goodwork.models.GoodWork;

import java.util.List;

public class GoodWorkAdapter extends RecyclerView.Adapter<GoodWorkAdapter.ViewHolder> {
    private static final String TAG = "GoodWorkAdapter";
    private Context context;
    private List<GoodWork> goodWorks;
    private RecyclerViewItemClickListener listener;

    public GoodWorkAdapter(Context context, List<GoodWork> goodWorks, RecyclerViewItemClickListener listener) {
        this.context = context;
        this.goodWorks = goodWorks;
        this.listener = listener;
    }

    @NonNull
    @Override
    public GoodWorkAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.good_work_item_view, parent, false);
        return new GoodWorkAdapter.ViewHolder(view,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull GoodWorkAdapter.ViewHolder holder, int position) {
        holder.name.setText(""+goodWorks.get(position).getAcf().getName());
        holder.village.setText(""+goodWorks.get(position).getAcf().getVillage());
        holder.thana_name.setText(""+goodWorks.get(position).getAcf().getThana());
        holder.title.setText(""+goodWorks.get(position).getAcf().getDescription());
        Glide.with(context).load(goodWorks.get(position).getAcf().getPhoto()).into(holder.good_work_iv);

    }

    @Override
    public int getItemCount() {
        return goodWorks.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;
        TextView village;
        TextView thana_name;
        TextView title;
        ImageView good_work_iv;
        RecyclerViewItemClickListener listener;


        public ViewHolder(@NonNull View itemView,RecyclerViewItemClickListener listener) {
            super(itemView);
            name=itemView.findViewById(R.id.person_name);
            village=itemView.findViewById(R.id.village_name);
            thana_name=itemView.findViewById(R.id.thana_name);
            title=itemView.findViewById(R.id.good_work_title);
            good_work_iv=itemView.findViewById(R.id.good_work_iv);
            this.listener=listener;
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            listener.didPressed(goodWorks.get(getAdapterPosition()).getId());
            Log.e(TAG, "onClick: selected id"+goodWorks.get(getAdapterPosition()).getId() );
        }
    }
}
