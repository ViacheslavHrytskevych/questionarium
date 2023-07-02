package org.example.repository;

import org.example.exception.IdNotFoundException;
import org.example.exception.IncorrectQueryException;
import org.example.model.Topic;
import org.example.repository.dao.TopicRepository;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TopicRepositoryImpl implements TopicRepository {

    private final Connection connection;

    private static final String save =
            """
                    INSERT INTO topic(
                    name)
                    VALUES (?)
            """;

    private static final String remove =
            """
                    DELETE FROM topic
                    WHERE id = ?
                                
            """;

    private static final String update =
            """
                    UPDATE topic
                    SET name=?
                    WHERE  id = ?;
                                        
            """;

    private static final String getAll =
            """
                   SELECT * FROM topic;
            """;

    public TopicRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Topic get(int id) {

        try {
            Statement statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from topic where id = " + id);
            resultSet.next();
            Topic topic = new Topic(resultSet.getString("name"));
            topic.setId(resultSet.getInt("id"));
            return topic;

        } catch (SQLException e) {
            throw new IdNotFoundException(e.getMessage());
        }
    }

    @Override
    public boolean save(Topic topic) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(save);

            preparedStatement.setString(1, topic.getName());
            return preparedStatement.execute();

        } catch (SQLException e) {
            throw new IncorrectQueryException(e.getMessage());
        }

    }

    @Override
    public boolean remove(int id) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(remove);

            preparedStatement.setInt(1, id);
            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new IdNotFoundException(e.getMessage());
        }
    }

    @Override
    public int update(Topic topic) {
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setInt(1, topic.getId());
            preparedStatement.setString(2, topic.getName());

            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new IncorrectQueryException(e.getMessage());
        }
    }

    @Override
    public List<Topic> getAll() {
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(getAll);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Topic> topics = new ArrayList<>();
            while (resultSet.next()) {
                topics.add(new Topic(resultSet.getInt("id"), resultSet.getString("name")));
            }
            return topics;
        } catch (SQLException e) {
            throw new IncorrectQueryException(e.getMessage());
        }
    }

}
