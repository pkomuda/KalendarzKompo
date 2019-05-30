package com.itpk.kalendarz.prezentacja.gui;

import com.itpk.kalendarz.logika.Alarmy;
import com.itpk.kalendarz.logika.RepozytoriumDni;

public class AlarmyGraficzne extends Alarmy
{	
	public AlarmyGraficzne(RepozytoriumDni dni)
	{
		super(dni);
	}

	@Override
	public void powiadom()
	{
		timer.schedule(new SprWydarzenGraficzne(dni,zaGodzine,0), teraz.getTime(), 10000);
		timer.schedule(new SprWydarzenGraficzne(dni,jutro,1), teraz.getTime(), 10000);
		timer.schedule(new SprWydarzenGraficzne(dni,zaTydzien,7), teraz.getTime(), 10000);
	}
}