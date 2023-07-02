package org.example.repository.dao;

import org.example.exception.IdNotFoundException;
import org.example.model.Question;
import org.example.repository.QuestionariumSingleton;

import java.sql.SQLException;

public interface QuestionRepository {

    boolean save(Question question);

    Question get(int id);

    boolean remove(int id) throws IdNotFoundException;

    int update(Question question);

    Question getRandom();

    Question getRandomByTopic(String topicName);

}
