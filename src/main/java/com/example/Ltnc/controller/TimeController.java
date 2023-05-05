package com.example.Ltnc.controller;

import com.example.Ltnc.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth/")
public class TimeController {
    @Autowired
    private TimeService timeService;
}
