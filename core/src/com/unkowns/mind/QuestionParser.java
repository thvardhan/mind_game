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
        if (r.length != 27)
            throw new QuestionFormatException("Check your questions.txt?");

        return new Question(r[0], r[1], r[2], new Choice(Integer.parseInt(r[3])
                , Integer.parseInt(r[4]), Integer.parseInt(r[5]), Integer.parseInt(r[6]),
                Integer.parseInt(r[7]), Integer.parseInt(r[8]), Integer.parseInt(r[9]),
                Integer.parseInt(r[10]), Integer.parseInt(r[11]), Integer.parseInt(r[12]),
                Integer.parseInt(r[13]), Integer.parseInt(r[14])), new Choice(Integer.parseInt(r[15])
                , Integer.parseInt(r[16]), Integer.parseInt(r[17]), Integer.parseInt(r[18]),
                Integer.parseInt(r[19]), Integer.parseInt(r[20]), Integer.parseInt(r[21]),
                Integer.parseInt(r[22]), Integer.parseInt(r[23]), Integer.parseInt(r[24]),
                Integer.parseInt(r[25]), Integer.parseInt(r[26])));
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
