package com.miroplanting.citybikedatafrontend.station;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.rxjava3.PagingRx;

import com.miroplanting.citybikedatafrontend.station.data.Station;
import com.miroplanting.citybikedatafrontend.station.data.StationPagingSource;

import io.reactivex.rxjava3.core.Flowable;
import kotlin.OptIn;
import kotlinx.coroutines.CoroutineScope;

public class StationViewModel extends AndroidViewModel {
    public Flowable<PagingData<Station>> stationPagingDataFlowable;
    private static final String TAG = "StationViewModel";

    public StationViewModel(@NonNull Application application) {
        super(application);
        init();
        Log.d(TAG, "StationViewModel: I ran");
    }

    @OptIn(markerClass = kotlinx.coroutines.ExperimentalCoroutinesApi.class)
    private void init() {
        StationPagingSource stationPagingSource = new StationPagingSource(getApplication().getApplicationContext());

        Pager<Integer, Station> pager = new Pager(new PagingConfig(
                20, 20, false, 20, 200),
                () -> stationPagingSource);

        stationPagingDataFlowable = PagingRx.getFlowable(pager);
        CoroutineScope coroutineScope = ViewModelKt.getViewModelScope(this);
        PagingRx.cachedIn(stationPagingDataFlowable, coroutineScope);
        Log.d(TAG, "init: I ran");
    }
}
