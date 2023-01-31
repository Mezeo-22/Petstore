package com.example.petstore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.Viewholder> {

    private final static String photoUrls = "petstore.swagger.io/v2/pet/1023";
    private List<Pet> pets;
    private Context context;

    PetAdapter(List<Pet> pets) { this.pets = pets; }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_main, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        Pet pet = pets.get(position);
    }

    @Override
    public int getItemCount() {
        if (pets == null) {
            return 0;
        }
        return pets.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView petName;
        ImageView petImage;

        ViewHolder(View itemView) {
            super(itemView);
            petName = (TextView) itemView.findViewById(R.id.petName);
            petImage = (ImageView) itemView.findViewById(R.id.petImage);
        }
    }
}
