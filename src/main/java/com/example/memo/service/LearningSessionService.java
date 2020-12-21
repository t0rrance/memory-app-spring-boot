package com.example.memo.service;

import com.example.memo.controller.model.AddPartWordsRequest;
import com.example.memo.controller.model.AddPartWordsResponse;
import com.example.memo.mapper.LearningSessionMapper;
import com.example.memo.mapper.WordMapper;
import com.example.memo.mapper.model.LearningSession;
import com.example.memo.mapper.model.Word;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LearningSessionService {

    private final WordMapper wordMapper;
    private final LearningSessionMapper learningSessionMapper;
    private final UserService userService;

    public LearningSessionService(WordMapper wordMapper, LearningSessionMapper learningSessionMapper, UserService userService) {
        this.wordMapper = wordMapper;
        this.learningSessionMapper = learningSessionMapper;
        this.userService = userService;
    }

    @Transactional
    public AddPartWordsResponse add(AddPartWordsRequest wordListRequest) {

        Optional<Long> userId = userService.getCurrentUserId();

        LearningSession learningSession = createLearningSession(userId.get());
        learningSessionMapper.insertLearningSession(learningSession);

        List<Word> listWords = createListObjects(learningSession.getId(),wordListRequest);
        saveWordList(listWords);

        AddPartWordsResponse response = learningSessionMapper.selectLearningSession(learningSession.getId()).orElseThrow();

        return response;
    }

    private LearningSession createLearningSession(Long studentId) {
        return LearningSession.builder()
                .userId(studentId)
                .date(new Date())
                .build();
    }

    private void saveWordList(List<Word> listWords) {
        for(var word : listWords)
            wordMapper.insertWord(word);
    }

    private List<Word> createListObjects(Long learningSessionId, AddPartWordsRequest wordListRequest) {
        return wordListRequest.getWordList().stream()
                .map(word -> createObjectWord(learningSessionId,word))
                .collect(Collectors.toList());
    }

    private Word createObjectWord(Long learningSessionId, com.example.memo.controller.model.Word word) {
        return Word.builder()
                .learningSessionId(learningSessionId)
                .polish(word.getPolish())
                .english(word.getEnglish())
                .build();
    }

}
