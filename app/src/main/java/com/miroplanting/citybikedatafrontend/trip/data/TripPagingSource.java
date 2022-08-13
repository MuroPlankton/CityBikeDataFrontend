package com.miroplanting.citybikedatafrontend.trip.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagingState;
import androidx.paging.rxjava3.RxPagingSource;

import com.miroplanting.citybikedatafrontend.api.APIClient;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class TripPagingSource extends RxPagingSource<Integer, Trip> {
    private Context context;


    public TripPagingSource(Context context) {
        super();
        this.context = context;
    }

    @Nullable
    @Override
    public Integer getRefreshKey(@NonNull PagingState<Integer, Trip> pagingState) {
        if (pagingState.getAnchorPosition() != null) {
            LoadResult.Page<Integer, Trip> page = pagingState.closestPageToPosition(pagingState.getAnchorPosition());
            if (page.getPrevKey() != null) {
                return pagingState.getAnchorPosition() - 1;
            } else if (page.getNextKey() == null) {
                return pagingState.getAnchorPosition() + 1;
            }
        }
        return null;
    }

    @NonNull
    @Override
    public Single<LoadResult<Integer, Trip>> loadSingle(@NonNull LoadParams<Integer> loadParams) {
        try {
            int page = loadParams.getKey() != null ? loadParams.getKey() : 1;

            Single<LoadResult<Integer, Trip>> single = APIClient.getApiInterface(context).doGetTrips(Integer.toString(page))
                    .subscribeOn(Schedulers.io()).map(TripResponse::getTrips)
                    .map(trips -> toLoadResult(trips, page)).onErrorReturn(LoadResult.Error::new);
            return single;
        } catch (Exception e) {
            e.printStackTrace();
            return Single.just(new LoadResult.Error(e));
        }
    }

    private LoadResult<Integer, Trip> toLoadResult(List<Trip> trips, int page) {
        return new LoadResult.Page(trips, page == 1 ? null : page - 1, page + 1);
    }
}
