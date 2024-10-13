package com.example.pj_btth03;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class AddStudent extends AppCompatActivity {
    private EditText edtId, edtName, edtBirthDay, edtAddress, edtEmail, edtGPA;
    private Spinner spinnerMajor, spinnerYear;
    private RadioGroup radioGroupGender;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        // Initialize views
        edtId = findViewById(R.id.edtId);
        edtName = findViewById(R.id.edtName);
        edtBirthDay = findViewById(R.id.edtBirthDay);
        edtAddress = findViewById(R.id.edtAddress);
        edtEmail = findViewById(R.id.edtEmail);
        edtGPA = findViewById(R.id.edtGPA);
        spinnerMajor = findViewById(R.id.spinnerMajor);
        spinnerYear = findViewById(R.id.spinnerYear);
        radioGroupGender = findViewById(R.id.llGender);

        // Set up submit button
        Button btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInputs()) {
                    Student students = createStudentFromInputs();
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("students", students);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
            }
        });
    }

    private boolean validateInputs() {
        if (edtId.getText().toString().isEmpty() ||
                edtName.getText().toString().isEmpty() ||
                edtBirthDay.getText().toString().isEmpty() ||
                edtAddress.getText().toString().isEmpty() ||
                edtEmail.getText().toString().isEmpty() ||
                edtGPA.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private Student createStudentFromInputs() {
        String id = edtId.getText().toString();
        String name = edtName.getText().toString();
        String birthDay = edtBirthDay.getText().toString();
        String address = edtAddress.getText().toString();
        String email = edtEmail.getText().toString();
        String major = spinnerMajor.getSelectedItem().toString();
        double gpa = Double.parseDouble(edtGPA.getText().toString());
        int year = Integer.parseInt(spinnerYear.getSelectedItem().toString());

        // Handling gender selection
        int selectedGenderId = radioGroupGender.getCheckedRadioButtonId();
        if (selectedGenderId == -1) {
            Toast.makeText(this, "Please select a gender", Toast.LENGTH_SHORT).show();
            return null;
        }
        String gender = ((RadioButton)findViewById(selectedGenderId)).getText().toString();

        return new Student(id, name, birthDay, address, email, major, gpa, year, gender);
    }
}
