package org.example.repository.dao;

import org.example.model.Question;
import org.example.repository.QuestionariumSingleton;

public interface QuestionRepository {

    boolean save(Question question);

    Question get(int id);

    boolean remove(int id);

    int update(Question question);

    Question getRandom();

    Question getRandomByTopic(String topicName);

}
