package com.itpk.kalendarz.logika;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Klasa zarzadzajaca wydarzeniami
 */
public class Wydarzenie
{
    /**
     * opis Opis wydarzenia
     * miejsce Miejsce Wydarzenia
     * data Data wydarzenia
     * czyGodzina Czy uzytkownik ustawil godzine wydarzenia
     * czyMiejsce Czy uzytkownik ustawil miejsce wydarzenia
     * przypomnienie Przypomnienie dla wydarzenia
     * czyPowiadomiono Czy juz powidomiono o danym wydarzeniu
     */
    private String opis;
    private String miejsce;
    private Calendar data;
    private Boolean czyGodzina;
    private Boolean czyMiejsce;
    private Przypomnienie przypomnienie;
    private boolean czyPowiadomiono;

    /**
     * Konstruktor wydarzenia
     * @param opis Opis wudarzenia
     * @param miejsce Miejsce wydarzenia
     * @param data Data wydarzenia
     */
    public Wydarzenie(String opis, String miejsce, Calendar data)
    {
    	czyMiejsce=(miejsce.length()!=0?true:false);
        this.opis = opis;
        this.miejsce = miejsce;
        this.data = new GregorianCalendar(data.get(Calendar.YEAR),data.get(Calendar.MONTH),data.get(Calendar.DAY_OF_MONTH),0,0);
        this.czyGodzina=false;
        this.przypomnienie = Przypomnienie.DZIEN_PRZED;
        
        Calendar teraz=new GregorianCalendar();
        if(this.data.compareTo(teraz)<=0)
        	this.czyPowiadomiono = true;
        else this.czyPowiadomiono = false;
    }

    /**
     * Konstruktor
     * @param opis Opis wydarzenia
     * @param miejsce Miejsce wydarzenia
     * @param data Data wydarzenia
     * @param godzina Godzina wydarzenia
     * @param minuta Minuta wydarzenia
     * @param przypomnienie Przypomnienie dla wydarzenia
     */
    public Wydarzenie(String opis, String miejsce, Calendar data,int godzina, int minuta,Przypomnienie przypomnienie)
    {
    	czyMiejsce=(miejsce.length()!=0?true:false);
        this.opis = opis;
        this.miejsce = miejsce;
        this.data = new GregorianCalendar(data.get(Calendar.YEAR),data.get(Calendar.MONTH),data.get(Calendar.DAY_OF_MONTH),godzina,minuta);
        this.czyGodzina=true;
        this.data.set(Calendar.HOUR_OF_DAY, godzina);
        this.data.set(Calendar.MINUTE, minuta);
        this.przypomnienie = przypomnienie;
        
        Calendar teraz=new GregorianCalendar();
        if(this.data.compareTo(teraz)<=0)
        	this.czyPowiadomiono = true;
        else this.czyPowiadomiono = false;
    }

    /**
     * Konstruktor
     * @param opis Opis wydarzenia
     * @param miejsce Miejsce wydarzenia
     * @param data Data wydarzenia
     * @param przypomnienie Przypomnienie dla wydarzenia
     */
    public Wydarzenie(String opis, String miejsce, Calendar data,Przypomnienie przypomnienie)
    {
        czyMiejsce=(miejsce.length()!=0?true:false);
        this.opis = opis;
        this.miejsce = miejsce;
        this.data = data;
        this.czyGodzina=true;
        this.przypomnienie = przypomnienie;

        Calendar teraz=new GregorianCalendar();
        if(this.data.compareTo(teraz)<=0)
            this.czyPowiadomiono = true;
        else this.czyPowiadomiono = false;
    }

    /**
     * Konstruktor
     * @param opis Opis wydarzenia
     * @param miejsce Miejsce wydarzenia
     * @param data Data wydarzenia
     * @param godzina Godzina wydarzenia
     * @param minuta Minuta wydarzenia
     */
    public Wydarzenie(String opis, String miejsce, Calendar data,int godzina, int minuta)
    {
    	czyMiejsce=(miejsce.length()!=0?true:false);
        this.opis = opis;
        this.miejsce = miejsce;
        this.data = new GregorianCalendar(data.get(Calendar.YEAR),data.get(Calendar.MONTH),data.get(Calendar.DAY_OF_MONTH),godzina,minuta);
        this.czyGodzina=true;
        this.data.set(Calendar.HOUR_OF_DAY, godzina);
        this.data.set(Calendar.MINUTE, minuta);
        this.przypomnienie = Przypomnienie.DZIEN_PRZED;
        
        Calendar teraz=new GregorianCalendar();
        if(this.data.compareTo(teraz)<=0)
        	this.czyPowiadomiono = true;
        else this.czyPowiadomiono = false;
    }

    /**
     * Konstruktor domyslny
     */
    public Wydarzenie()
    {
        this.opis = "";
        this.miejsce = "";
        this.czyMiejsce=false;
        this.data = new GregorianCalendar();
        this.czyGodzina=false;
        this.przypomnienie = Przypomnienie.DZIEN_PRZED;
        
        Calendar teraz=new GregorianCalendar();
        if(this.data.compareTo(teraz)<=0)
        	this.czyPowiadomiono = true;
        else this.czyPowiadomiono = false;
    }

    /*
     * Gettery i settery
     */

    /**
     * Funkcja zwraca opis wydarzenia
     * @return Opis wydarzenia
     */
    public String getOpis()
    {
        return opis;
    }

    /**
     * Funkcja ustawia opis obecnego wydarzenia
     * @param opis Opis dla obecnego wydarzenia
     */
    public void setOpis(String opis)
    {
        this.opis = opis;
    }

    /**
     * Funkcja zwraca miejsce wydarzenia
     * @return Miejsce wydarzenia
     */
    public String getMiejsce()
    {
        return miejsce;
    }
    /**
     * Funkcja ustawia miejsce obecnego wydarzenia
     * @param miejsce Miejsce dla obecnego wydarzenia
     */
    public void setMiejsce(String miejsce)
    {
        this.miejsce = miejsce;
    }

    /**
     * Funkcja zwraca date wydarzenia
     * @return Data wydarzenia
     */
    public Calendar getData()
    {
        return data;
    }

    /**
     * Funkcja informuje czy zostalo ustawione miejsce wydarzenia
     * @return Czy ustawiono miejsce wydarzenia
     */
    public Boolean getCzyMiejsce()
    {
        return czyMiejsce;
    }

    /**
     * Funkcja zwraca date wydarzenia
     * @return Data wydarzenia
     */
    public String getDataSQL()
    {
        return getRok() + "-" + RepozytoriumDni.dodajZero(getMiesiac()+1) + "-" + RepozytoriumDni.dodajZero(getDzien()) + " " + RepozytoriumDni.dodajZero(getGodzina()) + ":" + RepozytoriumDni.dodajZero(getMinuta());
    }

    /**
     * Funkcja zwraca date wydarzenia
     * @return Data wydarzenia
     */
    public int[] getDataTab()
    {
        return new int[]{data.get(Calendar.YEAR), data.get(Calendar.MONTH), data.get(Calendar.DAY_OF_MONTH)};
    }

    /**
     * Funkcja ustawia data obecnego wydarzenia
     * @param data Data dla obecnego wydarzenia
     */
    public void setData(Calendar data)
    {
        this.data = data;
    }

    /**
     * Funkcja zwraca przypomnienie dla wydarzenia
     * @return Przypomnienie wydarzenia
     */
    public Przypomnienie getPrzypomnienie()
    {
        return przypomnienie;
    }

    /**
     * Funkcja ustawia przypomnienie dla obecnego wydarzenia
     * @param przypom Przypomnienie dla obecnego wydarzenia
     */
    public void setPrzypomnienie(Przypomnienie przypom)
    {
        this.przypomnienie = przypom;
    }

    /**
     * Funkcja informuje czy zostala ustawiona godzina wydarzenia
     * @return Czy ustawiono godzine wydarzenia
     */
    public Boolean getCzyGodzina()
	{
		return czyGodzina;
	}

    /**
     * Funkcja ustawia czy godzina zostala podana
     * @param czyGodzina Czy godzina zostala podana
     */
    public void setCzyGodzina(boolean czyGodzina)
    {
    	this.czyGodzina = czyGodzina;
    }

    /**
     * Funkcja zwraca rok wydarzenia
     * @return Rok wydarzenia
     */
    public int getRok()
    {
    	return data.get(Calendar.YEAR);
    }

    /**
     * Funkcja ustawia rok obecnego wydarzenia
     * @param rok Rok dla obecnego wydarzenia
     */
    public void setRok(int rok)
    {
    	data.set(Calendar.YEAR, rok);
    }

    /**
     * Funkcja zwraca miesiac wydarzenia
     * @return Miesiac wydarzenia
     */
    public int getMiesiac()
    {
    	return data.get(Calendar.MONTH);
    }

    /**
     * Funkcja ustawia miesiac obecnego wydarzenia
     * @param miesiac Miesiac dla obecnego wydarzenia
     */
    public void setMiesiac(int miesiac)
    {
    	data.set(Calendar.MONTH, miesiac);
    }

    /**
     * Funkcja zwraca dzien wydarzenia
     * @return Dzien wydarzenia
     */
    public int getDzien()
    {
    	return data.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Funkcja ustawia dzien obecnego wydarzenia
     * @param dzien Dzien dla obecnego wydarzenia
     */
    public void setDzien(int dzien)
    {
    	data.set(Calendar.DAY_OF_MONTH, dzien);
    }

    /**
     * Funkcja zwraca godzina wydarzenia
     * @return Godzina wydarzenia
     */
    public int getGodzina()
	{
		return data.get(Calendar.HOUR_OF_DAY);
	}
    /**
     * Funkcja ustawia godzina obecnego wydarzenia
     * @param godzina Godzina dla obecnego wydarzenia
     */
    public void setGodzina(int godzina)
	{
		data.set(Calendar.HOUR_OF_DAY, godzina);
	}

    /**
     * Funkcja zwraca minute wydarzenia
     * @return Minuta wydarzenia
     */
    public int getMinuta()
	{
		return data.get(Calendar.MINUTE);
	}
    /**
     * Funkcja ustawia minuta obecnego wydarzenia
     * @param minuta Minuta dla obecnego wydarzenia
     */
    public void setMinuta(int minuta)
	{
		data.set(Calendar.MINUTE, minuta);
	}

    /**
     * Funkcja informuje czy juz powiadomiono o wydarzeniu
     * @return Czy powiadomiono o wydarzeniu
     */
    public boolean getCzyPowiadomiono()
    {
    	return czyPowiadomiono;
    }

    /**
     * Funkcja ustawia czy juz powiadomiono o obecnym wydarzeniu
     * @param czyPowiadomiono Czy powiadomiono o obecnym wydarzeniu
     */
    public void setCzyPowiadomiono(boolean czyPowiadomiono)
	{
		this.czyPowiadomiono = czyPowiadomiono;
	}

    /**
     * Funkcja zwraca ciag znakow opisujacy obecne wydarzenie
     * @return Ciag znakow z szczegolami wydarzenia
     */
    @Override
    public String toString()
    {
    	if(czyGodzina&&czyMiejsce)
    		return RepozytoriumDni.dodajZero(getGodzina())+":"+RepozytoriumDni.dodajZero(getMinuta())+" "+opis+" - "+miejsce;
    	else if(czyMiejsce)
    		return opis+" - "+miejsce;
    	else if(czyGodzina)
    		return RepozytoriumDni.dodajZero(getGodzina())+":"+RepozytoriumDni.dodajZero(getMinuta())+" "+opis;
    	else return opis;
    }

    /**
     * Funkcja zwraca ciag znakow opisujacy szczegoly wydarzenia
     * @return Ciag znakow z szczegolami wydarzenia
     */
    public String szczegolyWydarzenia()
    {
    	switch(przypomnienie)
    	{
    	case DZIEN_PRZED:
    		return "Jutro "+ toString();
    		
    	case GODZINA_PRZED:
    		return "Dzisiaj "+ toString();
    		
    	case TYDZIEN_PRZED:
    		return "Za tydzien "+ toString();
    	default:
    		return toString();
    	}
    }
}