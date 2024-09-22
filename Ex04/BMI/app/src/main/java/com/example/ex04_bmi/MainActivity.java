package com.example.ex04_bmi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    Button btnChuanDoan;
    EditText editTen, editChieucao, editCannang, editBMI, editChuandoan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        btnChuanDoan = findViewById(R.id.btnTinhBMI);
        editTen = findViewById(R.id.edtTen);
        editChieucao = findViewById(R.id.edtChieuCao);
        editCannang = findViewById(R.id.edtCanNang);
        editBMI = findViewById(R.id.edtBMI);
        editChuandoan = findViewById(R.id.edtChuanDoan);
        btnChuanDoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double H = Double.parseDouble(editChieucao.getText()+"");
                double W = Double.parseDouble(editCannang.getText()+"");
                double BMI = W/Math.pow(H,2);
                String chuanDoan = "";
                if (BMI < 18) {
                    chuanDoan="Bạn gầy";
                } else if(BMI<=24.9){
                    chuanDoan="Bạn bình thường";
                } else if(BMI<=29.9){
                    chuanDoan="Bạn béo phì độ 1";
                } else if (BMI<=34.9) {
                    chuanDoan="Bạn béo phì độ 2";
                } else {
                    chuanDoan="Bạn béo phì độ 3";
                }
                DecimalFormat dcf = new DecimalFormat("#.0");
                editBMI.setText(dcf.format(BMI));
                editChuandoan.setText(chuanDoan);
            }
        });
    }
}