package com.itpk.kalendarz.prezentacja.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.Calendar;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.itpk.kalendarz.dane.OdczytSQL;
import com.itpk.kalendarz.dane.OdczytXML;
import com.itpk.kalendarz.dane.ZapisDoICS;
import com.itpk.kalendarz.dane.ZapisDoSQL;
import com.itpk.kalendarz.dane.ZapisDoXML;
import com.itpk.kalendarz.logika.Alarmy;
import com.itpk.kalendarz.logika.RepozytoriumDni;
import com.itpk.kalendarz.logika.Wydarzenie;
import com.itpk.kalendarz.logika.wyjatki.NieprawidlowyXMLWyjatek;
import com.toedter.calendar.JCalendar;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Klasa z glownym widokiem kalendarza, z jej poziomu uruchamiane sa pozostale okna
 */
public class Kalendarz extends JFrame
{
	private JPanel panel;
	private JCalendar calendar;
	private Calendar data;
	private int dzienMiesiaca;
	private RepozytoriumDni dni;
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
	private JMenuItem zBazy;
	private JMenuItem doBazy;
	private JMenuItem doXML;
	private JMenuItem zXML;
	boolean wPanelu = false;

	/**
	 * Konstruktor
	 */
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

		zBazy = new JMenuItem("Z bazy danych");
		zBazy.addActionListener(e ->
		{
			OdczytSQL odczyt = new OdczytSQL();
			if (odczyt.czytajWydarzenia() != null)
			{
				dni.dodajWszystkieWydarzenia(odczyt.czytajWydarzenia());
				JOptionPane.showMessageDialog(null, "Importowanie zakończone pomyślnie.", "", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		importuj.add(zBazy);

		zXML = new JMenuItem("Z .xml");
		zXML.addActionListener(e ->
		{
			OdczytXML odczytXML = new OdczytXML();
			JFileChooser wyborPliku = new JFileChooser(".");
			FileNameExtensionFilter filtr = new FileNameExtensionFilter("pliki xml", "xml");
			wyborPliku.setFileFilter(filtr);
			int otrzymany = wyborPliku.showOpenDialog(null);
			if (otrzymany == JFileChooser.APPROVE_OPTION)
			{
				try
				{
					dni.dodajWszystkieWydarzenia(odczytXML.czytaj(wyborPliku.getSelectedFile().toString()));
				} catch (NieprawidlowyXMLWyjatek ex)
				{
					JOptionPane.showMessageDialog(null, "Nieprawidłowy plik", "", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		importuj.add(zXML);

		eksportuj = new JMenu("Eksportuj...");
		plik.add(eksportuj);

		doICS = new JMenuItem("Do .ics");
		doICS.addActionListener(e ->
		{
			zapisDoICS = new ZapisDoICS(dni);
			zapisDoICS.zapisz("eksport");
		});

		doBazy = new JMenuItem("Do bazy danych");
		doBazy.addActionListener(e ->
		{
			ZapisDoSQL zapis = new ZapisDoSQL();
			boolean sukces = true;
			for (Wydarzenie w : dni.wszystkieWydarzenia())
			{
				if (!zapis.dodajWydarzenie(w))
					sukces = false;
			}
			if (sukces)
				JOptionPane.showMessageDialog(null, "Eksportowanie zakończone pomyślnie.", "", JOptionPane.INFORMATION_MESSAGE);
		});
		eksportuj.add(doBazy);

		doXML = new JMenuItem("Do .xml");
		doXML.addActionListener(e ->
		{
			ZapisDoXML zapisDoXML = new ZapisDoXML(dni);
			JFileChooser wyborPliku = new JFileChooser(".");
			int otrzymany = wyborPliku.showSaveDialog(null);
			if (otrzymany == JFileChooser.APPROVE_OPTION)
				zapisDoXML.zapisz(wyborPliku.getSelectedFile() + ".xml");
		});
		eksportuj.add(doXML);
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
		JColorChooser wyborKoloru = new JColorChooser(new Color(238, 238, 238));
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
				JOptionPane.showMessageDialog(null, RepozytoriumDni.oProgramie(), "", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		menuBar.add(info);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new BorderLayout(0, 0));
		setContentPane(panel);

		dni = new RepozytoriumDni();
		Alarmy alarmy = new AlarmyGraficzne(dni);
        alarmy.powiadom();

		calendar = new JCalendar();
		calendar.getDayChooser().setWeekOfYearVisible(false);
		calendar.getDayChooser().setAlwaysFireDayProperty(true);
		calendar.getDayChooser().addPropertyChangeListener("day", e ->
		{
			data = calendar.getCalendar();
			dzienMiesiaca = (int) e.getNewValue();
			data.set(Calendar.DAY_OF_MONTH, dzienMiesiaca);
			if (wPanelu)
			{
				wPanelu = false;
				OkienkoWydarzenia okienko = new OkienkoWydarzenia(this);
				okienko.setLocationRelativeTo(okienko);
				okienko.setVisible(true);
			}
		});
		panel.add(calendar, BorderLayout.CENTER);

		JPanel panelTemp = calendar.getDayChooser().getDayPanel();
		Component komponenty[] = panelTemp.getComponents();
		for (int i = 0; i < komponenty.length; i++)
		{
			komponenty[i].addMouseListener(new MouseAdapter()
			{
				@Override
				public void mousePressed(MouseEvent mouseEvent)
				{
					super.mouseEntered(mouseEvent);
					wPanelu = true;
				}

				@Override
				public void mouseExited(MouseEvent mouseEvent)
				{
					super.mouseExited(mouseEvent);
					wPanelu = false;
				}
			});
		}
	}

	/**
	 * Metoda zwracajaca date typu Calendar
	 * @return Data typu Calendar
	 */
	public Calendar getData()
    {
        return data;
    }

	/**
	 * Metoda zwracajaca numer dnia miesiaca
	 * @return Numer dnia miesiaca
	 */
	public int getDzienMiesiaca()
    {
        return dzienMiesiaca;
    }

	/**
	 * Metoda zwracajaca repozytorium dni
	 * @return Repozytorium dni
	 */
	public RepozytoriumDni getDni()
    {
        return dni;
    }
}