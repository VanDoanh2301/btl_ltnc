package com.example.khambenh.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.khambenh.R;
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
    private TextInputEditText txtChuyenKhoa, txtNgayKham, txtGioKham, txtNote,edtName;

    private Button btnSave;
    private Long doctorId;
    private Long patientId;
    private Long id;

    private ImageView imgClose;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_doctor);

        edtName =  findViewById(R.id.txtInName_text);
        btnSave = findViewById(R.id.btn_save);
        txtBacsi = findViewById(R.id.txt_bs);
        txtChuyenKhoa = findViewById(R.id.txt_chuyenkhoa);
        txtNgayKham = findViewById(R.id.txt_ngaykham);
        txtNote = findViewById(R.id.txt_note);
        txtGioKham = findViewById(R.id.txt_giokham);
        imgClose = findViewById(R.id.icon_close_dt);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        id = sharedPreferences.getLong("patientId", -1);
        String name = sharedPreferences.getString("name","");
        edtName.setText(name);
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

        //Close activity
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AppointmentDoctorActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void saveAppointment() {
        String ngayKham = txtNgayKham.getText().toString().trim();
        String day = txtGioKham.getText().toString().trim();
        String note = txtNote.getText().toString();
        Appointment appointment = new Appointment(ngayKham, day, note, doctorId, id);

        if(TextUtils.isEmpty(ngayKham)) {
            txtNgayKham.setError("Input text");

        }

        if(TextUtils.isEmpty(day)) {
            txtGioKham.setError("Input text");

        }

        if(TextUtils.isEmpty(note)) {
            txtNote.setError("Input text");

        }
        apiCreateAppointment(appointment);
    }

    private void apiCreateAppointment(Appointment appointment) {
        RetrofitClient.getRetrofit().createAppointment(appointment).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                Intent i = new Intent(AppointmentDoctorActivity.this, InforAppointmentActivity.class);
                startActivity(i);
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