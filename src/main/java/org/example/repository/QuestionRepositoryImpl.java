package org.example.repository;

import org.example.exception.IdNotFoundException;
import org.example.exception.IncorrectQueryException;
import org.example.exception.NotFoundQuestionException;
import org.example.model.Question;
import org.example.repository.dao.QuestionRepository;

import java.sql.*;

public class QuestionRepositoryImpl implements QuestionRepository {

    private final Connection connection;

    private static final String save =
            """
                            INSERT INTO question(
                            text_question)
                            VALUES (?)
                    """;

    private static final String remove =
            """
                            DELETE FROM public.question
                            WHERE id = ?
                                        
                    """;

    private static final String update =
            """
                            UPDATE public.question
                            SET text_question = ?, topic_id = ?
                            WHERE  id = ?;
                                                
                    """;
    private static final String getRandom =
            """
                            SELECT question.text_question FROM Question 
                            ORDER BY RANDOM()
                            LIMIT 1;
                                                
                    """;
    private static final String getRandomByTopic =
            """
                            SELECT q.text_question, t.name FROM Question AS q JOIN Topic AS t ON q.topic_id = t.id
                            WHERE t.name = ?
                            ORDER BY RANDOM()
                            LIMIT 1;
                                                
                    """;

    public QuestionRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Question get(int id) {

        try {
            Statement statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from question where id = " + id);
            resultSet.next();

            return Question.builder()
                    .id(resultSet.getInt("id"))
                    .question(resultSet.getString("question"))
                    .build();

        } catch (SQLException e) {
            throw new IdNotFoundException(e.getMessage());
        }
    }

    @Override
    public boolean save(Question question) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(save);

            preparedStatement.setString(1, question.getQuestion());
            return preparedStatement.execute();

        } catch (SQLException e) {
            throw new IncorrectQueryException(e.getMessage());
        }
    }

    @Override
    public boolean remove(int id) throws IdNotFoundException {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(remove);

            preparedStatement.setInt(1, id);
            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new IdNotFoundException(e.getMessage());
        }
    }

    @Override
    public int update(Question question) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(update);) {

            preparedStatement.setInt(1, question.getId());
            preparedStatement.setString(2, question.getQuestion());
            preparedStatement.setInt(3, question.getTopicId());

            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new IncorrectQueryException(e.getMessage());
        }
    }

    @Override
    public Question getRandom() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getRandom);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String questionText = resultSet.getString("text_question");

                return Question.builder()
                        .question(questionText)
                        .build();
            } else {
                throw new NotFoundQuestionException("There are no available questions on this topic in the Question table.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Question getRandomByTopic(String topicName) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getRandomByTopic);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String questionText = resultSet.getString("text_question");

                Question question = Question.builder()
                        .question(questionText)
                        .build();

                return question;
            } else {
                throw new NotFoundQuestionException("There are no available questions on this topic in the Question table.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
