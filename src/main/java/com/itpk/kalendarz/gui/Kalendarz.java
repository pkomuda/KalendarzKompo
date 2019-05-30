package com.itpk.kalendarz.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.itpk.kalendarz.dane.ZapisDoICS;
import com.itpk.kalendarz.dane.ZapisDoSQL;
import com.itpk.kalendarz.logika.Alarmy;
import com.itpk.kalendarz.logika.Dni;
import com.itpk.kalendarz.logika.Dzien;
import com.itpk.kalendarz.logika.Przypomnienie;
import com.itpk.kalendarz.logika.Wydarzenie;
import com.toedter.calendar.JCalendar;
import javax.swing.JColorChooser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Kalendarz extends JFrame
{
	private JPanel panel;
	private JCalendar calendar;
	private Calendar data;
	private int dzienMiesiaca;
	private Dni dni;
	private JMenuBar menuBar;
	private JMenu plik;
	private JMenu importuj;
	private JMenu eksportuj;
	private JMenu info;
	private JMenu ustawienia;
	private JMenuItem kolory;
	private JMenuItem doICS;
	private ZapisDoICS zapisDoICS;
	private JMenuItem usuwanieWydarzen;
	private JMenuItem mntmFiltrowanieWydarze;

	public static void main(String[] args)
	{
		ZapisDoSQL z = new ZapisDoSQL();
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Kalendarz frame = new Kalendarz();
					frame.setLocationRelativeTo(frame);
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public Kalendarz()
	{
		setTitle("Kalendarz");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		plik = new JMenu("Plik");
		menuBar.add(plik);
		
		importuj = new JMenu("Importuj...");
		plik.add(importuj);
		
		eksportuj = new JMenu("Eksportuj...");
		plik.add(eksportuj);
		
		doICS = new JMenuItem("Do .ics");
		doICS.addActionListener(e ->
		{
			zapisDoICS = new ZapisDoICS(dni);
			zapisDoICS.zapisz("eksport");
		});
		eksportuj.add(doICS);
		
		usuwanieWydarzen = new JMenuItem("Usuwanie wydarzeń");
		usuwanieWydarzen.addActionListener(e ->
		{
			UsuniecieWydarzenia usun = new UsuniecieWydarzenia(dni);
			usun.setLocationRelativeTo(usun);
			usun.setVisible(true);
		});
		
		mntmFiltrowanieWydarze = new JMenuItem("Filtrowanie wydarzeń");
		mntmFiltrowanieWydarze.addActionListener(e ->
		{
			FiltrowanieWydarzen filtruj = new FiltrowanieWydarzen(dni);
			filtruj.setLocationRelativeTo(filtruj);
			filtruj.setVisible(true);
		});
		plik.add(mntmFiltrowanieWydarze);
		plik.add(usuwanieWydarzen);
		
		ustawienia = new JMenu("Ustawienia");
		menuBar.add(ustawienia);
		
		kolory = new JMenu("Kolory");
		JColorChooser wyborKoloru = new JColorChooser(new Color(238,238,238));
		wyborKoloru.getSelectionModel().addChangeListener(e ->
		{
			calendar.getDayChooser().getDayPanel().setBackground(wyborKoloru.getColor());
        });
		kolory.add(wyborKoloru.getChooserPanels()[1]);
		ustawienia.add(kolory);
		
		info = new JMenu("O programie");
		info.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				JOptionPane.showMessageDialog(null, Dni.oProgramie(), "", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		menuBar.add(info);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new BorderLayout(0, 0));
		setContentPane(panel);
		
		dni = new Dni();
		Dzien d1 = new Dzien(29, 4, 2019);
		Dzien d2 = new Dzien(30, 4, 2019);
		Dzien d3 = new Dzien(5, 5, 2019);
		Wydarzenie w1 = new Wydarzenie("Spotkanie - za godzine", "Łódź", new GregorianCalendar(2019, 4, 29),16,00,Przypomnienie.GODZINA_PRZED);
		Wydarzenie w2 = new Wydarzenie("Spotkanie - jutro", "Łódź", new GregorianCalendar(2019, 4, 30),23, 13);
		Wydarzenie w3 = new Wydarzenie("Spotkanie - za tydzień", "Łódź", new GregorianCalendar(2019,5,5),12,00,Przypomnienie.TYDZIEN_PRZED);
		d1.dodaj(w1);
		d2.dodaj(w2);
		d3.dodaj(w3);
        dni.dodaj(d1);
        dni.dodaj(d2);
        dni.dodaj(d3);
        Alarmy alarmy = new AlarmyGraficzne(dni);
//        alarmy.powiadom();
        
		calendar = new JCalendar();
		calendar.getDayChooser().setWeekOfYearVisible(false);
		calendar.getDayChooser().setAlwaysFireDayProperty(true);
		calendar.getDayChooser().addPropertyChangeListener("day", e ->
		{
			data = calendar.getCalendar();
			dzienMiesiaca = (int)e.getNewValue();
			data.set(Calendar.DAY_OF_MONTH, dzienMiesiaca);
            OkienkoWydarzenia okienko = new OkienkoWydarzenia(this);
            okienko.setLocationRelativeTo(okienko);
            okienko.setVisible(true);
		});
		panel.add(calendar, BorderLayout.CENTER);
	}
	
	public Calendar getData()
    {
        return data;
    }

    public int getDzienMiesiaca()
    {
        return dzienMiesiaca;
    }

    public Dni getDni()
    {
        return dni;
    }
    
    public static String dodajZero(int liczba)
    {
    	return String.format("%02d", liczba);
    }
}