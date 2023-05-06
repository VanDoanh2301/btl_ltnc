package com.example.khambenh;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;

import com.example.khambenh.controller.adapter.ImageAdapter;
import com.example.khambenh.model.domain.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {

    private Button btnBook, btnDay, btnDoctor, btnBackDialog;
    private AlertDialog dialog;
    private List<Image> images;

    private ViewPager viewPager;
    private ImageAdapter adapter;
    private Timer timer;
    private CircleIndicator circleIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnBook = findViewById(R.id.btn_datlich);

        circleIndicator= findViewById(R.id.circle_photo);
        viewPager = findViewById(R.id.view_photo);
        images = getListImage();
        adapter = new ImageAdapter(this,images);
        viewPager.setAdapter(adapter);
        circleIndicator.setViewPager(viewPager);
        adapter.registerDataSetObserver(circleIndicator.getDataSetObserver());

        showDialog();
        slideBar();
    }

    //Slide bar
    private void slideBar() {
        images = getListImage();
        if(timer ==null) {
            timer =new Timer();
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int currentItem = viewPager.getCurrentItem();
                        int total = images.size()-1;
                        if(currentItem<total) {
                            currentItem++;
                            viewPager.setCurrentItem(currentItem);
                        } else {
                            viewPager.setCurrentItem(0);
                        }
                    }
                });

            }
        },200, 3000);
    }

    //Create dialog
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
    //Set data image
    private List<Image> getListImage() {
        List<Image> images = new ArrayList<>();
        images.add(new Image(R.drawable.img_1));
        images.add(new Image(R.drawable.img));
        images.add(new Image(R.drawable.img_2));
        return images;
    }
}