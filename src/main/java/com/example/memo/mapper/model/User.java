package com.example.memo.mapper.model;

import com.example.memo.mapper.model.Role;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class User implements Serializable {
    private Long id;
    private String name;
    private String username;
    private String password;
    private Role role;
    private String token;
}
