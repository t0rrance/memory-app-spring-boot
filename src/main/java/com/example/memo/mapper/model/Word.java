package com.example.memo.mapper.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Word {
    private Long learningSessionId;
    private String polish;
    private String english;
}
