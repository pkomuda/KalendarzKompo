package com.itpk.kalendarz.dane;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Klasa abstrakcyjna odpowiadajaca za odczyt i zapis z bazy danych
 */
public abstract class KomunikacjaSQL
{
    /**
     * Sterownik do polaczenia z baza danych
     */
    public static final String STEROWNIK = "org.sqlite.JDBC";

    /**
     * Adres lokalnej bazy danych sqlite
     */
    public static final String ADRES = "jdbc:sqlite:kalendarz.db";

    /**
     * Polaczenie z baza
     */
    protected Connection polaczenie;

    /**
     * Zapytanie kierowane do bazy
     */
    protected Statement zapytanie;

    /**
     * Konstruktor
     */
    public KomunikacjaSQL()
    {
        try
        {
            Class.forName(KomunikacjaSQL.STEROWNIK);
        }
        catch (ClassNotFoundException e)
        {
            System.err.println("Brak sterownika JDBC");
            e.printStackTrace();
        }

        try
        {
            polaczenie = DriverManager.getConnection(ADRES);
            zapytanie = polaczenie.createStatement();
        }
        catch (SQLException e)
        {
            System.err.println("Problem z otwarciem polaczenia");
            e.printStackTrace();
        }
    }
}