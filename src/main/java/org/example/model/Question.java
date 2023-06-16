package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Question {

    private int id;

    private String textQuestion;

    private int topicId;

    public Question(int topicID, String textQuestion) {
    }
}
