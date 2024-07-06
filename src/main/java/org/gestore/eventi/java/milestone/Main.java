package org.gestore.eventi.java.milestone;

import java.util.Calendar;

import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
			//INPUT PER INSERIRE NUOVO PARAMETRO;
			Scanner inputEvent = new Scanner(System.in);
			
			//INPUT TITOLO EVENTO;
			System.out.println("- INSERISCI IL TITOLO DEL NUOVO EVENTO: ");
			String title = inputEvent.nextLine();
			
			
			//VERIFICA DELLA VALIDITA' DEGLI INPUT DATA
			Calendar dateEvent = new GregorianCalendar();
			do {
				
			 try {	
				dateEvent.setLenient(false);
				//INPUT GIORNO EVENTO;
				System.out.println("- INSERISCI IL GIORNO DEL NUOVO EVENTO: ");
				int day = inputEvent.nextInt();
				
				//INPUT MESE EVENTO;
				System.out.println("- INSERISCI IL MESE DEL NUOVO EVENTO: ");
				int month = inputEvent.nextInt();
				
				//INPUT ANNO EVENTO
				System.out.println("- INSERISCI L'ANNO DEL NUOVO EVENTO: ");
				int year = inputEvent.nextInt();
				
				dateEvent.set(year, month, day);
				
			 }catch( )
				
			}while(dateEvent.getTime());
			
			//INPUT POSTI TOTALI EVENTO;
			System.out.println("- INSERISCI I POSTI TOTALI DEL NUOVO EVENTO: ");
			int seat = inputEvent.nextInt();
		

			
		while(day > 0 && day < 32 && month > 0 && month < 12 && year > 0 && seat > 0) {
			
			System.out.println("- INSERIMENTO DELL'EVENTO AVVENUTO CORRETTAMENTE \n");
			
			Evento e = new Evento(title, new GregorianCalendar(day, month, year), seat);
	
			//DATA GIORNALIERA;
			dataTodayBeautifier();
		
			//MENU'
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
		System.out.println("- INSERIMENTO DEI DATI NON VALIDO!!!");
		System.out.println("- CHIUSRA PROGRAMMA...");
		System.out.println("- PROGRAMMA CHIUSO");
		System.out.println("- RITENTA");
	}
	
	
	/*
	 * METODO CHE RESTITUISCE LA DATA GIORNALIERA FORMATTANDO IL TEMPO;
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

		
	/*
	 * METODO CHE CONTROLLA LA VALIDITA' DELLA DATA INSERITA DALL'UTENTE;
	 */
	boolean dataCheckInput() {
		
	}
}

