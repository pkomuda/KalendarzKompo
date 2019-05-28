package com.itpk.kalendarz.logika;

import java.awt.Toolkit;
import java.util.Calendar;

public class SprWydarzenKonsolowe extends SprWydarzen
{
	public SprWydarzenKonsolowe(Dni dni, Calendar kiedy, int zaIle)
	{
		super(dni, kiedy, zaIle);
	}

	@Override
	public void powiadomienie(Wydarzenie w)
	{
		Toolkit.getDefaultToolkit().beep();
		System.out.println(w.szczegolyWydarzenia());
	}
}