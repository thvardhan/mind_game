package com.unkowns.mind;

import java.io.*;

public class QuestionParser {
    private File file;
    private BufferedReader reader;

    public QuestionParser(File file)throws IOException{
        this.file=file;
        this.reader=new BufferedReader(new FileReader(file));
    }

    public Question getQuestion()throws IOException{
        String[] r=reader.readLine().split(":");
        if(r.length<2)
            return null;
        return new Question(r[0],r[1]);
    }
}
