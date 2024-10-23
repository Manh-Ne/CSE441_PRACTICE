package com.example.pj_btth03;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
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

        studentList = loadStudentsFromJson();
        studentAdapter = new StudentAdapter(studentList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(studentAdapter);


        fabAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddStudentActivity.class);
                startActivityForResult(intent, 1);
            }
        });


        studentAdapter.setOnItemClickListener(new StudentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }

            @Override
            public void onEditClick(int position) {

                Intent intent = new Intent(MainActivity.this, EditStudentActivity.class);
                intent.putExtra("editStudent", studentList.get(position));
                intent.putExtra("position", position);
                startActivityForResult(intent, 2);
            }

            @Override
            public void onDeleteClick(int position) {
                // Tạo AlertDialog để xác nhận xóa
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Xác nhận xóa")
                        .setMessage("Bạn có chắc chắn muốn xóa sinh viên này không?")
                        .setPositiveButton("Có", (dialog, which) -> {
                            studentList.remove(position);
                            studentAdapter.notifyItemRemoved(position);
                        })
                        .setNegativeButton("Không", (dialog, which) -> dialog.dismiss())
                        .show();
            }

        });
    }

    // Giả lập dữ liệu sinh viên ban đầu
    private List<Student> loadStudentsFromJson() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("1", "Nguyễn Văn A", "2000-01-01", "Hà Nội", "a@gmail.com", "Công nghệ thông tin", 3.5, 2, "Nam"));
        students.add(new Student("2", "Trần Thị B", "1999-05-20", "TP. Hồ Chí Minh", "b@gmail.com", "Kinh tế", 3.7, 3, "Nữ"));
        return students;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == 1) {
                Student newStudent = (Student) data.getSerializableExtra("newStudent");
                studentList.add(newStudent);
                studentAdapter.notifyItemInserted(studentList.size() - 1);
            } else if (requestCode == 2) {
                Student updatedStudent = (Student) data.getSerializableExtra("updatedStudent");
                int position = data.getIntExtra("position", -1);
                if (position != -1 && updatedStudent != null) {
                    studentList.set(position, updatedStudent);
                    studentAdapter.notifyItemChanged(position);
                }
            }
        }
    }
}
