package ru.grokhotov.springboot.theatertickets;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Scope("prototype")
public class Perfomance implements ApplicationContextAware {
    private String name;
    private String describe;
    private String date;
    private String age;
    private Map<Integer, Ticket> freeTickets = new HashMap<>();
    private Map<Integer, Ticket> soldTickets = new HashMap<>();
    private Map<Integer, Ticket> backTickets = new HashMap<>();
    private ApplicationContext ctx;

    public Perfomance() {
    }

    public void setPerfomance(String name, String describe, String date, String age, int places) {
        this.name = name;
        this.describe = describe;
        this.date = date;
        this.age = age;
        for (int i = 0; i < places; i++) {
            int place = i + 1;
            Ticket ticket = new Ticket(place);
            freeTickets.put(place, ticket);
        }
    }

    public String getName() {
        return name;
    }

    public String getDescribe() {
        return describe;
    }

    public String getDate() {
        return date;
    }

    public String getAge() {
        return age;
    }

    public int getSoldTicketsCount() {
        return soldTickets.size();
    }

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

    public String getPerfomance() {
        return  getName() + ", " + getDescribe() +
                ", дата: " + getDate() +
                ", возрастное ограниечение: " + getAge() +
                ", продано билетов: " + Integer.toString(soldTickets.size()) +
                ", доступно к продаже: " + Integer.toString(freeTickets.size()) +
                ", возвращено билетов: " + Integer.toString(backTickets.size());
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
