package com.itpk.kalendarz.prezentacja.gui;

import java.awt.Frame;

import com.itpk.kalendarz.logika.ComparatorPoDacie;
import com.itpk.kalendarz.logika.Przypomnienie;
import com.itpk.kalendarz.logika.Wydarzenie;

import javax.swing.DefaultListModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DodanieWydarzenia extends UstawieniaWydarzenia {

	OkienkoWydarzenia glowneOkno;
	DefaultListModel<String> listModel;
	
	public DodanieWydarzenia(OkienkoWydarzenia glowneOkno, DefaultListModel listModel)
	{
		super(glowneOkno, listModel);
			getPrzyciskZatwierdz().addMouseListener(new MouseAdapter()
			{
				@Override
				public void mouseClicked(MouseEvent e)
				{
					if(getPoleGodzina().getValue()==null&&getPoleMinuta().getValue()==null)
						glowneOkno.getKalendarz().getDni().dodajWydarzenie(getPoleOpis().getText(), getPoleMiejsce().getText(), glowneOkno.getObecna());
					else if(getPoleGodzina().getValue()==null)
						glowneOkno.getKalendarz().getDni().dodajWydarzenie(getPoleOpis().getText(), getPoleMiejsce().getText(), glowneOkno.getObecna(), 0, (Integer)getPoleMinuta().getValue());
					else if(getPoleMinuta().getValue()==null)
						glowneOkno.getKalendarz().getDni().dodajWydarzenie(getPoleOpis().getText(), getPoleMiejsce().getText(), glowneOkno.getObecna(), (Integer)getPoleGodzina().getValue(), 0);
					else
						glowneOkno.getKalendarz().getDni().dodajWydarzenie(getPoleOpis().getText(), getPoleMiejsce().getText(), glowneOkno.getObecna(), (Integer)getPoleGodzina().getValue(), (Integer)getPoleMinuta().getValue());
					switch(getZaznaczonyWybor())
					{
		            	case "godzina przed":
		            		glowneOkno.getKalendarz().getDni().getDzien(glowneOkno.getObecna()).getLista().get(glowneOkno.getKalendarz().getDni().getDzien(glowneOkno.getObecna()).getLista().size()-1).setPrzypomnienie(Przypomnienie.GODZINA_PRZED);
		            		break;
		            	case "dzień przed":
		            		glowneOkno.getKalendarz().getDni().getDzien(glowneOkno.getObecna()).getLista().get(glowneOkno.getKalendarz().getDni().getDzien(glowneOkno.getObecna()).getLista().size()-1).setPrzypomnienie(Przypomnienie.DZIEN_PRZED);
		            		break;
		            	case "tydzień przed":
		            		glowneOkno.getKalendarz().getDni().getDzien(glowneOkno.getObecna()).getLista().get(glowneOkno.getKalendarz().getDni().getDzien(glowneOkno.getObecna()).getLista().size()-1).setPrzypomnienie(Przypomnienie.TYDZIEN_PRZED);
		            		break;
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