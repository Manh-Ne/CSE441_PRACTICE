package com.example.ex07_ptbac2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText edta, edtb;
    Button btnkq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edta =  findViewById(R.id.txta);
        edtb = findViewById(R.id.txtb);
        btnkq = findViewById(R.id.btnketqua);
        btnkq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(MainActivity.this, ResultActivity.class);
                Bundle bundle1 = new Bundle();
                int a = Integer.parseInt(edta.getText().toString());
                int b = Integer.parseInt(edtb.getText().toString());
                bundle1.putInt("So A", a);
                bundle1.putInt("So B", b);
                myintent.putExtra("mybackage", bundle1);
                startActivity(myintent);
            }
        });
    }
}