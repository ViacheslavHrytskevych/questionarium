package service;

import org.example.model.Question;
import org.example.model.Topic;
import org.example.repository.dao.QuestionRepository;
import org.example.service.QuestionService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionRepositoryMock implements QuestionRepository {



    private List<Question> questions;
    public List<Question> getQuestions() {
        return questions;
    }
    private Map<String, List<Question>> questionByTopic;

    public Map<String, List<Question>> getQuestionByTopic() {
        return questionByTopic;
    }

    public QuestionRepositoryMock() {
        this.questions = new ArrayList<>();
        this.questionByTopic = new HashMap<>();
    }


    @Override
    public boolean save(Question question) {
        questions.add(question);
        return true;
    }

    @Override
    public boolean remove(int id) {
        for (Question question : questions) {
            if (id == question.getId()) {
                questions.remove(question);
                return true;
            }
        }
        return false;
    }

    @Override
    public Question getRandom() {   // тут не понимаю что делать
        int random = (int)Math.floor(Math.random()*questions.size());
        return questions.get(random);

    }

    @Override
    public Question getRandomByTopic(String topicName) {  // тут не понимаю что делать
        int size = questionByTopic.get(topicName).size();
        int random = (int)Math.floor(Math.random()*size);
        return questionByTopic.get(topicName).get(random);

    }

    @Override
    public Question get(int id) {
        return null;
    }

    @Override
    public int update(Question question) {
        return 0;
    }
}
