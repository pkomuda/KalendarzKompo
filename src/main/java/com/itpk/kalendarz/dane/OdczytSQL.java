package com.itpk.kalendarz.dane;

import com.itpk.kalendarz.logika.Przypomnienie;
import com.itpk.kalendarz.logika.Wydarzenie;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class OdczytSQL extends KomunikacjaSQL
{
    public OdczytSQL()
    {
        super();
    }

    public List<Wydarzenie> czytajWydarzenia()
    {
        List<Wydarzenie> wydarzenia = new ArrayList<>();
        try
        {
            ResultSet result = zapytanie.executeQuery("SELECT * FROM wydarzenia");
            String opis, miejsce, data, przypomnienie;
            while(result.next())
            {
                opis = result.getString("opis");
                miejsce = result.getString("miejsce");
                data = result.getString("data");
                przypomnienie = result.getString("przypomnienie");
                wydarzenia.add(new Wydarzenie(opis, miejsce, stringNaCalendar(data), Przypomnienie.valueOf(przypomnienie)));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        return wydarzenia;
    }

    private Calendar stringNaCalendar(String data)
    {
        int rok = Integer.parseInt(data.substring(0,4));
        int miesiac = Integer.parseInt(data.substring(5,7)) - 1;
        int dzien = Integer.parseInt(data.substring(8,10));
        int godzina = Integer.parseInt(data.substring(11,13));
        int minuta = Integer.parseInt(data.substring(14,16));
        return new GregorianCalendar(rok, miesiac, dzien, godzina, minuta);
    }
}