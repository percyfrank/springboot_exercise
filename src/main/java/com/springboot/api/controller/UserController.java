package com.springboot.api.controller;

import com.springboot.api.dao.Userdao;
import com.springboot.api.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final Userdao userdao;

    public UserController(Userdao userdao) {
        this.userdao = userdao;
    }

    @GetMapping("/user")
    public User add(User user) throws Exception {
        userdao.add(new User("1", "kos", "1234"));
        return userdao.findById("1");
    }

    @DeleteMapping("/user")
    public ResponseEntity<Integer> deleteAll() {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(userdao.deleteAll());
    }


}
