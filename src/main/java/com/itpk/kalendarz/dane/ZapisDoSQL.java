package com.itpk.kalendarz.dane;

import com.itpk.kalendarz.logika.Wydarzenie;

import java.sql.*;

public class ZapisDoSQL extends KomunikacjaSQL
{
    public ZapisDoSQL()
    {
        super();
        stworzTabele();
    }

    public boolean stworzTabele()
    {
        String createCzytelnicy = "CREATE TABLE IF NOT EXISTS wydarzenia (opis text, miejsce text, data text, przypomnienie text)";
        try
        {
            zapytanie.execute(createCzytelnicy);
        }
        catch (SQLException e)
        {
            System.err.println("Blad przy tworzeniu tabeli");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean dodajWydarzenie(Wydarzenie w) {
        try {
            PreparedStatement przygZapytanie = polaczenie.prepareStatement(
                    "insert into wydarzenia values (?,?,?,?);");
            przygZapytanie.setString(1, w.getOpis());
            przygZapytanie.setString(2, w.getMiejsce());
            przygZapytanie.setString(3, w.getDataSQL());
            przygZapytanie.setString(4, w.getPrzypomnienie().name());
            przygZapytanie.execute();
        } catch (SQLException e) {
            System.err.println("Blad przy wstawianiu czytelnika");
            e.printStackTrace();
            return false;
        }
        return true;
    }
}