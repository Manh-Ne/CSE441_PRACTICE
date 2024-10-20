package com.example.pjbtth04;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Danhsach extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private List<Sinhvien> sinhVienList;
    private RecyclerView recyclerView;
    private SinhvienAdapter adapter;
    private ImageButton btnAdd, btnEdit;
    private ActivityResultLauncher<Intent> addSinhvienLauncher;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.View1);
        btnAdd = findViewById(R.id.btnAdd);
        btnEdit = findViewById(R.id.btnSua);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        sinhVienList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("sinhvien");
        adapter = new SinhvienAdapter(sinhVienList, databaseReference, getApplicationContext());
        recyclerView.setAdapter(adapter);

        loadDataFromFirebase();

        addSinhvienLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Sinhvien newSinhVien = (Sinhvien) result.getData().getSerializableExtra("newSinhVien");
                        if (newSinhVien != null) {
                            sinhVienList.add(newSinhVien);
                            adapter.notifyItemInserted(sinhVienList.size() - 1);
                        }
                    }
                }
        );

        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(Danhsach.this, AddStudent.class);
            addSinhvienLauncher.launch(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadDataFromFirebase();
    }

    private void loadDataFromFirebase() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                sinhVienList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Sinhvien sinhVien = snapshot.getValue(Sinhvien.class);
                    if (sinhVien != null) {
                        sinhVienList.add(sinhVien);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Danhsach.this, "Failed to load data.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
