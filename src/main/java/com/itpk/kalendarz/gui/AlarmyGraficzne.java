package com.itpk.kalendarz.gui;

import com.itpk.kalendarz.logika.Alarmy;
import com.itpk.kalendarz.logika.Dni;

public class AlarmyGraficzne extends Alarmy
{	
	public AlarmyGraficzne(Dni dni)
	{
		super(dni);
	}

	@Override
	public void powiadom()
	{
		timerSprWydarzenia.schedule(new SprWydarzenGraficzne(dni,zaGodzine,0), teraz.getTime(), 10000);
		timerSprWydarzenia.schedule(new SprWydarzenGraficzne(dni,jutro,1), teraz.getTime(), 10000);
		timerSprWydarzenia.schedule(new SprWydarzenGraficzne(dni,zaTydzien,7), teraz.getTime(), 10000);
	}
}