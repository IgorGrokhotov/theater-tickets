package ru.grokhotov.springboot.theatertickets;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.grokhotov.springboot.theatertickets.models.Perfomance;
import ru.grokhotov.springboot.theatertickets.models.Ticket;

import java.util.HashMap;
import java.util.Map;

@Component
@Scope("prototype")
public class PerfomanceService implements ApplicationContextAware {
    private Perfomance perfomance;
    private Map<Integer, Ticket> freeTickets = new HashMap<>();
    private Map<Integer, Ticket> soldTickets = new HashMap<>();
    private Map<Integer, Ticket> backTickets = new HashMap<>();
    private ApplicationContext ctx;

    public PerfomanceService() {
    }

    public void setPerfomance(String name, String describe, String date, String age, int places) {
        perfomance = new Perfomance(name, describe, date, age);
        for (int i = 0; i < places; i++) {
            int place = i + 1;
            Ticket ticket = new Ticket(perfomance, place);
            freeTickets.put(place, ticket);
        }
    }

    public Perfomance transferPerfomance(String date) {
        perfomance.setDate(date);
        return perfomance;
    }

    public int getSoldTicketsCount() {
        return soldTickets.size();
    }

//    public int getFreeTicketsCount() {
//        return freeTickets.size();
//    }

//    public int getBackTicketsCount() {
//        return backTickets.size();
//    }

    public Ticket saleTicket(Integer place) {
        if (!freeTickets.isEmpty()) {
            Ticket ticket = freeTickets.get(place);
            soldTickets.put(place, ticket);
            freeTickets.remove(place);
            return soldTickets.get(place);
        }
        return null;
    }

    public void backTicket(Ticket ticket) {
        backTickets.put((Integer) ticket.getPlace(), ticket);
        soldTickets.remove((Integer) ticket.getPlace());
    }

    public void print() {
        System.out.println(perfomance.getName() + ", " + perfomance.getDescribe() +
                ", дата: " + perfomance.getDate() +
                ", возрастное ограниечение: " + perfomance.getAge() +
                ", продано билетов: " + Integer.toString(soldTickets.size()) +
                ", доступно к продаже: " + Integer.toString(freeTickets.size()) +
                ", возвращено билетов: " + Integer.toString(backTickets.size()));
    }

    public Perfomance getPerfomance() {
        return perfomance;
    }

    public Map<Integer, Ticket> getFreeTickets() {
        return freeTickets;
    }

    public Ticket getSoldTickets(int idx) {
        return soldTickets.get(idx);
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        this.ctx = ctx;
    }
}
