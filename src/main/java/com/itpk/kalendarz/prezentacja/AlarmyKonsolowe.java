package com.itpk.kalendarz.prezentacja;

import com.itpk.kalendarz.logika.Alarmy;
import com.itpk.kalendarz.logika.RepozytoriumDni;

/**
 * Klasa sprawdzajaca, czy nie dodano nowego (nadchodzacego) wydarzenia w linii polecen
 */
public class AlarmyKonsolowe extends Alarmy
{	
	public AlarmyKonsolowe(RepozytoriumDni dni)
	{
		super(dni);
	}

	@Override
	public void powiadom()
	{
		timer.schedule(new SprWydarzenKonsolowe(dni,zaGodzine,0), teraz.getTime(), 10000);
		timer.schedule(new SprWydarzenKonsolowe(dni,jutro,1), teraz.getTime(), 10000);
		timer.schedule(new SprWydarzenKonsolowe(dni,zaTydzien,7), teraz.getTime(), 10000);
	}
}