package com.example.khambenh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.khambenh.model.api.RetrofitClient;
import com.example.khambenh.model.domain.Patient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class
RegisterActivity extends AppCompatActivity {

    private EditText edtName, edtAddress, edtDate, edtIdCard, edtPhone, edtEmail, edtPassword;
    private AppCompatButton btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtName = findViewById(R.id.name_edt);
        edtAddress = findViewById(R.id.address_edt);
        edtDate = findViewById(R.id.date_edt);
        edtIdCard = findViewById(R.id.idcard_edt);
        edtPhone = findViewById(R.id.phone_edt);
        edtEmail = findViewById(R.id.email_edt);
        edtPassword = findViewById(R.id.passwords_edt);


        btnRegister = findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString().trim();
                String address = edtAddress.getText().toString().trim();
                String dateStr = edtDate.getText().toString().trim();
                String idCard = edtIdCard.getText().toString().trim();
                Long phone = Long.valueOf(edtPhone.getText().toString().trim());
                String email = edtEmail.getText().toString().trim();
                String password = edtPassword.getText().toString();
                Patient patient = new Patient(name, email, phone, idCard, address, password, dateStr);
                createPatient(patient);


            }
        });

        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickDate();
            }
        });
    }

    private void onClickDate() {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                RegisterActivity.this,
                (view, year1, monthOfYear, dayOfMonth) -> {
                    edtDate.setText(year1 + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                },
                year, month, day);
        datePickerDialog.show();
    }

    private void createPatient(Patient patient) {
        RetrofitClient.getRetrofit().register(patient).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body().equals("Phone number already exists")) {
                    Toast.makeText(RegisterActivity.this, "Phone is required", Toast.LENGTH_SHORT).show();
                }
                if (response.body().equals("ID card number already exists")) {
                    Toast.makeText(RegisterActivity.this, "ID card number already exists", Toast.LENGTH_SHORT).show();
                }
                if (response.body().equals("Invalid email address")) {
                    Toast.makeText(RegisterActivity.this, "Invalid email address", Toast.LENGTH_SHORT).show();
                }
                if (response.body().equals("Name is required")) {
                    Toast.makeText(RegisterActivity.this, "Name is required", Toast.LENGTH_SHORT).show();
                }
                if (response.body().equals("IdCard is required")) {
                    Toast.makeText(RegisterActivity.this, "IdCard is required", Toast.LENGTH_SHORT).show();
                }
                if (response.body().equals("successfully")) {
                    Toast.makeText(RegisterActivity.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Call fail", Toast.LENGTH_SHORT).show();
            }
        });
    }
}