package ru.grokhotov.springboot.theatertickets;

public class Ticket {
    private int place;

    public Ticket(int place) {
        this.place = place;
    }

    public int getPlace() {
        return place;
    }

}
