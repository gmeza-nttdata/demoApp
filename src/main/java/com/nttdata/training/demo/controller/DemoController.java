package com.nttdata.training.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class DemoController {

    @Operation(
            summary = "1.0 Say Hi Operation"
    )
    @GetMapping
    private String demo() {
        log.info("Welcome to Updated Demo App - LOGGING");
        return "Welcome to Demo App";
    }

}
