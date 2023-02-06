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
        errorLog.setText("PetAPI retrofit");

        final Call<List<Pet>> call = petAPI.getPet(1102);

        errorLog.setText("final Call");

        call.enqueue(new Callback<List<Pet>>() {
            @Override
            public void onResponse(Call<List<Pet>> call, Response<List<Pet>> response) {
                errorLog.setText("onResponse");
                if (response.isSuccessful()) {
                    final TextView petName = (TextView) findViewById(R.id.petName);
                    petName.setText(response.body().toString());
                    errorLog.setText("setPetName");
                    pBar.setVisibility(View.INVISIBLE);
                } else {
                    errorLog.setText("onResponse else");
                    ResponseBody errorBody = response.errorBody();
                    errorLog.setText("errorBody");
                    try {
                        Toast.makeText(MainActivity.this, errorBody.string(),
                                Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        errorLog.setText("e else");
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Pet>> call, Throwable t) {
                errorLog.setText("onFailure");
                final TextView petName = (TextView) findViewById(R.id.petName);
                petName.setText("Что-то пошло не так.");
                pBar.setVisibility(View.INVISIBLE);
            }
        });
    }
}