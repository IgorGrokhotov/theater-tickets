package ru.grokhotov.springboot.theatertickets;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.grokhotov.springboot.theatertickets.repositories.PerfomanceRepository;
import ru.grokhotov.springboot.theatertickets.repositories.entities.PerfomanceEntity;

@Service
@Scope("prototype")
public class PerfomanceService implements ApplicationContextAware {
    private ApplicationContext ctx;
    private PerfomanceRepository repository;
    private TicketService ticketService;

    public PerfomanceService() {
    }

    @Autowired
    public PerfomanceService(PerfomanceRepository repository) {
        this.repository = repository;
    }

    public void printAll() {
        ticketService = ctx.getBean("ticketService", TicketService.class);
        repository.findAll().forEach(PerfomanceEntity ->
                System.out.println(PerfomanceEntity.getName() +
                        " (" + PerfomanceEntity.getDescribe() + ")" +
                        ", дата: " + PerfomanceEntity.getDate() +
                        ", возрастное ограниечение: " + PerfomanceEntity.getAge() +
                        ", продано билетов: " + ticketService.soldTicketsCount(PerfomanceEntity.getName()).toString() +
                        ", доступно к продаже: " + ticketService.freeTicketsCount(PerfomanceEntity.getName()).toString() +
                        ", возвращено билетов: " + ticketService.returnedTicketsCount(PerfomanceEntity.getName()).toString()
                ));
    }

    public void printPerfomance(String perfomance) {
        ticketService = ctx.getBean("ticketService", TicketService.class);
        PerfomanceEntity perfomanceEntity = repository.findById(perfomance).get();
        System.out.println(perfomanceEntity.getName() +
                " (" + perfomanceEntity.getDescribe() + ")" +
                ", дата: " + perfomanceEntity.getDate() +
                ", возрастное ограниечение: " + perfomanceEntity.getAge() +
                ", продано билетов: " + ticketService.soldTicketsCount(perfomanceEntity.getName()).toString() +
                ", доступно к продаже: " + ticketService.freeTicketsCount(perfomanceEntity.getName()).toString() +
                ", возвращено билетов: " + ticketService.returnedTicketsCount(perfomanceEntity.getName()).toString()
        );
    }

    public void save(String name, String describe, String date, String age, int places) {
        repository.save(new PerfomanceEntity(name, describe, date, age));
        if (places > 0) {
            ticketService = ctx.getBean("ticketService", TicketService.class);
            for (int i = 1; i <= places; i++) {
                ticketService.save(name, i, "доступен");
            }
        }
    }

    public void delete(String name) {
        ticketService = ctx.getBean("ticketService", TicketService.class);
        ticketService.deleteAll(name);
        repository.deleteById(name);
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        this.ctx = ctx;
    }

}
