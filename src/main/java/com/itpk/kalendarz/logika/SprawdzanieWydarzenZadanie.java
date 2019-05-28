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
	private int zaIle;
	
	public SprawdzanieWydarzenZadanie(Dni dni, Calendar kiedy, int zaIle)
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
//				System.out.println("teraz "+teraz.getTime());
//				System.out.println("kiedy "+kiedy.getTime());
//				System.out.println("data wydarzenia "+w.getData().getTime());
//				System.out.println((zaIle==0 && w.getPrzypomnienie()==Przypomnienie.GODZINA_PRZED && w.getCzyGodzina() && w.getData().compareTo(teraz)>0 && w.getData().compareTo(kiedy)<0)&& (!w.czyPowiadomiono));
				if(((zaIle==0 && w.getPrzypomnienie()==Przypomnienie.GODZINA_PRZED && w.getCzyGodzina() && w.getData().compareTo(teraz)>0 && w.getData().compareTo(kiedy)<0) ||
						(zaIle==1 && w.getPrzypomnienie()==Przypomnienie.DZIEN_PRZED) ||
						(zaIle==7 && w.getPrzypomnienie()==Przypomnienie.TYDZIEN_PRZED))&& (!w.czyPowiadomiono))
				{
					
					System.out.println("Alert - "+w);
					Toolkit.getDefaultToolkit().beep();
					w.setCzyPowiadomiono(true);
				}
			}
		}
	}
}