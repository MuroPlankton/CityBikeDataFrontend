package com.miroplanting.citybikedatafrontend;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    static APIInterface apiInterface;
    private static final String TAG = "APIClient";

    public static APIInterface getApiInterface(Context context) {

        if (apiInterface == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            SharedPreferences settings = context.getSharedPreferences("apiInfo", 0);
            String apiAddress = settings.getString("apiURL", "");
            Log.d("APIClient", "ApiURL: " + apiAddress);

            Retrofit retrofit = new Retrofit.Builder().baseUrl(apiAddress)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create()).client(client).build();

            apiInterface = retrofit.create(APIInterface.class);
        }
        Log.d(TAG, "getApiInterface: I ran");
        return apiInterface;
    }
}
