package com.miroplanting.citybikedatafrontend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.miroplanting.citybikedatafrontend.trip.TripComparator;
import com.miroplanting.citybikedatafrontend.trip.TripViewModel;
import com.miroplanting.citybikedatafrontend.trip.TripsAdapter;
import com.miroplanting.citybikedatafrontend.trip.TripsLoadStateAdapter;

import autodispose2.AutoDispose;
import autodispose2.androidx.lifecycle.AndroidLifecycleScopeProvider;
import dagger.hilt.android.AndroidEntryPoint;

public class MainActivity extends AppCompatActivity {
    //TODO: launch app with splash screen and check if shared preference exists for the api address
    //then ask for the address before loading the data if necessary

    private TripViewModel tripViewModel;
    private TripsAdapter tripsAdapter;
    private RecyclerView tripsRecycler;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences settings = getApplicationContext().getSharedPreferences("apiInfo", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("apiURL", "http://192.168.8.110:8080");
        editor.apply();

        tripsAdapter = new TripsAdapter(new TripComparator());
        tripViewModel = new ViewModelProvider(this).get(TripViewModel.class);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        tripsRecycler = findViewById(R.id.trips_recycler);
        tripsRecycler.setLayoutManager(linearLayoutManager);
        tripsRecycler.setAdapter(tripsAdapter.withLoadStateFooter(new TripsLoadStateAdapter(v -> tripsAdapter.retry())));

        tripViewModel.tripPagingDataFlowable.to(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(tripPagingData -> tripsAdapter.submitData(getLifecycle(), tripPagingData));
        Log.d(TAG, "onCreate: I ran");
    }
}