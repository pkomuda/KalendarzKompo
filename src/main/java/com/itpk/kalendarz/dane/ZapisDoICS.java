package com.itpk.kalendarz.dane;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;

import com.itpk.kalendarz.logika.RepozytoriumDni;
import com.itpk.kalendarz.logika.Wydarzenie;

public class ZapisDoICS
{	
	private Calendar teraz;
	private RepozytoriumDni dni;
	
	public ZapisDoICS(RepozytoriumDni dni)
	{
		this.dni = dni;
	}
	
	public void zapisz(String nazwaPliku)
    {
        try (BufferedWriter w = new BufferedWriter(new FileWriter("./" + nazwaPliku + ".ics")))
        {
        	teraz = new GregorianCalendar();
            w.write("BEGIN:VCALENDAR\n");
            w.write("VERSION:2.0\n");
            w.write("CALSCALE:GREGORIAN\n");
            w.write("PRODID:KalendarzKompo\n");
			for (Wydarzenie wydarzenie : dni.wszystkieWydarzenia())
			{
				w.write("BEGIN:VEVENT\n");
				w.write("UID:" + UUID.randomUUID().toString() + "\n");
				w.write("DTSTAMP:" + teraz.get(Calendar.YEAR) + RepozytoriumDni.dodajZero(teraz.get(Calendar.MONTH)+1) + RepozytoriumDni.dodajZero(teraz.get(Calendar.DAY_OF_MONTH)) + "T" + RepozytoriumDni.dodajZero(teraz.get(Calendar.HOUR_OF_DAY)) + RepozytoriumDni.dodajZero(teraz.get(Calendar.MINUTE)) + "00\n");
				if (wydarzenie.getCzyGodzina())
					w.write("DTSTART:" + wydarzenie.getRok() + RepozytoriumDni.dodajZero(wydarzenie.getMiesiac()+1) + RepozytoriumDni.dodajZero(wydarzenie.getDzien()) + "T" + RepozytoriumDni.dodajZero(wydarzenie.getGodzina()) + RepozytoriumDni.dodajZero(wydarzenie.getMinuta()) + "00\n");
				else
					w.write("DTSTART:" + wydarzenie.getRok() + RepozytoriumDni.dodajZero(wydarzenie.getMiesiac()+1) + RepozytoriumDni.dodajZero(wydarzenie.getDzien()) + "T000000\n");
				w.write("SUMMARY:" + wydarzenie.getOpis() + "\n");
				w.write("LOCATION:" + wydarzenie.getMiejsce() + "\n");
				w.write("BEGIN:VALARM\n");
				switch (wydarzenie.getPrzypomnienie())
				{
					case GODZINA_PRZED:
						w.write("TRIGGER:-PT1H\n");
						break;
					case DZIEN_PRZED:
						w.write("TRIGGER:-P1D\n");
						break;
					case TYDZIEN_PRZED:
						w.write("TRIGGER:-P1W\n");
						break;
				}
				w.write("DESCRIPTION:" + wydarzenie.getOpis() + "- przypomnienie\n");
				w.write("ACTION:AUDIO\n");
				w.write("END:VALARM\n");
				w.write("END:VEVENT\n");
			}
            w.write("END:VCALENDAR");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}