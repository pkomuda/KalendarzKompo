package com.itpk.kalendarz.logika;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;

public abstract class Alarmy
{
	protected Calendar zaGodzine;
	protected Calendar jutro;
	protected Calendar zaTydzien;
	protected Calendar teraz;
	private Timer timerZaGodzine;
	private Timer timerJutro;
	private Timer timerZaTydzien;
	protected Timer timerSprWydarzenia;
	protected Dni dni;
	
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
	
	public abstract void powiadom();
//	{
//		timerSprWydarzenia.schedule(new SprWydarzen(dni,zaGodzine,0), teraz.getTime(), 10000);
//		timerSprWydarzenia.schedule(new SprWydarzen(dni,jutro,1), teraz.getTime(), 10000);
//		timerSprWydarzenia.schedule(new SprWydarzen(dni,zaTydzien,7), teraz.getTime(), 10000);
//	}
	
	private void setCzasNaZero(Calendar kiedy)
	{
		kiedy.set(Calendar.HOUR_OF_DAY, 0);
		kiedy.set(Calendar.MINUTE, 0);
		kiedy.set(Calendar.SECOND, 0);
	}
}