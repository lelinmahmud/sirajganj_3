package com.sirajganj3.app.ui.opinion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sirajganj3.app.R;

import java.util.List;

public class OpinionAdapter extends RecyclerView.Adapter<OpinionAdapter.ViewHolder> {

    private Context context;
    private List<Opinion> opinions;

    public OpinionAdapter(Context context, List<Opinion> opinions) {
        this.context = context;
        this.opinions = opinions;
    }

    @NonNull
    @Override
    public OpinionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.opinion_item_view, parent, false);

        return new OpinionAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OpinionAdapter.ViewHolder holder, int position) {
        holder.userName.setText(""+opinions.get(position).getCommentAuthor());
        holder.title_1.setText(""+opinions.get(position).getTitle());
        holder.title_2.setText(""+opinions.get(position).getTitle());
        holder.content.setText(""+opinions.get(position).getContent());
        holder.reply.setText(""+opinions.get(position).getCommentContent());
      //  holder.userName.setText(""+opinions.get(position).getCommentAuthor());
       // holder.userName.setText(""+opinions.get(position).getCommentAuthor());
    }

    @Override
    public int getItemCount() {
        return opinions.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView userName, title_1,title_2,content,reply;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            userName = itemView.findViewById(R.id.user_name);
            title_1 = itemView.findViewById(R.id.title_one);
            title_2 = itemView.findViewById(R.id.title_two);
            content = itemView.findViewById(R.id.content);
            reply = itemView.findViewById(R.id.reply);


        }


    }
}
