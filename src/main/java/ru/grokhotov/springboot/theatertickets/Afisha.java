package ru.grokhotov.springboot.theatertickets;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Scope("prototype")
public class Afisha implements ApplicationContextAware {
    private Map<String, PerfomanceService> afisha = new HashMap<>();
    private ApplicationContext ctx;

    public Afisha() {
    }

    public void addPerfomance(PerfomanceService perfomance) {
        afisha.put(perfomance.getPerfomance().getName(), perfomance);
    }

    public void cancelPerfomance(PerfomanceService perfomance) {
        afisha.remove(perfomance.getPerfomance().getName());
    }

    public PerfomanceService getPerfomance(String perfomanceName) {
        return afisha.get(perfomanceName);
    }

    public Map<String, PerfomanceService> getAfisha() {
        return afisha;
    }

    public void print(String title) {
        if (title != null) {
            System.out.println(title);
        }
        for (Map.Entry<String, PerfomanceService> a : afisha.entrySet()) {
            a.getValue().print();
        }
        System.out.print("\n");
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        this.ctx = ctx;
    }
}
