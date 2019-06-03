package com.itpk.kalendarz.prezentacja.gui;

import java.text.NumberFormat;
import java.util.Enumeration;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;

import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

public abstract class UstawieniaWydarzenia extends JFrame{
	
	private OkienkoWydarzenia glowneOkno;
	private JPanel panel;
	private JTextField poleOpis;
	private JTextField poleMiejsce;
	private JFormattedTextField poleGodzina;
	private JFormattedTextField poleMinuta;
	private JButton przyciskZatwierdz;
	private JRadioButton wyborDzien;
	private JRadioButton wyborTydzien;
	private JRadioButton wyborGodzina;
	private ButtonGroup grupa;
	
	public UstawieniaWydarzenia(OkienkoWydarzenia glowneOkno, DefaultListModel<String> listModel)
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
		
		poleGodzina = new JFormattedTextField(getFormatter(0,23));
		sl_panel.putConstraint(SpringLayout.WEST, lblMinuta, 21, SpringLayout.EAST, poleGodzina);
		sl_panel.putConstraint(SpringLayout.NORTH, poleGodzina, -5, SpringLayout.NORTH, lblGodzina);
		sl_panel.putConstraint(SpringLayout.WEST, poleGodzina, 0, SpringLayout.WEST, poleOpis);
		sl_panel.putConstraint(SpringLayout.EAST, poleGodzina, -318, SpringLayout.EAST, panel);
		panel.add(poleGodzina);
		
		poleMinuta = new JFormattedTextField(getFormatter(0,59));
		sl_panel.putConstraint(SpringLayout.NORTH, poleMinuta, -5, SpringLayout.NORTH, lblGodzina);
		sl_panel.putConstraint(SpringLayout.WEST, poleMinuta, 25, SpringLayout.EAST, lblMinuta);
		sl_panel.putConstraint(SpringLayout.EAST, poleMinuta, -182, SpringLayout.EAST, panel);
		panel.add(poleMinuta);
		
		JLabel lblPrzypomnienie = new JLabel("Przypomnienie");
		sl_panel.putConstraint(SpringLayout.NORTH, lblPrzypomnienie, 29, SpringLayout.SOUTH, poleGodzina);
		sl_panel.putConstraint(SpringLayout.WEST, lblPrzypomnienie, 0, SpringLayout.WEST, lblOpis);
		panel.add(lblPrzypomnienie);
		
		wyborGodzina = new JRadioButton("godzina przed");
		sl_panel.putConstraint(SpringLayout.NORTH, wyborGodzina, 16, SpringLayout.SOUTH, lblPrzypomnienie);
		sl_panel.putConstraint(SpringLayout.WEST, wyborGodzina, 0, SpringLayout.WEST, lblOpis);
		panel.add(wyborGodzina);
		
		wyborDzien = new JRadioButton("dzień przed");
		sl_panel.putConstraint(SpringLayout.NORTH, wyborDzien, 0, SpringLayout.NORTH, wyborGodzina);
		sl_panel.putConstraint(SpringLayout.WEST, wyborDzien, 0, SpringLayout.WEST, lblMinuta);
		panel.add(wyborDzien);
		
		wyborTydzien = new JRadioButton("tydzień przed");
		sl_panel.putConstraint(SpringLayout.NORTH, wyborTydzien, 0, SpringLayout.NORTH, wyborGodzina);
		sl_panel.putConstraint(SpringLayout.WEST, wyborTydzien, 19, SpringLayout.EAST, wyborDzien);
		panel.add(wyborTydzien);
		
		grupa = new ButtonGroup();
		grupa.add(wyborGodzina);
		grupa.add(wyborDzien);
		grupa.add(wyborTydzien);
		wyborDzien.setSelected(true);
		
		przyciskZatwierdz = new JButton("Zatwierdź");
		
		sl_panel.putConstraint(SpringLayout.SOUTH, przyciskZatwierdz, 0, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, przyciskZatwierdz, -10, SpringLayout.EAST, panel);
		panel.add(przyciskZatwierdz);
	}
	
	public JTextField getPoleOpis()
	{
		return poleOpis;
	}

	public JTextField getPoleMiejsce()
	{
		return poleMiejsce;
	}

	public JFormattedTextField getPoleGodzina()
	{
		return poleGodzina;
	}

	public JFormattedTextField getPoleMinuta()
	{
		return poleMinuta;
	}
	
	public JButton getPrzyciskZatwierdz()
	{
		return przyciskZatwierdz;
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
	
	public void setWyborGodzina(boolean zaznaczone)
	{
		wyborGodzina.setSelected(zaznaczone);
	}
	
	public void setWyborDzien(boolean zaznaczone)
	{
		wyborDzien.setSelected(zaznaczone);
	}
	
	public void setWyborTydzien(boolean zaznaczone)
	{
		wyborTydzien.setSelected(zaznaczone);
	}
	
	public String getZaznaczonyWybor()
	{
		for (Enumeration<AbstractButton> przyciski = grupa.getElements(); przyciski.hasMoreElements();)
		{
			AbstractButton przycisk = przyciski.nextElement();
			if (przycisk.isSelected())
				return przycisk.getText();
		}
		return null;
	}
	
	private NumberFormatter getFormatter(int min, int max)
	{
		NumberFormat format = NumberFormat.getInstance();
	    NumberFormatter formatter = new WlasnyNumberFormatter(format);
	    formatter.setValueClass(Integer.class);
	    formatter.setMinimum(min);
	    formatter.setMaximum(max);
	    formatter.setAllowsInvalid(false);
	    formatter.setCommitsOnValidEdit(true);
	    return formatter;
	}
}