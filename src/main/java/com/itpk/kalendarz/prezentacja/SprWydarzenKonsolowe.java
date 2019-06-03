package com.itpk.kalendarz.prezentacja;

import com.itpk.kalendarz.logika.RepozytoriumDni;
import com.itpk.kalendarz.logika.SprWydarzen;
import com.itpk.kalendarz.logika.Wydarzenie;

import javax.sound.sampled.LineUnavailableException;
import java.util.Calendar;

public class SprWydarzenKonsolowe extends SprWydarzen
{
	public SprWydarzenKonsolowe(RepozytoriumDni dni, Calendar kiedy, int zaIle)
	{
		super(dni, kiedy, zaIle);
	}

	@Override
	public void powiadomienie(Wydarzenie w)
	{
		try
		{
			Dzwieki.ton(2000, 300);
		}
		catch (LineUnavailableException e)
		{
			e.printStackTrace();
		}
		System.out.println(w.szczegolyWydarzenia());
	}
}