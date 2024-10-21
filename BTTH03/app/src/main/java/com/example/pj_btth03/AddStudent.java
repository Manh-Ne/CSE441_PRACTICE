package com.example.pj_btth03;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddStudent extends AppCompatActivity {

    private EditText edtId, edtName, edtBirthDay, edtAddress, edtEmail, edtGPA;
    private Spinner spinnerMajor, spinnerYear;
    private RadioGroup radioGroupGender;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        edtId = findViewById(R.id.edtId);
        edtName = findViewById(R.id.edtName);
        edtBirthDay = findViewById(R.id.edtBirthDay);
        edtAddress = findViewById(R.id.edtAddress);
        edtEmail = findViewById(R.id.edtEmail);
        edtGPA = findViewById(R.id.edtGPA);
        spinnerMajor = findViewById(R.id.spinnerMajor);
        spinnerYear = findViewById(R.id.spinnerYear);
        radioGroupGender = findViewById(R.id.llGender);
        btnSubmit = findViewById(R.id.btnSubmit);


        setupSpinners();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edtId.getText().toString().trim();
                String name = edtName.getText().toString().trim();
                String birthDay = edtBirthDay.getText().toString().trim();
                String address = edtAddress.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();
                String gpaString = edtGPA.getText().toString().trim();


                clearErrors();


                if (id.isEmpty()) {
                    edtId.setError("Mã sinh viên không được để trống");
                    edtId.requestFocus();
                    return;
                }
                if (name.isEmpty()) {
                    edtName.setError("Tên không được để trống");
                    edtName.requestFocus();
                    return;
                }
                if (birthDay.isEmpty()) {
                    edtBirthDay.setError("Ngày sinh không được để trống");
                    edtBirthDay.requestFocus();
                    return;
                }
                if (address.isEmpty()) {
                    edtAddress.setError("Địa chỉ không được để trống");
                    edtAddress.requestFocus();
                    return;
                }
                if (email.isEmpty()) {
                    edtEmail.setError("Email không được để trống");
                    edtEmail.requestFocus();
                    return;
                }
                if (!isValidEmail(email)) {
                    edtEmail.setError("Email không hợp lệ");
                    edtEmail.requestFocus();
                    return;
                }
                if (gpaString.isEmpty()) {
                    edtGPA.setError("GPA không được để trống");
                    edtGPA.requestFocus();
                    return;
                }

                double gpa;
                try {
                    gpa = Double.parseDouble(gpaString);
                    if (gpa < 0 || gpa > 9) {
                        edtGPA.setError("GPA phải nằm trong khoảng từ 0 đến 4");
                        edtGPA.requestFocus();
                        return;
                    }
                } catch (NumberFormatException e) {
                    edtGPA.setError("GPA không hợp lệ");
                    edtGPA.requestFocus();
                    return;
                }

                String major = spinnerMajor.getSelectedItem().toString();
                int year = Integer.parseInt(spinnerYear.getSelectedItem().toString());

                String gender = "Khác";
                int selectedId = radioGroupGender.getCheckedRadioButtonId();
                if (selectedId == R.id.rbMale) {
                    gender = "Nam";
                } else if (selectedId == R.id.rbFemale) {
                    gender = "Nữ";
                }


                Student newStudent = new Student(id, name, birthDay, address, email, major, gpa, year, gender);
                Intent resultIntent = new Intent();
                resultIntent.putExtra("newStudent", newStudent);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }


    private void clearErrors() {
        edtId.setError(null);
        edtName.setError(null);
        edtBirthDay.setError(null);
        edtAddress.setError(null);
        edtEmail.setError(null);
        edtGPA.setError(null);
    }


    private boolean isValidEmail(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return email.matches(emailPattern);
    }

    private void setupSpinners() {

        String[] majors = {"Công nghệ thông tin", "Kinh tế", "Điện tử viễn thông"};
        String[] years = {"1", "2", "3", "4"};

        ArrayAdapter<String> majorAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, majors);
        majorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMajor.setAdapter(majorAdapter);

        ArrayAdapter<String> yearAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, years);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerYear.setAdapter(yearAdapter);
    }
}
