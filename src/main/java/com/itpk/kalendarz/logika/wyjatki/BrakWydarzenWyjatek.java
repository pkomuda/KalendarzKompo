package com.itpk.kalendarz.logika.wyjatki;

/**
 * Wyjatek rzucany w przypadku braku wydarzen w danym dniu
 */
public class BrakWydarzenWyjatek extends Exception
{
    /**
     * Konstruktor
     */
	public BrakWydarzenWyjatek()
    {
        super();
    }
}