package com.example.pjbtth04;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddStudent extends AppCompatActivity {
    private EditText edtName, edtMSV, edtClass, edtGPA;
    private Button btnCreate;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        edtName = findViewById(R.id.edtName);
        edtMSV = findViewById(R.id.edtMSV);
        edtClass = findViewById(R.id.edtClass);
        edtGPA = findViewById(R.id.edtGPA);
        btnCreate = findViewById(R.id.btnCreate);
        databaseReference = FirebaseDatabase.getInstance().getReference("sinhvien");

        btnCreate.setOnClickListener(view -> {
            String name = edtName.getText().toString().trim();
            String msv = edtMSV.getText().toString().trim();
            String studentClass = edtClass.getText().toString().trim();
            String gpa = edtGPA.getText().toString().trim();

            if (validateInput(name, msv, studentClass, gpa)) {
                checkMSVAndAddStudent(msv, name, studentClass, gpa);
            }
        });
    }

    private void checkMSVAndAddStudent(String msv, String name, String studentClass, String gpa) {
        databaseReference.child(msv).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Toast.makeText(AddStudent.this, "MSSV đã tồn tại!", Toast.LENGTH_SHORT).show();
                } else {
                    Student student = new Student(msv, name, studentClass, Double.parseDouble(gpa));
                    databaseReference.child(msv).setValue(student);
                    Toast.makeText(AddStudent.this, "Thêm sinh viên thành công!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(AddStudent.this, "Lỗi khi thêm sinh viên", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validateInput(String name, String msv, String studentClass, String gpa) {
        if (name.isEmpty() || msv.isEmpty() || studentClass.isEmpty() || gpa.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
