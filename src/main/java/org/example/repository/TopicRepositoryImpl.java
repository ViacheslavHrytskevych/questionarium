package org.example.repository;

import org.example.model.Topic;
import org.example.repository.dao.TopicRepository;

import java.sql.*;

public class TopicRepositoryImpl implements TopicRepository {

    private final Connection connection;

    private static final String save =
            """
                    INSERT INTO public.topic(
                    name)
                    VALUES (?)
            """;

    private static final String remove =
            """
                    DELETE FROM public.topic
                    WHERE id = ?
                                
            """;

    private static final String update =
            """
                    UPDATE public.topic
                    SET name=?
                    WHERE  id = ?;
                                        
            """;

    public TopicRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Topic get(int id) {

        try {
            Statement statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from games where name = " + id);
            resultSet.next();

            return Topic.builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .build();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean save(Topic topic) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(save);

            preparedStatement.setInt(1, topic.getId());
            preparedStatement.setString(2, topic.getName());
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
    public int update(Topic topic) {
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setInt(1, topic.getId());
            preparedStatement.setString(2, topic.getName());

            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
