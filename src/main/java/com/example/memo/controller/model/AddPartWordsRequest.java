package com.example.memo.controller.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class AddPartWordsRequest {
    private List<Word> wordList;
}
