package com.itpk.kalendarz.prezentacja.gui;

import java.util.Calendar;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.JOptionPane;

import com.itpk.kalendarz.logika.RepozytoriumDni;
import com.itpk.kalendarz.logika.Dzwieki;
import com.itpk.kalendarz.logika.SprWydarzen;
import com.itpk.kalendarz.logika.Wydarzenie;

public class SprWydarzenGraficzne extends SprWydarzen
{
	public SprWydarzenGraficzne(RepozytoriumDni dni, Calendar kiedy, int zaIle)
	{
		super(dni, kiedy, zaIle);
	}

	@Override
	public void powiadomienie(Wydarzenie w)
	{
		try
		{
			Thread.sleep(3000);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		try
		{
			Dzwieki.ton(2000, 300);
		}
		catch (LineUnavailableException e)
		{
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, w.szczegolyWydarzenia(), "Alarm", JOptionPane.INFORMATION_MESSAGE);
	}
}