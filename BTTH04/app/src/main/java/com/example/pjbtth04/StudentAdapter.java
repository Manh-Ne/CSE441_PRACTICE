package com.example.pjbtth04;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {
    private ArrayList<Student> studentList;
    private Context context;

    public StudentAdapter(ArrayList<Student> studentList) {
        this.studentList = studentList;
        this.context = context;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_student_item, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = studentList.get(position);
        holder.txtName.setText(student.getName());
        holder.txtMSV.setText("MSSV: " + student.getMsv());
        holder.txtClass.setText("Lớp: " + student.getClassName());
        holder.txtGPA.setText("Điểm trung bình: " + student.getGpa());

        // Edit Button logic
        holder.btnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(context, EditStudent.class);
            intent.putExtra("msv", student.getMsv());  // Pass MSSV to edit activity
            context.startActivity(intent);
        });

        // Delete Button logic
        holder.btnDelete.setOnClickListener(v -> {
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("sinhvien");
            databaseReference.child(student.getMsv()).removeValue()
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(context, "Sinh viên đã bị xóa", Toast.LENGTH_SHORT).show();
                        studentList.remove(position);
                        notifyItemRemoved(position);  // Update RecyclerView
                    })
                    .addOnFailureListener(e -> Toast.makeText(context, "Lỗi khi xóa sinh viên", Toast.LENGTH_SHORT).show());
        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    static class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtMSV, txtClass, txtGPA;
        Button btnEdit, btnDelete;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtMSV = itemView.findViewById(R.id.txtMSV);
            txtClass = itemView.findViewById(R.id.txtClass);
            txtGPA = itemView.findViewById(R.id.txtGPA);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
