package com.miroplanting.citybikedatafrontend.api;

import com.miroplanting.citybikedatafrontend.station.data.StationResponse;
import com.miroplanting.citybikedatafrontend.trip.data.TripResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {
    @GET("/station/")
    Single<StationResponse> doGetStations(@Query("page") String page);

    @GET("/trip/")
    Single<TripResponse> doGetTrips(@Query("page") String page);
}
