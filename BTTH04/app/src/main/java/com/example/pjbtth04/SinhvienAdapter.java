package com.example.pjbtth04;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class SinhvienAdapter extends RecyclerView.Adapter<SinhvienAdapter.SinhVienViewHolder> {
    private List<Sinhvien> sinhVienList;
    private DatabaseReference databaseReference;
    private Context context;

    public SinhvienAdapter(List<Sinhvien> sinhVienList, DatabaseReference databaseReference, Context context) {
        this.sinhVienList = sinhVienList;
        this.databaseReference = databaseReference;
        this.context = context;
    }

    @NonNull
    @Override
    public SinhVienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sinhvien, parent, false);
        return new SinhVienViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SinhVienViewHolder holder, int position) {
        Sinhvien sinhVien = sinhVienList.get(position);
        holder.tvHoTen.setText("Name: " + sinhVien.getHoten());
        holder.tvMsv.setText("MSSV: " + sinhVien.getMasv());
        holder.tvLop.setText("Lớp: " + sinhVien.getLop());
        holder.tvDiem.setText("GPA: " + sinhVien.getDiem());

        holder.btnD.setOnClickListener(v -> {
            String sinhVienId = sinhVien.getMasv();
            databaseReference.child(sinhVienId).removeValue()
                    .addOnSuccessListener(aVoid -> {
                        sinhVienList.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, sinhVienList.size());
                        Toast.makeText(context, "Xóa thành công!", Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(context, "Xóa thất bại: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        });
        holder.btnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), EditStudent.class);
            intent.putExtra("sinhVienId", sinhVien.getMasv());
            intent.putExtra("sinhVien", sinhVien);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return sinhVienList.size();
    }

    public static class SinhVienViewHolder extends RecyclerView.ViewHolder {
        TextView tvHoTen, tvLop, tvDiem, tvMsv;
        ImageButton btnD,btnEdit;

        public SinhVienViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHoTen = itemView.findViewById(R.id.tvhoten);
            tvMsv = itemView.findViewById(R.id.tvmsv);
            tvLop = itemView.findViewById(R.id.tvclass);
            tvDiem = itemView.findViewById(R.id.tvGba);
            btnD = itemView.findViewById(R.id.btnXoa);
            btnEdit = itemView.findViewById(R.id.btnSua);
        }
    }
}
