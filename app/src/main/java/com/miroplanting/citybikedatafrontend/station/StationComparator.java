package com.miroplanting.citybikedatafrontend.station;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public class StationComparator extends DiffUtil.ItemCallback<Station> {
    @Override
    public boolean areItemsTheSame(@NonNull Station oldItem, @NonNull Station newItem) {
        return oldItem.getId().equals(newItem.getId());
    }

    @Override
    public boolean areContentsTheSame(@NonNull Station oldItem, @NonNull Station newItem) {
        return oldItem.getId().equals(newItem.getId());
    }
}
