package com.itpk.kalendarz.logika;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.itpk.kalendarz.logika.wyjatki.BrakWydarzenWyjatek;

public class Dni implements Kolekcja<Dzien>
{
    private List<Dzien> listaDni;


    public Dni()
    {
        this.listaDni = new ArrayList<>();
    }

    @Override
    public List<Dzien> getLista()
    {
        return this.listaDni;
    }

    @Override
    public void setLista(List<Dzien> listaDni)
    {
        this.listaDni = listaDni;
    }

    @Override
    public boolean dodaj(Dzien dzien)
    {
        return this.listaDni.add(dzien);
    }

    @Override
    public boolean usun(Dzien dzien)
    {
        return this.listaDni.remove(dzien);
    }

    public Dzien getDzien(int dzien, int miesiac, int rok) throws BrakWydarzenWyjatek
    {
    	Dzien temp = new Dzien(dzien, miesiac, rok);
    	if (listaDni.contains(temp))
        	return listaDni.get(listaDni.indexOf(temp));
    	else
    		throw new BrakWydarzenWyjatek();
    }
    
    public Dzien getDzien(Calendar obecna) 
    {
    	Dzien temp = new Dzien(obecna);
    	if (listaDni.contains(temp))
        	return listaDni.get(listaDni.indexOf(temp));
    	else
    		return null;
    }
    
    public boolean dodajWydarzenie(String opis, String miejsce, Calendar obecna,int godzina, int minuta)	//TODO godzina (w konstruktorze wydarzenia tez)
    {
    	if(!listaDni.contains(new Dzien(obecna)))
    		dodaj(new Dzien(obecna));
    	return getDzien(obecna).dodaj(opis, miejsce, obecna, godzina, minuta);
    }
    
    public boolean dodajWydarzenie(String opis, String miejsce, Calendar obecna)	//TODO godzina (w konstruktorze wydarzenia tez)
    {
    	if(!listaDni.contains(new Dzien(obecna)))
    		dodaj(new Dzien(obecna));
    	return getDzien(obecna).dodaj(opis, miejsce, obecna);
    }
    
    public boolean usunWydarzenie(Calendar obecna,int indeks)
    {
    	return getDzien(obecna).usun(indeks);
    }

    @Override
    public String toString()
    {
        return "Dni{" +
                "listaDni=" + listaDni +
                '}';
    }
}