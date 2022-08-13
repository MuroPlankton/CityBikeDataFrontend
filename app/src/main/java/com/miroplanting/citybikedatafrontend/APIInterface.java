package com.miroplanting.citybikedatafrontend;

import com.miroplanting.citybikedatafrontend.station.StationResponse;
import com.miroplanting.citybikedatafrontend.trip.TripResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {
    @GET("/station/")
    Single<StationResponse> doGetStations(@Query("page") String page);

    @GET("/trip/")
    Single<TripResponse> doGetTrips(@Query("page") String page);
}
