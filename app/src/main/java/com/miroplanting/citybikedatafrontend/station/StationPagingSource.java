package com.miroplanting.citybikedatafrontend.station;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagingState;
import androidx.paging.rxjava3.RxPagingSource;

import com.miroplanting.citybikedatafrontend.APIClient;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class StationPagingSource extends RxPagingSource<Integer, Station> {
    private Context context;

    public StationPagingSource(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public Integer getRefreshKey(@NonNull PagingState<Integer, Station> pagingState) {
        return 0;
    }

    @NonNull
    @Override
    public Single<LoadResult<Integer, Station>> loadSingle(@NonNull LoadParams<Integer> loadParams) {
        try {
            int page = loadParams.getKey() != null ? loadParams.getKey() : 1;

            return APIClient.getApiInterface(context).doGetStations(Integer.toString(page))
                    .subscribeOn(Schedulers.io()).map(StationResponse::getStations)
                    .map(stations -> toLoadResult(stations, page)).onErrorReturn(LoadResult.Error::new);
        } catch (Exception e) {
            return Single.just(new LoadResult.Error(e));
        }
    }

    private LoadResult<Integer, Station> toLoadResult(List<Station> stations, int page) {
        return new LoadResult.Page(stations, page == 1 ? null : page - 1, page + 1);
    }
}
