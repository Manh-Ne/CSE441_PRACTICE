package com.example.pj1_ex14;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText edta, edtb;
    Button btnCong;
    ListView lv1;
    ArrayList<String> list;
    ArrayAdapter<String> myarray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControl();
        addEvent();
    }
    private void addEvent(){
        btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XuLyCong();
            }
            private void XuLyCong(){
                int a = Integer.parseInt(edta.getText().toString());
                int b = Integer.parseInt(edtb.getText().toString());
                String c = a+" + "+b+" = "+(a+b);
                list.add(c);
                myarray.notifyDataSetChanged();
                edta.setText("");
                edtb.setText("");
            }
        });
    }
    
}