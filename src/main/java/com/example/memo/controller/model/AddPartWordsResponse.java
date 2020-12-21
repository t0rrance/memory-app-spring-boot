package com.example.memo.controller.model;

import lombok.Data;
import java.util.List;

@Data
public class AddPartWordsResponse {
    private Long id;
    private List<Word> wordList;
}
