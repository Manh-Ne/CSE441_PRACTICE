package com.example.pj16;

import android.content.SharedPreferences;
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
    EditText edtA, edtB, edtKQ;
    Button btnTong, btnClear;
    TextView txtLichSu;
    String lichsu = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtA = findViewById(R.id.edtA);
        edtB = findViewById(R.id.edtB);
        edtKQ = findViewById(R.id.edtKQ);
        btnTong = findViewById(R.id.btnTong);
        btnClear = findViewById(R.id.btnClear);
        txtLichSu = findViewById(R.id.txtLichSu);
        SharedPreferences myprefs = getSharedPreferences("mysave", MODE_PRIVATE);
        lichsu = myprefs.getString("ls","");
        txtLichSu.setText(lichsu);
        btnTong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = Integer.parseInt(edtA.getText().toString());
                int b = Integer.parseInt(edtB.getText().toString());
                int kq = a + b;
                edtKQ.setText(kq+"");
                lichsu += a+" + "+b+" = "+kq;
                txtLichSu.setText(lichsu);
                lichsu += "\n";
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lichsu="";
                txtLichSu.setText(lichsu);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences myprefs = getSharedPreferences("mysave", MODE_PRIVATE);
        SharedPreferences.Editor myedit = myprefs.edit();
        myedit.putString("ls", lichsu);
        myedit.commit();
    }
}