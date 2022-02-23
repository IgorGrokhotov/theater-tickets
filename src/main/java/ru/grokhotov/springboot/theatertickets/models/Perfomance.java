package ru.grokhotov.springboot.theatertickets.models;

public class Perfomance {
    private String name;
    private String describe;
    private String date;
    private String age;

    public Perfomance(String name, String describe, String date, String age) {
        this.name = name;
        this.describe = describe;
        this.date = date;
        this.age = age;
    }

    public String getName() {
        return name;
    }

//    public void setName(String name) {
//        this.name = name;
//    }

    public String getDescribe() {
        return describe;
    }

//    public void setDescribe(String describe) {
//        this.describe = describe;
//    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAge() {
        return age;
    }

//    public void setAge(String age) {
//        this.age = age;
//    }
}
