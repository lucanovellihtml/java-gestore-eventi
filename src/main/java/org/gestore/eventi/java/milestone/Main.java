package org.gestore.eventi.java.milestone;

import java.util.Calendar;

import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		

		Evento e = new Evento("The Avengers", new GregorianCalendar(2024, 6, 4), 20);
		
		dataTodayBeautifier();
		int chooseInput;
		do {
			Scanner choose = new Scanner(System.in);
			System.out.println("- INSERISCI [1]PRENOTA - [2]DISDICI - [3]ESCI: ");
			chooseInput = choose.nextInt();
			
			switch(chooseInput) {
				case 1:
					//CONTROLLO DATA EVENTO;
					if(!e.toCheckDate()) {
						System.out.println(e.toString());
						System.out.println("- L'EVENTO DISPONIBILE");
						
						//CONTROLLO POSTI DISPONIBILI;
						if(!e.toCheckSeatsAvailable()) {
							System.out.println("- POSTI DISPONIBILI ---> " + e.seatsAvailableBooked() + "\n");
							
							Scanner inputBooked = new Scanner(System.in);
							System.out.println("- INSERISCI QUANTE PRENOTAZIONI VUORI FARE: ");
							int seatBooked = inputBooked.nextInt();
							
							//CONTROLLO SE LE PRENOTAZIONI NON SUPERINO IL NUMERO DEI POSTI RIMANENTI;
							if(seatBooked <= e.seatsAvailableBooked() && seatBooked > -1) {
							
								for(int i = 0; i < seatBooked; i++) {
									e.prenota();
									e.seatsAvailableBooked();
								}
								System.out.println("- AGGIORNAMENTO EVENTO...\n");
							}
							else
								System.out.println("- INSERISCI UN NUMERO DI PRENOTAZIONI VALIDO \n");
							
							System.out.println(e.toString());
							System.out.println("- POSTI DISPONIBILI ---> " + e.seatsAvailableBooked());
							System.out.println("- POSTI TOTALI ---> " + e.getSeatsTotal());
							System.out.println("- POSTI PRENOTATI ---> " + e.getSeatsTotalBooked() + "\n");
						}
						else
							System.out.println("- NON CI SONO POSTI DISPONIBILI \n");
					}
					else
						System.out.println("- L'EVENTO FUORI PROGRAMMAZIONE \n");
					
					break;
					
				case 2:
					//CONTROLLO DATA EVENTO;
					if(!e.toCheckDate()) {
						System.out.println(e.toString());
						System.out.println("- L'EVENTO DISPONIBILE");
						
						//CONTROLLO POSTI DISPONIBILI;
						if(!e.toCheckSeatsAvailable()) {
							System.out.println("- POSTI PRENOTATI ---> " + e.getSeatsTotalBooked() + "\n");
							
							Scanner inputCancel = new Scanner(System.in);
							System.out.println("- INSERISCI QUANTE PRENOTAZIONI VUORI CANCELLARE: ");
							int seatCancel = inputCancel.nextInt();
							
							//CONTROLLO SE LE CANCELLAZIONI NON SUPERINO IL NUMERO DEI POSTI DISPONIBILI
							if(seatCancel <= e.getSeatsTotalBooked() && seatCancel > -1) {
								
								//CONTROLLO SE ESISTONO PRENOTAZIONI;
								if(e.getSeatsTotalBooked() > 0) {
									for(int i = 0; i < seatCancel; i++) 
										e.disdici();
									
									System.out.println("- AGGIORNAMENTO EVENTO...\n");
								}
								else
									System.out.println("- NON CI SONO PRENOTAZIONI DISPONIBILI \n");
								
							}else
								System.out.println("- INSERISCI UN NUMERO DI CANCELLAZIONE VALIDO \n");
							

							System.out.println(e.toString());
							System.out.println("- POSTI DISPONIBILI ---> " + e.seatsAvailableBooked());
							System.out.println("- POSTI TOTALI ---> " + e.getSeatsTotal());
							System.out.println("- POSTI PRENOTATI ---> " + e.getSeatsTotalBooked() + "\n");
						}
						else
							System.out.println("- POSTI NON DISPONIBILI \n");
					}
					else
						System.out.println("- L'EVENTO FUORI PROGRAMMAZIONE \n");
					
					break;
					
					
				case 3:  
					System.out.println("- ARRIVEDERCI");
					break;
					
				 default:
					 System.out.println("- INSERISCI UNA SCELTA VALIDA!!! \n");
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

