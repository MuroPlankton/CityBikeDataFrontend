package com.miroplanting.citybikedatafrontend.api;

import android.content.Context;
import android.content.SharedPreferences;

import com.miroplanting.citybikedatafrontend.api.APIInterface;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    static APIInterface apiInterface;

    public static APIInterface getApiInterface(Context context) {

        if (apiInterface == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().connectTimeout(5, TimeUnit.SECONDS)
                    .writeTimeout(5, TimeUnit.SECONDS).readTimeout(5, TimeUnit.SECONDS)
                    .addInterceptor(interceptor).build();

            SharedPreferences settings = context.getSharedPreferences("apiInfo", 0);
            String apiAddress = settings.getString("apiURL", "");

            Retrofit retrofit = new Retrofit.Builder().baseUrl(apiAddress)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create()).client(client).build();

            apiInterface = retrofit.create(APIInterface.class);
        }
        return apiInterface;
    }
}
