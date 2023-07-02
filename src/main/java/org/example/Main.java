package org.example;

import org.example.model.Question;
import org.example.model.Topic;
import org.example.repository.QuestionRepositoryImpl;
import org.example.repository.QuestionariumSingleton;
import org.example.repository.TopicRepositoryImpl;
import org.example.service.QuestionService;
import org.example.service.TopicService;

import java.util.Scanner;

public class Main {

    private static final String GREETING_MESSAGE = "Welcome to our program!";
    private static final String COMMAND_LIST = """
            Get random question by topic  -  press '1'
            Get random question - press '2'
            Add question - '3'
            Remove question - press '4'
            Get topic  - press '5'
            Add Topic  -  press '6'
            Exit - '0'""";

    private static final QuestionService questionservice = new QuestionService(new QuestionRepositoryImpl(QuestionariumSingleton.getConnection()));
    private static final TopicService topicservice = new TopicService(new TopicRepositoryImpl(QuestionariumSingleton.getConnection()));

    public static void main(String[] args) {

        System.out.println(GREETING_MESSAGE);
        Scanner scanner = new Scanner(System.in);
        System.out.println(COMMAND_LIST);
        int message = scanner.nextInt();
        while (message != 0) {
            System.out.println(COMMAND_LIST);
            String response = scanner.nextLine();
            switch (message) {
                case 1 -> questionservice.getRandomByTopic(response);
                case 2 -> System.out.println(questionservice.getRandom());
                case 3 -> {
                    System.out.println("Enter the content of the question");
                    Question question = new Question(scanner.nextLine());
                    System.out.println(questionservice.addQuestion(question));
                }
                case 4 -> {
                    System.out.println("Enter the question id: ");
                    System.out.println(questionservice.remove(scanner.nextInt()));
                }
                case 5 -> {
                    System.out.println("Enter the topic id: ");
                    System.out.println(topicservice.get(scanner.nextInt()).toString());
                }
                case 6 -> {
                    System.out.println("Enter the name of the topic: ");
                    Topic topic = new Topic(scanner.nextLine());
                    System.out.println(topicservice.saveTopic(topic));
                }

            }
            message = scanner.nextInt();
        }System.out.println("The program is completed");
    }
}
