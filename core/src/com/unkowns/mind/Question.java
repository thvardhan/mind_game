package com.unkowns.mind;

import java.util.ArrayList;

public class Question {
    private Choice white;
    private Choice black;

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

    public Question(String question, String one, String two, Choice choiceWhite, Choice choiceBlack) {
        this(question, one, two);
        this.white = choiceWhite;
        this.black = choiceBlack;
    }

    public static Choice getChoice(ArrayList<Question> arr) {
        Choice choice = new Choice(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        for (Question q : arr) {
            choice.E += q.getChoice().E;
            choice.I += q.getChoice().I;
            choice.S += q.getChoice().S;
            choice.N += q.getChoice().N;
            choice.T += q.getChoice().T;
            choice.F += q.getChoice().F;
            choice.J += q.getChoice().J;
            choice.P += q.getChoice().P;
            choice.spiritual += q.getChoice().spiritual;
            choice.academic += q.getChoice().academic;
            choice.emotional += q.getChoice().emotional;
            choice.intelligence += q.getChoice().intelligence;
        }
        return choice;
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

    public Choice getChoice() {
        return this.getAnswerID() == 1 ? white : this.getAnswerID() == 2 ? black : null;
    }
}

