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
	protected Timer timer;
	protected Dni dni;
	
	public Alarmy(Dni dni)
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
	
	public abstract void powiadom();
	
	private void setCzasNaZero(Calendar kiedy)
	{
		kiedy.set(Calendar.HOUR_OF_DAY, 0);
		kiedy.set(Calendar.MINUTE, 0);
		kiedy.set(Calendar.SECOND, 0);
	}
}