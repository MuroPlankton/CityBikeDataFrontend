package com.miroplanting.citybikedatafrontend.trip;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.miroplanting.citybikedatafrontend.R;
import com.miroplanting.citybikedatafrontend.station.Station;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class TripsAdapter extends PagingDataAdapter<Trip, TripsAdapter.TripViewHolder> {
    public static final int LOADING_ITEM = 0, TRIP_ITEM = 1;
    private static final String TAG = "TripsAdapter";

    public TripsAdapter(@NonNull DiffUtil.ItemCallback<Trip> diffCallback) {
        super(diffCallback);
        Log.d(TAG, "TripsAdapter: I ran");
    }

    @NonNull
    @Override
    public TripViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: I ran");
        return new TripViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TripViewHolder holder, int position) {
        Trip trip = getItem(position);

        Station departureStation = trip.getDepartureStation();
        String dtString = trip.getDepartureTime();
        holder.departureStationName.setText(departureStation.getNimi());
        holder.departureTime.setText(dtString.substring(0, 9));
        holder.departureDate.setText(dtString.substring(11, 18));

        Station returnStation = trip.getReturnStation();
        String rtString = trip.getReturnTime();
        LocalDateTime returnTime = LocalDateTime.parse(rtString);
        holder.returnStationName.setText(returnStation.getNimi());
        holder.returnTime.setText(rtString.substring(0, 9));
        holder.returnDate.setText(rtString.substring(11, 18));

        holder.duration.setText("" + LocalTime.MIN.plusSeconds(trip.getDuration()));
        holder.distance.setText(String.format("%dkm", trip.getDistanceMeters() / 1000));
        Log.d(TAG, "onBindViewHolder: I ran");
    }

    @Override
    public int getItemViewType(int position) {
        Log.d(TAG, "getItemViewType: I ran");
        return position == getItemCount() ? TRIP_ITEM : LOADING_ITEM;
    }

    public class TripViewHolder extends RecyclerView.ViewHolder {
        public TextView departureStationName, departureDate, departureTime,
                returnStationName, returnDate, returnTime,
                duration, distance;

        public TripViewHolder(@NonNull View itemView) {
            super(itemView);
            departureStationName = itemView.findViewById(R.id.trip_list_item_departure_station_name);
            departureDate = itemView.findViewById(R.id.trip_list_item_departure_station_date_text);
            departureTime = itemView.findViewById(R.id.trip_list_item_departure_station_time_text);

            returnStationName = itemView.findViewById(R.id.trip_list_item_return_station_name);
            returnDate = itemView.findViewById(R.id.trip_list_item_return_station_date_text);
            returnTime = itemView.findViewById(R.id.trip_list_item_return_station_time_text);

            duration = itemView.findViewById(R.id.trip_list_item_duration_text);
            distance = itemView.findViewById(R.id.trip_list_item_distance_text);
            Log.d(TAG, "TripViewHolder: I ran");
        }
    }
}
