package ru.grokhotov.springboot.theatertickets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class OneTheaterTicketsApplication {

	public static void main(String[] args) {
		final ConfigurableApplicationContext ctx = SpringApplication.run(OneTheaterTicketsApplication.class, args);

		PerfomanceService perfomanceService = ctx.getBean("perfomanceService", PerfomanceService.class);

		perfomanceService.delete("Золушка");
		perfomanceService.delete("Петрушка");
		perfomanceService.delete("12 месяцев");
		perfomanceService.delete("Морозко");
		perfomanceService.delete("Колобок");

		perfomanceService.save("Золушка", "Спектакль", "18 февраля 2022", "18+",11);
		perfomanceService.save("Петрушка", "Опера", "28 февраля 2022", "12+",7);
		perfomanceService.save("Садко", "Опера", "28 февраля 2022", "9+",17);
		perfomanceService.save("Морозко", "Опера", "8 февраля 2022", "19+",5);
		perfomanceService.save("Колобок", "Балет", "1 апреля 2022", "16+", 23);

		System.out.println(' ');
		System.out.println("Афиша: ");
		perfomanceService.printAll();

		perfomanceService.delete("Садко");
		perfomanceService.save("12 месяцев", "Балет", "8 июня 2022", "6+",15);
		System.out.println(' ');
		System.out.println("Измененная афиша: ");
		perfomanceService.printAll();

		String perfomance = "12 месяцев";
		System.out.println(' ');
		System.out.println("Продажа/возврат билетов на представление: " + perfomance);
		TicketService ticketService = ctx.getBean("ticketService", TicketService.class);
		for (int i = 3; i < 13; i++) {
			ticketService.ticketSale(perfomance, i);
		}
		for (int i = 5; i < 11; i++) {
			ticketService.ticketReturn(perfomance, i);
		}
		perfomanceService.printPerfomance(perfomance);
		System.out.println(' ');
		ticketService.printPerfomance(perfomance);
		System.out.println(' ');


	}
}
