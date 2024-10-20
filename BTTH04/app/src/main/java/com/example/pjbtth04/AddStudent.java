package com.example.pjbtth04;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddStudent extends AppCompatActivity {
    private EditText edtHoten, edtLop, edtMSSV, edtDiem;
    private Button btnSubmit;
    private DatabaseReference databaseReference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        edtHoten = findViewById(R.id.editN1);
        edtLop = findViewById(R.id.editL1);
        edtMSSV = findViewById(R.id.editM1);
        edtDiem = findViewById(R.id.editG1);
        btnSubmit = findViewById(R.id.btnSubmit);

        databaseReference = FirebaseDatabase.getInstance().getReference("sinhvien");

        btnSubmit.setOnClickListener(v -> {
            String hoten = edtHoten.getText().toString();
            String lop = edtLop.getText().toString();
            String mssv = edtMSSV.getText().toString();
            String diemStr = edtDiem.getText().toString();

            if (hoten.isEmpty() || lop.isEmpty() || mssv.isEmpty() || diemStr.isEmpty()) {
                Toast.makeText(AddStudent.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                return;
            }

            double diem;
            try {
                diem = Double.parseDouble(diemStr);
            } catch (NumberFormatException e) {
                Toast.makeText(AddStudent.this, "Điểm trung bình không hợp lệ!", Toast.LENGTH_SHORT).show();
                return;
            }
            addStudentToFirebase(mssv, hoten, lop, diem);
        });
    }

    private void addStudentToFirebase(String mssv, String hoten, String lop, double diem) {
        databaseReference.child(mssv).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Toast.makeText(AddStudent.this, "Sinh viên đã tồn tại!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Sinhvien newSinhVien = new Sinhvien(hoten, lop, diem, mssv);
                databaseReference.child(mssv).setValue(newSinhVien)
                        .addOnSuccessListener(aVoid -> {
                            Intent resultIntent = new Intent();
                            resultIntent.putExtra("newSinhVien", newSinhVien);
                            setResult(RESULT_OK, resultIntent);
                            Toast.makeText(AddStudent.this, "Thêm sinh viên thành công!", Toast.LENGTH_SHORT).show();
                            finish();
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(AddStudent.this, "Thêm sinh viên thất bại!", Toast.LENGTH_SHORT).show();
                        });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AddStudent.this, "Lỗi khi kiểm tra sinh viên: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
