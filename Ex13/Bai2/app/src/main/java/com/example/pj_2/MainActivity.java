package com.example.pj_2;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    String[] arr = {"Ipad","Iphone","New Ipad","Samsung","Nokia","Sony Ericson","LG","Q-Mobile","HTC","Blacberry","G-Phone","FPT-Phone"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView selection = (TextView) findViewById(R.id.selection);
        final GridView gv = (GridView) findViewById(R.id.gridView1);
        ArrayAdapter<String>da = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arr);
        gv.setAdapter(da);
    }
}