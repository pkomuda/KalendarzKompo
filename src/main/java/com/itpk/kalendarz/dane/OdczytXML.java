package com.itpk.kalendarz.dane;

import com.itpk.kalendarz.logika.Wydarzenie;
import com.itpk.kalendarz.logika.wyjatki.NieprawidlowyXMLWyjatek;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Klasa do odczytu wydarzen z pliku xml
 */
public class OdczytXML
{
    /**
     * Metoda czytajaca wydarzenia z pliku xml
     * @param sciezka Sciezka do pliku
     * @return Lista wydarzen
     * @throws NieprawidlowyXMLWyjatek Gdy plik nie bedzie zawieral zserializowanych wydarzen
     */
    public List<Wydarzenie> czytaj(String sciezka) throws NieprawidlowyXMLWyjatek
    {
        File plik = new File(sciezka);
        int iloscWydarzen = 0;
        List<Wydarzenie> wydarzenia = new ArrayList<>();
        try
        {
            Scanner skaner = new Scanner(plik);
            if (plik.length()==0 || !skaner.nextLine().contains("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"))
                throw new NieprawidlowyXMLWyjatek();
            if (!skaner.nextLine().contains("<java version=\""))
                throw new NieprawidlowyXMLWyjatek();
            while (skaner.hasNextLine())
            {
                String linia = skaner.nextLine();
                if(linia.contains("<object class=\"com.itpk.kalendarz.logika.Wydarzenie\" id=\"Wydarzenie"))
                    iloscWydarzen++;
            }
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }

        try (XMLDecoder d = new XMLDecoder(new BufferedInputStream(new FileInputStream(sciezka))))
        {
            for (int i=0; i<iloscWydarzen; i++)
                wydarzenia.add((Wydarzenie) d.readObject());
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        return wydarzenia;
    }
}