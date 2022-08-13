package com.miroplanting.citybikedatafrontend;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class SplashScreenActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences settings = getApplicationContext().getSharedPreferences("apiInfo", 0);
        String apiAddress = settings.getString("apiURL", "");

        if (apiAddress.isEmpty()) {
            startActivity(new Intent(getApplicationContext(), APIAddressActivity.class));
        } else {
            startActivity(new Intent(getApplicationContext(), ListPickerActivity.class));
        }
    }
}
