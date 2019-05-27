package com.itpk.kalendarz.logika;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Wydarzenie
{
    private String opis;
    private String miejsce;
    private Calendar data;
    private Boolean czyGodzina;
    private Boolean czyMiejsce;
    private Przypomnienie przypomnienie;
    
  
    public Wydarzenie(String opis, String miejsce, Calendar data)
    {
    	czyMiejsce=(miejsce.length()!=0?true:false); 
        this.opis = opis;
        this.miejsce = miejsce;
        this.data = new GregorianCalendar(data.get(Calendar.YEAR),data.get(Calendar.MONTH),data.get(Calendar.DAY_OF_MONTH),0,0);
        this.czyGodzina=false;
        this.przypomnienie = Przypomnienie.DZIEN_PRZED;
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
    }

    public Wydarzenie()
    {
        this.opis = "";
        this.miejsce = "";
        this.czyMiejsce=false;
        this.data = new GregorianCalendar();
        this.czyGodzina=false;
        this.przypomnienie = Przypomnienie.DZIEN_PRZED;
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

    @Override
    public String toString()
    {
    	if(czyGodzina&&czyMiejsce)
    		return this.data.get(Calendar.HOUR_OF_DAY)+":"+this.data.get(Calendar.MINUTE)+"\n"+opis+" - "+miejsce;
    	else if(czyMiejsce)
    		return opis+" - "+miejsce;
    	else if(czyGodzina)
    		return this.data.get(Calendar.HOUR_OF_DAY)+":"+this.data.get(Calendar.MINUTE)+"\n"+opis;
    	else return opis;
			
    }
}