package com.example.khambenh;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnBook, btnDay, btnDoctor, btnBackDialog;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnBook = findViewById(R.id.btn_datlich);
        showDialog();
    }

    @SuppressLint("MissingInflatedId")
    void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Lựa chọn");
        View view = getLayoutInflater().inflate(R.layout.layout_dialog, null);
        btnDay = view.findViewById(R.id.btn_ngay);
        btnDoctor = view.findViewById(R.id.btn_bacsi);
        btnBackDialog = view.findViewById(R.id.btn_back_dialog);
        btnBackDialog.setOnClickListener(v -> dialog.dismiss());
        builder.setView(view);
        dialog = builder.create();
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.custom_dialog);
        dialog.getWindow().getAttributes().windowAnimations = R.style.MydialogAnimation;
        btnBook.setOnClickListener(v -> {
            dialog.show();
        });
        btnDay.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, AppointmentActivity.class);
            startActivity(i);
        });
        btnDoctor.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, DoctorActivity.class);
            startActivity(i);
        });

    }
}