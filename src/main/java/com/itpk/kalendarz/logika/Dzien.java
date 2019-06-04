package com.itpk.kalendarz.logika;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;

/**
 * Klasa zarzadzajaca dniem
 */

public class Dzien implements Kolekcja<Wydarzenie>, Comparable<Dzien>
{
    /**
     * data Data dnia
     * wydarzenia Lista wydarzen z dnia
     */
    private Calendar data;
    private List<Wydarzenie> wydarzenia;

    /*
     * Konstruktory dnia
     */

    /**
     * Konstruktor dnia
     * @param data Data w ktorym chemy stworzyc dany dzien
     */

    public Dzien(Calendar data)
    {
        this.data = data;
        this.wydarzenia = new ArrayList<>();
    }

    /**
     * Konstruktor dnia
     * @param dzien Dzien w miesicu
     * @param miesiac Miesiac w roku
     * @param rok rok
     * w ktorym chcemy storzyc dany dzien
     */

    public Dzien(int dzien, int miesiac, int rok)
    {
        this.data = new GregorianCalendar();
        this.data.set(rok, miesiac, dzien);
        this.wydarzenia = new ArrayList<>();
    }

    /*
     * Gettery i settery
     */

    /**
     * Funkcja date dnia w formnie fablicy int [rok,miesiac,dzien]
     * @return Tablica z data
     */
    public int[] getDataTab()
    {
        return new int[]{data.get(Calendar.YEAR), data.get(Calendar.MONTH), data.get(Calendar.DAY_OF_MONTH)};
    }

    /**
     * Funkcja zwraca liste wydarzen z obecnego dnia
     * @return Lista wydarzen z obecnego dnia
     */

    @Override
    public List<Wydarzenie> getLista()
    {
        return wydarzenia;
    }

    /**
     * Funkcja ustawia liste wydarzen w obecnym dniu
     * @param wydarzenia Lista wydarzen przekazana dla obecnego dnia
     */
    @Override
    public void setLista(List<Wydarzenie> wydarzenia)
    {
        this.wydarzenia = wydarzenia;
    }

    /**
     * Funkcja dodaje wydarzenie do obecnego dnia
     * @param w Wydarzenie ktore chemy dodac
     * @return Czy udalosie dodac wydarzenie
     */
    @Override
    public boolean dodaj(Wydarzenie w)
    {
    	if(Arrays.equals(getDataTab(), w.getDataTab()))
    		return this.wydarzenia.add(w);
    	else return false;
    }

    /**
     * Funkcja dodaje wydarzenie do obecnego dnia
     * @param opis Opis wydarzenia
     * @param miejsce Miejsce wydarzenia
     * @param obecna Dzien wydarznia
     * @param godzina Godzina wydarzenia
     * @param minuta Minuta wydarzenia
     * @return Czy udało sie dodać wydarzenie
     */
    
    public boolean dodaj(String opis, String miejsce, Calendar obecna,int godzina, int minuta)
    {
    	Wydarzenie w=new Wydarzenie(opis,miejsce,obecna,godzina,minuta);
    	if(Arrays.equals(getDataTab(), w.getDataTab()))
    		return this.wydarzenia.add(w);
    	else return false;
    }

    /**
     * Funkcja dodaje calodniowe wydarzenie do obecnego dnia
     * @param opis Opis wydarzenia
     * @param miejsce Miejsce wydarzenia
     * @param obecna Dzien wydarznia
     * @return Czy udalo sie dodac wydarzenie
     */
    
    public boolean dodaj(String opis, String miejsce, Calendar obecna)
    {
    	Wydarzenie w=new Wydarzenie(opis,miejsce,obecna);
    	if(Arrays.equals(getDataTab(), w.getDataTab()))
    		return this.wydarzenia.add(w);
    	else return false;
    }

    /**
     * Funkcja usuwa dane wydarzenie z obecnego dnia
     * @param w Wydarzenie ktore chcemy usunac
     * @return Czy udalo sie usunac wydarzenie
     */

    @Override
    public boolean usun(Wydarzenie w)
    {
        return this.wydarzenia.remove(w);
    }

    /**
     * Funkcja usuwa wydarzenie o danym indeksie z obecnego dnia
     * @param indeks Indeks wydarzenia ktore chcemy usunac
     * @return Czy udalo sie usunac wydarzenie
     */
    
    public boolean usun(int indeks)
    {
        return this.wydarzenia.remove(wydarzenia.get(indeks));
    }

    /**
     * Funkcja sortujaca wydarzenia w obecnego dnia
     */

    public void sortujWydarzenia()
    {
        this.wydarzenia.sort(new ComparatorPoDacie());
    }

    /**
     * Funkcja zwracajaca liste przefiltrowanych wydarzen z obecnego dnia
     * @param s Ciag znakow wedlug ktorych filtrujemy wydarzenia
     * @return Lista wyfiltrowanych wydarzen
     */
    public List<Wydarzenie> wydarzeniaZawierajace(String s)
    {
    	List<Wydarzenie> wybrane= new ArrayList<Wydarzenie>();
    	for (Wydarzenie wydarzenie : wydarzenia) 
    	{
    		if (StringUtils.containsIgnoreCase(wydarzenie.getOpis(), s)|| StringUtils.containsIgnoreCase(wydarzenie.getMiejsce(), s))
    			wybrane.add(wydarzenie);
		}
    	return wybrane;
    }

    /**
     * Funkcja zwraca ciag znakow opisujacy wszystkie wydarzenia z obecnego dnia
     * @return Ciag znakow z wydarzeniami
     */

    @Override
    public String toString()
    {
        StringBuilder wszystkie= new StringBuilder();
        wszystkie.append("\n").append(getDataTab()[0]).append("-").append(RepozytoriumDni.dodajZero(getDataTab()[1]+1)).append("-").append(RepozytoriumDni.dodajZero(getDataTab()[2]));
        for (Wydarzenie w:wydarzenia)
        {
            wszystkie.append("\n\t").append(wydarzenia.indexOf(w)).append(") ").append(w.toString());
        }
        return wszystkie.toString();
    }

    /**
     * Funkcja sprawdzajaca czy dwa obiekty sa takie same
     * @param obj Objekt ktory chcemy porownac
     * @return Czy obiekty sa takie same
     */
    @Override
    public boolean equals(Object obj)
    {
    	if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Dzien o = (Dzien)obj;
        return (Arrays.equals(getDataTab(), o.getDataTab()));
    }

    /**
     * Funkcja przyporzadkowujaca obiekt do grupy
     * @return Kod grupy
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(getDataTab());
    }

    /**
     * Funkcja porownujaca dwa dni
     * @param o Obiekt ktory chcemy porownac
     * @return -1,0,1 w zaleznosci od dat
     */
    @Override
    public int compareTo(Dzien o)
    {
        if (getDataTab()[0] > o.getDataTab()[0])
            return 1;
        else if (getDataTab()[0] < o.getDataTab()[0])
            return -1;
        else
        {
            if (getDataTab()[1] > o.getDataTab()[1])
                return 1;
            else if (getDataTab()[1] < o.getDataTab()[1])
                return -1;
            else
            {
                if (getDataTab()[2] > o.getDataTab()[2])
                    return 1;
                else if (getDataTab()[2] < o.getDataTab()[2])
                    return -1;
                else
                    return 0;
            }
        }
    }
}