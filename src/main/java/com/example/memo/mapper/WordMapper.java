package com.example.memo.mapper;

import com.example.memo.controller.model.ReviewResponse;
import com.example.memo.mapper.model.Word;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WordMapper {

    void insertWord(@Param("word") Word word);

    Optional<ReviewResponse> selectReview(@Param("id") Long id);

}
