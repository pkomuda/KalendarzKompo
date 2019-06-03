package com.itpk.kalendarz.logika;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;

public class Dzien implements Kolekcja<Wydarzenie>, Comparable<Dzien>
{
    private Calendar data;
    private List<Wydarzenie> wydarzenia;

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

    public int[] getDataTab()
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
    	if(Arrays.equals(getDataTab(), w.getDataTab()))
    		return this.wydarzenia.add(w);
    	else return false;
    }
    
    public boolean dodaj(String opis, String miejsce, Calendar obecna,int godzina, int minuta)
    {
    	Wydarzenie w=new Wydarzenie(opis,miejsce,obecna,godzina,minuta);
    	if(Arrays.equals(getDataTab(), w.getDataTab()))
    		return this.wydarzenia.add(w);
    	else return false;
    }
    
    public boolean dodaj(String opis, String miejsce, Calendar obecna)
    {
    	Wydarzenie w=new Wydarzenie(opis,miejsce,obecna);
    	if(Arrays.equals(getDataTab(), w.getDataTab()))
    		return this.wydarzenia.add(w);
    	else return false;
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

    @Override
    public String toString()
    {
        StringBuilder wszystkie= new StringBuilder();
        wszystkie.append("\n").append(getDataTab()[0]).append("-").append(getDataTab()[1]).append("-").append(getDataTab()[2]);
        for (Wydarzenie w:wydarzenia)
        {
            wszystkie.append("\n\t").append(wydarzenia.indexOf(w)).append(") ").append(w.toString());
        }
        return wszystkie.toString();
    }

    @Override
    public boolean equals(Object obj)
    {
    	if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Dzien o = (Dzien)obj;
        return (Arrays.equals(getDataTab(), o.getDataTab()));
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getDataTab());
    }
    
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