package org.gestore.eventi.java.milestone;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		

		Evento e = new Evento("The Avengers", new GregorianCalendar(2024, 7, 4), 20);
		
		dataTodayBeautifier();
		int chooseInput;
		do {
			Scanner choose = new Scanner(System.in);
			System.out.println("- INSERISCI [1]PRENOTA - [2]DISDICI - [3]ESCI: ");
			chooseInput = choose.nextInt();
			
			switch(chooseInput) {
			case 1:
				Scanner inputBooked = new Scanner(System.in);
				System.out.println("- INSERISCI QUANTE PRENOTAZIONI VUORI FARE: ");
				int seatBooked = inputBooked.nextInt();
				if(e.checkEvent()) {
					System.out.println("- L'EVENTO DISPONIBILE");
					for(int i = 0; i < seatBooked; i++)
						e.prenota();
					System.out.println("- POSTI DISPONIBILI ---> " + e.seatsAvailable());
					System.out.println("- POSTI TOTALI ---> " + e.getSeatsTotal());
					System.out.println("- POSTI PRENOTATI ---> " + e.getSeatsTotalBooked());
				}
				else {
					System.out.println("- L'EVENTO NON E' PIU' DISPONIBILE");
				}
				break;
				
			case 2:
				Scanner inputCancel = new Scanner(System.in);
				System.out.println("- INSERISCI QUANTE PRENOTAZIONI VUORI CANCELLARE: ");
				int seatCancel = inputCancel.nextInt();
				if(e.checkEvent()) {
					System.out.println("- L'EVENTO DISPONIBILE");
					for(int i = 0; i < seatCancel; i++)
						e.disdici();
					System.out.println("- POSTI DISPONIBILI ---> " + e.seatsAvailable());
					System.out.println("- POSTI TOTALI ---> " + e.getSeatsTotal());
					System.out.println("- POSTI PRENOTATI ---> " + e.getSeatsTotalBooked());
				}
				else {
					System.out.println("- L'EVENTO NON E' PIU' DISPONIBILE");
				}
				break;
				
			case 3:  
				System.out.println("- ARRIVEDERCI");
				break;
				
			 default:
				 System.out.println("- INSERISCI UNA SCELTA VALIDA!!!");
				 break;
			}
		}while(chooseInput != 3);

		
	}
	
	
	/*
	 * METODO CHE RESTITUISCE LA DATA GIORNALIERA FORMATTANDO IL TEMPO
	 */
	static void dataTodayBeautifier() {
		
		Calendar todayDate = Calendar.getInstance();
		
		todayDate.set(Calendar.HOUR_OF_DAY, 0);
		todayDate.set(Calendar.MINUTE, 0);
		todayDate.set(Calendar.SECOND, 0);
		todayDate.set(Calendar.MILLISECOND, 0);
		int day = todayDate.get(Calendar.DATE);
		String month = todayDate.getDisplayName(Calendar.MONTH, Calendar.LONG_FORMAT, Locale.ITALY);
		int year = todayDate.get(Calendar.YEAR);
		String dateBeautifier = day + " " + month + " " + year;
		System.out.println("- DATA DI OGGI ---> " + dateBeautifier);
	}
		
}

