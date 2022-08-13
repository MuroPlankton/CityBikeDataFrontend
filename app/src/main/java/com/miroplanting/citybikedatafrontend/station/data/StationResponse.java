package com.miroplanting.citybikedatafrontend.station.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.miroplanting.citybikedatafrontend.station.data.Station;

import java.util.List;

public class StationResponse {
    @SerializedName("content")
    @Expose
    private List<Station> stations = null;

    @SerializedName("number")
    @Expose
    private int page;

    @SerializedName("totalPages")
    @Expose
    private int totalPages;

    @SerializedName("totalElements")
    @Expose
    private int totalElements;

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
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
