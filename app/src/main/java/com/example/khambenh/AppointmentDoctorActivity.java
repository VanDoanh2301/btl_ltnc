package com.example.khambenh;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.khambenh.model.api.RetrofitClient;
import com.example.khambenh.model.domain.Appointment;
import com.example.khambenh.model.domain.Doctor;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppointmentDoctorActivity extends AppCompatActivity {

    private TextInputEditText txtBacsi;
    private TextInputEditText txtChuyenKhoa, txtNgayKham, txtGioKham, txtNote;

    private Button btnSave;
    private Long doctorId;
    private Long patientId;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_doctor);

        btnSave = findViewById(R.id.btn_save);
        txtBacsi = findViewById(R.id.txt_bs);
        txtChuyenKhoa = findViewById(R.id.txt_chuyenkhoa);
        txtNgayKham = findViewById(R.id.txt_ngaykham);
        txtNote = findViewById(R.id.txt_note);
        txtGioKham = findViewById(R.id.txt_giokham);
        txtNgayKham.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    AppointmentDoctorActivity.this,
                    (view, year1, monthOfYear, dayOfMonth) -> {
                        txtNgayKham.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year1);

                    },
                    year, month, day);
            datePickerDialog.show();
        });
        txtBacsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AppointmentDoctorActivity.this, DoctorActivity.class);
                startActivity(i);
            }
        });
        readData();
        btnSave.setOnClickListener(v -> {
            saveAppointment();

        });
    }

    private void saveAppointment() {
        String ngayKham = txtNgayKham.getText().toString().trim();
        String day = txtGioKham.getText().toString().trim();
        String note = txtNote.getText().toString();
        Appointment appointment = new Appointment(ngayKham, day, note, doctorId, 1L);
        apiCreateAppointment(appointment);
    }

    private void apiCreateAppointment(Appointment appointment) {
        RetrofitClient.getRetrofit().createAppointment(appointment).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                Toast.makeText(AppointmentDoctorActivity.this, "Save", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                Toast.makeText(AppointmentDoctorActivity.this, "Call error", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void readData() {
        Intent i = getIntent();
        Bundle bundle = i.getBundleExtra("dataId");
        if (bundle == null) {
            return;
        }
        doctorId = bundle.getLong("doctorId");
        RetrofitClient.getRetrofit().getDoctorId(doctorId).enqueue(new Callback<Doctor>() {
            @Override
            public void onResponse(Call<Doctor> call, Response<Doctor> response) {
                Doctor doctor = response.body();
                txtBacsi.setText(doctor.getName());
                txtChuyenKhoa.setText(doctor.getSpecialist().getSpecialistName());
                txtGioKham.setText(doctor.getDay());
            }

            @Override
            public void onFailure(Call<Doctor> call, Throwable t) {
                Toast.makeText(AppointmentDoctorActivity.this, "Call error", Toast.LENGTH_SHORT).show();

            }
        });
    }
}