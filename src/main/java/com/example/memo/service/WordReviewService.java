package com.example.memo.service;

import com.example.memo.controller.model.ReviewResponse;
import com.example.memo.controller.model.WordReviewSessionResponse;
import com.example.memo.mapper.LearningSessionMapper;
import com.example.memo.mapper.WordMapper;
import com.example.memo.utils.Time;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class WordReviewService {

    private final LearningSessionMapper learningSessionMapper;
    private final WordMapper wordMapper;

    public WordReviewService(LearningSessionMapper learningSessionMapper, WordMapper wordMapper) {
        this.learningSessionMapper = learningSessionMapper;
        this.wordMapper = wordMapper;
    }

    @Transactional(readOnly = true)
    public List<WordReviewSessionResponse> selectSessionToReview() {
        List<WordReviewSessionResponse> allSessions = learningSessionMapper.selectLearningSessions();
        List<WordReviewSessionResponse> sessionToReview = evaluateSessionToReview(allSessions);
        return sessionToReview;
    }

    private List<WordReviewSessionResponse> evaluateSessionToReview(List<WordReviewSessionResponse> allSessions) {
        return allSessions.stream()
                .filter(wordSession -> dateDifference(wordSession))
                .collect(Collectors.toList());
    }

    private boolean dateDifference(WordReviewSessionResponse wordSession) {
        long today = Time.getDateDiff(wordSession.getDate(), new Date(), TimeUnit.DAYS);
        if (today >= 0 && today <= 3 || today >= 6 && today <= 7 || today >= 30 && today <= 31 || today >= 90 && today <= 90)
            return true;
        return false;
    }

    @Transactional(readOnly = true)
    public Optional<ReviewResponse> selectReview(Long id) {
        Optional<ReviewResponse> reviewResponse = wordMapper.selectReview(id);
        return reviewResponse;
    }

}
