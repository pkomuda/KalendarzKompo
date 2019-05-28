package com.itpk.kalendarz.logika;

import java.awt.Toolkit;
import java.util.TimerTask;

public class PowiadomieniaZadanie extends TimerTask
{
	@Override
	public void run()
	{
		Toolkit.getDefaultToolkit().beep();
	}
}