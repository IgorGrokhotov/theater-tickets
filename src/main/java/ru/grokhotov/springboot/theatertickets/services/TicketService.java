package ru.grokhotov.springboot.theatertickets.services;

import ru.grokhotov.springboot.theatertickets.model.Ticket;

public class TicketService {

    public Ticket getTicketByPerfomance(String perfomance) {
        System.out.println("Билет на представление: " + perfomance);
        return new Ticket(perfomance);
    }

}
