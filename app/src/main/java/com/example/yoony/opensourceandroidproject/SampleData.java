package com.example.yoony.opensourceandroidproject;

public class SampleData {
    private int quest;
    private String job;
    private boolean ischecked;

    public SampleData(int quest, String job, boolean ischecked){
        this.quest=quest;
        this.job=job;
        this.ischecked=ischecked;
    }

    public int getQuest(){
        return this.quest;
    }

    public String getJob(){
        return this.job;
    }

    public boolean isChecked() {
        return this.ischecked;
    }
}
