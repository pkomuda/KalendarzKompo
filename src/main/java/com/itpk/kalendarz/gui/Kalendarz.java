package com.itpk.kalendarz.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.itpk.kalendarz.dane.ZapisDoICS;
import com.itpk.kalendarz.logika.Dni;
import com.itpk.kalendarz.logika.Dzien;
import com.itpk.kalendarz.logika.Przypomnienie;
import com.itpk.kalendarz.logika.Wydarzenie;
import com.toedter.calendar.JCalendar;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;

public class Kalendarz extends JFrame
{
	private JPanel panel;
	private JCalendar calendar;
	private Calendar data;
	private int dzienMiesiaca;
	private Dni dni;
	private JButton info;
	private JButton eksport;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JPanel panel2;
	private JPanel panel1;

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
		
		panel2 = new JPanel();
		panel2.setLayout(new BorderLayout(0, 0));
		calendar.getYearChooser().add(panel2, BorderLayout.NORTH);
		
		eksport = new JButton("Wyeksportuj kalendarz");
		panel2.add(eksport, BorderLayout.EAST);
		
		panel1 = new JPanel();
		calendar.getMonthChooser().add(panel1, BorderLayout.NORTH);
		
		info = new JButton("O programie");
		panel1.add(info);
		info.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				JOptionPane.showMessageDialog(null, Dni.oProgramie(), "", JOptionPane.INFORMATION_MESSAGE);
			}
		});
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