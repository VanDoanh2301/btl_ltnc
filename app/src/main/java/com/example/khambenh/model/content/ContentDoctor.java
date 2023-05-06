package com.example.khambenh.model.content;

import com.example.khambenh.model.domain.Doctor;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContentDoctor {
    @SerializedName("content")
    private List<Doctor> content;


    public ContentDoctor() {
    }

    public ContentDoctor(List<Doctor> content) {
        this.content = content;
    }

    public List<Doctor> getContent() {
        return content;
    }

    public void setContent(List<Doctor> content) {
        this.content = content;
    }
}
