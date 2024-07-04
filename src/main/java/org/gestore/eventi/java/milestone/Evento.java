package org.gestore.eventi.java.milestone;

import java.util.Calendar;
import java.util.Locale;

public class Evento {
	
	
	/*
	 * VARIABILI D'ISTANZA;
	 * TITLE = TITOLO DELL'EVENTO;
	 * DATE = DATA EVENTO;
	 * SEATSTOTAL = CAPIENZA TOTALE DEI POSTI DELL'EVENTO;
	 * SEATSTOTALBOOKED = TOTALE DEI POSTI PRENOTATI;
	 */
	private String title;
	private Calendar date;
	private int seatsTotal, seatsTotalBooked;
	
	
	// COSTRUTTORE;
	public Evento(String title, Calendar date, int seatsTotal) {
		this.title = title;
		this.date = date;
		this.seatsTotal = seatsTotal;
		this.seatsTotalBooked = 0;
	}


	// METODI SETTER AND GETTER;
	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public Calendar getDate() {
		return date;
	}


	public void setDate(Calendar date) {
		this.date = date;
	}


	public int getSeatsTotal() {
		return seatsTotal;
	}


	public void setSeatsTotal(int seatsTotal) {
		this.seatsTotal = seatsTotal;
	}


	public int getSeatsTotalBooked() {
		return seatsTotalBooked;
	}


	public void setSeatsTotalBooked(int seatsTotalBooked) {
		this.seatsTotalBooked = seatsTotalBooked;
	}
	
	
	/*
	 * METODO CHE CALCOLA IL TOTALE DEI POSTI ANCORA DISPONIBILI;
	 */
	public int seatsAvailable(){
		return this.seatsTotal - this.seatsTotalBooked;
	}
	
	
	/*
	 * PRENOTA
	 */
	public void prenota() {
		this.seatsTotalBooked ++;
	}
	
	
	/*
	 * DISDICI
	 */
	public void disdici() {
		
		if(this.seatsTotalBooked > 0)
			this.seatsTotalBooked ++;
		else
			System.out.println("- NON CI SONO PRENOTAZIONI");
	}
	
	
	
	/*
	 * METODO CHE CONTROLLA LA DISPONIBILITA' DELL'EVENTO;
	 */
	public boolean checkEvent() {
		if(this.toCheckDate()) {
			System.out.println("- FUORI PROGRAMMAZIONE");
			return false;
		}
		else if(this.toCheckSeatsAvailable()) {
			System.out.println("- POSTI NON DISPONIBILI");
			return false;
		}
		else
			return true;
		
		
	}
	

	
	
	
	/*
	 * METODO CHE CONTROLLA SE LA DATA SIA ANCORA DISPONIBILE;
	 * VIENE CALCOLATA LA DATA GIORNALIERA FORMATTANDO IL TEMPO;
	 * SE LA DATA DELL'EVENTO E' ANTECEDENTE ALLA DATA DEL GIORNO, VIENE RESTITUITO TRUE E L'EVENTO NON E' DISPONIBILE;
	 */
	public boolean toCheckDate() { 
		
		Calendar todayDate = Calendar.getInstance();
		
		todayDate.set(Calendar.HOUR_OF_DAY, 0);
		todayDate.set(Calendar.MINUTE, 0);
		todayDate.set(Calendar.SECOND, 0);
		todayDate.set(Calendar.MILLISECOND, 0);
		
		if(todayDate.after(this.date) || todayDate.equals(this.date))
			return true;
		
		return false;
	}
	
	
	/*
	 * METODO CHE FORMATTA LA DATA;
	 */
	public String dataBeautifier() {
		
		int day = this.date.get(Calendar.DATE);
		String month = this.date.getDisplayName(Calendar.MONTH, Calendar.LONG_FORMAT, Locale.ITALY);
		int year = this.date.get(Calendar.YEAR);
		String dateBeautifier = day + " " + month + " " + year;
		
		return dateBeautifier;
	}
	
	
	
	/*
	 * METODO CHE CONTROLLA IL TOTALE DEI POSTI DISPONIBILI;
	 */
	public boolean toCheckSeatsAvailable() {
		if(this.seatsAvailable() < 0)
			return true;
		
		return false;
	}
	
	
	@Override
	public String toString() {
		return "- TITOLO ---> " + this.title + "\n- DATA ---> " + this.date;
	}
	
}
