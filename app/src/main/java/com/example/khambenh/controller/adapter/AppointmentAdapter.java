package com.example.khambenh.controller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.khambenh.R;
import com.example.khambenh.model.domain.Appointment;

import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppViewHolder>{
    private Context context;
    private List<Appointment> appointments;

    public AppointmentAdapter(Context context) {
        this.context = context;
    }

   public void setData(List<Appointment> appointments) {
        this.appointments =appointments;
        notifyDataSetChanged();
    }
    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_appointment, parent, false);
        return new AppViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppViewHolder holder, int position) {

        Appointment appointment = appointments.get(position);
        if(appointment == null) {
            return;
        }
        holder.txtDate.setText(appointment.getAppointmentDate());
    }

    @Override
    public int getItemCount() {
        return (appointments == null) ? 0:appointments.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public class AppViewHolder extends RecyclerView.ViewHolder {
        private TextView txtDate;
        private CardView cardView;
        public AppViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDate = itemView.findViewById(R.id.txt_dateC);
            cardView = itemView.findViewById(R.id.card_calendar);

            txtDate.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            });

        }
    }
}
