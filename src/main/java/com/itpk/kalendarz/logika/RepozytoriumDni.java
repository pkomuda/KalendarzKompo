package com.itpk.kalendarz.logika;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.itpk.kalendarz.logika.wyjatki.BrakWydarzenWyjatek;

/**
 * Klasa zarzadzajaca repozytorium dni
 */

public class RepozytoriumDni implements Kolekcja<Dzien>
{
    /**
     * Lista dni
     */
    private List<Dzien> listaDni;

    /**
     * Konstruktor repozytorium dni
     */

    public RepozytoriumDni()
    {
        this.listaDni = new ArrayList<>();
    }

    /*
     * gettery i settery
     */


    /**
     * Funkcja zwraca liste dni
     * @return Lista wszystkich dni
     */
    @Override
    public List<Dzien> getLista()
    {
        return this.listaDni;
    }

    /**
     * Funkcja ustawia liste dni
     * @param listaDni Lista dni przekazana przez uzytkownia
     */
    @Override
    public void setLista(List<Dzien> listaDni)
    {
        this.listaDni = listaDni;
    }

    /**
     * Funkcja zwracajaca dzien o podanej dacie
     * @param dzien Dzien miesiaca
     * @param miesiac Miesiac w roku
     * @param rok Rok
     * @return Dzien o podanej dacie
     * @throws BrakWydarzenWyjatek Brak wydarzen w tym dniu
     */
    public Dzien getDzien(int dzien, int miesiac, int rok) throws BrakWydarzenWyjatek
    {
        Dzien temp = new Dzien(dzien, miesiac, rok);
        if (!listaDni.contains(temp) || listaDni.get(listaDni.indexOf(temp)).getLista().isEmpty())
            throw new BrakWydarzenWyjatek();
        else
            return listaDni.get(listaDni.indexOf(temp));
    }

    /**
     * Funkcja zwracajaca dzien o podanej dacie
     * @param obecna Data poczukiwanego dnia
     * @return Dzien o podanej dacie
     * lub null w przypadku braku wydarzen w tym dniu
     */

    public Dzien getDzien(Calendar obecna)
    {
        Dzien temp = new Dzien(obecna);
        if (listaDni.contains(temp))
            return listaDni.get(listaDni.indexOf(temp));
        else
            return null;
    }

    /**
     * Funkcja dodaje dzien do repozytorium
     * @param dzien Dzien ktory chcemy dodac
     * @return Czy udalo sie dodac dzien
     */
    @Override
    public boolean dodaj(Dzien dzien)
    {
        return this.listaDni.add(dzien);
    }

    /**
     * Funkcja usuwa dzien z repozytorium dni
     * @param dzien Dzien ktory chcemy usunac
     * @return Czy udalo sie usunac dzien
     */
    @Override
    public boolean usun(Dzien dzien)
    {
        return this.listaDni.remove(dzien);
    }

    /**
     * Funkcja sortuje dni w repozytoruim po dacie
     */
    public void sortuj()
    {
    	listaDni.sort(Dzien::compareTo);
    }

    /**
     * Funkcja dodaje wydarzenie do dnia z konkretna data
     * @param w Wydarzenie ktore chcemy dodac
     * @return Czy udało sie dodac wydarzenie
     */

    public boolean dodajWydarzenie(Wydarzenie w)
    {
        if(!listaDni.contains(new Dzien(w.getData())))
            dodaj(new Dzien(w.getData()));
        return getDzien(w.getData()).dodaj(w);
    }

    /**
     * Funkcja dodaje wydarzenie do dnia z konkretna data
     * @param opis Opis wydarzenia
     * @param miejsce Miejsce wydarzenia
     * @param obecna Dzien w ktorym chemy dodac wydarzenie
     * @param godzina Godzina wydarzenia
     * @param minuta Minuta wydarzenia
     * @return Czy udalo sie dodac wydarzenie
     */
    
    public boolean dodajWydarzenie(String opis, String miejsce, Calendar obecna,int godzina, int minuta)
    {
    	if(!listaDni.contains(new Dzien(obecna)))
    		dodaj(new Dzien(obecna));
    	return getDzien(obecna).dodaj(opis, miejsce, obecna, godzina, minuta);
    }

    /**
     * Funkcja dodaje calodniowe wydarzenie do dnia z konkretna data
     * @param opis Opis wydarzenia
     * @param miejsce Miejsce wydarzenia
     * @param obecna Dzien w ktorym chemy dodac wydarzenie
     * @return Czy udalo sie dodac wydarzenie
     */
    
    public boolean dodajWydarzenie(String opis, String miejsce, Calendar obecna)
    {
    	if(!listaDni.contains(new Dzien(obecna)))
    		dodaj(new Dzien(obecna));
    	return getDzien(obecna).dodaj(opis, miejsce, obecna);
    }

    /**
     * Funkcja dodaje do kalendarza wszystkie przekazane wydarzenia
     * @param wydarzenia Wydarzenia ktore chcemy dodać do kalendarza
     */

    public void dodajWszystkieWydarzenia(List<Wydarzenie> wydarzenia)
    {
        for (Wydarzenie w:wydarzenia)
        {
               dodajWydarzenie(w);
        }
    }

    /**
     * Funkcja usuwa wydarzenie z danego dnia o podanym indeksie w liscie wydarzen z dnia
     * @param obecna Dzien w ktorym chcemy usunac wydarzenie
     * @param indeks Indeks z listy wydarzen z dnia
     * @return Czy udalo sie usunac
     */
    
    public boolean usunWydarzenie(Calendar obecna,int indeks)
    {
        boolean sprawdz=getDzien(obecna).usun(indeks);
        if(getDzien(obecna).getLista().size()==0)
            usun(getDzien(obecna));
    	return sprawdz;
    }

    /**
     * Funkcja usuwa wydarzenia starsze niz podana data
     * @param dzien Dzien miesiaca
     * @param miesiac Miesiac w roku
     * @param rok Rok
     */
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

    /**
     * Funkcja usuwa wydarzenia starsze niz podany dzien
     * @param dzien Dzien przed ktorym usuwamy wszystkie wydarzenia
     */
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

    /**
     * Funkcja zwraca liste przefiltrowanych wydarzen
     * @param s Ciag znakow wedlug ktorych filtrujemy wydarzenia
     * @return Lista wyfiltrowanych wydarzen
     */
    
    public List<Wydarzenie> wydarzeniaZawierajace (String s)
    {
    	List<Wydarzenie> wybrane=new ArrayList<>();
    	for (Dzien dzien : listaDni) 
    	{
    		wybrane.addAll(dzien.wydarzeniaZawierajace(s));
		}
    	return wybrane;
    }

    /**
     * Funkcja zwraca wszystkie wydarzenia
     * @return Wszystkie wydarzenia
     */

    public List<Wydarzenie> wszystkieWydarzenia ()
    {
        List<Wydarzenie> wybrane = new ArrayList<>();
        for (Dzien dzien : listaDni)
        {
            wybrane.addAll(dzien.getLista());
        }
        return wybrane;
    }

    /**
     * Funkcja zwraca ciag znakow opisujacy wszystkie wydarzenia
     * @return Ciag znakow z wydarzeniami
     */
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

    /**
     * Funkcja dodaje zero w jedno cyfrowym miesiacu i dniu
     * @param liczba Numer miesiaca i dnia
     * @return Liczba z zerem z przodu
     */
    public static String dodajZero(int liczba)
    {
        return String.format("%02d", liczba);
    }

    /**
     * Funkcja zwraca informacje o autorach
     * @return Informacja o autorach
     */
    public static String oProgramie()
    {
    	return "Autorzy:\nIdalia Tybińkowska 216908\nPrzemysław Komuda 216802";
    }
}