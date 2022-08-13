package com.miroplanting.citybikedatafrontend.trip;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public class TripComparator extends DiffUtil.ItemCallback<Trip> {
    @Override
    public boolean areItemsTheSame(@NonNull Trip oldItem, @NonNull Trip newItem) {
        return oldItem.getId().equals(newItem.getId());
    }

    @Override
    public boolean areContentsTheSame(@NonNull Trip oldItem, @NonNull Trip newItem) {
        return oldItem.getId().equals(newItem.getId());
    }
}
