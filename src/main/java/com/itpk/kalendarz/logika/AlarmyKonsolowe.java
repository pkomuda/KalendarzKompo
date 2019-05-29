package com.itpk.kalendarz.logika;

public class AlarmyKonsolowe extends Alarmy
{	
	public AlarmyKonsolowe(Dni dni)
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