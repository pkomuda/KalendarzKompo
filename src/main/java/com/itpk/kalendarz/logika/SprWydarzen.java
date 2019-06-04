package com.itpk.kalendarz.logika;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimerTask;

/**
 * Klasa abstrakcyjna ktorej implementacje powiadamiaja o nadchodzacych wydarzeniach
 */
public abstract class SprWydarzen extends TimerTask
{
	/**
	 * Wszystkie dni
	 */
	private RepozytoriumDni dni;

	/**
	 * Data, w ktorej wystapi powiadomienie
	 */
	private Calendar kiedy;

	/**
	 * Obecna data
	 */
	private Calendar teraz;

	/**
	 * Za ile dni ma wystapic powiadomienie
	 */
	private int zaIle;

	/**
	 * Konstruktor
	 * @param dni Repozytorium dni
	 * @param kiedy W jaki dzien powiadomic uzytkownika
	 * @param zaIle Za ile dni powiadomic uzytkownika
	 */
	public SprWydarzen(RepozytoriumDni dni, Calendar kiedy, int zaIle)
	{
		this.dni = dni;
		this.kiedy = kiedy;
		this.teraz = new GregorianCalendar();
		this.zaIle=zaIle;
	}

	/**
	 * Uruchom zadanie sprawdzajace czy uzytkownik zostal juz powiadomionu
	 */
	@Override
	public void run()
	{
		if (dni.getLista().contains(new Dzien(kiedy)))
		{
			for (Wydarzenie w : dni.getDzien(kiedy).getLista())
			{
				if(((zaIle==0 && w.getPrzypomnienie()==Przypomnienie.GODZINA_PRZED && w.getCzyGodzina() && w.getData().compareTo(teraz)>0 && w.getData().compareTo(kiedy)<0) ||
						(zaIle==1 && w.getPrzypomnienie()==Przypomnienie.DZIEN_PRZED) ||
						(zaIle==7 && w.getPrzypomnienie()==Przypomnienie.TYDZIEN_PRZED))&& (!w.getCzyPowiadomiono()))
				{
					w.setCzyPowiadomiono(true);
					powiadomienie(w);
				}
			}
		}
	}

	/**
	 * Metoda abstrakcyjna do powiadomien
	 * @param w O jakim wydarzeniu powiadomic
	 */
	public abstract void powiadomienie(Wydarzenie w);
}