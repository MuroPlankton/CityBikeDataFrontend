package com.miroplanting.citybikedatafrontend;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.miroplanting.citybikedatafrontend.station.ui.StationListActivity;
import com.miroplanting.citybikedatafrontend.trip.ui.TripListActivity;

public class ListPickerActivity extends Activity {

    private Button stations, trips;

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.activity_list_picker_stations_button) {
                startActivity(new Intent(getApplicationContext(), StationListActivity.class));
            } else {
                startActivity(new Intent(getApplicationContext(), TripListActivity.class));
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_picker);
        stations = findViewById(R.id.activity_list_picker_stations_button);
        trips = findViewById(R.id.activity_list_picker_trips_button);
        stations.setOnClickListener(onClickListener);
        trips.setOnClickListener(onClickListener);
    }
}
