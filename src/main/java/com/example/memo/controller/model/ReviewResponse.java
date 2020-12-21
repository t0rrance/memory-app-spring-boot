package com.example.memo.controller.model;

import lombok.Data;

import java.util.List;

@Data
public class ReviewResponse {
    private Long id;
    private List<Word> wordList;
}
