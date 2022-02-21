package ru.grokhotov.springboot.theatertickets;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Scope("prototype")
public class Afisha {
    private Map<String, Perfomance> afisha = new HashMap<>();

    public Afisha() {
    }

    public void addPerfomance(Perfomance perfomance) {
        afisha.put(perfomance.getName(), perfomance);
    }

    public void cancelPerfomance(Perfomance perfomance) {
        afisha.remove(perfomance.getName());
    }

    public Perfomance getPerfomance(String perfomanceName) {
        return afisha.get(perfomanceName);
    }

    public Map<String, Perfomance> getAfisha() {
        return afisha;
    }
}
