package service;

import org.example.model.Topic;
import org.example.service.TopicService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TopicServiceTest {

    private TopicRepositoryMock topicRepositoryMock;

    @Before
    public void doBefore() {
        topicRepositoryMock = new TopicRepositoryMock();
    }

    @Test
    public void saveTopicTest() {
        TopicService topicService = new TopicService(topicRepositoryMock);
        Topic topic = Topic.builder() //эти данные мы закидываем в Лист в Мокм,  чтобы их потом из этого листа вытащить тестируя метод
                .name("List")
                .id(100)
                .build();

        topicService.saveTopic(topic);

        Assert.assertTrue(topicRepositoryMock.topics.contains(topic));
    }

    @Test
    public void getTest() {
        TopicService topicService = new TopicService(topicRepositoryMock);
        Topic topic = Topic.builder()  //эти данные мы закидываем в Лист в Мокм,  чтобы их потом из этого листа вытащить тестируя метод
                .name("SQL")
                .id(99)

                .build();
        topicRepositoryMock.topics.add(topic);

        Topic expected = topicService.get(topic.getId());

        Assert.assertEquals(topic, expected);
    }
}
