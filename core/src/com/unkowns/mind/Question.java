package com.unkowns.mind;

public class Question {
    private String one;
    private String two;

    private String question;

    private short answerID;

    private boolean answered;

    public Question(String question, String one, String two) {
        this.one=one;
        this.two=two;
        this.question = question;
        answered = false;
        answerID = 0;
    }

    public short getAnswerID() {
        return answerID;
    }

    public void setAnswerID(short answerID) {
        this.answerID = answerID;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    public String getQuestion() {
        return question;
    }

    public String getOne() {
        return one;
    }

    public String getTwo() {
        return two;
    }
}

