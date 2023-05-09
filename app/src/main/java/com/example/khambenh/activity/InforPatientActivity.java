package com.example.khambenh.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.khambenh.R;
import com.example.khambenh.model.api.RetrofitClient;
import com.example.khambenh.model.domain.Patient;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InforPatientActivity extends AppCompatActivity {
    private EditText edtName, edtAddress,edtDate,edtIdCard,edtPhone,edtEmail;

    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor_patient);
        edtName = findViewById(R.id.edt_name);
        edtAddress = findViewById(R.id.edt_address);
        edtDate = findViewById(R.id.edt_date);
        edtIdCard = findViewById(R.id.edt_idcard);
        edtPhone = findViewById(R.id.edt_phone);
        edtEmail = findViewById(R.id.edt_email);
        toolbar = findViewById(R.id.tl_infor);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getPatient();
    }

    private void getPatient() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String email = sharedPreferences.getString("email", "");

        RetrofitClient.getRetrofit().getPatientByEmail(email).enqueue(new Callback<Patient>() {
            @Override
            public void onResponse(Call<Patient> call, Response<Patient> response) {
                Patient patient = response.body();

                Date date = patient.getBirthYear();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String dateStr = formatter.format(date);

                edtName.setText(patient.getName());
                edtAddress.setText(patient.getAddress());
                edtIdCard.setText(patient.getIdCard());
                edtDate.setText(dateStr);
                edtPhone.setText(String.valueOf(patient.getPhone()));
                edtEmail.setText(patient.getEmail());
            }

            @Override
            public void onFailure(Call<Patient> call, Throwable t) {

                Toast.makeText(InforPatientActivity.this, "Call error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}