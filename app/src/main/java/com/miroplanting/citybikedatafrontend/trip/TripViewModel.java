package com.miroplanting.citybikedatafrontend.trip;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.rxjava3.PagingRx;

import com.miroplanting.citybikedatafrontend.trip.data.Trip;
import com.miroplanting.citybikedatafrontend.trip.data.TripPagingSource;

import io.reactivex.rxjava3.core.Flowable;
import kotlin.OptIn;
import kotlinx.coroutines.CoroutineScope;

public class TripViewModel extends AndroidViewModel {
    public Flowable<PagingData<Trip>> tripPagingDataFlowable;

    public TripViewModel(@NonNull Application application) {
        super(application);
        init();
    }

    @OptIn(markerClass = kotlinx.coroutines.ExperimentalCoroutinesApi.class)
    private void init() {
        TripPagingSource tripPagingSource = new TripPagingSource(getApplication().getApplicationContext());

        Pager<Integer, Trip> pager = new Pager(new PagingConfig(
                20, 20, false, 20, 200),
                () -> tripPagingSource);

        tripPagingDataFlowable = PagingRx.getFlowable(pager);
        CoroutineScope coroutineScope = ViewModelKt.getViewModelScope(this);
        PagingRx.cachedIn(tripPagingDataFlowable, coroutineScope);
    }
}
