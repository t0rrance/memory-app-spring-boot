package com.example.memo.mapper.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class LearningSession {
    private Long id;
    private Long userId;
    private Date date;
}
