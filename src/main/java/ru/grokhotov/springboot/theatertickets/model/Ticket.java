package ru.grokhotov.springboot.theatertickets.model;

public class Ticket {
//  private String theater;
    private String perfomance;
//  private String datetime;
//  private String hall;
//  private String address;
//  private String place;
//  private int price;

    public Ticket(String perfomance) {
        this.perfomance = perfomance;
    }

    public String getPerfomance() {
        return perfomance;
    }

    public void setPerfomance(String perfomance) {
        this.perfomance = perfomance;
    }
}
