package com.example.pj_btth03;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private List<Student> studentList;
    private OnItemClickListener listener;

    public StudentAdapter(List<Student> studentList) {
        this.studentList = studentList;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onEditClick(int position);
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_item, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = studentList.get(position);


        String studentInfo = "ID: " + student.getId() +
                "\nName: " + student.getName() +
                "\nBirthDay: " + student.getBirthDay() +
                "\nAddress: " + student.getAddress() +
                "\nEmail: " + student.getEmail() +
                "\nMajor: " + student.getMajor() +
                "\nGPA: " + student.getGpa() +
                "\nYear: " + student.getYear() +
                "\nGender: " + student.getGender();

        holder.txtName.setText(studentInfo);  // Hiển thị thông tin sinh viên

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(position);
            }
        });
        holder.btnEdit.setOnClickListener(v -> {
            if (listener != null) {
                listener.onEditClick(position);
            }
        });
        holder.btnDelete.setOnClickListener(v -> {
            if (listener != null) {
                listener.onDeleteClick(position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return studentList.size();
    }

    static class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView txtName;
        Button btnEdit, btnDelete;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
