package org.gestore.eventi.java.milestone;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner choose = new Scanner(System.in);
		Scanner chooseTitleList = new Scanner(System.in);
		System.out.println("-*-*-*-*- BENVENUTO -*-*-*-*-\n");
		dataTodayBeautifier();
		
		//INIZIALIZZA VARIABILE LIST
		System.out.println("- INSERISCI IL NOME DELLA LISTA: ");
		String titleList = chooseTitleList.nextLine();
		ProgrammaEventi listEvent = new ProgrammaEventi(titleList);
		
	
		int chooseInput;
		
		//MENU'
		do {
			
			System.out.println("- INSERISCI [1]PRENOTA - [2]DISDICI - [3]ESCI - [4]AGGIUNGI: ");
			chooseInput = choose.nextInt();
			
			
			switch(chooseInput) {
			case 1:
				checkPrenota(listEvent, choose);
				break;
				
				
			case 2:
				checkCancellazione(listEvent, choose);
				break;
				
				
			case 3:  
				System.out.println("- ARRIVEDERCI");
				System.out.println("- CHIUSURA PROGRAMMA...");
				System.out.println("- PROGRAMMA CHIUSO");
				break;
				
				
			case 4:
				System.out.println("-*-*-*-*- HAI SELEZIONATO AGGIUNGI -*-*-*-*-\n");
				Scanner chooseAdd = new Scanner(System.in);
				int chooseAddInput;
				do {
				
					System.out.println("- INSERISCI [1]AGGIUNGI EVENTO - [2]LISTA FILTRATA - [3]NUMERO LISTA EVENTI - [4]SVUOTA LISTA - [5]STAMPA LISTA - [6]RIORDINA LISTA - [7]ESCI - [8]AGGIUNGI CONCERTO: ");
					chooseAddInput = chooseAdd.nextInt();
					
					switch(chooseAddInput){
					case 1: 
						checkAdded(listEvent);
					    break;
					
					
					case 2:
						checkFiltered(listEvent);
						break;
					
					
					case 3:
						System.out.println("-*-*-*-*- HAI SELEZIONATO NUMERO LISTA EVENTI -*-*-*-*-\n");
						System.out.println("- NUMERO EVENTI PRESENTI ---> " + listEvent.getLengthEvents()); 
						break;
						
					
					case 4:
						System.out.println("-*-*-*-*- HAI SELEZIONATO SVUOTA LISTA EVENTI -*-*-*-*-\n");
						listEvent.emptyList();
						break;
						
						
					case 5:
						System.out.println("-*-*-*-*- HAI SELEZIONATO STAMPALISTA -*-*-*-*-\n");
						System.out.println("- LISTA ---> " + listEvent.toString() + "\n");
						break;
					
					
					case 6:
						System.out.println("-*-*-*-*- HAI SELEZIONATO RIORDINA LISTA -*-*-*-*-\n");
						System.out.println(listEvent.getStringEventDate());
						break;
						
					
					case 7:	
						System.out.println("- CHIUSURA METODO AGGIUNGI...");
						System.out.println("- METODO AGGIUNGI CHIUSO\n");
						break;
						
						
					case 8:
						checkAddedConcert(listEvent);
						break;
						
				 default:
					 System.out.println("- INSERISCI UNA SCELTA VALIDA!!!");
					 System.out.println("- RITENTA!!! \n");
					 break;
			}
		   }while(chooseAddInput != 7);
			break;

		   default:
			 System.out.println("- INSERISCI UNA SCELTA VALIDA!!!");
			 System.out.println("- RITENTA!!! \n");
			 break;	
		   }
	}while(chooseInput != 3);

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
		System.out.println("- DATA DI OGGI ---> " + dateBeautifier + "\n");
	}
	
	
	/*
	 * METODO CHE CONTROLLA LA LOGICA SUL CASE DI PRENOTAZIONE;
	 */
	static void checkPrenota(ProgrammaEventi listEvent, Scanner choose) {
		System.out.println("-*-*-*-*- HAI SELEZIONATO PRENOTA -*-*-*-*-");
		
		listEvent.getListEvent();
		

		//CHECK LISTA POPOLATA
		if(listEvent.getLengthEvents() > 0) {
			
			System.out.println("- INSERISCI LA POSIZIONE DELL'EVENTO: ");
			int chooseInputEventListBooked = choose.nextInt();
			
			//CONTROLLO DATA EVENTO;
			if(!listEvent.getEvent(chooseInputEventListBooked).toCheckDate()) {
				System.out.println(listEvent.getEvent(chooseInputEventListBooked).toString());
				System.out.println("- L'EVENTO DISPONIBILE");
				
				//CONTROLLO POSTI DISPONIBILI;
				if(listEvent.getEvent(chooseInputEventListBooked).seatsAvailableBooked() > 0) {
					
					Scanner inputBooked = new Scanner(System.in);
					System.out.println("- INSERISCI QUANTE PRENOTAZIONI VUORI FARE: ");
					int seatBooked = inputBooked.nextInt();
					
					//CONTROLLO SE LE PRENOTAZIONI NON SUPERINO IL NUMERO DEI POSTI RIMANENTI;
					if(seatBooked <= listEvent.getEvent(chooseInputEventListBooked).seatsAvailableBooked() && seatBooked > -1) {
					
						//INCREMENTO LA VARIABILE DEI POSTI PRENOTATI;
						for(int i = 0; i < seatBooked; i++)
							listEvent.getEvent(chooseInputEventListBooked).prenota();
						
						System.out.println("- AGGIORNAMENTO EVENTO...");
						System.out.println("- TRANSAZIONE COMPLETATA\n");
					}
					else
						System.out.println("- INSERISCI UN NUMERO DI PRENOTAZIONI VALIDO \n");
					
					/* 
					 * STAMPA TITOLO;
					 * DATA;
					 * POSTI DISPONIBILI;
					 * POSTI TOTALI;
					 * POSTI PRENOTATI;
					 */
					System.out.println(listEvent.getEvent(chooseInputEventListBooked).toString());
					System.out.println("- POSTI DISPONIBILI ---> " + listEvent.getEvent(chooseInputEventListBooked).seatsAvailableBooked());
					System.out.println("- POSTI TOTALI ---> " + listEvent.getEvent(chooseInputEventListBooked).getSeatsTotal());
					System.out.println("- POSTI PRENOTATI ---> " + listEvent.getEvent(chooseInputEventListBooked).getSeatsTotalBooked() + "\n");
				}
				else
					System.out.println("- NON CI SONO POSTI DISPONIBILI \n");
			}
			else
				System.out.println("- L'EVENTO FUORI PROGRAMMAZIONE \n");
		}
		else
			System.out.println("- NON PUOI PRENOTARE PERCHE' LA LISTA E' VUOTA \n");
	}
	
	
	/*
	 * METODO CHE CONTROLLA LA LOGICA SUL CASE DI CANCELLAZIONE;
	 */
	static void checkCancellazione(ProgrammaEventi listEvent, Scanner choose) {
		System.out.println("-*-*-*-*- HAI SELEZIONATO DISDICI -*-*-*-*-");

		listEvent.getListEvent();
		
		
		//CHECK LISTA POPOLATA
		if(listEvent.getLengthEvents() > 0) {
		
			System.out.println("- INSERISCI LA POSIZIONE DELL'EVENTO: ");
			int chooseInputEventListCancel = choose.nextInt();
			
			//CONTROLLO DATA EVENTO;
			if(!listEvent.getEvent(chooseInputEventListCancel).toCheckDate()) {
				System.out.println(listEvent.getEvent(chooseInputEventListCancel).toString());
				System.out.println("- L'EVENTO DISPONIBILE");
				System.out.println("- POSTI PRENOTATI ---> " + listEvent.getEvent(chooseInputEventListCancel).getSeatsTotalBooked() + "\n");
				
				//CONTROLLO SE ESISTONO PRENOTAZIONI;
				if(listEvent.getEvent(chooseInputEventListCancel).getSeatsTotalBooked() > 0) {
					
					Scanner inputCancel = new Scanner(System.in);
					System.out.println("- INSERISCI QUANTE PRENOTAZIONI VUORI CANCELLARE: ");
					int seatCancel = inputCancel.nextInt();
														
					//CONTROLLO SE LE CANCELLAZIONI NON SUPERINO IL NUMERO DEI POSTI PRENOTATI
					if(seatCancel <= listEvent.getEvent(chooseInputEventListCancel).getSeatsTotalBooked() && seatCancel > -1) {
						
						for(int i = 0; i < seatCancel; i++) 
							listEvent.getEvent(chooseInputEventListCancel).disdici();
						
						System.out.println("- AGGIORNAMENTO EVENTO...");
						System.out.println("- TRANSAZIONE COMPLETATA\n");
						
					}else
						System.out.println("- INSERISCI UN NUMERO DI CANCELLAZIONE VALIDO \n");
			
					/* 
					 * STAMPA TITOLO;
					 * DATA;
					 * POSTI DISPONIBILI;
					 * POSTI TOTALI;
					 * POSTI PRENOTATI;
					 */
					System.out.println(listEvent.getEvent(chooseInputEventListCancel).toString());
					System.out.println("- POSTI DISPONIBILI ---> " + listEvent.getEvent(chooseInputEventListCancel).seatsAvailableBooked());
					System.out.println("- POSTI TOTALI ---> " + listEvent.getEvent(chooseInputEventListCancel).getSeatsTotal());
					System.out.println("- POSTI PRENOTATI ---> " + listEvent.getEvent(chooseInputEventListCancel).getSeatsTotalBooked() + "\n");
				}
				else
					System.out.println("- NON CI SONO PRENOTAZIONI DISPONIBILI \n");
			}
			else
				System.out.println("- L'EVENTO FUORI PROGRAMMAZIONE \n");
		}
		else
			System.out.println("- NON PUOI DISDIRE PERCHE' LA LISTA E' VUOTA \n");
	}
	
	
	/*
	 * METODO CHE CONTROLLA LA LOGICA DI AGGIUNTA EVENTO;
	 */
	static void checkAdded(ProgrammaEventi listEvent) {
		System.out.println("-*-*-*-*- HAI SELEZIONATO AGGIUNGI EVENTO -*-*-*-*-\n");
		
		//PARAMETRI OGGETTO EVENTO
		Calendar dateEvent = new GregorianCalendar();
		int seat;
		String title;
		
		
		//INPUT PER INSERIRE NUOVO PARAMETRO;
		Scanner inputEvent = new Scanner(System.in);
		
		
		//INPUT TITOLO EVENTO;
		System.out.println("- INSERISCI IL TITOLO DEL NUOVO EVENTO: ");
		title = inputEvent.nextLine();
			
		
		/*
		 * VERIFICA DELLA VALIDITA' DEGLI INPUT DATA;
		 * INPUT DATA EVENTO;		
		 */
		boolean flagValidationDate = false;
		
		do {
				
				try {
					
					flagValidationDate = true;
					//NON ACCETTA VALORI DEL GIORNO E MESE NON ESISTENTI NEL CALENDARIO;
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
					dateEvent.getTime();
					
				}catch(ArrayIndexOutOfBoundsException | IllegalArgumentException error) {
					flagValidationDate = false;	
					System.out.println("- INSERISCI UNA DATA VALIDA!!! \n");
			}
		
		}while(flagValidationDate != true);
			System.out.println("- DATA INSERITA CORRETTAMENTE \n");
	
		
		/*
		 * VERIFICA DELLA VALIDITA' DELL'INPUT POSTI TOTALI;
		 * INPUT POSTI TOTALI EVENTO;	
		 */		
		do {
			
			System.out.println("- INSERISCI IL NUMERO DEI POSTI TOTALI VALIDO!!! \n");
			System.out.println("- INSERISCI I POSTI TOTALI DEL NUOVO EVENTO: ");
			seat = inputEvent.nextInt();
			
		}while(seat < 0);
			System.out.println("- INPUT POSTI TOTALI INSERITO CORRETTAMENTE \n");
		
			
		//CREAZIONE OGGETTO EVENTO
		System.out.println("- INSERIMENTO DELL'EVENTO AVVENUTO CORRETTAMENTE \n");
		Evento event = new Evento(title, dateEvent, seat);
		System.out.print(event.toString() + "\n");
		listEvent.addEvent(new Evento(title, dateEvent, seat));
	}

	
	/*
	 * METODO CHE CONTROLLA LA LOGICA DI AGGIUNTA CONCERTO;
	 */
	static void checkAddedConcert(ProgrammaEventi listEvent) {
		System.out.println("-*-*-*-*- HAI SELEZIONATO AGGIUNGI CONCERTO -*-*-*-*-\n");
		
		//PARAMETRI OGGETTO CONCERTO
		Calendar dateEvent = new GregorianCalendar();
		int seat;
		String title;
		double price;
		
		
		//INPUT PER INSERIRE NUOVO PARAMETRO;
		Scanner inputEvent = new Scanner(System.in);
		
		
		//INPUT TITOLO CONCERTO;
		System.out.println("- INSERISCI IL TITOLO DEL NUOVO CONCERTO: ");
		title = inputEvent.nextLine();
			
		
		/*
		 * VERIFICA DELLA VALIDITA' DEGLI INPUT DATA;
		 * INPUT DATA E ORARIO CONCERTO;		
		 */
		boolean flagValidationDate = false;
		
		do {
				
				try {
					
					flagValidationDate = true;
					//NON ACCETTA VALORI DEL GIORNO E MESE NON ESISTENTI NEL CALENDARIO;
					dateEvent.setLenient(false);
					
					//INPUT GIORNO CONCERTO;
					System.out.println("- INSERISCI IL GIORNO DEL NUOVO CONCERTO: ");
					int day = inputEvent.nextInt();
					
					//INPUT MESE CONCERTO;
					System.out.println("- INSERISCI IL MESE DEL NUOVO CONCERTO: ");
					int month = inputEvent.nextInt();
					
					//INPUT ANNO CONCERTO;
					System.out.println("- INSERISCI L'ANNO DEL NUOVO CONCERTO: ");
					int year = inputEvent.nextInt();
					
					//INPUT ORA CONCERTO;
					System.out.println("- INSERISCI L'ORA DEL NUOVO CONCERTO: ");
					int hour = inputEvent.nextInt();
					
					//INPUT MINUTO CONCERTO;
					System.out.println("- INSERISCI IL MINUTO DEL NUOVO CONCERTO: ");
					int minute = inputEvent.nextInt();

					//INPUT SECONDI CONCERTO;
					System.out.println("- INSERISCI I SECONDI DEL NUOVO CONCERTO: ");
					int second = inputEvent.nextInt();
					
					dateEvent.set(year, month, day, hour, minute, second);
					dateEvent.getTime();
					
				}catch(ArrayIndexOutOfBoundsException | IllegalArgumentException error) {
					flagValidationDate = false;	
					System.out.println("- INSERISCI UNA DATA VALIDA!!! \n");
			}
		
		}while(flagValidationDate != true);
			System.out.println("- DATA INSERITA CORRETTAMENTE \n");
	
		
		/*
		 * VERIFICA DELLA VALIDITA' DELL'INPUT POSTI TOTALI;
		 * INPUT POSTI TOTALI CONCERTO;	
		 */		
		do {
			
			System.out.println("- INSERISCI IL NUMERO DEI POSTI TOTALI VALIDO!!! \n");
			System.out.println("- INSERISCI I POSTI TOTALI DEL NUOVO CONCERTO: ");
			seat = inputEvent.nextInt();
			
		}while(seat < 0);
			System.out.println("- INPUT POSTI TOTALI INSERITO CORRETTAMENTE \n");
		
			
		/*
		 * VERIFICA DELLA VALIDITA' DELL'INPUT PREZZO;
		 * INPUT PREZZO CONCERTO;	
		 */		
		do {
			
			System.out.println("- INSERISCI IL IL PREZZO VALIDO!!! \n");
			System.out.println("- INSERISCI IL PREZZO DEL NUOVO CONCERTO: ");
			price = inputEvent.nextDouble();
			
		}while(price < 0);
			System.out.println("- INPUT PREZZO INSERITO CORRETTAMENTE \n");
			
			
		//CREAZIONE OGGETTO EVENTO
		System.out.println("- INSERIMENTO DEL CONCERTO AVVENUTO CORRETTAMENTE \n");
		Concerto concert = new Concerto(title, dateEvent, seat, dateEvent.getTime() ,price);
		System.out.println(concert.toString());
		listEvent.addEvent(concert);
	}
	
	
	/*
	 * METODO CHE CONTROLLA LA LOGICA DI FILTRAGGIO;
	 */
	static void checkFiltered(ProgrammaEventi listEvent) {
		System.out.println("-*-*-*-*- HAI SELEZIONATO LISTA FILTRATA -*-*-*-*-\n");
		
		/*
		 * VERIFICA DELLA VALIDITA' DEGLI INPUT DATA;
		 * INPUT DATA EVENTO;		
		 */
		boolean flagValidationDateFiltr = false;
		Calendar dateEventFiltr = new GregorianCalendar();
		
		
		//INPUT PER INSERIRE NUOVO PARAMETRO;
		Scanner inputEventFiltr = new Scanner(System.in);
		
		do {
				
				try {
					
					flagValidationDateFiltr = true;
					//NON ACCETTA VALORI DEL GIORNO E MESE NON ESISTENTI NEL CALENDARIO;
					dateEventFiltr.setLenient(false);
					
					//INPUT GIORNO EVENTO;
					System.out.println("- INSERISCI IL GIORNO DEL NUOVO EVENTO: ");
					int day = inputEventFiltr.nextInt();
					
					//INPUT MESE EVENTO;
					System.out.println("- INSERISCI IL MESE DEL NUOVO EVENTO: ");
					int month = inputEventFiltr.nextInt();
					
					//INPUT ANNO EVENTO
					System.out.println("- INSERISCI L'ANNO DEL NUOVO EVENTO: ");
					int year = inputEventFiltr.nextInt();
					
					dateEventFiltr.set(year, month, day);
					dateEventFiltr.getTime();
					
				}catch(ArrayIndexOutOfBoundsException | IllegalArgumentException error) {
					flagValidationDateFiltr = false;	
					System.out.println("- INSERISCI UNA DATA VALIDA!!! \n");
			}
		
		}while(flagValidationDateFiltr != true);
			System.out.println("- DATA INSERITA CORRETTAMENTE \n");
			
		ArrayList<Evento> listEventFiltr = listEvent.getEventsCalendar(dateEventFiltr);
		System.out.println("- LISTA FILTRATA --->" + listEventFiltr.toString() + "\n");
	}

}
