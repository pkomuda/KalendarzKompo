package com.itpk.kalendarz.logika;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.itpk.kalendarz.gui.Kalendarz;

public class Wydarzenie
{
    private String opis;
    private String miejsce;
    private Calendar data;
    private Boolean czyGodzina;
    private Boolean czyMiejsce;
    private Przypomnienie przypomnienie;
    boolean czyPowiadomiono;
  
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
    public Wydarzenie(String opis, Calendar data, Timer godzina)
    {
        this.opis = opis;
        this.data = data;
        this.godzina = godzina;
        this.przypomnienie = Przypomnienie.DZIEN_PRZED;
    }

    public Wydarzenie(String opis, String miejsce, Calendar data)
    {
        this.opis = opis;
        this.miejsce = miejsce;
        this.data = data;
        this.przypomnienie = Przypomnienie.DZIEN_PRZED;
    }
    */

    public String getOpis()
    {
        return opis;
    }

    public void setOpis(String opis)
    {
        this.opis = opis;
    }

    public String getMiejsce()
    {
        return miejsce;
    }

    public void setMiejsce(String miejsce)
    {
        this.miejsce = miejsce;
    }

    public Calendar getData()
    {
        return data;
    }
    
    public int[] getDataTab()
    {
        return new int[]{data.get(Calendar.YEAR), data.get(Calendar.MONTH), data.get(Calendar.DAY_OF_MONTH)};
    }

    public void setData(Calendar data)
    {
        this.data = data;
    }

    public Przypomnienie getPrzypomnienie()
    {
        return przypomnienie;
    }

    public void setPrzypomnienie(Przypomnienie przypom)
    {
        this.przypomnienie = przypom;
    }
    
    public Boolean getCzyGodzina()
	{
		return czyGodzina;
	}
    
    public void setCzyGodzina(boolean czyGodzina)
    {
    	this.czyGodzina = czyGodzina;
    }
    
    public int getRok()
    {
    	return data.get(Calendar.YEAR);
    }
    
    public void setRok(int rok)
    {
    	data.set(Calendar.YEAR, rok);
    }
    
    // Trzeba dodac 1 !!!
    public int getMiesiac()
    {
    	return data.get(Calendar.MONTH);
    }
    
    public void setMiesiac(int miesiac)
    {
    	data.set(Calendar.MONTH, miesiac);
    }
    
    public int getDzien()
    {
    	return data.get(Calendar.DAY_OF_MONTH);
    }
    
    public void setDzien(int dzien)
    {
    	data.set(Calendar.DAY_OF_MONTH, dzien);
    }
    
    public int getGodzina()
	{
		return data.get(Calendar.HOUR_OF_DAY);
	}
    
    public void setGodzina(int godzina)
	{
		data.set(Calendar.HOUR_OF_DAY, godzina);
	}
    
    public int getMinuta()
	{
		return data.get(Calendar.MINUTE);
	}
    
    public void setMinuta(int minuta)
	{
		data.set(Calendar.MINUTE, minuta);
	}
    
    public boolean getCzyPowiadomiono()
    {
    	return czyPowiadomiono;
    }
    
    public void setCzyPowiadomiono(boolean czyPowiadomiono)
	{
		this.czyPowiadomiono = czyPowiadomiono;
	}

    @Override
    public String toString()
    {
    	if(czyGodzina&&czyMiejsce)
    		return Kalendarz.dodajZero(getGodzina())+":"+Kalendarz.dodajZero(getMinuta())+"\n"+opis+" - "+miejsce;
    	else if(czyMiejsce)
    		return opis+" - "+miejsce;
    	else if(czyGodzina)
    		return Kalendarz.dodajZero(getGodzina())+":"+Kalendarz.dodajZero(getMinuta())+"\n"+opis;
    	else return opis;
    }
    
    public String szczegolyWydarzenia()
    {
    	switch(przypomnienie)
    	{
    	case DZIEN_PRZED:
    		return "Jutro o "+ toString();
    		
    	case GODZINA_PRZED:
    		return "Za godzine o "+ toString();
    		
    	case TYDZIEN_PRZED:
    		return "Za tydzien o "+ toString();
    	default:
    		return toString();
    	}
    }
}