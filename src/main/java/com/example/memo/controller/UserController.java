package com.example.memo.controller;

import com.example.memo.auth.JwtTokenProvider;
import com.example.memo.mapper.model.User;
import com.example.memo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    public UserController(UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/api/user/registration")
    public ResponseEntity<User> register(@RequestBody User user) {
        if(userService.findByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        User response = userService.add(user);
        return ResponseEntity.ok(response);
    }

    @GetMapping("api/user/login")
    public ResponseEntity<?> login (Principal principal) {
        if(principal == null) {
            return ResponseEntity.ok(principal);
        }
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) principal;
        User user = userService.findByUsername(authenticationToken.getName()).orElseThrow();
        user.setToken(jwtTokenProvider.generateToken(authenticationToken));

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
