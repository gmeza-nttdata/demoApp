package com.nttdata.training.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping
    private String demo() {
        return "Welcome to Demo App";
    }

}
