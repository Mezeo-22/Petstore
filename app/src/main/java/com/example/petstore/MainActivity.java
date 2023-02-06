package com.example.petstore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView errorLog;
    private ProgressBar pBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        errorLog = (TextView) findViewById(R.id.errorLog);
        pBar = (ProgressBar)findViewById(R.id.progressBar);
        pBar.setVisibility(View.INVISIBLE);

        pBar.setVisibility(View.VISIBLE);
        PetAPI petAPI = PetAPI.retrofit.create(PetAPI.class);

        final Call<List<Pet>> call = petAPI.getPet();

        call.enqueue(new Callback<List<Pet>>() {
            @Override
            public void onResponse(Call<List<Pet>> call, Response<List<Pet>> response) {
                if (response.isSuccessful()) {
                    final TextView petName = (TextView) findViewById(R.id.petName);
                    petName.setText(response.body().toString());
                    pBar.setVisibility(View.INVISIBLE);
                } else {

                    ResponseBody errorBody = response.errorBody();
                    try {
                        Toast.makeText(MainActivity.this, errorBody.string(),
                                Toast.LENGTH_SHORT).show();
                        pBar.setVisibility(View.INVISIBLE);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Pet>> call, Throwable t) {
                errorLog.setText("fail");
                pBar.setVisibility(View.INVISIBLE);
            }
        });
    }
}