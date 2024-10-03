package com.example.pj_prac03;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {
    private List<Country> countries;
    private Context context;

    public CountryAdapter(List<Country> countries, Context context) {
        this.countries = countries;
        this.context = context;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate layout item_country cho từng item trong danh sách
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_country, parent, false);
        return new CountryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        Country currentCountry = countries.get(position);

        // Bind dữ liệu vào các thành phần của ViewHolder
        holder.imageView.setImageResource(currentCountry.getFlag());
        holder.countryTextView.setText(currentCountry.getName());
        holder.capitalTextView.setText(currentCountry.getCountryCapital());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, CountryDetail.class);
                intent.putExtra("countryName", currentCountry.getName());
                intent.putExtra("capital", currentCountry.getCountryCapital());
                intent.putExtra("population", currentCountry.getPopulation());
                intent.putExtra("area", currentCountry.getArea());
                intent.putExtra("density", currentCountry.getDensity());
                intent.putExtra("worldShare", currentCountry.getWorldShare());
                intent.putExtra("flagResource", currentCountry.getFlag());


                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }


    static class CountryViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView countryTextView;
        TextView capitalTextView;

        CountryViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imv_flag);
            countryTextView = itemView.findViewById(R.id.txtCountry);
            capitalTextView = itemView.findViewById(R.id.txtCapital);
        }
    }
}