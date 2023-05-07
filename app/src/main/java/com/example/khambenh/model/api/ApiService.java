package com.example.khambenh.model.api;

import com.example.khambenh.model.content.ContentSpecical;
import com.example.khambenh.model.domain.Appointment;
import com.example.khambenh.model.content.ContentDoctor;
import com.example.khambenh.model.domain.Doctor;
import com.example.khambenh.model.domain.Patient;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("doctors")
    Call<ContentDoctor> getAllDoctor();

    @GET("doctors/times")
    Call<List<Doctor>> getAllDoctorByTime(@Query("period") String period, @Query("specialistId") Long specialistId);

    @GET("specialists")
    Call<ContentSpecical> getAllSpec();

    @GET("doctors/{id}")
    Call<Doctor> getDoctorId(@Path("id") Long id);
    @Headers("Content-Type: application/json")
    @POST("newAppointment")
    Call<String> createAppointment(@Body Appointment appointment);


    @POST("login")
    Call<Patient> login(@Query("email") String email, @Query("password") String password);

    @POST("newPatient")
    Call<String> register(@Body Patient patient);
}
