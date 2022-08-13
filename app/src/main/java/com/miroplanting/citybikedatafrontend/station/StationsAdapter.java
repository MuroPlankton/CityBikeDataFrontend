package com.miroplanting.citybikedatafrontend.station;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.miroplanting.citybikedatafrontend.R;

public class StationsAdapter extends PagingDataAdapter<Station, StationsAdapter.StationViewHolder> {
    public static final int LOADING_ITEM = 0, TRIP_ITEM = 1;

    public StationsAdapter(@NonNull DiffUtil.ItemCallback<Station> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public StationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StationViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.station_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StationViewHolder holder, int position) {
        Station station = getItem(position);

        holder.name.setText(station.getNimi());
        holder.address.setText(station.getOsoite());
        holder.city.setText(station.getKaupunki());
        String operator = station.getOperator().equals(" ") ? "Operator wasn't defined." : station.getOperator();
        holder.operator.setText(operator);
        holder.capacity.setText(station.getCapacity().toString());
    }

    @Override
    public int getItemViewType(int position) {
        return position == getItemCount() ? TRIP_ITEM : LOADING_ITEM;
    }

    public class StationViewHolder extends RecyclerView.ViewHolder {

        public TextView name, address, city, operator, capacity;

        public StationViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.station_list_item_name);
            address = itemView.findViewById(R.id.station_list_item_address);
            city = itemView.findViewById(R.id.station_list_item_city);
            operator = itemView.findViewById(R.id.station_list_item_operator);
            capacity = itemView.findViewById(R.id.station_list_item_bike_count);
        }
    }
}
