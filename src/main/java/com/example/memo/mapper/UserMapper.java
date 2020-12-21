package com.example.memo.mapper;

import com.example.memo.controller.model.UserResponse;
import com.example.memo.mapper.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserMapper {

    Optional<User> findByUsername(@Param("username") String username);

    void insertUser(@Param("user") User user);

    Optional<User> selectUser(@Param("id") Long id);

    Optional<Long> selectUserId(String username);

    List<UserResponse> selectUserList();

}

