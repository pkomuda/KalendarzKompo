package com.itpk.kalendarz.prezentacja;

import com.itpk.kalendarz.prezentacja.gui.Kalendarz;

import java.awt.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Main
{

    public static void main(String[] args)
    {
        EventQueue.invokeLater(() -> {
            try
            {
                Kalendarz frame = new Kalendarz();
                frame.setLocationRelativeTo(frame);
                frame.setVisible(true);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        });
    }

}