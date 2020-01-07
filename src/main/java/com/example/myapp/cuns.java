package com.example.myapp;

public class cuns {
    private String title;
    private String content;
    private String times;
    private int ids;

    public cuns(String ti,int id,String con,String time){
        this.ids=id;
        this.title=ti;
        this.content=con;
        this.times=time;
    }
    public cuns(String ti,String con,String time){
        this.title=ti;
        this.content=con;
        this.times=time;
    }
    public cuns(String ti,int i,String time){
        this.ids=i;
        this.title=ti;
        this.times=time;
    }
    public cuns(String ti,String con){
        this.title=ti;
        this.content=con;
    }
    public int getIds(){
        return ids;
    }
    public String getTitle(){
        return title;
    }
    public String getContent(){
        return content;
    }
    public String getTimes(){
        return times;
    }
}
