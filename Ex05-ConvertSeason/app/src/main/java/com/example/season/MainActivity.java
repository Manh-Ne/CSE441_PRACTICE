package com.example.season;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button btnChuyenDoi;
    EditText edtNamDuongLich;
    TextView txtNamAmLich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnChuyenDoi = findViewById(R.id.btnChuyenDoi);
        edtNamDuongLich = findViewById(R.id.edtNamDuongLich);
        txtNamAmLich = findViewById(R.id.txtNamAmLich);

        // Xử lý sự kiện khi nhấn nút chuyển đổi
        btnChuyenDoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namDuongLichStr = edtNamDuongLich.getText().toString();

                if (!namDuongLichStr.isEmpty()) {
                    int namDuongLich = Integer.parseInt(namDuongLichStr);
                    String namAmLich = chuyenDoiNamAmLich(namDuongLich);
                    txtNamAmLich.setText(namAmLich);
                } else {
                    txtNamAmLich.setText("Vui lòng nhập năm dương lịch.");
                }
            }
        });
    }

    // Phương thức chuyển đổi năm dương lịch sang năm âm lịch
    private String chuyenDoiNamAmLich(int namDuongLich) {
        // Danh sách các thiên can
        String[] thienCan = {
                "Giáp", "Ất", "Bính", "Đinh", "Mậu", "Kỷ",
                "Canh", "Tân", "Nhâm", "Quý"
        };

        // Danh sách các địa chi
        String[] diaChi = {
                "Tý", "Sửu", "Dần", "Mão", "Thìn", "Tỵ",
                "Ngọ", "Mùi", "Thân", "Dậu", "Tuất", "Hợi"
        };

        // Tính thiên can và địa chi
        int indexThienCan = (namDuongLich - 4) % 10; // Thiên can bắt đầu từ năm 4
        int indexDiaChi = (namDuongLich - 4) % 12;   // Địa chi bắt đầu từ năm 4

        // Kết hợp thiên can và địa chi
        return thienCan[indexThienCan] + " " + diaChi[indexDiaChi];
    }
}