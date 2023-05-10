package com.example.khambenh.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.khambenh.R;
import com.example.khambenh.controller.adapter.AppointmentAdapter;
import com.example.khambenh.model.api.RetrofitClient;
import com.example.khambenh.model.domain.Appointment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InforAppointmentActivity extends AppCompatActivity {

    private RecyclerView rc;
    private AppointmentAdapter adapter;
    private List<Appointment> appointments = new ArrayList<>();
    private Toolbar toolbar;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor_appointment);

        toolbar = findViewById(R.id.tl_inforA);
        rc = findViewById(R.id.rc_date);
        adapter = new AppointmentAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rc.setLayoutManager(linearLayoutManager);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        Long patientId = sharedPreferences.getLong("patientId", -1);

        getAppointment(patientId);

       adapter.setOnItemClickListener(new AppointmentAdapter.OnItemClickListener() {
           @Override
           public void onItemClick(int position) {
               Appointment appointment = appointments.get(position);
               Long id = appointment.getId();
               Intent i = new Intent(InforAppointmentActivity.this, InforAppointmentIdActivity.class);
               Bundle bundle = new Bundle();
               bundle.putLong("id", id);
               i.putExtra("data", bundle);
               startActivity(i);
           }
       });
    }

    private void getAppointment(Long patientId) {
        RetrofitClient.getRetrofit().getAppointmentByPatientId(patientId).enqueue(new Callback<List<Appointment>>() {
            @Override
            public void onResponse(Call<List<Appointment>> call, Response<List<Appointment>> response) {
                if(response.body() == null) {
                    Toast.makeText(InforAppointmentActivity.this, "Danh sach trong", Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Appointment> app = response.body();
                appointments.addAll(app);
                adapter.setData(appointments);
                rc.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Appointment>> call, Throwable t) {
                Toast.makeText(InforAppointmentActivity.this, "Call error", Toast.LENGTH_SHORT).show();

            }
        });
    }
}