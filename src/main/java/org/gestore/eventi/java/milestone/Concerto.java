package org.gestore.eventi.java.milestone;

import java.util.Date;
import java.util.Calendar;
import java.util.Locale;

public class Concerto extends Evento{

	protected Date time;
	protected double price;
	
	public Concerto(String title, Calendar date, int seatsTotal, Date time, double price) {
		super(title, date, seatsTotal);
		this.time = time;
		this.price = price;
	}

	// METODI SETTER AND GETTER;
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
	/*
	 * METODO CHE FORMATTA IL PREZZO;
	 */
	public String priceBeautifier() {
		return String.format("%.2f", this.price) + "€";
	}
	
	
	@Override
	public String dataBeautifier() {
		
		int day = this.date.get(Calendar.DATE);
		String month = this.date.getDisplayName(Calendar.MONTH, Calendar.LONG_FORMAT, Locale.ITALY);
		int year = this.date.get(Calendar.YEAR);
		int hour = this.date.get(Calendar.HOUR_OF_DAY);
		int minute = this.date.get(Calendar.MINUTE);
		int second = this.date.get(Calendar.SECOND);
		String dateBeautifier = day + " " + month + " " + year + " - " + hour + ":" + minute + ":" + second;
		
		return dateBeautifier;

	}
	
	
	@Override
	public String toString() {
		return this.dataBeautifier() + " - " + this.title + " - " + this.priceBeautifier() + "\n";
	}
	
	
	
	
}
