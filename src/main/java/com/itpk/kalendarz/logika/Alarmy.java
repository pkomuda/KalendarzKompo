package com.itpk.kalendarz.logika;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;

/**
 * Klasa abstrakcyjna, ktora odpowiada za sprawdzanie, czy nie dodano nowego wydarzenia
 */
public abstract class Alarmy
{
	/**
	 * Obecna data + godzina
	 */
	protected Calendar zaGodzine;

	/**
	 * Obecna data + dzien
	 */
	protected Calendar jutro;

	/**
	 * Obecna data + tydzien
	 */
	protected Calendar zaTydzien;

	/**
	 * Obecna data
	 */
	protected Calendar teraz;

	/**
	 * Obiekt timer majacy za zadanie wykrywanie nowych wydarzen
	 */
	protected Timer timer;

	/**
	 * Wszystkie dni
	 */
	protected RepozytoriumDni dni;

	/**
	 * Konstruktor
	 * @param dni Repozytorium dni
	 */
	public Alarmy(RepozytoriumDni dni)
	{	
		this.dni = dni;
		
		zaGodzine = new GregorianCalendar();
		zaGodzine.add(Calendar.HOUR_OF_DAY, 1);
		
		jutro = new GregorianCalendar();
		setCzasNaZero(jutro);
		jutro.add(Calendar.DAY_OF_MONTH, 1);
		
		zaTydzien = new GregorianCalendar();
		setCzasNaZero(zaTydzien);
		zaTydzien.add(Calendar.DAY_OF_MONTH, 7);
		
		timer = new Timer();
		teraz = new GregorianCalendar();
	}

	/**
	 * Metoda abstrakcyjna odpowiedzialna za powiadomienia
	 */
	public abstract void powiadom();

	/**
	 * Ustaw czas dla danego dnia na 00:00:00
	 * @param kiedy Dla jakiego dnia
	 */
	private void setCzasNaZero(Calendar kiedy)
	{
		kiedy.set(Calendar.HOUR_OF_DAY, 0);
		kiedy.set(Calendar.MINUTE, 0);
		kiedy.set(Calendar.SECOND, 0);
	}
}