package com.example.pj_3;

import android.app.Activity;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class MyArrayAdapter extends ArrayAdapter<Phone> {
    Activity context;
    int idLayout;
    ArrayList<Phone> myList;

    public MyArrayAdapter(Activity context, int idLayout, ArrayList<Phone> myList){
        super(context, idLayout, myList);
        this.context = context;
        this.idLayout = idLayout;
        this.myList = myList;
    }
    
}
