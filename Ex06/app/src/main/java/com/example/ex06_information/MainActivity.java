package com.example.ex06_information;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText edtTen, editCMND, editBosung;
    CheckBox chkDocBao, chkDocSach, chkDocCode;
    Button btnSend;
    RadioGroup group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtTen = findViewById(R.id.editHoTen);
        editCMND = findViewById(R.id.editCMND);
        editBosung = findViewById(R.id.editBosung);
        chkDocBao = findViewById(R.id.cbDocBao);
        chkDocSach = findViewById(R.id.cbDocSach);
        chkDocCode = findViewById(R.id.cbDocCode);
        btnSend = findViewById(R.id.btnGuiTT);
        group = findViewById(R.id.idGroup);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doShowInformation();
            }
        });
    }

    @SuppressLint("WrongViewCast")
    public void doShowInformation() {
        String ten = edtTen.getText().toString().trim();
        if (ten.length() < 3) {
            edtTen.requestFocus();
            edtTen.selectAll();
            Toast.makeText(this, "Tên phải >= 3 ký tự", Toast.LENGTH_LONG).show();
            return;
        }

        String cmnd = editCMND.getText().toString().trim();
        if (cmnd.length() != 9) {
            editCMND.requestFocus();
            editCMND.selectAll();
            Toast.makeText(this, "CMND phải đúng 9 ký tự", Toast.LENGTH_LONG).show();
            return;
        }

        int id = group.getCheckedRadioButtonId();
        if (id == -1) {
            Toast.makeText(this, "Phải chọn bằng cấp", Toast.LENGTH_LONG).show();
            return;
        }

        RadioButton rad = findViewById(id);
        String bang = rad.getText().toString();

        StringBuilder sothich = new StringBuilder();
        if (chkDocBao.isChecked())
            sothich.append(chkDocBao.getText()).append("\n");
        if (chkDocSach.isChecked())
            sothich.append(chkDocSach.getText()).append("\n");
        if (chkDocCode.isChecked())
            sothich.append(chkDocCode.getText()).append("\n");

        String bosung = editBosung.getText().toString();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông tin cá nhân")
                .setMessage(String.format("%s\n%s\n%s\n%s\n_______\nThông tin bổ sung: \n%s\n_______\n",
                        ten, cmnd, bang, sothich.toString(), bosung))
                .setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.cancel();
                    }
                });

        builder.create().show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
        b.setTitle("Question")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

        b.create().show();
    }
}