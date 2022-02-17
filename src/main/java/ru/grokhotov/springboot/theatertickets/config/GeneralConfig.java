package ru.grokhotov.springboot.theatertickets.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.grokhotov.springboot.theatertickets.services.TicketService;

@Configuration
public class GeneralConfig {

    @Bean
    public TicketService ticketService() {
        return new TicketService();
    }

}
