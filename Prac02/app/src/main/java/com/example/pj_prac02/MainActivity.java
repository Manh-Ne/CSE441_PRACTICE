package com.example.pj_prac02;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private StaffViewModel staffViewModel;
    private TextView txtStaffResult;
    private TextView txtStatus;
    private EditText edtStaffid, edtStaffName, edtBirthDay, edtSalary;
    private Button btnAddStaff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      
        txtStaffResult = findViewById(R.id.tvStaffResult);
        txtStatus = findViewById(R.id.tvStatus);
        edtStaffid = findViewById(R.id.edtStaffId);
        edtStaffName = findViewById(R.id.edtStaffName);
        edtBirthDay = findViewById(R.id.edtBirthDate);
        edtSalary = findViewById(R.id.edtSalary);
        btnAddStaff = findViewById(R.id.btnAddStaff);

        // Khởi tạo ViewModel
        staffViewModel = new ViewModelProvider(this).get(StaffViewModel.class);

        // Quản lí danh sách nhân viên
        staffViewModel.getStaffList().observe(this, new Observer<List<Staff>>() {
            @Override
            public void onChanged(List<Staff> staffList) {
                if (staffList != null && !staffList.isEmpty()) {
                    // Kiểm tra số lượng nhân viên và cập nhật trạng thái
                    if (staffList.size() >= 2) {
                        txtStatus.setText("Sau khi thêm vài nhân viên");
                    } else {
                        txtStatus.setText("Đã nhận nút thêm mới");
                    }

                    // Hiển thị thông tin
                    StringBuilder staffDisplay = new StringBuilder();
                    for (Staff staff : staffList) {
                        staffDisplay.append(staff.getId()).append("-")
                                .append(staff.getName()).append("-")
                                .append(staff.getBirthDay()).append("-")
                                .append(staff.getSalary()).append("\n");
                    }
                    txtStaffResult.setText(staffDisplay.toString());
                }
            }
        });

        // Theo dõi và cập nhật trạng thái
        TextWatcher inputWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Kiểm tra tất cả các trường đã được điền chưa
                if (!edtStaffid.getText().toString().isEmpty() &&
                        !edtStaffName.getText().toString().isEmpty() &&
                        !edtBirthDay.getText().toString().isEmpty() &&
                        !edtSalary.getText().toString().isEmpty()) {

                    txtStatus.setText("Đã nhập nhưng chưa nhận nút");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };

        // Thêm Watcher vào các field
        edtStaffid.addTextChangedListener(inputWatcher);
        edtStaffName.addTextChangedListener(inputWatcher);
        edtBirthDay.addTextChangedListener(inputWatcher);
        edtSalary.addTextChangedListener(inputWatcher);


        btnAddStaff.setOnClickListener(view -> {
            // Truy xuất dữ liệu đầu vào
            String id = edtStaffid.getText().toString();
            String name = edtStaffName.getText().toString();
            String birthDay = edtBirthDay.getText().toString();
            String salary = edtSalary.getText().toString();


            if (!id.isEmpty() && !name.isEmpty() && !birthDay.isEmpty() && !salary.isEmpty()) {
                // Thêm nhân viên mới thông qua ViewModel
                staffViewModel.addStaff(id, name, birthDay, salary);

                // Cập nhật trạng thái sau khi thêm
                if (staffViewModel.getStaffList().getValue().size() >= 2) {
                    txtStatus.setText("Sau khi thêm vài nhân viên");
                } else {
                    txtStatus.setText("Đã nhận nút thêm mới");
                }
            } else {
                txtStatus.setText("Chưa nhập đủ dữ liệu");
            }
        });
    }
}
