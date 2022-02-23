package ru.grokhotov.springboot.theatertickets.models;

import ru.grokhotov.springboot.theatertickets.PerfomanceService;

public class Ticket {
    private Perfomance perfomance;
    private int place;

    public Ticket(Perfomance perfomance, int place) {
        this.perfomance = perfomance;
        this.place = place;
    }

    public int getPlace() {
        return place;
    }

    public Perfomance getPerfomance() {
        return perfomance;
    }
}
