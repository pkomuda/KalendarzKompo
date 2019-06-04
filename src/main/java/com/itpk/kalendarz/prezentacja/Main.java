package com.itpk.kalendarz.prezentacja;

import com.itpk.kalendarz.logika.Alarmy;
import com.itpk.kalendarz.logika.RepozytoriumDni;
import com.itpk.kalendarz.logika.Wydarzenie;
import com.itpk.kalendarz.prezentacja.gui.Kalendarz;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * Glowna klasa aplikacji
 */
public class Main
{
    /**
     * Metoda uruchamiajaca interfejs graficzny lub linie polecen w zaleznosci od argumentu uruchomienia programu
     * @param args Argumenty uruchomienia programu
     */
    public static void main(String[] args)
    {
        if (args.length==1 && (args[0].equalsIgnoreCase("c")))
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
            Alarmy alarmy = new AlarmyKonsolowe(dni);
            alarmy.powiadom();
            while (wybor != 0)
            {
                System.out.println("Menu");
                System.out.println("1.Dodaj wydarzenie");
                System.out.println("2.Usuń wydarzenie");
                System.out.println("3.Wyswietl wszystkie wydarzenia");
                System.out.println("4.Usuń wydarzenia starsze niż (dzien, miesiac, rok)");
                System.out.println("5.Filtruj wydarzenia");
                System.out.println("6.O programie");
                System.out.println("0.Wyjdź z programu");
                Scanner scanner = new Scanner(System.in);
                wybor = scanner.nextInt();
                wyczyscKonsole();
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
                        dni.dodajWydarzenie(opis, miejsce, new GregorianCalendar(rok, miesiac-1, dzien));
                        break;

                    case 2:
                        System.out.print("data(YYYY MM DD): ");
                        rok = scanner.nextInt();
                        miesiac = scanner.nextInt();
                        dzien = scanner.nextInt();
                        System.out.print("Nr wydarzenia: ");
                        indeks = scanner.nextInt();
                        dni.usunWydarzenie(new GregorianCalendar(rok, miesiac-1, dzien), indeks);
                        break;

                    case 3:
                        System.out.println(dni.toString());
                        break;

                    case 4:
                        System.out.print("data(YYYY MM DD): ");
                        rok = scanner.nextInt();
                        miesiac = scanner.nextInt();
                        dzien = scanner.nextInt();
                        dni.usunWydarzeniaStarszeNiz(dzien, miesiac-1, rok);
                        break;

                    case 5:
                        System.out.println("Szukaj: ");
                        s = scanner.next();
                        for (Wydarzenie w : dni.wydarzeniaZawierajace(s))
                            System.out.println(w);
                        break;

                    case 6:
                        System.out.println(RepozytoriumDni.oProgramie());
                        break;

                    case 0:
                        System.exit(0);
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

    private static void wyczyscKonsole()
    {
        if (System.getProperty("os.name").contains("Windows"))
        {
            try
            {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            catch (InterruptedException | IOException e)
            {
                System.out.println(new String(new char[50]).replace("\0", "\r\n"));
            }
        }
        else
        {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }
}