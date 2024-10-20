package com.example.pj24;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MyArrayAdapter extends ArrayAdapter<TyGia> {
    Activity context;
    int resource;
    List<TyGia> objects;
    public MyArrayAdapter(Activity context, int resource, List<TyGia> objects){
        super(context, resource, objects);
        this.context = context;
        this.objects = objects;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View item = inflater.inflate(this.resource, null);
        TyGia tyGia = this.objects.get(position);
        ImageView imgHinh = (ImageView) item.findViewById(R.id.imgHinh);
        TextView txtType = (TextView) item.findViewById(R.id.txtType);
        TextView txtMuaTM = (TextView) item.findViewById(R.id.txtMuaTM);
        TextView txtBanTM = (TextView) item.findViewById(R.id.txtBanTM);
        TextView txtMuaCK = (TextView) item.findViewById(R.id.txtMuaCK);
        TextView txtBanCK = (TextView) item.findViewById(R.id.txtBanCK);

        imgHinh.setImageBitmap(tyGia.getBitmap());
        txtType.setText(tyGia.getMuatienmat());
        txtMuaTM.setText(tyGia.getMuatienmat());
        txtBanTM.setText(tyGia.getBantienmat());
        txtMuaCK.setText(tyGia.getMuack());;
        txtBanCK.setText(tyGia.getBanck());
        return item;
    }
}