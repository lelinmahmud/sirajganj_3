package com.sirajganj3.app.ui.communication;

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
import com.sirajganj3.app.ui.communication.model.Vehicle;
import com.sirajganj3.app.ui.opinion.OpinionAdapter;

import java.util.List;

public class VehiclesAdapter extends RecyclerView.Adapter<VehiclesAdapter.ViewHolder> {
    private Context context;
    private List<Vehicle> vehicles;

    public VehiclesAdapter(Context context, List<Vehicle> vehicles) {
        this.context = context;
        this.vehicles = vehicles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.communication_item_view, parent, false);
        return new VehiclesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(""+vehicles.get(position).getAcf().getTransportType());
        holder.driverName.setText(""+vehicles.get(position).getAcf().getDriverName());
        holder.phone.setText(""+vehicles.get(position).getAcf().getPhoneNumber());
        Glide.with(context).load(vehicles.get(position).getAcf().getDriverImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return vehicles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,driverName,phone;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.name);
            driverName=itemView.findViewById(R.id.driver_name);
            phone=itemView.findViewById(R.id.phone);
            imageView=itemView.findViewById(R.id.image);

        }
    }

}
