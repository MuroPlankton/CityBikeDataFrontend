package com.miroplanting.citybikedatafrontend;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class APIAddressActivity extends Activity {

    private Button urlSaveButton;
    private EditText backendURLEditText;

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String text = backendURLEditText.getText().toString();
            if (text.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Can't save empty URL!", Toast.LENGTH_SHORT).show();
            } else {
                SharedPreferences settings = getApplicationContext().getSharedPreferences("apiInfo", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("apiURL", text);
                editor.apply();

                startActivity(new Intent(getApplicationContext(), ListPickerActivity.class));
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_address);
        urlSaveButton = findViewById(R.id.activity_api_address_button);
        backendURLEditText = findViewById(R.id.activity_api_address_edit_text);
        urlSaveButton.setOnClickListener(onClickListener);
    }
}
