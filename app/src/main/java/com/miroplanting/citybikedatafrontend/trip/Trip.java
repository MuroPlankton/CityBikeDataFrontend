package com.miroplanting.citybikedatafrontend.trip;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.miroplanting.citybikedatafrontend.station.Station;

public class Trip {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("departureTime")
    @Expose
    private String departureTime;

    @SerializedName("returnTime")
    @Expose
    private String returnTime;

    @SerializedName("departureStation")
    @Expose
    private Station departureStation;

    @SerializedName("returnStation")
    @Expose
    private Station returnStation;

    @SerializedName("distanceMeters")
    @Expose
    private Integer distanceMeters;

    @SerializedName("duration")
    @Expose
    private Integer duration;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    public Station getDepartureStation() {
        return departureStation;
    }

    public void setDepartureStation(Station departureStation) {
        this.departureStation = departureStation;
    }

    public Station getReturnStation() {
        return returnStation;
    }

    public void setReturnStation(Station returnStation) {
        this.returnStation = returnStation;
    }

    public Integer getDistanceMeters() {
        return distanceMeters;
    }

    public void setDistanceMeters(Integer distanceMeters) {
        this.distanceMeters = distanceMeters;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return obj == this;
    }
}
