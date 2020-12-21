package com.example.memo.controller.model;

import lombok.Data;

import java.util.Date;

@Data
public class WordReviewSessionResponse {
    private Long id;
    private Long userId;
    private Date date;
    private int quantity;
}
