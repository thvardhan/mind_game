package com.unkowns.mind;

import com.unkowns.mind.exceptions.QuestionFormatException;

import java.util.ArrayList;

public class QuestionParser {
    private String[] questions;
    private int line;

    public QuestionParser(String questions) {
        line = 0;
        this.questions = questions.split("\\r?\\n");
    }

    public Question getQuestion() throws QuestionFormatException {

        String qt = questions[line++];

        if (qt == null)
            return null;

        String[] r = qt.split(":");
        if (r.length != 3)
            throw new QuestionFormatException("Check your questions.txt?");

        return new Question(r[0], r[1], r[2]);
    }

    public boolean initArray(ArrayList<Question> list) {
        for (int i = 0; i < questions.length; i++) {
            try {
                list.add(getQuestion());
            } catch (QuestionFormatException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
}
