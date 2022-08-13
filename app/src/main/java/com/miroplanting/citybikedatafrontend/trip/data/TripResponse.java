package com.miroplanting.citybikedatafrontend.trip.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.miroplanting.citybikedatafrontend.trip.data.Trip;

import java.util.List;

public class TripResponse {
    @SerializedName("content")
    @Expose
    private List<Trip> trips = null;

    @SerializedName("number")
    @Expose
    private int page;

    @SerializedName("totalPages")
    @Expose
    private int totalPages;

    @SerializedName("totalElements")
    @Expose
    private int totalElements;

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }
}
