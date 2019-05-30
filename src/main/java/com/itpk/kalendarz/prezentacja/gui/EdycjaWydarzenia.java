package com.itpk.kalendarz.prezentacja.gui;

import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;

import javax.swing.DefaultListModel;

import com.itpk.kalendarz.logika.ComparatorPoDacie;
import com.itpk.kalendarz.logika.Przypomnienie;
import com.itpk.kalendarz.logika.Wydarzenie;

public class EdycjaWydarzenia extends UstawieniaWydarzenia
{
	OkienkoWydarzenia glowneOkno;
	DefaultListModel<String> listModel;
	
	public EdycjaWydarzenia(OkienkoWydarzenia glowneOkno, DefaultListModel<String> listModel)
	{
		super(glowneOkno, listModel);
		this.glowneOkno = glowneOkno;
		this.listModel = listModel;
		setTitle("Edycja wydarzenia");
		getPrzyciskZatwierdz().addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				glowneOkno.zaznaczoneWydarzenie().setOpis(getPoleOpis().getText());
				glowneOkno.zaznaczoneWydarzenie().setMiejsce(getPoleMiejsce().getText());
				switch(getZaznaczonyWybor())
				{
	            	case "godzina przed":
	            		glowneOkno.zaznaczoneWydarzenie().setPrzypomnienie(Przypomnienie.GODZINA_PRZED);
	            		break;
	            	case "dzień przed":
	            		glowneOkno.zaznaczoneWydarzenie().setPrzypomnienie(Przypomnienie.DZIEN_PRZED);
	            		break;
	            	case "tydzień przed":
	            		glowneOkno.zaznaczoneWydarzenie().setPrzypomnienie(Przypomnienie.TYDZIEN_PRZED);
	            		break;
				}
				if(getPoleGodzina().getValue()==null&&getPoleMinuta().getValue()==null)
				{
					glowneOkno.zaznaczoneWydarzenie().setCzyGodzina(false);
				}
				else if(getPoleGodzina().getValue()==null)
				{
					glowneOkno.zaznaczoneWydarzenie().getData().set(Calendar.MINUTE, (int)getPoleMinuta().getValue());
					glowneOkno.zaznaczoneWydarzenie().setCzyGodzina(false);
				}
				else if(getPoleMinuta().getValue()==null)
				{
					glowneOkno.zaznaczoneWydarzenie().getData().set(Calendar.HOUR_OF_DAY, (int)getPoleGodzina().getValue());
					glowneOkno.zaznaczoneWydarzenie().setCzyGodzina(false);
				}
				else
				{
					glowneOkno.zaznaczoneWydarzenie().setGodzina((int)getPoleGodzina().getValue());
					glowneOkno.zaznaczoneWydarzenie().setMinuta((int)getPoleMinuta().getValue());
					glowneOkno.zaznaczoneWydarzenie().setCzyGodzina(true);
				}
				if(glowneOkno.getKalendarz().getDni().getDzien(glowneOkno.getObecna())!=null)
				{
					glowneOkno.getKalendarz().getDni().getDzien(glowneOkno.getObecna()).getLista().sort(new ComparatorPoDacie());
					listModel.clear();
					for (Wydarzenie w : glowneOkno.getKalendarz().getDni().getDzien(glowneOkno.getObecna()).getLista())
					{
						listModel.addElement(w.toString());
					}
					glowneOkno.getList().setModel(listModel);
				}
				Frame.getFrames()[getFrames().length-1].dispose();
			}
		});
	}
}