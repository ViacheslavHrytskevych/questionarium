package service;

import org.example.model.Question;
import org.example.model.Topic;
import org.example.service.QuestionService;
import org.example.service.TopicService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class QuestionServiceTest {

    private QuestionRepositoryMock questionRepositoryMock;

    @Before
    public void doBefore() {
        questionRepositoryMock = new QuestionRepositoryMock();
    }


    @Test
    public void addQuestion() {
        QuestionService questionService = new QuestionService(questionRepositoryMock);
        Question question = Question.builder()
                .question("What is SQL")
                .topicId(23)
                .build();
        questionService.addQuestion(question);

        Assert.assertTrue(questionRepositoryMock.getQuestions().contains(question));
    }

    @Test
    public void remove() {
        QuestionService questionService = new QuestionService(questionRepositoryMock);
        Question question = Question.builder()
                .id(1)
                .question("What is SQL")
                .topicId(23)
                .build();
        questionService.addQuestion(question);

        boolean isDelete = questionService.remove(1);

        Assert.assertTrue(isDelete);
    }

    @Test
    public void getRandom() {
        QuestionService questionService = new QuestionService(questionRepositoryMock);
        Question question = Question.builder()
                .id(1)
                .question("What is SQL")
                .topicId(23)
                .build();
        questionService.addQuestion(question);
        question = Question.builder()
                .id(2)
                .question("What is Spring")
                .topicId(22)
                .build();
        questionService.addQuestion(question);
        question = Question.builder()
                .id(3)
                .question("What is Loop")
                .topicId(21)
                .build();
        questionService.addQuestion(question);

       question = questionService.getRandom();

        Assert.assertTrue(questionRepositoryMock.getQuestions().contains(question));
    }

    @Test
    public void getRandomByTopic() {
        QuestionService questionService = new QuestionService(questionRepositoryMock);

        List<Question> questionsSql = new ArrayList<>();
        List<Question> questionsJava = new ArrayList<>();

        questionsSql.add(new Question(1,"What is SQL?"));
        questionsSql.add(new Question(1, "What is Alias?"));
        questionsSql.add(new Question(1,"What is Qwery?"));
        questionsJava.add(new Question(2,"What is Java?"));
        questionsJava.add(new Question(2,"What is Class?"));
        questionsJava.add(new Question(2,"What is IDEA?"));

        questionRepositoryMock.getQuestionByTopic().put("SQL", questionsSql);
        questionRepositoryMock.getQuestionByTopic().put("JAVA", questionsJava);

        Question java = questionService.getRandomByTopic("JAVA");
        Question sql = questionService.getRandomByTopic("SQL");

        Assert.assertNotNull(java);
        Assert.assertNotNull(sql);

    }
}