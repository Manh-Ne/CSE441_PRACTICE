package com.example.pj_prac03;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CountryDetail extends AppCompatActivity {
    private ImageView imvFlagDetail;
    private TextView txtCountryNameDetail, txtCapitalDetail, txtPopulation, txtArea, txtDensity, txtWorldShare;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_detail);

        imvFlagDetail = findViewById(R.id.imv_flag_detail);
        txtCountryNameDetail = findViewById(R.id.txtCountryNameDetail);
        txtPopulation = findViewById(R.id.txtPopulation);
        txtCapitalDetail = findViewById(R.id.txtCapitalDetail);
        txtArea = findViewById(R.id.txtArea);
        txtDensity = findViewById(R.id.txtDensity);
        txtWorldShare = findViewById(R.id.txtWorldShare);

        // Lấy dữ liệu từ Intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String countryName = extras.getString("countryName");
            String capital = extras.getString("capital");
            int population = extras.getInt("population");
            double area = extras.getDouble("area");
            double density = extras.getDouble("density");
            double worldShare = extras.getDouble("worldShare");
            int flagResource = extras.getInt("flagResource");


            txtCountryNameDetail.setText(countryName);
            txtCapitalDetail.setText("Capital: " + capital);
            txtPopulation.setText("Population: " + population);
            txtArea.setText("Area: " + area + " sq km");
            txtDensity.setText("Density: " + density + " per sq km");
            txtWorldShare.setText("World Share: " + worldShare + "%");


            imvFlagDetail.setImageResource(flagResource);
        }
    }

}