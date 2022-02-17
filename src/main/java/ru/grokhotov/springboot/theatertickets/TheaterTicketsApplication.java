package ru.grokhotov.springboot.theatertickets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.grokhotov.springboot.theatertickets.services.TicketService;

@SpringBootApplication
public class TheaterTicketsApplication {

	public static void main(String[] args) {
		final ConfigurableApplicationContext ctx = SpringApplication.run(TheaterTicketsApplication.class, args);
		((TicketService) ctx.getBean("ticketService")).getTicketByPerfomance("Золушка");
	}

}
