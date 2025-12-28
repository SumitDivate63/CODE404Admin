package com.crn.code404admin;

public class ParticipantModel {
    String name;
    String mobile;
    int score;
    String level;
    public ParticipantModel() {

    }

    public ParticipantModel(String name,String mobile, int score,String level) {
        this.level = level;
        this.mobile = mobile;
        this.name = name;
        this.score = score;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
