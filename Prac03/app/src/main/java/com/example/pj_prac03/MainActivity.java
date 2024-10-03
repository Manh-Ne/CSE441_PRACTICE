package com.example.pj_prac03;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CountryAdapter countryAdapter;
    private List<Country> countryList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rcvCountry);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Tạo danh sách các quốc gia
        countryList = new ArrayList<>();
        countryList.add(new Country("Vietnam", "Hanoi", R.drawable.vn, 97338579, 331212, 294.1, 1.25));
        countryList.add(new Country("China", "Beijing", R.drawable.cn, 1444216107, 9596960, 151, 18.47));
        countryList.add(new Country("India", "New Delhi", R.drawable.in, 1393409038, 3287263, 422.3, 17.70));
        countryList.add(new Country("United States", "Washington, D.C.", R.drawable.us, 331893745, 9833517, 36.7, 4.25));
        countryList.add(new Country("Brazil", "Brasília", R.drawable.br, 213993437, 8515767, 25.1, 2.73));
        countryList.add(new Country("England", "Lon Don", R.drawable.en, 213993437, 8515767, 25.1, 2.73));
        // Thiết lập adapter
        countryAdapter = new CountryAdapter(countryList, this);
        recyclerView.setAdapter(countryAdapter);
    }
}