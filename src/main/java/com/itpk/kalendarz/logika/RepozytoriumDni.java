package com.itpk.kalendarz.logika;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.itpk.kalendarz.logika.wyjatki.BrakWydarzenWyjatek;

public class RepozytoriumDni implements Kolekcja<Dzien>
{
    private List<Dzien> listaDni;


    public RepozytoriumDni()
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
    
    public void sortuj()
    {
    	listaDni.sort(Dzien::compareTo);
    }

    public Dzien getDzien(int dzien, int miesiac, int rok) throws BrakWydarzenWyjatek
    {
    	Dzien temp = new Dzien(dzien, miesiac, rok);
    	if (!listaDni.contains(temp) || listaDni.get(listaDni.indexOf(temp)).getLista().isEmpty())
        	throw new BrakWydarzenWyjatek();
    	else
    		return listaDni.get(listaDni.indexOf(temp));
    }
    
    public Dzien getDzien(Calendar obecna) 
    {
    	Dzien temp = new Dzien(obecna);
    	if (listaDni.contains(temp))
        	return listaDni.get(listaDni.indexOf(temp));
    	else
    		return null;
    }

    public boolean dodajWydarzenie(Wydarzenie w)
    {
        if(!listaDni.contains(new Dzien(w.getData())))
            dodaj(new Dzien(w.getData()));
        return getDzien(w.getData()).dodaj(w);
    }
    
    public boolean dodajWydarzenie(String opis, String miejsce, Calendar obecna,int godzina, int minuta)
    {
    	if(!listaDni.contains(new Dzien(obecna)))
    		dodaj(new Dzien(obecna));
    	return getDzien(obecna).dodaj(opis, miejsce, obecna, godzina, minuta);
    }
    
    public boolean dodajWydarzenie(String opis, String miejsce, Calendar obecna)
    {
    	if(!listaDni.contains(new Dzien(obecna)))
    		dodaj(new Dzien(obecna));
    	return getDzien(obecna).dodaj(opis, miejsce, obecna);
    }

    public void dodajWszystkieWydarzenia(List<Wydarzenie> wydarzenia)
    {
        for (Wydarzenie w:wydarzenia)
        {
               dodajWydarzenie(w);
        }
    }
    
    public boolean usunWydarzenie(Calendar obecna,int indeks)
    {
        boolean sprawdz=getDzien(obecna).usun(indeks);
        if(getDzien(obecna).getLista().size()==0)
            usun(getDzien(obecna));
    	return sprawdz;
    }
    
    public void usunWydarzeniaStarszeNiz(int dzien,int miesiac,int rok)
    {
    	sortuj();
    	Dzien data = new Dzien(dzien, miesiac, rok);
    	for (Dzien d : listaDni) 
    	{
    		if(d.compareTo(data)>=0)
    			break;
    		d.getLista().clear();
    	}
    }
    
    public void usunWydarzeniaStarszeNiz(Calendar dzien)
    {
    	sortuj();
    	Dzien data = new Dzien(dzien);
    	for (Dzien d : listaDni) 
    	{
    		if(d.compareTo(data)>=0)
    			break;
    		d.getLista().clear();
    	}
    }
    
    public List<Wydarzenie> wydarzeniaZawierajace (String s)
    {
    	List<Wydarzenie> wybrane=new ArrayList<>();
    	for (Dzien dzien : listaDni) 
    	{
    		wybrane.addAll(dzien.wydarzeniaZawierajace(s));
		}
    	return wybrane;
    }

    public List<Wydarzenie> wszystkieWydarzenia ()
    {
        List<Wydarzenie> wybrane = new ArrayList<>();
        for (Dzien dzien : listaDni)
        {
            wybrane.addAll(dzien.getLista());
        }
        return wybrane;
    }

    @Override
    public String toString()
    {
        StringBuilder wszystko=new StringBuilder();
        wszystko.append("WYDARZENIA\n");
        for (Dzien d:listaDni)
        {
            wszystko.append(d.toString());
        }
        return "\n"+wszystko.toString();
    }

    public static String dodajZero(int liczba)
    {
        return String.format("%02d", liczba);
    }

    public static String oProgramie()
    {
    	return "Autorzy:\nIdalia Tybińkowska 216908\nPrzemysław Komuda 216802";
    }
}