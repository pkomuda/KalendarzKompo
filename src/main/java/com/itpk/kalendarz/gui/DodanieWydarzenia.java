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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DodanieWydarzenia extends JFrame {

	private OkienkoWydarzenia glowneOkno;
	private JPanel panel;
	private JTextField poleOpis;
	private JTextField poleMiejsce;
	private JFormattedTextField poleGodzina;
	private JFormattedTextField poleMinuta;
	
	public DodanieWydarzenia(OkienkoWydarzenia glowneOkno, DefaultListModel listModel, boolean czyEdycja)
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		
		JLabel lblOpis = new JLabel("Opis");
		sl_panel.putConstraint(SpringLayout.NORTH, lblOpis, 25, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, lblOpis, 10, SpringLayout.WEST, panel);
		panel.add(lblOpis);
		
		JLabel lblMiejsce = new JLabel("Miejsce");
		sl_panel.putConstraint(SpringLayout.NORTH, lblMiejsce, 34, SpringLayout.SOUTH, lblOpis);
		sl_panel.putConstraint(SpringLayout.WEST, lblMiejsce, 0, SpringLayout.WEST, lblOpis);
		panel.add(lblMiejsce);
		
		JLabel lblGodzina = new JLabel("Godzina");
		sl_panel.putConstraint(SpringLayout.NORTH, lblGodzina, 34, SpringLayout.SOUTH, lblMiejsce);
		sl_panel.putConstraint(SpringLayout.WEST, lblGodzina, 0, SpringLayout.WEST, lblOpis);
		panel.add(lblGodzina);
		
		JLabel lblMinuta = new JLabel("Minuta");
		sl_panel.putConstraint(SpringLayout.NORTH, lblMinuta, 0, SpringLayout.NORTH, lblGodzina);
		panel.add(lblMinuta);
		
		poleOpis = new JTextField();
		sl_panel.putConstraint(SpringLayout.NORTH, poleOpis, -5, SpringLayout.NORTH, lblOpis);
		sl_panel.putConstraint(SpringLayout.WEST, poleOpis, 37, SpringLayout.EAST, lblOpis);
		sl_panel.putConstraint(SpringLayout.EAST, poleOpis, -27, SpringLayout.EAST, panel);
		panel.add(poleOpis);
		poleOpis.setColumns(10);
		
		poleMiejsce = new JTextField();
		sl_panel.putConstraint(SpringLayout.NORTH, poleMiejsce, -5, SpringLayout.NORTH, lblMiejsce);
		sl_panel.putConstraint(SpringLayout.WEST, poleMiejsce, 0, SpringLayout.WEST, poleOpis);
		sl_panel.putConstraint(SpringLayout.EAST, poleMiejsce, -154, SpringLayout.EAST, panel);
		panel.add(poleMiejsce);
		poleMiejsce.setColumns(10);
		
		NumberFormat format = NumberFormat.getInstance();
	    NumberFormatter formatterGodzina = new WlasnyNumberFormatter(format);
	    formatterGodzina.setValueClass(Integer.class);
	    formatterGodzina.setMinimum(0);
	    formatterGodzina.setMaximum(23);
	    formatterGodzina.setAllowsInvalid(false);
	    formatterGodzina.setCommitsOnValidEdit(true);
		poleGodzina = new JFormattedTextField(formatterGodzina);
		sl_panel.putConstraint(SpringLayout.WEST, lblMinuta, 21, SpringLayout.EAST, poleGodzina);
		sl_panel.putConstraint(SpringLayout.NORTH, poleGodzina, -5, SpringLayout.NORTH, lblGodzina);
		sl_panel.putConstraint(SpringLayout.WEST, poleGodzina, 0, SpringLayout.WEST, poleOpis);
		sl_panel.putConstraint(SpringLayout.EAST, poleGodzina, -318, SpringLayout.EAST, panel);
		panel.add(poleGodzina);
		
		NumberFormatter formatterMinuta = new WlasnyNumberFormatter(format);
		formatterMinuta.setValueClass(Integer.class);
		formatterMinuta.setMinimum(0);
		formatterMinuta.setMaximum(59);
		formatterMinuta.setAllowsInvalid(false);
		formatterMinuta.setCommitsOnValidEdit(true);
		poleMinuta = new JFormattedTextField(formatterMinuta);
		sl_panel.putConstraint(SpringLayout.NORTH, poleMinuta, -5, SpringLayout.NORTH, lblGodzina);
		sl_panel.putConstraint(SpringLayout.WEST, poleMinuta, 25, SpringLayout.EAST, lblMinuta);
		sl_panel.putConstraint(SpringLayout.EAST, poleMinuta, -182, SpringLayout.EAST, panel);
		panel.add(poleMinuta);
		
		JLabel lblPrzypomnienie = new JLabel("Przypomnienie");
		sl_panel.putConstraint(SpringLayout.NORTH, lblPrzypomnienie, 29, SpringLayout.SOUTH, poleGodzina);
		sl_panel.putConstraint(SpringLayout.WEST, lblPrzypomnienie, 0, SpringLayout.WEST, lblOpis);
		panel.add(lblPrzypomnienie);
		
		JRadioButton wyborGodzina = new JRadioButton("godzina przed");
		sl_panel.putConstraint(SpringLayout.NORTH, wyborGodzina, 16, SpringLayout.SOUTH, lblPrzypomnienie);
		sl_panel.putConstraint(SpringLayout.WEST, wyborGodzina, 0, SpringLayout.WEST, lblOpis);
		panel.add(wyborGodzina);
		
		JRadioButton wyborDzien = new JRadioButton("dzień przed");
		sl_panel.putConstraint(SpringLayout.NORTH, wyborDzien, 0, SpringLayout.NORTH, wyborGodzina);
		sl_panel.putConstraint(SpringLayout.WEST, wyborDzien, 0, SpringLayout.WEST, lblMinuta);
		panel.add(wyborDzien);
		
		JRadioButton wyborTydzien = new JRadioButton("tydzień przed");
		sl_panel.putConstraint(SpringLayout.NORTH, wyborTydzien, 0, SpringLayout.NORTH, wyborGodzina);
		sl_panel.putConstraint(SpringLayout.WEST, wyborTydzien, 19, SpringLayout.EAST, wyborDzien);
		panel.add(wyborTydzien);
		
		JButton przyciskZatwierdz = new JButton("Zatwierdź");
		if (!czyEdycja)
		{
			przyciskZatwierdz.addMouseListener(new MouseAdapter()
			{
				@Override
				public void mouseClicked(MouseEvent e)
				{
					if(poleGodzina.getValue()==null&&poleMinuta.getValue()==null)
						glowneOkno.getKalendarz().getDni().dodajWydarzenie(poleOpis.getText(), poleMiejsce.getText(), glowneOkno.getObecna());
					else if(poleGodzina.getValue()==null)
						glowneOkno.getKalendarz().getDni().dodajWydarzenie(poleOpis.getText(), poleMiejsce.getText(), glowneOkno.getObecna(), 0, (Integer)poleMinuta.getValue());
					else if(poleMinuta.getValue()==null)
						glowneOkno.getKalendarz().getDni().dodajWydarzenie(poleOpis.getText(), poleMiejsce.getText(), glowneOkno.getObecna(), (Integer)poleGodzina.getValue(), 0);
					else
						glowneOkno.getKalendarz().getDni().dodajWydarzenie(poleOpis.getText(), poleMiejsce.getText(), glowneOkno.getObecna(), (Integer)poleGodzina.getValue(), (Integer)poleMinuta.getValue());
	//				for (Wydarzenie w : glowneOkno.getKalendarz().getDni().getDzien(glowneOkno.getObecna()).getLista())
	//				{
	////					listModel.addElement(w.toString());
	//					System.out.println(w);
	//					//System.out.println(glowneOkno.getObecna().getTime());
	//				}
					if(glowneOkno.getKalendarz().getDni().getDzien(glowneOkno.getObecna())!=null)
					{
						glowneOkno.getKalendarz().getDni().getDzien(glowneOkno.getObecna()).getLista().sort(new ComparatorPoDacie());
						listModel.clear();
						for (Wydarzenie w : glowneOkno.getKalendarz().getDni().getDzien(glowneOkno.getObecna()).getLista())
						{
							listModel.addElement(w.toString());
	//						System.out.println(w);
						}
						glowneOkno.getList().setModel(listModel);
					}
					Frame.getFrames()[getFrames().length-1].dispose();
				}
			});
		}
		else
		{
			przyciskZatwierdz.addMouseListener(new MouseAdapter()
			{
				@Override
				public void mouseClicked(MouseEvent e)
				{
					if(poleGodzina.getValue()==null&&poleMinuta.getValue()==null)
					{
						glowneOkno.zaznaczoneWydarzenie().setOpis(poleOpis.getText());
						glowneOkno.zaznaczoneWydarzenie().setMiejsce(poleMiejsce.getText());
						glowneOkno.zaznaczoneWydarzenie().setCzyGodzina(false);
					}
					else if(poleGodzina.getValue()==null)
					{
						glowneOkno.zaznaczoneWydarzenie().setOpis(poleOpis.getText());
						glowneOkno.zaznaczoneWydarzenie().setMiejsce(poleMiejsce.getText());
						glowneOkno.zaznaczoneWydarzenie().getData().set(Calendar.MINUTE, (int)poleMinuta.getValue());
						glowneOkno.zaznaczoneWydarzenie().setCzyGodzina(false);
					}
					else if(poleMinuta.getValue()==null)
					{
						glowneOkno.zaznaczoneWydarzenie().setOpis(poleOpis.getText());
						glowneOkno.zaznaczoneWydarzenie().setMiejsce(poleMiejsce.getText());
						glowneOkno.zaznaczoneWydarzenie().getData().set(Calendar.HOUR_OF_DAY, (int)poleGodzina.getValue());
						glowneOkno.zaznaczoneWydarzenie().setCzyGodzina(false);
					}
					else
					{
						glowneOkno.zaznaczoneWydarzenie().setOpis(poleOpis.getText());
						glowneOkno.zaznaczoneWydarzenie().setMiejsce(poleMiejsce.getText());
						glowneOkno.zaznaczoneWydarzenie().setGodzina((int)poleGodzina.getValue());
						glowneOkno.zaznaczoneWydarzenie().setMinuta((int)poleMinuta.getValue());
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
		sl_panel.putConstraint(SpringLayout.SOUTH, przyciskZatwierdz, 0, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, przyciskZatwierdz, -10, SpringLayout.EAST, panel);
		panel.add(przyciskZatwierdz);
	}
	
	public void setOpis(String opis)
	{
		poleOpis.setText(opis);
	}
	
	public void setMiejsce(String miejsce)
	{
		poleMiejsce.setText(miejsce);
	}
	
	public void setGodzina(int godzina)
	{
		poleGodzina.setValue(godzina);
	}
	
	public void setMinuta(int minuta)
	{
		poleMinuta.setValue(minuta);
	}
}