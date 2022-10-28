package com.springboot.api.controller;

import com.springboot.api.dao.Userdao;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final Userdao userdao;

    public UserController(Userdao userdao) {
        this.userdao = userdao;
    }


}
