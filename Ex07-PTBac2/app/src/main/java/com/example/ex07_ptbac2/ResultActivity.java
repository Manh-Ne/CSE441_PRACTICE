package com.example.ex07_ptbac2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class ResultActivity extends AppCompatActivity {
    TextView edtkq;  // Sửa thành TextView thay vì EditText
    Button btnback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        edtkq = findViewById(R.id.txtketqua);
        btnback = findViewById(R.id.btnBack);
        Intent yourIntent = getIntent();
        Bundle yourBundle = yourIntent.getBundleExtra("mybackage");
        int a = yourBundle.getInt("So A");
        int b = yourBundle.getInt("So B");
        String kq = "";
        if (a == 0 && b != 0) {
            kq = "Phương trình vô nghiệm";
        } else if (a == 0 && b == 0) {
            kq = "Phương trình có vô số nghiệm";
        } else {
            DecimalFormat dcf = new DecimalFormat("0.##");
            kq = dcf.format(-b * 1.0 / a);
        }
        edtkq.setText(kq);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}