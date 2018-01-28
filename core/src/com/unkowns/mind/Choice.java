package com.unkowns.mind;


public class Choice {
    public int E;
    public int I;
    public int S;
    public int N;
    public int T;
    public int F;
    public int J;
    public int P;
    public int spiritual;
    public int academic;
    public int emotional;
    public int intelligence;

    public Choice(int e, int i, int s, int n, int t, int f, int j, int p, int spiritual, int academic, int emotional, int intelligence) {
        E = e;
        I = i;
        S = s;
        N = n;
        T = t;
        F = f;
        J = j;
        P = p;
        this.spiritual = spiritual;
        this.academic = academic;
        this.emotional = emotional;
        this.intelligence = intelligence;
    }


    public String getPersona() {
        String stlv = "";
        stlv += E >= I ? "E" : "I";
        stlv += S >= N ? "S" : "N";
        stlv += T >= F ? "T" : "F";
        stlv += J >= P ? "J" : "P";
        return stlv;
    }

    public String toString() {
        return "[" + E + "," + I + "," + S + "," + N + "," + T + "," + F + "," + J + "," + P + "," + spiritual + "," + academic + "," + emotional + "," + intelligence + "]";
    }
}
