package com.example.khambenh.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.khambenh.R;
import com.example.khambenh.model.api.RetrofitClient;
import com.example.khambenh.model.domain.Appointment;
import com.example.khambenh.model.domain.Doctor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InforAppointmentIdActivity extends AppCompatActivity {

    private EditText edtName, edtDate, edtTime, edtNote;
    Long doctorId;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor_appointment_id);

        edtName = findViewById(R.id.doctoredt);
        edtDate = findViewById(R.id.dateedt);
        edtTime = findViewById(R.id.timeedt);
        edtNote = findViewById(R.id.noteedt);


        Intent i = getIntent();
        Bundle bundle = i.getBundleExtra("data");
        Long id = bundle.getLong("id");

        RetrofitClient.getRetrofit().getAppointmentId(id).enqueue(new Callback<Appointment>() {
            @Override
            public void onResponse(Call<Appointment> call, Response<Appointment> response) {
                Appointment appointment = response.body();
                doctorId = appointment.getDoctor();
                RetrofitClient.getRetrofit().getDoctorId(doctorId).enqueue(new Callback<Doctor>() {
                    @Override
                    public void onResponse(Call<Doctor> call, Response<Doctor> response) {
                        Doctor doctor = response.body();
                        edtName.setText(doctor.getName());
                    }

                    @Override
                    public void onFailure(Call<Doctor> call, Throwable t) {

                    }
                });
                edtDate.setText(appointment.getAppointmentDate());
                edtTime.setText(appointment.getPeriod());
                edtNote.setText(appointment.getNote());
            }

            @Override
            public void onFailure(Call<Appointment> call, Throwable t) {

            }
        });


    }
}