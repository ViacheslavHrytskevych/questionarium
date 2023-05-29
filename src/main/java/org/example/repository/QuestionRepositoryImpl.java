package org.example.repository;

import org.example.model.Question;
import org.example.repository.dao.QuestionRepository;
import java.sql.*;

public class QuestionRepositoryImpl implements QuestionRepository {

    private final Connection connection;

    private static final String save =
            """
                    INSERT INTO public.question(
                    question, topic_id)
                    VALUES (?, ?)
            """;

    private static final String remove =
            """
                    DELETE FROM public.question
                    WHERE id = ?
                                
            """;

    private static final String update =
            """
                    UPDATE public.question
                    SET question = ?, topic_id = ?
                    WHERE  id = ?;
                                        
            """;

    public QuestionRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Question get(int id) {

        try {
            Statement statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from games where name = " + id);
            resultSet.next();

            return Question.builder()
                    .id(resultSet.getInt("id"))
                    .question(resultSet.getString("question"))
                    .build();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean save(Question question) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(save);

            preparedStatement.setString(1, question.getQuestion());
            preparedStatement.setInt(2, question.getTopicId());
            return preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean remove(int id) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(remove);

            preparedStatement.setInt(1, id);
            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Question question) {
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setInt(1, question.getId());
            preparedStatement.setString(2, question.getQuestion());
            preparedStatement.setInt(3, question.getTopicId());

            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
