package com.springboot.api.controller;

import com.springboot.api.dao.Userdao;
import com.springboot.api.domain.User;
import com.springboot.api.domain.dto.UserRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final Userdao userdao;

    public UserController(Userdao userdao) {
        this.userdao = userdao;
    }

    @GetMapping("/user")
    public User add(UserRequestDto user) throws Exception {
        userdao.add(new User("2", "kos", "1234"));
        return userdao.findById("2");
    }

    @PostMapping("/user")
    public User postUser(@RequestBody User user) {
        return user;
    }

    @DeleteMapping("/user")
    public ResponseEntity<Integer> deleteAll() {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(userdao.deleteAll());
    }


}
