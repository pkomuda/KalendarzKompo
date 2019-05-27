package com.itpk.kalendarz.dane;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.itpk.kalendarz.gui.Kalendarz;
import com.itpk.kalendarz.logika.Wydarzenie;

public class ZapisDoICS
{	
	private Calendar teraz;
	
	public void zapisz(Wydarzenie wydarzenie, String nazwaPliku)
    {
        try (BufferedWriter w = new BufferedWriter(new FileWriter("./" + nazwaPliku + ".ics")))
        {
        	teraz = new GregorianCalendar();
            w.write("BEGIN:VCALENDAR\n");
            w.write("VERSION:2.0\n");
            w.write("PRODID:KalendarzKompo\n");
            w.write("BEGIN:VEVENT\n");
            w.write("UID:216802\n");
            w.write("DTSTAMP:" + teraz.get(Calendar.YEAR) + Kalendarz.dodajZero(teraz.get(Calendar.MONTH)+1) + Kalendarz.dodajZero(teraz.get(Calendar.DAY_OF_MONTH)) + "T" + Kalendarz.dodajZero(teraz.get(Calendar.HOUR_OF_DAY)) + Kalendarz.dodajZero(teraz.get(Calendar.MINUTE)) + "Z\n");
            if (wydarzenie.getCzyGodzina())
            	w.write("DTSTART:" + wydarzenie.getRok() + Kalendarz.dodajZero(wydarzenie.getMiesiac()+1) + Kalendarz.dodajZero(wydarzenie.getDzien()) + "T" + Kalendarz.dodajZero(wydarzenie.getGodzina()) + Kalendarz.dodajZero(wydarzenie.getMinuta()) + "Z\n");
            else
            	w.write("DTSTART:" + wydarzenie.getRok() + Kalendarz.dodajZero(wydarzenie.getMiesiac()+1) + Kalendarz.dodajZero(wydarzenie.getDzien()) + "T" + Kalendarz.dodajZero(wydarzenie.getGodzina()) + Kalendarz.dodajZero(wydarzenie.getMinuta()) + "Z\n");
            w.write("SUMMARY:" + wydarzenie.getOpis() + "\n");
            w.write("LOCATION:" + wydarzenie.getMiejsce() + "\n");
            w.write("END:VEVENT\n");
            w.write("END:VCALENDAR");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

/*
BEGIN:VCALENDAR
VERSION:2.0
PRODID:-//hacksw/handcal//NONSGML v1.0//EN
BEGIN:VEVENT
UID:uid1@example.com
DTSTAMP:19970714T170000Z
ORGANIZER;CN=John Doe:MAILTO:john.doe@example.com
DTSTART:19970714T170000Z
DTEND:19970715T035959Z
SUMMARY:Bastille Day Party
GEO:48.85299;2.36885
END:VEVENT
END:VCALENDAR
*/