package com.itpk.kalendarz.dane;

import com.itpk.kalendarz.logika.RepozytoriumDni;
import com.itpk.kalendarz.logika.Wydarzenie;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Klasa do zapisu wydarzen do pliku xml
 */
public class ZapisDoXML
{
    /**
     * Wszystkie dni
     */
    private RepozytoriumDni dni;

    /**
     * Konstruktor
     * @param dni Lista dni
     */
    public ZapisDoXML(RepozytoriumDni dni)
    {
        this.dni = dni;
    }

    /**
     * Metoda serializujaca wydarzenia do pliku
     * @param sciezka Sciezka pliku do zapisu
     */
    public void zapisz(String sciezka)
    {
        try (XMLEncoder e = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(sciezka))))
        {
            for (Wydarzenie w : dni.wszystkieWydarzenia())
                 e.writeObject(w);
        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
    }
}