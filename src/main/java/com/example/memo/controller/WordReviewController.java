package com.example.memo.controller;

import com.example.memo.controller.model.ReviewResponse;
import com.example.memo.controller.model.WordReviewSessionResponse;
import com.example.memo.service.UserService;
import com.example.memo.service.WordReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/review")
public class WordReviewController {

    private final WordReviewService wordReviewService;
    private final UserService userService;

    public WordReviewController(WordReviewService wordReviewService, UserService userService) {
        this.wordReviewService = wordReviewService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<WordReviewSessionResponse>> getListSessionToReview() {
        if(userService.getCurrentUserId().isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.ok().body(wordReviewService.selectSessionToReview());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponse> getReview(@PathVariable("id") Long id) {
        Optional<ReviewResponse> response = wordReviewService.selectReview(id);
        if(response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.ok().body(response.get());
    }

}
