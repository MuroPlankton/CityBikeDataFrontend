package com.miroplanting.citybikedatafrontend;

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

public class DataLoadStateAdapter extends LoadStateAdapter<DataLoadStateAdapter.LoadStateViewHolder> {
    private View.OnClickListener retryCallback;

    private static final String TAG = "TripsLoadStateAdapter";

    public DataLoadStateAdapter(View.OnClickListener retryCallback) {
        this.retryCallback = retryCallback;
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
        return new LoadStateViewHolder(viewGroup, retryCallback);
    }

    public static class LoadStateViewHolder extends RecyclerView.ViewHolder {
        private ProgressBar progressBar;
        private TextView errorMSG;
        private Button retry;

        public LoadStateViewHolder(@NonNull ViewGroup parent, View.OnClickListener retryCallback) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.load_state_item, parent, false));

            progressBar = parent.findViewById(R.id.progressBar);
            errorMSG = parent.findViewById(R.id.errorMsg);
            retry = parent.findViewById(R.id.retryButton);

            retry.setOnClickListener(retryCallback);
            Log.d(TAG, "LoadStateViewHolder: I ran");
        }

        public void bind(LoadState loadState) {
            if (loadState instanceof LoadState.Error) {
                LoadState.Error loadStateError = (LoadState.Error) loadState;
                errorMSG.setText(loadStateError.getError().getLocalizedMessage());
                setWidgetVisibility(View.VISIBLE);
            }
            setWidgetVisibility(View.GONE);
            Log.d(TAG, "bind: I ran");
        }

        private void setWidgetVisibility(int visible) {
            progressBar.setVisibility(visible);
            retry.setVisibility(visible);
            errorMSG.setVisibility(visible);
            Log.d(TAG, "setWidgetVisibility: I ran");
        }
    }
}
