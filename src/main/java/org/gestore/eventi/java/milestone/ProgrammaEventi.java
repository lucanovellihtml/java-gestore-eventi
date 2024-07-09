package org.gestore.eventi.java.milestone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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
	
	
	@Override
	public String toString() {
		return Arrays.toString(this.listEvents.toArray());
	}
	
	
}
