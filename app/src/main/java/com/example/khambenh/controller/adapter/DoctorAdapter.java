package com.example.khambenh.controller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.khambenh.R;
import com.example.khambenh.model.domain.Doctor;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder> {

    private Context context;
    private List<Doctor> doctors;

    public DoctorAdapter(Context context) {
        this.context = context;
    }
    public void setData(List<Doctor> doctors) {
        this.doctors = doctors;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_list_doctor, parent, false);
        return new DoctorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorViewHolder holder, int position) {
        Doctor doctor = doctors.get(position);
        if (doctor == null) {
            return;
        }
        Glide.with(context).load(doctor.getAvatar()).into(holder.imgAvatar);
        holder.txtName.setText(doctor.getName());
        holder.txtPhone.setText(doctor.getPhone());
        holder.txtDay.setText(doctor.getDay());
        holder.txtSpec.setText(doctor.getSpecialist().getSpecialistName());
    }

    @Override
    public int getItemCount() {
        return (doctors == null) ? 0:doctors.size();
    }

    public class DoctorViewHolder extends RecyclerView.ViewHolder {
        private ShapeableImageView imgAvatar;
        private TextView txtName,txtPhone,txtDay,txtSpec;
        public DoctorViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.img_doctor);
            txtName = itemView.findViewById(R.id.txt_name);
            txtDay = itemView.findViewById(R.id.txt_day);
            txtPhone = itemView.findViewById(R.id.txt_phone);
            txtSpec = itemView.findViewById(R.id.txt_spec);
        }
    }
}
