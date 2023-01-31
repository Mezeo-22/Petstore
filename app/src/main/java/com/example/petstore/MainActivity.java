package com.example.petstore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        PetAPI petAPI = PetAPI.retrofit.create(PetAPI.class);

        final Call<List<Pet>> call = petAPI.getPet(1023);

        call.enqueue(new Callback<List<Pet>>() {
            @Override
            public void onResponse(Call<List<Pet>> call, Response<List<Pet>> response) {
                final TextView petname = (TextView) findViewById(R.id.petName);
                petname.setText(response.body().toString());
            }

            @Override
            public void onFailure(Call<List<Pet>> call, Throwable t) {
                final TextView petName = (TextView) findViewById(R.id.petName);
                petName.setText("Что-то пошло не так.");
            }
        });
    }
}