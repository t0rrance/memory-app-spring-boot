package com.example.memo.mapper;

import com.example.memo.controller.model.AddPartWordsResponse;
import com.example.memo.controller.model.ReviewResponse;
import com.example.memo.controller.model.WordReviewSessionResponse;
import com.example.memo.mapper.model.LearningSession;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LearningSessionMapper {

    void insertLearningSession(@Param("learningSession")LearningSession learningSession);

    Optional<AddPartWordsResponse> selectLearningSession(@Param("id") Long id);

    List<WordReviewSessionResponse> selectLearningSessions();

}
