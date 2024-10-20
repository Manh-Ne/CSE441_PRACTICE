package com.example.pjbtth04;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class EditStudent extends AppCompatActivity {

    private EditText edtHoten, edtLop, edtMSSV, edtDiem;
    private Button btnUpdate;
    private DatabaseReference databaseReference;
    private String sinhVienId;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        edtHoten = findViewById(R.id.editN1);
        edtLop = findViewById(R.id.editL1);
        edtMSSV = findViewById(R.id.editM1);
        edtDiem = findViewById(R.id.editG1);
        btnUpdate = findViewById(R.id.btnUpdate);
        Intent intent = getIntent();
        sinhVienId = intent.getStringExtra("sinhVienId");
        Sinhvien sinhVien = (Sinhvien) intent.getSerializableExtra("sinhVien");

        if (sinhVien != null) {
            edtHoten.setText(sinhVien.getHoten());
            edtLop.setText(sinhVien.getLop());
            edtMSSV.setText(sinhVien.getMasv());
            edtDiem.setText(String.valueOf(sinhVien.getDiem()));
        }

        databaseReference = FirebaseDatabase.getInstance().getReference("sinhvien");

        btnUpdate.setOnClickListener(v -> {
            String hoten = edtHoten.getText().toString();
            String lop = edtLop.getText().toString();
            String mssv = edtMSSV.getText().toString();
            double diem;

            try {
                diem = Double.parseDouble(edtDiem.getText().toString());
            } catch (NumberFormatException e) {
                Toast.makeText(EditStudent.this, "Điểm không hợp lệ!", Toast.LENGTH_SHORT).show();
                return;
            }
            Sinhvien updatedSinhVien = new Sinhvien(hoten, lop, diem, mssv);
            databaseReference.child(sinhVienId).setValue(updatedSinhVien)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(EditStudent.this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                        finish();
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(EditStudent.this, "Cập nhật thất bại: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        });
    }
}