package com.example.yoony.opensourceandroidproject;

public class Goal {

    String maintext;//메모
    int isdone;//완료여부

    public Goal(String maintext, int isdone) {
        this.maintext = maintext;
        this.isdone = isdone;
    }

    public String getMaintext() {
        return maintext;
    }

    public void setMaintext(String maintext) {
        this.maintext = maintext;
    }


    public int getIsdone() {
        return isdone;
    }

    public void setIsdone(int isdone) {
        this.isdone = isdone;
    }
}
