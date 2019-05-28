package com.itpk.kalendarz.logika;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;

public class Alarmy
{
	private Calendar zaGodzine;
	private Calendar jutro;
	private Calendar zaTydzien;
	private Calendar teraz;
	private Timer timerZaGodzine;
	private Timer timerJutro;
	private Timer timerZaTydzien;
	private Timer timerSprWydarzenia;
	private Dni dni;
	
	public Alarmy(Dni dni)
	{	
		this.dni = dni;
		
		zaGodzine = new GregorianCalendar();
		zaGodzine.add(Calendar.HOUR_OF_DAY, 1);
		timerZaGodzine = new Timer();
		
		jutro = new GregorianCalendar();
		setCzasNaZero(jutro);
		jutro.add(Calendar.DAY_OF_MONTH, 1);
		timerJutro = new Timer();
		
		zaTydzien = new GregorianCalendar();
		setCzasNaZero(zaTydzien);
		zaTydzien.add(Calendar.DAY_OF_MONTH, 7);
		timerZaTydzien = new Timer();
		
		timerSprWydarzenia = new Timer();
		teraz = new GregorianCalendar();
	}
	
	public void powiadom()
	{
		timerSprWydarzenia.schedule(new SprawdzanieWydarzenZadanie(dni,zaGodzine), teraz.getTime(), 10000);
		timerSprWydarzenia.schedule(new SprawdzanieWydarzenZadanie(dni,jutro), teraz.getTime(), 10000);
		timerSprWydarzenia.schedule(new SprawdzanieWydarzenZadanie(dni,zaTydzien), teraz.getTime(), 10000);
	}
	
	private void setCzasNaZero(Calendar kiedy)
	{
		kiedy.set(Calendar.HOUR_OF_DAY, 0);
		kiedy.set(Calendar.MINUTE, 0);
		kiedy.set(Calendar.SECOND, 0);
	}
}