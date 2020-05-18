package com.example.android.pets;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.pets.data.Mascota;

import java.util.List;

public class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.petsViewHolder> {
    private final List<Mascota> mascotaList;
    private LayoutInflater mInflater;
    private Context context;

    public AdapterRecyclerView(Context context, List<Mascota> mascotaList){
        mInflater = LayoutInflater.from(context);
        this.mascotaList = mascotaList;
        this.context = context;
    }

    @NonNull
    @Override
    public petsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.item_list_view, parent, false);
        return new petsViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull petsViewHolder holder, int position) {
        Mascota mCurrent = mascotaList.get(position);
        holder.name.setText(mCurrent.getName());
        holder.breed.setText(mCurrent.getBreed());
    }

    @Override
    public int getItemCount() {
        return mascotaList.size();
    }

    class petsViewHolder extends RecyclerView.ViewHolder
                         implements View.OnClickListener{
        private TextView name, breed;
        private AdapterRecyclerView adapter;

        public petsViewHolder(@NonNull View itemView, AdapterRecyclerView adapter) {
            super(itemView);
            name = itemView.findViewById(R.id.TextViewName);
            breed = itemView.findViewById(R.id.TextViewBreed);
            this.adapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getLayoutPosition();
            Mascota mascota = mascotaList.get(position);
            Intent receptor = new Intent(context, EditorActivity.class);

            receptor.putExtra("id", mascota.getId());
            receptor.putExtra("name", mascota.getName());
            receptor.putExtra("breed", mascota.getBreed());
            receptor.putExtra("gender", mascota.getGender());
            receptor.putExtra("weight", mascota.getWeight());
            context.startActivity(receptor);
            adapter.notifyDataSetChanged();
        }
    }
}
