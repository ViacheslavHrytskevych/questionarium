package org.example.service;

import org.example.model.Topic;
import org.example.repository.dao.TopicRepository;

public class TopicService {

    private final TopicRepository topicRepository;

    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public boolean saveTopic(Topic topic) {
        return topicRepository.save(topic);
    }

    public Topic get(int id) {
        return topicRepository.get(id);
    }
}
