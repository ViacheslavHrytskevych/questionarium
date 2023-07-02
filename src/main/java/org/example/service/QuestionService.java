package org.example.service;

import org.example.model.Question;
import org.example.repository.dao.QuestionRepository;

public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public boolean addQuestion(Question question) {
        return questionRepository.save(question);
    }

    public boolean remove(int id) {
        return questionRepository.remove(id);
    }

    public Question getRandom() {
        return questionRepository.getRandom();
    }

    public Question getRandomByTopic(String topicName) {
        return questionRepository.getRandomByTopic(topicName);
    }

}
