package ru.grokhotov.springboot.theatertickets.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.grokhotov.springboot.theatertickets.repositories.entities.PerfomanceEntity;

public interface PerfomanceRepository extends JpaRepository<PerfomanceEntity, String> {

    void deleteById(String name);
}
