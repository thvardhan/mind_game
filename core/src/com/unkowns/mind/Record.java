package com.unkowns.mind;

public class Record {
    private String persona;
    private int emotional;
    private int spiritual;
    private int academic;
    private int intelligence;

    public Record(String persona, int emotional, int spiritual, int academic, int intelligence) {
        this.persona = persona;
        this.emotional = emotional;
        this.spiritual = spiritual;
        this.academic = academic;
        this.intelligence = intelligence;
    }

    public String getPersona() {
        return persona;
    }

    public int getEmotional() {
        return emotional;
    }

    public int getSpiritual() {
        return spiritual;
    }

    public int getAcademic() {
        return academic;
    }

    public int getIntelligence() {
        return intelligence;
    }
}
