package ru.grokhotov.springboot.theatertickets.repositories.entities;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ticket", schema = "public")
public class TicketEntity {

    @Id
    private String ticket_id;
    private String perfomance;
    private Integer place;
    private String status;

    public TicketEntity(String perfomance, Integer place, String status) {
        this.ticket_id = perfomance + '_' + place.toString();
        this.perfomance = perfomance;
        this.place = place;
        this.status = status;
    }

}
