package com.miroplanting.citybikedatafrontend.station;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.miroplanting.citybikedatafrontend.DataLoadStateAdapter;
import com.miroplanting.citybikedatafrontend.R;

import autodispose2.AutoDispose;
import autodispose2.androidx.lifecycle.AndroidLifecycleScopeProvider;

public class StationListActivity extends AppCompatActivity {
    private StationViewModel stationViewModel;
    private StationsAdapter stationsAdapter;
    private RecyclerView stationsRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stationsAdapter = new StationsAdapter(new StationComparator());
        stationViewModel = new ViewModelProvider(this).get(StationViewModel.class);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        stationsRecycler = findViewById(R.id.trips_recycler);
        stationsRecycler.setLayoutManager(linearLayoutManager);
        stationsRecycler.setAdapter(stationsAdapter.withLoadStateFooter(new DataLoadStateAdapter(v -> stationsAdapter.retry())));

        stationViewModel.stationPagingDataFlowable
                .to(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(stationPagingData -> stationsAdapter.submitData(getLifecycle(), stationPagingData));
    }
}
