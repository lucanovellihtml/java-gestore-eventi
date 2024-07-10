package org.gestore.eventi.java.milestone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ProgrammaEventi {

	protected String title;
	protected ArrayList<Evento> listEvents;
	
	public ProgrammaEventi(String title) {
		this.title = title;
		this.listEvents = new ArrayList<Evento>();
	}

	
	// METODI SETTER AND GETTER;
	public void setTitle(String title) {
		this.title = title;
	}

	public void setListEvents(ArrayList<Evento> listEvents) {
		this.listEvents = listEvents;
	}
	
	
	/*
	 * METODO CHE AGGIUNGE ALLA LISTA UN EVENTO;
	 */
	public void addEvent(Evento event) {
		this.listEvents.add(event);
	}
	
	
	/*
	 * METODO CHE RESTITUISCE UNA LISTA CON TUTTI GLI EVENTI PRESENTI IN UNA CERTA DATA;
	 */
	public ArrayList<Evento> getEventsCalendar(Calendar date){
		
		ArrayList<Evento> listEventsCalendar = new ArrayList<Evento>();
		
		date.set(Calendar.HOUR_OF_DAY, 0);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.SECOND, 0);
		date.set(Calendar.MILLISECOND, 0);
		int day = date.get(Calendar.DATE);
		String month = date.getDisplayName(Calendar.MONTH, Calendar.LONG_FORMAT, Locale.ITALY);
		int year = date.get(Calendar.YEAR);
		String dateBeautifierInput = day + " " + month + " " + year;
		
		System.out.println("- DATA FILTRAGGIO ---> " + dateBeautifierInput + "\n");
		
		for(Evento events : this.listEvents) {
			System.out.println("- STO ANALIZZANDO L'EVENTO...");
			System.out.println("- DATA EVENTO ---> " + events.dataBeautifier());
			if(events.getDate().equals(date)) {
				listEventsCalendar.add(events);
				System.out.println("- L'EVENTO HA LA STESSA DATA RICHIESTA, EVENTO AGGIUNTO ALLA LISTA\n");
			}
			else
				System.out.println("- L'EVENTO NON HA LA STESSA DATA RICHIESTA, EVENTO NON AGGIUNTO ALLA LISTA\n");
		}
		
		return listEventsCalendar;
		
	}
	
	
	/*
	 * METODO CHE RESTITUISCE IL NUMERO DEGLI EVENTI PRESENTI NEL PROGRAMMA;
	 */
	public int getLengthEvents() {
		return this.listEvents.size();
	}
	
	
	/*
	 * METODO CHE SVUOLA LA LISTA;
	 */
	public void emptyList() {
		
		//CONTROLLA SE LA LISTA NON SIA VUOTA;
		if(this.getLengthEvents() > 0) {
			System.out.println("- STO SVUOTANDO LA LISTA...");
			this.listEvents.clear();
			System.out.println("- LISTA SVUOTATA CORRETTAMENTE");
		}
		else
			System.out.println("- LISTA VUOTA, NON E' POSSIBILE SVUOTARLA");
	}
	
	
	/*
	 * METODO CHE RESTITUISCE IL SINGOLO OGGETTO;
	 */
	public Evento getEvent(int index) {
		return this.listEvents.get(index);
	}
	
	
	/*
	 * METODO CHE RESTITUISCE LA LISTA;
	 */
	public void getListEvent() {
		for(int i = 0; i < this.listEvents.size(); i++) {
			System.out.println("- SCELTA ---> " + i + "\n - EVENTO ---> " + this.listEvents.get(i) + "\n");
		}		
	}
	
	
	/*
	 * METODO CHE RESTITUISCE IL TITOLO DEL LISTA E GLI EVENTI ORDINATI PER DATA;
	 * VIENE CREATO UN ARRAYLIST, CHE SARA' COPIA DELLA LISTA ORIGINALE, PER AGIRCI SOPRA;
	 * VIENE CREATA LA LOGICA DEL BUBBLE SORT;
	 * VIENE VALORIZZATA UNA STRINGA CON LA SOMMA DI TUTTI GLI EVENTI ORDINATI PER DATA;
	 */
	public String getStringEventDate() {
		
		String dateString = "";
		ArrayList<Evento> arrayProgrammEvents = new ArrayList<Evento>();
		Evento temp;
		
		//CREAZIONE COPIA LISTA;
		for(Evento event : this.listEvents) {
			arrayProgrammEvents.add(event);
		}
		
		//CHECK BUBBLE SORT;
		for (int i = 0; i < this.listEvents.size(); i++) {
			//System.out.println("ANALISI ---> " + i +"\n");
			for (int j = 1; j < this.listEvents.size(); j++) {
				   //System.out.println("DATA DA ANALIZZARE:" + this.listEvents.get(j - 1).dataBeautifier() + " con " + this.listEvents.get(j).dataBeautifier());
				   if (arrayProgrammEvents.get(j - 1).getDate().compareTo(arrayProgrammEvents.get(j).getDate()) < 0) {
						//System.out.println("SI'");
						temp = arrayProgrammEvents.get(j - 1);
		                arrayProgrammEvents.set(j - 1, arrayProgrammEvents.get(j));
		                arrayProgrammEvents.set(j, temp);	
				   }
			}
	
		}
		
		//VALORIZZAZIONE VARIABILE STRING CON GLI EVENTI ORDINATI;
		for(Evento event : arrayProgrammEvents) {
			dateString += "- " + event;
		}
		
		return dateString;
		
	}
	
	
	@Override
	public String toString() {
		return Arrays.toString(this.listEvents.toArray());
	}
	
	
}
