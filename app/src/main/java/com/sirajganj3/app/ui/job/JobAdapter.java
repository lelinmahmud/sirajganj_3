package com.sirajganj3.app.ui.job;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sirajganj3.app.R;
import com.sirajganj3.app.ui.job.models.JobInfo;

import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.ViewHolder> {

    private Context context;
    private List<JobInfo> jobInfos;

    public JobAdapter(Context context, List<JobInfo> jobInfos) {
        this.context = context;
        this.jobInfos = jobInfos;
    }

    @NonNull
    @Override
    public JobAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.jobs_item_view, parent, false);

        return new JobAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobAdapter.ViewHolder holder, int position) {
        holder.company_name.setText(""+jobInfos.get(position).getAcf().getCompanyName());
        holder.post_name.setText(""+jobInfos.get(position).getAcf().getPostName());
        holder.sallary.setText(""+jobInfos.get(position).getAcf().getSalary());
        holder.owner.setText(""+jobInfos.get(position).getAcf().getOwner());
        holder.phone.setText(""+jobInfos.get(position).getAcf().getPhone());
    }

    @Override
    public int getItemCount() {
        return jobInfos.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView post_name;
        TextView company_name;
        TextView sallary;
        TextView owner;
        TextView phone;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            post_name=itemView.findViewById(R.id.job_title_tv);
            company_name=itemView.findViewById(R.id.company_name);
            sallary=itemView.findViewById(R.id.price_tv);
            owner=itemView.findViewById(R.id.owner_name_tv);
            phone=itemView.findViewById(R.id.owner_phone_number_tv);

        }


    }
}
