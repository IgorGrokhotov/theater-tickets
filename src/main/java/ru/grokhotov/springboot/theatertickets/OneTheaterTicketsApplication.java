package ru.grokhotov.springboot.theatertickets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Map;

@SpringBootApplication
public class OneTheaterTicketsApplication {

	public static void main(String[] args) {
		final ConfigurableApplicationContext ctx = SpringApplication.run(OneTheaterTicketsApplication.class, args);
		Afisha afisha = ctx.getBean("afisha", Afisha.class);
		Perfomance perfomance = ctx.getBean("perfomance", Perfomance.class);
		perfomance.setPerfomance("Золушка", "Спектакль", "18 февраля 2022", "18+", 28);
		afisha.addPerfomance(perfomance);
		perfomance= ctx.getBean("perfomance", Perfomance.class);
		perfomance.setPerfomance("Петрушка", "Опера", "28 февраля 2022", "12+", 12);
		afisha.addPerfomance(perfomance);
		perfomance = ctx.getBean("perfomance", Perfomance.class);
		perfomance.setPerfomance("Садко", "Опера", "28 февраля 2022", "9+", 33);
		afisha.addPerfomance(perfomance);
		perfomance = ctx.getBean("perfomance", Perfomance.class);
		perfomance.setPerfomance("Морозко", "Опера", "8 февраля 2022", "19+", 55);
		afisha.addPerfomance(perfomance);

		System.out.println(' ');
		System.out.println("Афиша: ");
		for (Map.Entry<String, Perfomance> a : afisha.getAfisha().entrySet()) {
			Perfomance p = a.getValue();
      		System.out.println(p.getPerfomance());
		}

		System.out.println(' ');
		Perfomance sadko  = afisha.getPerfomance("Садко");
		afisha.cancelPerfomance(sadko);
		System.out.println("Измененная афиша: ");
		for (Map.Entry<String, Perfomance> a : afisha.getAfisha().entrySet()) {
			Perfomance p = a.getValue();
			System.out.println(p.getPerfomance());
		}

		System.out.println(' ');
		Perfomance petrushka  = afisha.getPerfomance("Петрушка");
		int a1 = 3;
		int a2 = petrushka.getFreeTickets().size();
		int cnt = a1 + (int) (Math.random() * a2);
		cnt = cnt < a2 ? cnt : a2;
		for (int i = 0; i < cnt ; i++) {
			Integer place = i + 1;
			Ticket ticket = petrushka.saleTicket(place);
			System.out.println("Продан билет на представление: " + petrushka.getName() + ", место: " + ticket.getPlace());
		}

		System.out.println(' ');
		int a3 = 1;
		int a4 = petrushka.getSoldTicketsCount();
		cnt = a3 + (int) (Math.random() * a4);
		cnt = cnt < a4 ? cnt : a4;
		for (int i = 0; i < cnt ; i++) {
			Integer place = i + 1;
			Ticket ticket = petrushka.getSoldTickets(place);
			petrushka.backTicket(ticket);
			System.out.println("Возвращен билет на представление: " + petrushka.getName() + ", место: " + ticket.getPlace());
		}

		System.out.println(' ');
		System.out.println("Актульная афиша: ");
		for (Map.Entry<String, Perfomance> a : afisha.getAfisha().entrySet()) {
			Perfomance p = a.getValue();
			System.out.println(p.getPerfomance());
		}

	}
}
