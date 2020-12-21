package com.example.memo.controller;

import com.example.memo.controller.model.AddPartWordsRequest;
import com.example.memo.controller.model.AddPartWordsResponse;
import com.example.memo.controller.model.WordReviewSessionResponse;
import com.example.memo.service.UserService;
import com.example.memo.service.LearningSessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/add/words")
public class AddPartWordsController {

    private final LearningSessionService learningSessionService;
    private final UserService userService;

    public AddPartWordsController(LearningSessionService learningSessionService, UserService userService) {
        this.learningSessionService = learningSessionService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<AddPartWordsResponse> add(@RequestBody AddPartWordsRequest wordListRequest) {
        if(wordListRequest.getWordList().isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        AddPartWordsResponse wordListResponse = learningSessionService.add(wordListRequest);
        return ResponseEntity.ok(wordListResponse);
    }

}
