package com.itpk.kalendarz.prezentacja.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.itpk.kalendarz.logika.RepozytoriumDni;
import com.itpk.kalendarz.logika.Wydarzenie;

import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Klasa z oknem do wyszukiwania wydarzen o danym opisie/miejscu
 */
public class FiltrowanieWydarzen extends JFrame
{
	private JPanel panel;
	private JTextField pole;
	private JList list;
	private JPanel panel_1;
	private JButton przycisk;

	public FiltrowanieWydarzen(RepozytoriumDni dni)
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new BorderLayout(0, 0));
		setContentPane(panel);
		
		panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);
		
		pole = new JTextField();
		panel_1.add(pole);
		pole.setColumns(10);
		pole.setToolTipText("Filtrowanie zdarzeń według ich opisu i miejsca");
		
		list = new JList<String>();
		panel.add(list, BorderLayout.CENTER);
		
		DefaultListModel<String> listModel = new DefaultListModel<>();
		przycisk = new JButton("Szukaj");
		przycisk.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (pole.getText().length() > 0)
				{
					listModel.clear();
					for (Wydarzenie w : dni.wydarzeniaZawierajace(pole.getText()))
						listModel.addElement(w.toString());
					list.setModel(listModel);
				}
			}
		});
		panel_1.add(przycisk);
		
	}
}