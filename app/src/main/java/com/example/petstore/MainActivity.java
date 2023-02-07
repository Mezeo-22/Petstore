package com.example.petstore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView errorLog;
    private ProgressBar pBar;
    private TextView petName;
    private ImageView petImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        petImage = (ImageView)findViewById(R.id.petImage);
        errorLog = (TextView) findViewById(R.id.errorLog);
        pBar = (ProgressBar)findViewById(R.id.progressBar);
        petName = (TextView) findViewById(R.id.petName);

        pBar.setVisibility(View.INVISIBLE);
        pBar.setVisibility(View.VISIBLE);

        PetAPI petAPI = PetAPI.retrofit.create(PetAPI.class);
        Call<Pet> call = petAPI.getPet();

        call.enqueue(new Callback<Pet>() {
            @Override
            public void onResponse(Call<Pet> call, Response<Pet> response) {
                if (response.isSuccessful()) {
                    Pet cutie = response.body();
                    petName.setText(cutie.getName());
                    Picasso.get().load(cutie.getPhotoUrls().get(0)).into(petImage);
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
            public void onFailure(Call<Pet> call, Throwable t) {
                errorLog.setText("Что-то пошло не так!");
                pBar.setVisibility(View.INVISIBLE);
            }
        });
    }
}