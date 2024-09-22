package com.example.ptbac2;


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

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    Button btnTieptuc, btnGiai, btnThoat;
    EditText edta, edtb, edtc;
    TextView txtkq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnTieptuc = findViewById(R.id.btnTieptuc);
        btnGiai = findViewById(R.id.btnGiaipt);
        btnThoat = findViewById(R.id.btnThoat);
        edta = findViewById(R.id.edita);
        edtb = findViewById(R.id.editb);
        edtc = findViewById(R.id.editc);
        txtkq = findViewById(R.id.txtKq);
        btnGiai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sa = edta.getText().toString();
                String sb = edtb.getText().toString();
                String sc = edtc.getText().toString();


                int a = Integer.parseInt(sa);
                int b = Integer.parseInt(sb);
                int c = Integer.parseInt(sc);
                String kq = "";
                DecimalFormat dcf = new DecimalFormat("0.00");


                if (a == 0) {
                    if (b == 0) {
                        if (c == 0) {
                            kq = "PT vô số nghiệm";
                        } else {
                            kq = "PT vô nghiệm";
                        }
                    } else {
                        kq = "PT có 1 No, x = " + dcf.format(-c / (double) b);
                    }
                } else {
                    double delta = b * b - 4 * a * c;
                    if (delta < 0) {
                        kq = "PT vô nghiệm";
                    } else if (delta == 0) {
                        kq = "PT có No kép x1=x2=" + dcf.format(-b / (2.0 * a));
                    } else {
                        double x1 = (-b + Math.sqrt(delta)) / (2 * a);
                        double x2 = (-b - Math.sqrt(delta)) / (2 * a);
                        kq = "PT có 2 No: x1=" + dcf.format(x1) + "; x2=" + dcf.format(x2);
                    }
                }


                txtkq.setText(kq);
            }
        });
        btnTieptuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edta.setText("");
                edtb.setText("");
                edtc.setText("");
                edta.requestFocus();
            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}