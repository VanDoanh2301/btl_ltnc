package com.example.khambenh.model.content;

import com.example.khambenh.model.domain.Doctor;
import com.example.khambenh.model.domain.Specialist;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContentSpecical {
    @SerializedName("content")

    private List<Specialist> content;

    public ContentSpecical() {
    }

    public ContentSpecical(List<Specialist> content) {
        this.content = content;
    }

    public List<Specialist> getContent() {
        return content;
    }

    public void setContent(List<Specialist> content) {
        this.content = content;
    }
}

