package com.springboot.api.controller;

import com.springboot.api.domain.User;
import com.springboot.api.dto.UserRequestDto;
import com.springboot.api.repository.UserDao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserDao userdao;

    public UserController(UserDao userdao) {
        this.userdao = userdao;
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<User> get(@PathVariable String id) {
        return ResponseEntity
                .ok()
                .body(userdao.findById(id));
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity
                .ok()
                .body(userdao.getAll());
    }

    @PostMapping(value = "/users")
    public ResponseEntity<User> add(@RequestBody UserRequestDto userRequestDto) {
        User user = new User(userRequestDto.getId(), userRequestDto.getName(), userRequestDto.getPassword());
        return ResponseEntity
                .ok()
                .body(userdao.add(user));
    }
    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<Integer> deleteById(@PathVariable String id) {
        return ResponseEntity
                .ok()
                .body(userdao.deleteById(id));
    }
    @DeleteMapping(value = "/users")
    public ResponseEntity<Integer> deleteAll() {
        return ResponseEntity
                .ok()
                .body(userdao.deleteAll());
    }

//    @GetMapping(value = "/users/{id}")
//    public User get(@PathVariable String id) {
//        return userdao.findById(id);
//    }
//
//    @GetMapping(value = "/users")
//    public List<User> getAll() {
//        return userdao.getAll();
//    }
//
//    @PostMapping(value = "/users")
//    public User add(@RequestBody UserRequestDto userRequestDto) {
//        return new User(userRequestDto.getId(), userRequestDto.getName(), userRequestDto.getPassword());
//    }
//
//    @DeleteMapping(value = "/users/{id}")
//    public int deleteById(@PathVariable String id) {
//        return userdao.deleteById(id);
//    }
//
//    @DeleteMapping(value = "/users")
//    public int deleteAll() {
//        return userdao.deleteAll();
//    }

}
