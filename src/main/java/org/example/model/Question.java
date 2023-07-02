package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
public class Question {

    private int id;

    private String question;

    private int topicId;

    public Question(int topicID, String textQuestion) {
    }

    public Question(String question) {
        this.question = question;
    }
}
