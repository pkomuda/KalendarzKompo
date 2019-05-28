package com.itpk.kalendarz.logika;

import java.awt.Toolkit;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimerTask;

public class SprawdzanieWydarzenZadanie extends TimerTask
{
	private Dni dni;
	private Calendar kiedy;
	private Calendar teraz;
	
	public SprawdzanieWydarzenZadanie(Dni dni, Calendar kiedy)
	{
		this.dni = dni;
		this.kiedy = kiedy;
		this.teraz = new GregorianCalendar();
	}
	
	@Override
	public void run()
	{
		if (dni.getLista().contains(new Dzien(kiedy)))
		{
			for (Wydarzenie w : dni.getDzien(kiedy).getLista())
			{
				if(/*w.getPrzypomnienie()==Przypomnienie. &&*/ !w.czyPowiadomiono)
				{
					System.out.println("Alert - "+w);
					Toolkit.getDefaultToolkit().beep();
					w.setCzyPowiadomiono(true);
				}
			}
		}
	}
}