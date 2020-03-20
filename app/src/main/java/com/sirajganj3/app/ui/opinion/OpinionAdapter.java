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
        holder.title.setText(""+opinions.get(position).getTitle());
        holder.answerSubject.setText(""+opinions.get(position).getTitle());
        holder.userName.setText(""+opinions.get(position).getContent());
        holder.userName.setText(""+opinions.get(position).getCommentAuthor());
        holder.userName.setText(""+opinions.get(position).getCommentAuthor());
    }

    @Override
    public int getItemCount() {
        return opinions.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView userName, title,answerSubject, description,answer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.user_name);
            title = itemView.findViewById(R.id.opinion_subject_tv);
            answerSubject = itemView.findViewById(R.id.answer_subject_tv);
            description = itemView.findViewById(R.id.user_opinion_tv);
            answer = itemView.findViewById(R.id.answer_opinion_tv);


        }


    }
}
