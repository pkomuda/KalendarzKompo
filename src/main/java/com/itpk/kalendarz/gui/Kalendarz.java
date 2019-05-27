package com.itpk.kalendarz.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.itpk.kalendarz.logika.Dni;
import com.itpk.kalendarz.logika.Dzien;
import com.itpk.kalendarz.logika.Wydarzenie;
import com.toedter.calendar.JCalendar;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Kalendarz extends JFrame
{
	private JPanel panel;
	private JCalendar calendar;
	private Calendar data;
	private int dzienMiesiaca;
	private Dni dni;
	private JButton info;

	public static void main(String[] args)
	{
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
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new BorderLayout(0, 0));
		setContentPane(panel);
		
		dni = new Dni();
		Dzien d1 = new Dzien();
		d1.dodaj(new Wydarzenie("Spotkanie", "Łódź",new GregorianCalendar()));
        dni.dodaj(d1);
		
		calendar = new JCalendar();
		calendar.getDayChooser().setWeekOfYearVisible(false);
		calendar.getDayChooser().setAlwaysFireDayProperty(true);
		calendar.getDayChooser().addPropertyChangeListener("day", e ->
		{
			data = calendar.getCalendar();
			dzienMiesiaca = (int)e.getNewValue();
			data.set(Calendar.DAY_OF_WEEK, dzienMiesiaca);
            OkienkoWydarzenia okienko = new OkienkoWydarzenia(this);
            okienko.setLocationRelativeTo(okienko);
            okienko.setVisible(true);
		});
		panel.add(calendar, BorderLayout.CENTER);
		
		info = new JButton("O programie");
		info.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				JOptionPane.showMessageDialog(null, "Autorzy:\nIdalia Tybińkowska 216908\nPrzemysław Komuda 216802", "", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		calendar.getYearChooser().add(info, BorderLayout.EAST);
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
}