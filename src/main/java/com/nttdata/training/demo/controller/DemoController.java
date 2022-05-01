package com.nttdata.training.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class DemoController {

    @GetMapping
    private String demo() {
        log.info("Welcome to Updated Demo App - LOGGING");
        return "Welcome to Updated Demo App, this is from staging";
    }

}
