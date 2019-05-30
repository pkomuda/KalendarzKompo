package com.itpk.kalendarz.dane;

import com.itpk.kalendarz.logika.RepozytoriumDni;
import com.itpk.kalendarz.logika.Wydarzenie;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class ZapisDoXML
{
    private RepozytoriumDni dni;

    public ZapisDoXML(RepozytoriumDni dni)
    {
        this.dni = dni;
    }

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