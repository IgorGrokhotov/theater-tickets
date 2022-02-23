package ru.grokhotov.springboot.theatertickets.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import ru.grokhotov.springboot.theatertickets.PerfomanceService;
import ru.grokhotov.springboot.theatertickets.models.Perfomance;
import ru.grokhotov.springboot.theatertickets.models.Ticket;

@Component
@Aspect
public class EmailNotifier {

    @AfterReturning(pointcut = "execution(* ru.grokhotov.springboot.theatertickets.PerfomanceService.saleTicket (..))", returning = "result")
    public void saleTicket(JoinPoint point, Object result) {
        Ticket ticket = (Ticket) result;
        sendMail("Вами куплен билет на спектакль " + ticket.getPerfomance().getName() +
                " (" + ticket.getPerfomance().getDate() + "). Место: " + ticket.getPlace());
    }

    @AfterReturning(pointcut = "execution(* ru.grokhotov.springboot.theatertickets.PerfomanceService.transferPerfomance (..))", returning = "result")
    public void transferPerfomanceAfter(JoinPoint point, Object result) {
        Perfomance perfomance = (Perfomance) result;
        sendMail("Спектакль " + perfomance.getName() +
                " перенесен на " + perfomance.getDate());
    }

    @After("execution(* ru.grokhotov.springboot.theatertickets.Afisha.addPerfomance (..))")
    public void addPerfomanceAfter(JoinPoint point) {
        Object[] objects = point.getArgs();
        PerfomanceService perfomance = (PerfomanceService) objects[0];
        sendMail("В афишу театра добавлен спектакль " + perfomance.getPerfomance().getName() +
                ", который состоится " + perfomance.getPerfomance().getDate() +
                ", возрастное ограничение: " + perfomance.getPerfomance().getAge());
    }

    @After("execution(* ru.grokhotov.springboot.theatertickets.PerfomanceService.backTicket (..))")
    public void backTicketAfter(JoinPoint point) {
        Object[] objects = point.getArgs();
        Ticket ticket = (Ticket) objects[0];
		sendMail("Возвращен билет на спектакль " + ticket.getPerfomance().getName() + ", место: " + ticket.getPlace());
    }

    @After("execution(* ru.grokhotov.springboot.theatertickets.Afisha.cancelPerfomance (..))")
    public void cancelPerfomanceAfter(JoinPoint point) {
        Object[] objects = point.getArgs();
        PerfomanceService perfomance = (PerfomanceService) objects[0];
        sendMail("Отменен спектакль: " + perfomance.getPerfomance().getName() +
                ", который должен был состоятся " + perfomance.getPerfomance().getDate());
    }

    public void sendMail(String msg) {
        System.out.println("[EMail sent] " + msg);
    }

}
