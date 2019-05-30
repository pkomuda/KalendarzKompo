package com.itpk.kalendarz.prezentacja.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;

import com.itpk.kalendarz.logika.RepozytoriumDni;

import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;

import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;

public class UsuniecieWydarzenia extends JFrame
{
	private JPanel panel;
	private JLabel podpisDzien;
	private JLabel podpisMiesiac;
	private JLabel podpisRok;
	private JFormattedTextField poleDzien;
	private JFormattedTextField poleMiesiac;
	private JFormattedTextField poleRok;
	private JButton przyciskZatwierdz;
	private JLabel podpis1;
	private JLabel podpis2;

	public UsuniecieWydarzenia(RepozytoriumDni dni)
	{	
		setTitle("Usuwanie wydarzeń starszych niż...");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		
		podpisDzien = new JLabel("Dzień");
		sl_panel.putConstraint(SpringLayout.WEST, podpisDzien, 90, SpringLayout.WEST, panel);
		panel.add(podpisDzien);
		
		podpisMiesiac = new JLabel("Miesiąc");
		sl_panel.putConstraint(SpringLayout.NORTH, podpisMiesiac, 87, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, podpisMiesiac, -207, SpringLayout.EAST, panel);
		sl_panel.putConstraint(SpringLayout.NORTH, podpisDzien, 0, SpringLayout.NORTH, podpisMiesiac);
		panel.add(podpisMiesiac);
		
		podpisRok = new JLabel("Rok");
		sl_panel.putConstraint(SpringLayout.NORTH, podpisRok, 0, SpringLayout.NORTH, podpisDzien);
		sl_panel.putConstraint(SpringLayout.WEST, podpisRok, 77, SpringLayout.EAST, podpisMiesiac);
		panel.add(podpisRok);
		
		poleDzien = new JFormattedTextField(getFormatter(1, 31));
		sl_panel.putConstraint(SpringLayout.NORTH, poleDzien, 22, SpringLayout.SOUTH, podpisDzien);
		sl_panel.putConstraint(SpringLayout.WEST, poleDzien, 82, SpringLayout.WEST, panel);
		panel.add(poleDzien);
		
		poleMiesiac = new JFormattedTextField(getFormatter(1, 12));
		sl_panel.putConstraint(SpringLayout.NORTH, poleMiesiac, 0, SpringLayout.NORTH, poleDzien);
		sl_panel.putConstraint(SpringLayout.EAST, poleMiesiac, -201, SpringLayout.EAST, panel);
		panel.add(poleMiesiac);
		
		poleRok = new JFormattedTextField(getFormatter(0, 9999));
		sl_panel.putConstraint(SpringLayout.NORTH, poleRok, 0, SpringLayout.NORTH, poleDzien);
		sl_panel.putConstraint(SpringLayout.EAST, poleRok, -87, SpringLayout.EAST, panel);
		panel.add(poleRok);
		
		przyciskZatwierdz = new JButton("Zatwierdź");
		przyciskZatwierdz.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (poleDzien.getValue()!=null && poleMiesiac.getValue()!=null && poleRok.getValue()!=null)
				{
					dni.usunWydarzeniaStarszeNiz(getDzien(), getMiesiac(), getRok());
					Frame.getFrames()[getFrames().length-1].dispose();
				}
				else
				{
					Frame.getFrames()[getFrames().length-1].dispose();
				}
			}
		});
		sl_panel.putConstraint(SpringLayout.WEST, przyciskZatwierdz, 159, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, przyciskZatwierdz, -40, SpringLayout.SOUTH, panel);
		panel.add(przyciskZatwierdz);
		
		podpis1 = new JLabel("-");
		sl_panel.putConstraint(SpringLayout.WEST, poleMiesiac, 20, SpringLayout.EAST, podpis1);
		sl_panel.putConstraint(SpringLayout.NORTH, podpis1, 130, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, podpis1, 156, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, poleDzien, -19, SpringLayout.WEST, podpis1);
		panel.add(podpis1);
		
		podpis2 = new JLabel("-");
		sl_panel.putConstraint(SpringLayout.WEST, poleRok, 24, SpringLayout.EAST, podpis2);
		sl_panel.putConstraint(SpringLayout.NORTH, podpis2, 5, SpringLayout.NORTH, poleDzien);
		sl_panel.putConstraint(SpringLayout.WEST, podpis2, 27, SpringLayout.EAST, poleMiesiac);
		sl_panel.putConstraint(SpringLayout.EAST, podpis2, 10, SpringLayout.EAST, przyciskZatwierdz);
		panel.add(podpis2);
	}
	
	private int getDzien()
	{
		return (int)poleDzien.getValue();
	}
	
	private int getMiesiac()
	{
		return (int)poleMiesiac.getValue() - 1;
	}
	
	private int getRok()
	{
		return (int)poleRok.getValue();
	}
	
	private NumberFormatter getFormatter(int min, int max)
	{
		NumberFormat format = NumberFormat.getInstance();
		format.setGroupingUsed(false);
	    NumberFormatter formatter = new WlasnyNumberFormatter(format);
	    formatter.setValueClass(Integer.class);
	    formatter.setMinimum(min);
	    formatter.setMaximum(max);
	    formatter.setAllowsInvalid(false);
	    formatter.setCommitsOnValidEdit(true);
	    return formatter;
	}
}