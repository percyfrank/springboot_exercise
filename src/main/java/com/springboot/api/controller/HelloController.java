package com.springboot.api.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @RequestMapping(value = "/hello")
    public String hello() {
        return "Hello World";
    }


}


