package com.springboot.api.controller;

import com.springboot.api.domain.dto.MemberDto;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class HelloController {

    @RequestMapping(value = "/hello")
    public String hello() {
        return "Hello World";
    }


}


