package org.gestore.eventi.java.milestone;

import java.util.ArrayList;
import java.util.Calendar;

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
	 * METODO CHE RESTITUISCE UNA LISTA CON TUTTIT GLI EVENTI PRESENTI IN UNA CERTA DATA;
	 */
	public ArrayList<Evento> getEventsCalendar(Calendar date){
		
		ArrayList<Evento> listEventsCalendar = new ArrayList<Evento>();
		
		for(Evento events : this.listEvents) {
			System.out.println("- STO ANALIZZANDO L'EVENTO...");
			if(events.getDate().equals(date)) {
				listEventsCalendar.add(events);
				System.out.println("- L'EVENTO HA LA STESSA DATA RICHIESTA, EVENTO AGGIUNTO ALLA LISTA");
			}
			else
				System.out.println("- L'EVENTO NON HA LA STESSA DATA RICHIESTA, EVENTO NON AGGIUNTO ALLA LISTA");
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
		System.out.println("- STO SVUOTANDO LA LISTA...");
		this.listEvents.clear();
		System.out.println("- LISTA SVUOTATA CORRETTAMENTE");
	}
}
