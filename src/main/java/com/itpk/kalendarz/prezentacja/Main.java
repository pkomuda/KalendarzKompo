package com.itpk.kalendarz.prezentacja;

import com.itpk.kalendarz.logika.RepozytoriumDni;
import com.itpk.kalendarz.prezentacja.gui.Kalendarz;

import java.awt.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        if (args.length==1 && (args[0].equals("c") || args[0].equals("C")))
        {
            int wybor = 1;
            String opis;
            String miejsce;
            Calendar data;
            int rok;
            int miesiac;
            int dzien;
            int indeks;
            String s;

            RepozytoriumDni dni = new RepozytoriumDni();
            while (wybor != 0)
            {
                System.out.println("Menu");
                System.out.println("1.Dodaj wydarzenie");
                System.out.println("2.Usuń wydarzenie");
                System.out.println("3.Wyswietl wszystkie wydarzenia");
                System.out.println("4.Usuń wydarzenia starszze niż (dzien, miesiac, rok)");
                System.out.println("5.Filtruj wydarzenia");
                System.out.println("6.O programie");
                System.out.println("0.Wyjdź z programu");
                Scanner scanner = new Scanner(System.in);
                wybor = scanner.nextInt();

                switch (wybor)
                {
                    case 1:
                        System.out.print("opis: ");
                        opis = scanner.next();
                        System.out.print("miejsce: ");
                        miejsce = scanner.next();
                        do
                        {
                            System.out.print("data(YYYY MM DD): ");
                            rok = scanner.nextInt();
                            miesiac = scanner.nextInt();
                            dzien = scanner.nextInt();
                        }
                        while (miesiac < 1 || miesiac > 12 || dzien < 1 || dzien > 31);
                        dni.dodajWydarzenie(opis, miejsce, new GregorianCalendar(rok, miesiac, dzien));
                        break;

                    case 2:
                        System.out.print("data(YYYY MM DD): ");
                        rok = scanner.nextInt();
                        miesiac = scanner.nextInt();
                        dzien = scanner.nextInt();
                        System.out.print("Nr wydarzenia: ");
                        indeks = scanner.nextInt();
                        dni.usunWydarzenie(new GregorianCalendar(rok, miesiac, dzien), indeks);
                        break;

                    case 3:
                        System.out.println(dni.toString());
                        break;

                    case 4:
                        System.out.print("data(YYYY MM DD): ");
                        rok = scanner.nextInt();
                        miesiac = scanner.nextInt();
                        dzien = scanner.nextInt();
                        dni.usunWydarzeniaStarszeNiz(dzien, miesiac, rok);
                        break;

                    case 5:
                        System.out.println("Szukaj: ");
                        s = scanner.next();
                        dni.wydarzeniaZawierajace(s);
                        break;

                    case 6:
                        System.out.println(RepozytoriumDni.oProgramie());
                        break;

                    case 0:
                        break;

                    default:
                        System.out.println("Nie ma takiej opcji");

                }
            }
        }
        else
        {
            EventQueue.invokeLater(() ->
            {
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
}