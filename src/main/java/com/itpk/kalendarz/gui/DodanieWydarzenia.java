package com.itpk.kalendarz.gui;

import java.awt.Frame;
import java.text.NumberFormat;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;

import com.itpk.kalendarz.logika.ComparatorPoDacie;
import com.itpk.kalendarz.logika.Wydarzenie;

import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
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