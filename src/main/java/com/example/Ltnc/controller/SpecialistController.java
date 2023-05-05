package com.example.Ltnc.controller;

import com.example.Ltnc.service.SpecialistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth/")
public class SpecialistController {

    @Autowired
    private SpecialistService specialistService;
}
