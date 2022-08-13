package com.miroplanting.citybikedatafrontend.trip;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.miroplanting.citybikedatafrontend.DataLoadStateAdapter;
import com.miroplanting.citybikedatafrontend.R;

import autodispose2.AutoDispose;
import autodispose2.androidx.lifecycle.AndroidLifecycleScopeProvider;

public class TripListActivity extends AppCompatActivity {
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



        tripsAdapter = new TripsAdapter(new TripComparator());
        tripViewModel = new ViewModelProvider(this).get(TripViewModel.class);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        tripsRecycler = findViewById(R.id.trips_recycler);
        tripsRecycler.setLayoutManager(linearLayoutManager);
        tripsRecycler.setAdapter(tripsAdapter.withLoadStateFooter(new DataLoadStateAdapter(v -> tripsAdapter.retry())));

        tripViewModel.tripPagingDataFlowable.to(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(tripPagingData -> tripsAdapter.submitData(getLifecycle(), tripPagingData));
        Log.d(TAG, "onCreate: I ran");
    }
}