package com.itpk.kalendarz.logika;

import java.awt.Toolkit;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimerTask;

public abstract class SprWydarzen extends TimerTask
{
	private Dni dni;
	private Calendar kiedy;
	private Calendar teraz;
	private int zaIle;
	
	public SprWydarzen(Dni dni, Calendar kiedy, int zaIle)
	{
		this.dni = dni;
		this.kiedy = kiedy;
		this.teraz = new GregorianCalendar();
		this.zaIle=zaIle;
	}
	
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
	
	public abstract void powiadomienie(Wydarzenie w);
}