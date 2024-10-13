package com.example.pj_btth03;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton fabAddStudent;
    private List<Student> studentList;
    private StudentAdapter studentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        fabAddStudent = findViewById(R.id.fabAddStudent);

        // Initialize student list and adapter
        studentList = loadStudentsFromJson();
        studentAdapter = new StudentAdapter(studentList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(studentAdapter);

        // Add event to FloatingActionButton
        fabAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddStudent.class);
                startActivityForResult(intent, 1);
            }
        });

        // Handle item click (view details)
        studentAdapter.setOnItemClickListener(new StudentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // View student details
                Intent intent = new Intent(MainActivity.this, DetailStudent.class);
                intent.putExtra("student", studentList.get(position));
                startActivity(intent);
            }

            @Override
            public void onEditClick(int position) {
                // Edit student
                Intent intent = new Intent(MainActivity.this, EditStudentActivity.class);
                intent.putExtra("student", studentList.get(position));
                startActivityForResult(intent, 2);
            }

            @Override
            public void onDeleteClick(int position) {
                // Delete student
                studentList.remove(position);
                studentAdapter.notifyItemRemoved(position);
            }
        });
    }

    private List<Student> loadStudentsFromJson() {
        // Load student list from JSON (sample data)
        List<Student> students = new ArrayList<>();
        // Add mock data
        students.add(new Student("1", "Nguyễn Văn A", "2000-01-01", "Hà Nội", "a@gmail.com", "Công nghệ thông tin", 3.5, 2, "Nam"));
        students.add(new Student("2", "Trần Thị B", "1999-05-20", "TP. Hồ Chí Minh", "b@gmail.com", "Kinh tế", 3.7, 3, "Nữ"));
        return students;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            Student newStudent = (Student) data.getSerializableExtra("newStudent");
            studentList.add(newStudent);
            studentAdapter.notifyItemInserted(studentList.size() - 1);
        }
    }
}
