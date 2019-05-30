package com.itpk.kalendarz.prezentacja.gui;

import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.text.NumberFormatter;

class WlasnyNumberFormatter extends NumberFormatter
{
	public WlasnyNumberFormatter(NumberFormat format)
	{
		super(format);
	}

	@Override
	public Object stringToValue(String text) throws ParseException
	{
		if (text.equals(""))
			return null;
		return super.stringToValue(text);
	}
}