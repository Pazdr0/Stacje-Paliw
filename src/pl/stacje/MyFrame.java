package pl.stacje;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class MyFrame extends JFrame{
	
	private static final long serialVersionUID = 672L;

	private JPanel panelInfo = new JPanel();
	private JPanel panelInfo2 = new JPanel();
	private JButton przyciskDalej = new JButton("Dalej");
	private JLabel etykietkaPojemnosc = new JLabel("Pojemność zbiornika: ");
	private JLabel etykietkaSpalanie = new JLabel("Średnie spalanie samochodu: ");
	private JLabel etykietkaIleWZbiorniku = new JLabel("Ile jest akutalnie paliwa w zbiorniku: ");
	private JLabel etykietkaInfo = new JLabel("Informacje na temat pojazdu oraz trasy.");
	private JLabel etykietkaInfo2 = new JLabel("Podaj ile aktualnie jest paliwa w zbiorniku, bądż ile kilometów zostało do przejechania, pozostałe parametry obowiązkowe"); 
	private JLabel etykietkaIleKm = new JLabel("Ile kilometrów zostało do przejechania: ");
	private JTextArea polePojemnosc = new JTextArea();
	private JTextArea poleSpalanie = new JTextArea();
	private JTextArea poleIleWZbiorniku = new JTextArea();
	private JTextArea poleIleKm = new JTextArea();
	
	
	
	public MyFrame() {
		informacje();
	}

	private void informacje() {
		etykietkaInfo.setFont(new Font("Serif", Font.PLAIN, 26));
		etykietkaInfo2.setFont(new Font("Serif", Font.PLAIN, 20));
		etykietkaInfo.setVerticalAlignment(SwingConstants.CENTER);
		
		Dimension poleDim = new Dimension(10, 10);
		polePojemnosc.setMaximumSize(poleDim);
		
		GridLayout gridLayout = new GridLayout(4, 2);
		gridLayout.setHgap(10);
		gridLayout.setVgap(70);
		panelInfo.setLayout(gridLayout);
		panelInfo2.setLayout(new GridLayout(2, 1));
	

		panelInfo.add(etykietkaPojemnosc);
		panelInfo.add(polePojemnosc);
		panelInfo.add(etykietkaSpalanie);
		panelInfo.add(poleSpalanie);
		panelInfo.add(etykietkaIleWZbiorniku);
		panelInfo.add(poleIleWZbiorniku);	
		panelInfo.add(etykietkaIleKm);		
		panelInfo.add(poleIleKm);
		
		panelInfo2.add(etykietkaInfo);
		panelInfo2.add(etykietkaInfo2);
		
		this.getContentPane().add(przyciskDalej, BorderLayout.SOUTH);
		this.getContentPane().add(panelInfo2, BorderLayout.NORTH);
		this.getContentPane().add(panelInfo, BorderLayout.CENTER);
	}
	
	
}
