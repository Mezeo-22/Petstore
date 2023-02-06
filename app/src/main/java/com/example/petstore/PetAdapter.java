package com.example.petstore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PetAdapter {

    private final static String photoUrls = "petstore.swagger.io/v2/pet/1023";
    private List<Pet> pets;
    private Context context;
}
