package com.miroplanting.citybikedatafrontend.trip;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.LoadState;
import androidx.paging.LoadStateAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.miroplanting.citybikedatafrontend.R;

public class TripsLoadStateAdapter extends LoadStateAdapter<TripsLoadStateAdapter.LoadStateViewHolder> {
    private View.OnClickListener tripRetryCallback;

    private static final String TAG = "TripsLoadStateAdapter";

    public TripsLoadStateAdapter(View.OnClickListener tripRetryCallback) {
        this.tripRetryCallback = tripRetryCallback;
        Log.d(TAG, "TripsLoadStateAdapter: I ran");
    }

    @Override
    public void onBindViewHolder(@NonNull LoadStateViewHolder loadStateViewHolder, @NonNull LoadState loadState) {
        loadStateViewHolder.bind(loadState);
        Log.d(TAG, "onBindViewHolder: I ran");
    }

    @NonNull
    @Override
    public LoadStateViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, @NonNull LoadState loadState) {
        Log.d(TAG, "onCreateViewHolder: I ran");
        return new LoadStateViewHolder(viewGroup, tripRetryCallback);
    }

    public static class LoadStateViewHolder extends RecyclerView.ViewHolder {
        private ProgressBar progressBar;
        private TextView tripErrorMSG;
        private Button tripRetry;

        public LoadStateViewHolder(@NonNull ViewGroup parent, View.OnClickListener tripRetryCallback) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.load_state_item, parent, false));

            progressBar = parent.findViewById(R.id.progressBar);
            tripErrorMSG = parent.findViewById(R.id.errorMsg);
            tripRetry = parent.findViewById(R.id.retryButton);

            tripRetry.setOnClickListener(tripRetryCallback);
            Log.d(TAG, "LoadStateViewHolder: I ran");
        }

        public void bind(LoadState loadState) {
            if (loadState instanceof LoadState.Error) {
                LoadState.Error loadStateError = (LoadState.Error) loadState;
                tripErrorMSG.setText(loadStateError.getError().getLocalizedMessage());
                setWidgetVisibility(View.VISIBLE);
            }
            setWidgetVisibility(View.GONE);
            Log.d(TAG, "bind: I ran");
        }

        private void setWidgetVisibility(int visible) {
            progressBar.setVisibility(visible);
            tripRetry.setVisibility(visible);
            tripErrorMSG.setVisibility(visible);
            Log.d(TAG, "setWidgetVisibility: I ran");
        }
    }
}
