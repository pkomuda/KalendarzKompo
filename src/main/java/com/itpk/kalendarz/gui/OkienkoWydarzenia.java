package com.itpk.kalendarz.gui;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import com.itpk.kalendarz.logika.ComparatorPoDacie;
import com.itpk.kalendarz.logika.Wydarzenie;
import com.itpk.kalendarz.logika.wyjatki.BrakWydarzenWyjatek;

import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.Font;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OkienkoWydarzenia extends JFrame
{
	private Kalendarz glowneOkno;
	private JPanel panel;
	private JLabel naglowek;
	private JList<String> list;
	private Calendar obecna;
	private JPanel panelPrzyciski;
	private JButton usunPrzycisk;
	private JButton dodajPrzycisk;

	public OkienkoWydarzenia(Kalendarz glowneOkno)
	{
		this.glowneOkno = glowneOkno;
		
		/***********Okno dnia**************/
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 600);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new BorderLayout(0, 0));
		setContentPane(panel);
		
		/***********Naglowek*****************/
		naglowek = new JLabel("naglowek");
		naglowek.setFont(new Font("Lucida Grande", Font.BOLD, 24));
        naglowek.setText("Wydarzenia z dnia " + glowneOkno.getDzienMiesiaca() + "." + (glowneOkno.getData().get(Calendar.MONTH) + 1) + "." + glowneOkno.getData().get(Calendar.YEAR));
		panel.add(naglowek, BorderLayout.NORTH);
		
		obecna = new GregorianCalendar();
        obecna.set(glowneOkno.getData().get(Calendar.YEAR), glowneOkno.getData().get(Calendar.MONTH), glowneOkno.getDzienMiesiaca());
        
        /***************Lista wydarzeń***************/
        DefaultListModel<String> listModel = new DefaultListModel<>();
        List<Wydarzenie> temp;
		try
		{
			temp = new ArrayList<>(glowneOkno.getDni().getDzien(getDzienMiesiaca(obecna), getMiesiac(obecna), getRok(obecna)).getLista());
			for (Wydarzenie w : temp)
                listModel.addElement(w.toString());
		}
		catch (BrakWydarzenWyjatek e)
		{
			listModel.addElement("Brak wydarzen w tym dniu");
		}
		list = new JList<String>(listModel);
		list.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (e.getClickCount() == 2)
				{
					Wydarzenie w = glowneOkno.getDni().getDzien(obecna).getLista().get(list.getSelectedIndex());
					UstawieniaWydarzenia edycja = new EdycjaWydarzenia(getThis(), listModel);
					edycja.setLocationRelativeTo(edycja);
					edycja.setVisible(true);
					edycja.setOpis(w.getOpis());
					edycja.setMiejsce(w.getMiejsce());
					if (w.getCzyGodzina())
					{
						edycja.setGodzina(w.getGodzina());
						edycja.setMinuta(w.getMinuta());
					}
				}
			}
		});
		panel.add(list, BorderLayout.CENTER);
		
		panelPrzyciski = new JPanel();
		panel.add(panelPrzyciski, BorderLayout.SOUTH);
		
		/*********Dodanie wydarzenia*********/
		dodajPrzycisk = new JButton("Dodaj");
		dodajPrzycisk.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				UstawieniaWydarzenia okienko = new DodanieWydarzenia(getThis(), listModel);
				okienko.setLocationRelativeTo(okienko);
				okienko.setVisible(true);
			}
		});
		panelPrzyciski.add(dodajPrzycisk);
		
		/*******Usuniecie wydarzenia********/
		usunPrzycisk = new JButton("Usuń");
		usunPrzycisk.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				int zaznaczony = list.getSelectedIndex();
				if (zaznaczony != -1)
				{
				    listModel.remove(zaznaczony);
				    glowneOkno.getDni().usunWydarzenie(obecna, zaznaczony);
				    list.setModel(listModel);
				}
			}
		});
		panelPrzyciski.add(usunPrzycisk);
	}
	
	public JList getList()
	{
		return list;
	}
	
	public OkienkoWydarzenia getThis()
	{
		return this;
	}
	
	public Kalendarz getKalendarz()
	{
		return glowneOkno;
	}
	
	public Calendar getObecna() {
		return obecna;
	}
	
	public JPanel getPanel()
	{
		return panel;
	}
	
	private int getDzienMiesiaca(Calendar calendar)
    {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    private int getMiesiac(Calendar calendar)
    {
        return calendar.get(Calendar.MONTH);
    }

    private int getRok(Calendar calendar)
    {
        return calendar.get(Calendar.YEAR);
    }
    
    public Wydarzenie zaznaczoneWydarzenie() 
    {
    	return glowneOkno.getDni().getDzien(obecna).getLista().get(list.getSelectedIndex());
    }
}