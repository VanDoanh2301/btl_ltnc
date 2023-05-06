package com.example.khambenh;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khambenh.controller.adapter.DoctorAdapter;
import com.example.khambenh.controller.adapter.DoctorDialogAdapter;
import com.example.khambenh.controller.adapter.SpecialistAdapter;
import com.example.khambenh.controller.adapter.TimeAdapter;
import com.example.khambenh.model.api.RetrofitClient;
import com.example.khambenh.model.content.ContentDoctor;
import com.example.khambenh.model.content.ContentSpecical;
import com.example.khambenh.model.domain.Doctor;
import com.example.khambenh.model.domain.Specialist;
import com.example.khambenh.model.domain.Time;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppointmentActivity extends AppCompatActivity {

    private RecyclerView rcTime, rcSpec,rcDtor;
    private TimeAdapter timeAdapter;

    private DoctorDialogAdapter doctorAdapter;
    private SpecialistAdapter specialistAdapter;
    private List<Specialist> specialists;
    private List<Time> timeList;

    private List<Doctor> doctors;

    private TextView txtGone;
    private TextInputEditText edtGioKham, edtChuyenKhoa, edtBacsi;

    private Button backTime,btnSpec, btnDoctor;
    private AlertDialog dialogC,dialogA,dialogB;

    private String period;
    private Long specialistId;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        timeAdapter = new TimeAdapter(this);
        specialistAdapter = new SpecialistAdapter(this);
        doctorAdapter = new DoctorDialogAdapter(this);

        edtChuyenKhoa = findViewById(R.id.edt_chuyenkhoa);
        edtGioKham = findViewById(R.id.edt_giokham);
        edtBacsi = findViewById(R.id.edt_bacsi);

        txtGone = findViewById(R.id.txt_gone);
        specialists = new ArrayList<>();
        doctors = new ArrayList<>();
        timeList = getListTime();
        showTime();
        showSpecial();
        showDoctor();

    }

    @SuppressLint("MissingInflatedId")
    private void showDoctor() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Lựa chọn ");

        View view = getLayoutInflater().inflate(R.layout.layout_doctor, null);
        rcDtor = view.findViewById(R.id.rc_dtor);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcDtor.setLayoutManager(linearLayoutManager);
        btnDoctor = view.findViewById(R.id.btn_back_dt);

        btnDoctor.setOnClickListener(v -> dialogB.dismiss());
        builder.setView(view);
        dialogB = builder.create();
        dialogB.getWindow().setBackgroundDrawableResource(R.drawable.custom_dialog);
        dialogB.getWindow().getAttributes().windowAnimations = R.style.MydialogAnimation;
        edtBacsi.setOnClickListener(v -> {
            doctors.clear();
            RetrofitClient.getRetrofit().getAllDoctorByTime(period, specialistId).enqueue(new Callback<List<Doctor>>() {
                @Override
                public void onResponse(Call<List<Doctor>> call, Response<List<Doctor>> response) {
                    if(response.body() == null) {
                        Toast.makeText(AppointmentActivity.this, "Danh sách Trống", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    doctors = response.body();
                    doctorAdapter.setData(doctors);
                    rcDtor.setAdapter(doctorAdapter);
                }

                @Override
                public void onFailure(Call<List<Doctor>> call, Throwable t) {
                    Toast.makeText(AppointmentActivity.this, "Call fail", Toast.LENGTH_SHORT).show();

                }
            });
            dialogB.show();
        });

        doctorAdapter.setOnItemClickListener(position -> {
            Doctor doctor = doctors.get(position);
            edtBacsi.setText(doctor.getName());
            dialogB.dismiss();
        });
    }


    @SuppressLint("MissingInflatedId")
    private void showSpecial() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Lựa chọn ");

        View view = getLayoutInflater().inflate(R.layout.layout_specialist, null);
        rcSpec = view.findViewById(R.id.rc_special);

        RetrofitClient.getRetrofit().getAllSpec().enqueue(new Callback<ContentSpecical>() {
            @Override
            public void onResponse(Call<ContentSpecical> call, Response<ContentSpecical> response) {
                if(response.isSuccessful()) {
                    ContentSpecical content = response.body();
                    if (content ==null) {
                        return;
                    }
                    List<Specialist> s = content.getContent();
                    specialists.addAll(s);
                    specialistAdapter.setData(specialists);
                    rcSpec.setAdapter(specialistAdapter);
                }
            }
            @Override
            public void onFailure(Call<ContentSpecical> call, Throwable t) {

                Toast.makeText(AppointmentActivity.this, "Call fail", Toast.LENGTH_SHORT).show();
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcSpec.setLayoutManager(linearLayoutManager);
        btnSpec = view.findViewById(R.id.btn_back_spec);

        btnSpec.setOnClickListener(v -> dialogC.dismiss());
        builder.setView(view);
        dialogC = builder.create();
        dialogC.getWindow().setBackgroundDrawableResource(R.drawable.custom_dialog);
        dialogC.getWindow().getAttributes().windowAnimations = R.style.MydialogAnimation;
        edtChuyenKhoa.setOnClickListener(v -> {
            dialogC.show();
        });
        specialistAdapter.setOnItemClickListener(position -> {
            Specialist specialist = specialists.get(position);
            specialistId = specialist.getSpecialistId();
            edtChuyenKhoa.setText(specialist.getSpecialistName());
            dialogC.dismiss();
        });
    }

    @SuppressLint("MissingInflatedId")
    private void showTime() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Lựa chọn ");

        View view = getLayoutInflater().inflate(R.layout.layout_time, null);
        rcTime = view.findViewById(R.id.rc_time);

        timeAdapter.setData(getListTime());
        rcTime.setAdapter(timeAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rcTime.setLayoutManager(gridLayoutManager);
        backTime = view.findViewById(R.id.btn_back_time);

        backTime.setOnClickListener(v -> dialogA.dismiss());
        builder.setView(view);
        dialogA = builder.create();
        dialogA.getWindow().setBackgroundDrawableResource(R.drawable.custom_dialog);
        dialogA.getWindow().getAttributes().windowAnimations = R.style.MydialogAnimation;
        edtGioKham.setOnClickListener(v -> {
            dialogA.show();
        });

        timeAdapter.setOnItemClickListener(position -> {
            Time time = timeList.get(position);
            edtGioKham.setText(time.getPeriod());
            period = time.getPeriod();

            if(doctors != null) {
            edtBacsi.setVisibility(View.VISIBLE);}

            dialogA.dismiss();

        });
    }

    private List<Time> getListTime() {
        List<Time> timeList = new ArrayList<>();
        timeList.add(new Time("6:00-7:00"));
        timeList.add(new Time("8:00-9:00"));
        timeList.add(new Time("10:00-11:00"));
        timeList.add(new Time("13:00-14:00"));
        timeList.add(new Time("15:00-16:00"));
       return  timeList;
    }
}