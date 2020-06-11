package com.example.yoony.opensourceandroidproject.db.model;


import java.io.Serializable;

public class Goal implements Serializable {
    private int indexNumber;
    private String goalTitle;

    public Goal() {
    }

    public Goal(String goalTitle) {
        this.goalTitle = goalTitle;
    }

    public String getGoalTitle() {
        return goalTitle;
    }

    public int getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(int indexNumber) {
        this.indexNumber = indexNumber;
    }

    public void setGoalTitle(String goalTitle) {
        this.goalTitle = goalTitle;
    }

    @Override
    public String toString() {
        return "Goal{" +
                "indexNumber=" + indexNumber +
                ", goalTitle='" + goalTitle + '\'' +
                '}';
    }
}
