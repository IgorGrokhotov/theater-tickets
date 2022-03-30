package ru.grokhotov.springboot.theatertickets.repositories.entities;

import lombok.*;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "perfomance", schema = "public")
public class PerfomanceEntity {

    @Id
    private String name;
    private String describe;
    private String date;
    private String age;

}
