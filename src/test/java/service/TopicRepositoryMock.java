package service;

import org.example.model.Topic;
import org.example.repository.dao.TopicRepository;

import java.util.ArrayList;
import java.util.List;

public class TopicRepositoryMock implements TopicRepository {

    public List<Topic> topics = new ArrayList<>();

    @Override
    public boolean save(Topic topic) {
        topics.add(topic);
        return true;
    }

    @Override
    public Topic get(int id) {
        for (Topic topic : topics)
            if (topic.getId() == id)
                return topic;
        return null;
    }

    @Override
    public boolean remove(int id) {
        return false;
    }

    @Override
    public int update(Topic topic) {
        return 0;
    }
}
