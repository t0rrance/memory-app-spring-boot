package com.example.memo.controller;

import com.example.memo.controller.model.UserResponse;
import com.example.memo.mapper.model.User;
import com.example.memo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/admin/all")
    public ResponseEntity<List<UserResponse>> findAllUsers() {
        List<UserResponse> userList = userService.getAllUsers();
        return ResponseEntity.ok(userList);
    }

}
