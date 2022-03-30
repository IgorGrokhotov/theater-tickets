package ru.grokhotov.springboot.theatertickets;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.grokhotov.springboot.theatertickets.repositories.TicketRepository;
import ru.grokhotov.springboot.theatertickets.repositories.entities.TicketEntity;

@Service
@Scope("prototype")
public class TicketService implements ApplicationContextAware {
    private ApplicationContext ctx;
    private TicketRepository repository;

    @Autowired
    public TicketService(TicketRepository repository) {
        this.repository = repository;
    }

    public void printPerfomance(String perfomance) {
        repository.findAllByPerfomance(perfomance).forEach(TicketEntity ->
                System.out.println(TicketEntity.getPerfomance() +
                        ", место: " + TicketEntity.getPlace().toString() +
                        " (" + TicketEntity.getStatus() + ")"
                ));
    }

    public void save (String perfomance_name, int place, String status) {
        repository.save(new TicketEntity(perfomance_name, place, status));
    }

    public void deleteAll(String perfomance) {
        repository.deleteByPerfomance(perfomance);
    }

    public Integer freeTicketsCount(String perfomance) {
        return repository.countByPerfomanceAndStatus(perfomance, "доступен");
    }

    public Integer soldTicketsCount(String perfomance) {
        return repository.countByPerfomanceAndStatus(perfomance, "продан");
    }

    public Integer returnedTicketsCount(String perfomance) {
        return repository.countByPerfomanceAndStatus(perfomance, "возврат");
    }

    public void ticketSale(String perfomance, Integer place) {
        TicketEntity ticketEntity;
        ticketEntity = repository.findFreePlaceTicketByPerfomance(perfomance, place);
        ticketEntity.setStatus("продан");
        repository.save(ticketEntity);
    }

    public void ticketReturn(String perfomance, Integer place) {
        TicketEntity ticketEntity;
        ticketEntity = repository.findSoldPlaceTicketByPerfomance(perfomance, place);
        ticketEntity.setStatus("возврат");
        repository.save(ticketEntity);
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        this.ctx = ctx;
    }

}
