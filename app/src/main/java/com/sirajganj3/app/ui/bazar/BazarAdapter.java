package com.sirajganj3.app.ui.bazar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sirajganj3.app.R;
import com.sirajganj3.app.ui.bazar.models.BazarInfo;

import java.util.List;

public class BazarAdapter extends RecyclerView.Adapter<BazarAdapter.ViewHolder> {

    private Context context;
    private List<BazarInfo> bazarInfos;

    public BazarAdapter(Context context, List<BazarInfo> bazarInfos) {
        this.context = context;
        this.bazarInfos = bazarInfos;
    }

    @NonNull
    @Override
    public BazarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.bazar_item_view, parent, false);

        return new BazarAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BazarAdapter.ViewHolder holder, int position) {
        holder.product_name.setText(""+bazarInfos.get(position).getAcf().getProductName());
        holder.amonut.setText(""+bazarInfos.get(position).getAcf().getQuantity());
        holder.price.setText(""+bazarInfos.get(position).getAcf().getPrice());
        holder.phone_number.setText(""+bazarInfos.get(position).getAcf().getPhone());
        holder.seller_name.setText(""+bazarInfos.get(position).getAcf().getSellerName());
        Glide.with(context).load(bazarInfos.get(position).getAcf().getProductImage()).into(holder.p_image);
    }

    @Override
    public int getItemCount() {
        return bazarInfos.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView product_name;
        TextView amonut;
        TextView price;
        TextView seller_name;
        TextView phone_number;
        ImageView p_image;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            product_name=itemView.findViewById(R.id.bazar_item_title);
            amonut=itemView.findViewById(R.id.amount_tv);
            seller_name=itemView.findViewById(R.id.owner_name_tv);
            price=itemView.findViewById(R.id.price_tv);
            phone_number=itemView.findViewById(R.id.owner_phone_number_tv);
            p_image=itemView.findViewById(R.id.iv_news);

        }


    }
}
