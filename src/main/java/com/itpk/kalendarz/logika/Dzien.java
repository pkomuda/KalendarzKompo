package com.itpk.kalendarz.logika;


import java.util.*;

public class Dzien implements Kolekcja<Wydarzenie>, Comparable<Dzien>
{
    private Calendar data;
    private List<Wydarzenie> wydarzenia;

    public Dzien()
    {
        this.data = new GregorianCalendar();
//        this.data.set(Calendar.DAY_OF_MONTH, 1);
        this.wydarzenia = new ArrayList<>();
    }

    public Dzien(Calendar data)
    {
        this.data = data;
        this.wydarzenia = new ArrayList<>();
    }

    public Dzien(int dzien, int miesiac, int rok)
    {
        this.data = new GregorianCalendar();
        this.data.set(rok, miesiac, dzien);
        this.wydarzenia = new ArrayList<>();
    }

    public int[] getData()
    {
        return new int[]{data.get(Calendar.YEAR), data.get(Calendar.MONTH), data.get(Calendar.DAY_OF_MONTH)};
    }

    public void setData(Calendar data)
    {
        this.data = data;
    }

    @Override
    public List<Wydarzenie> getLista()
    {
        return wydarzenia;
    }

    @Override
    public void setLista(List<Wydarzenie> wydarzenia)
    {
        this.wydarzenia = wydarzenia;
    }

    @Override
    public boolean dodaj(Wydarzenie w)
    {
        return this.wydarzenia.add(w);
    }
    
    public boolean dodaj(String opis, String miejsce, Calendar obecna,int godzina, int minuta)
    {
        return this.wydarzenia.add(new Wydarzenie(opis,miejsce,obecna,godzina,minuta));
    }
    
    public boolean dodaj(String opis, String miejsce, Calendar obecna)
    {
        return this.wydarzenia.add(new Wydarzenie(opis,miejsce,obecna));
    }

    @Override
    public boolean usun(Wydarzenie w)
    {
        return this.wydarzenia.remove(w);
    }
    
    public boolean usun(int indeks)
    {
        return this.wydarzenia.remove(wydarzenia.get(indeks));
    }

    public void sortujWydarzenia()
    {
        this.wydarzenia.sort(new ComparatorPoDacie());
    }

    @Override
    public String toString()
    {
        return "Dzien{" +
                "data=" + data +
                ", wydarzenia=" + wydarzenia +
                '}';
    }

    @Override
    public boolean equals(Object obj)
    {
    	if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Dzien o = (Dzien)obj;
        return (Arrays.equals(getData(), o.getData()));
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getData());
    }
    
    @Override
    public int compareTo(Dzien o)
    {
        if (getData()[0] > o.getData()[0])
            return 1;
        else if (getData()[0] < o.getData()[0])
            return -1;
        else
        {
            if (getData()[1] > o.getData()[1])
                return 1;
            else if (getData()[1] < o.getData()[1])
                return -1;
            else
            {
                if (getData()[2] > o.getData()[2])
                    return 1;
                else if (getData()[2] < o.getData()[2])
                    return -1;
                else
                    return 0;
            }
        }
    }
}