package com.example.pjbtth04;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity {
    private Button btnAddStudent;
    private RecyclerView recyclerView;
    private StudentAdapter studentAdapter;
    private ArrayList<Student> studentList = new ArrayList<>();
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddStudent = findViewById(R.id.btnAdd);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(studentAdapter);
        databaseReference = FirebaseDatabase.getInstance().getReference("sinhvien");
        studentList = new ArrayList<>();
        studentAdapter = new StudentAdapter(studentList);
        loadStudents();

        btnAddStudent.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddStudent.class);
            startActivity(intent);
        });
    }

    private void loadStudents() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                studentList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Student student = dataSnapshot.getValue(Student.class);
                    if (student != null) {
                        studentList.add(student);
                    }
                }
                studentAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Failed to load data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
