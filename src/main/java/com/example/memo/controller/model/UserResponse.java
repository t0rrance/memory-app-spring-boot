package com.example.memo.controller.model;

import com.example.memo.mapper.model.Role;
import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String name;
    private String username;
    private Role role;
}
