package com.unkowns.mind;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class QuestionParser {
    private final File file;
    private BufferedReader reader;

    public QuestionParser(File file)throws IOException{
        this.file=file;
        this.reader=new BufferedReader(new FileReader(file));
    }

    public Question getQuestion()throws IOException{
        String qt = reader.readLine();
        if (qt == null)
            return null;

        String[] r = qt.split(":");
        if (r.length != 3)
            return null;

        return new Question(r[0], r[1], r[2]);
    }

    public boolean initArray(ArrayList<Question> list) {
        try {
            Question q = this.getQuestion();
            do {
                list.add(q);
                q = this.getQuestion();
            } while (q != null);
            return true;
        } catch (Exception e) {
            System.out.println("Unable to initialize array due to");
            e.printStackTrace();
            return false;
        }
    }
}
