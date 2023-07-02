package org.example.service;

import org.example.model.Question;
import org.example.repository.QuestionariumSingleton;
import org.example.repository.TopicRepositoryImpl;
import org.example.repository.dao.QuestionRepository;

import java.util.Scanner;

public class QuestionService {

    private final QuestionRepository questionRepository;
    private final TopicService topicservice = new TopicService(new TopicRepositoryImpl(QuestionariumSingleton.getConnection()));

    private final Scanner scanner = new Scanner(System.in);

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

    public void getRandomByTopic() {
        System.out.println("Select a topic from the list below");
        System.out.println(topicservice.getAll());
        System.out.println(questionRepository.getRandomByTopic(scanner.nextLine()));
    }
    public Question getRandomByTopic(String topicName) {
        System.out.println("Select a topic from the list below");
        System.out.println(topicservice.getAll());
        return questionRepository.getRandomByTopic(topicName);
    }

}
