package com.itpk.kalendarz.dane;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ZapisDoSQL
{
    public static final String STEROWNIK = "org.sqlite.JDBC";
    public static final String ADRES = "jdbc:sqlite:kalendarz.sqlite";
    private Connection polaczenie;
    private Statement zapytanie;

    public ZapisDoSQL()
    {
        try
        {
            Class.forName(ZapisDoSQL.STEROWNIK);
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