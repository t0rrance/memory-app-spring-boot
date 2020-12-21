package com.example.memo.service;

import com.example.memo.controller.model.UserResponse;
import com.example.memo.mapper.model.Role;
import com.example.memo.mapper.model.User;
import com.example.memo.mapper.UserMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User add(User user) {
        User createUser = createUser(user);
        createUser.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapper.insertUser(createUser);
        return userMapper.selectUser(createUser.getId()).orElseThrow();
    }

    private User createUser(User user) {
        return User.builder()
                .name(user.getName())
                .username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .role(Role.ADMIN)
                .token(null)
                .build();
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers() {
        return userMapper.selectUserList();
    }

    @Transactional(readOnly = true)
    public Optional<User> findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Transactional(readOnly = true)
    public Optional<Long> getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userMapper.selectUserId(auth.getName());
    }

}
