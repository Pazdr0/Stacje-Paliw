package pl.stacje;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.SwingConstants;

import com.sun.javafx.scene.paint.GradientUtils.Point;
import com.sun.xml.internal.bind.annotation.OverrideAnnotationOf;
import com.sun.xml.internal.messaging.saaj.util.TeeInputStream;

import sun.awt.Win32ColorModel24;


public class MyFrame extends JFrame{
	
	private static final long serialVersionUID = 672L;

	private JPanel panelGlowny;
	private JPanel panelMapa;
	private JPanel panelInfo;
	private JPanel panelInfo2;
	private JPanel panelDane;
	private JWindow okno;
	private JButton przyciskDalej;
	private JLabel etykietkaPojemnosc;
	private JLabel etykietkaSpalanie;
	private JLabel etykietkaIleWZbiorniku;
	private JLabel etykietkaInfo;
	private JLabel etykietkaInfo2; 
	private JLabel etykietkaIleKm;
	private JTextArea polePojemnosc;
	private JTextArea poleSpalanie;
	private JTextArea poleIleWZbiorniku;
	private JTextArea poleIleKm;
	
	
	private daneSamochodu samochod;
	private Trasa trasa;
	private MyMap mojaMapa;
	
	private JTextField skad;
	private JTextField dokad;
	
	public MyFrame() {
		//Panel główny, który zarządza pozostałymi komponentami, CardLayout, dodaje do niego pozostałe panele
		panelGlowny = new JPanel();
		panelMapa = new JPanel();
		panelDane = new JPanel();
		samochod = new daneSamochodu();
		trasa = new Trasa();

		informacjeOkno();

		panelGlowny.setLayout(new CardLayout());
		panelGlowny.add(panelDane, "Panel Dane");
		panelGlowny.add(panelMapa, "Panel Mapa");
		this.getContentPane().add(panelGlowny, BorderLayout.CENTER);
	}
	
	private void informacjeOkno() {
		//TODO do poprawienia jest cały układ graficzny, narazie jest dosyć chujowy, ale działa
		panelInfo = new JPanel();
		panelInfo2 = new JPanel();
		przyciskDalej = new JButton("Dalej");
		etykietkaPojemnosc = new JLabel("Pojemność zbiornika: ");
		etykietkaSpalanie = new JLabel("Średnie spalanie samochodu: ");
		etykietkaIleWZbiorniku = new JLabel("Ile jest akutalnie paliwa w zbiorniku: ");
		etykietkaInfo = new JLabel("Informacje na temat pojazdu oraz trasy.");
		etykietkaInfo2 = new JLabel("Podaj ile aktualnie jest paliwa w zbiorniku, bądż ile kilometów zostało do przejechania, pozostałe parametry obowiązkowe"); 
		etykietkaIleKm = new JLabel("Ile kilometrów zostało do przejechania: ");
		polePojemnosc = new JTextArea();
		poleSpalanie = new JTextArea();
		poleIleWZbiorniku = new JTextArea();
		poleIleKm = new JTextArea();
		etykietkaInfo.setFont(new Font("Serif", Font.PLAIN, 26));
		etykietkaInfo2.setFont(new Font("Serif", Font.PLAIN, 20));
		etykietkaInfo.setVerticalAlignment(SwingConstants.CENTER);
		
		Dimension poleDim = new Dimension(10, 10);
		polePojemnosc.setMaximumSize(poleDim);
		
		//Ustawienie Layoutu
		GridLayout gridLayout = new GridLayout(4, 2);
		gridLayout.setHgap(10);
		gridLayout.setVgap(70);
		panelInfo.setLayout(gridLayout);
		panelInfo2.setLayout(new GridLayout(2, 1));

		//Dodaje wszystkie komponenty do panelu
		panelInfo.add(etykietkaPojemnosc);
		panelInfo.add(polePojemnosc);
		panelInfo.add(etykietkaSpalanie);
		panelInfo.add(poleSpalanie);
		panelInfo.add(etykietkaIleWZbiorniku);
		panelInfo.add(poleIleWZbiorniku);	
		panelInfo.add(etykietkaIleKm);		
		panelInfo.add(poleIleKm);
		
		//Dodaje wszystkie komponenty do panelu 2
		panelInfo2.add(etykietkaInfo);
		panelInfo2.add(etykietkaInfo2);
		
		//Po klinieciu przycisku wpisuje dane z okienek do obieku samochod i przechodze do okna wybierania trasy
		przyciskDalej.addActionListener(new przyciskListener());
		
		//Dodaje panel przycisk i drugi panel do panelu glównego
		panelDane.setLayout(new BorderLayout());
		panelDane.add(przyciskDalej, BorderLayout.SOUTH);
		panelDane.add(panelInfo2, BorderLayout.NORTH);
		panelDane.add(panelInfo, BorderLayout.CENTER);
	}
	
	private void menuWyboru() {
		skad = new JTextField();
		dokad = new JTextField();
		skad.setOpaque(false);
		dokad.setOpaque(false);
		skad.addActionListener(new celListener());
		dokad.addActionListener(new celListener());
//		skad.setBackground(Color.BLUE);
//		dokad.setBackground(Color.BLUE);
		okno = new JWindow(this);
		okno.setLayout(new GridLayout(2, 1));
		okno.setBackground(Color.BLUE);
		okno.getContentPane().add(skad);
		okno.getContentPane().add(dokad);
		ustawOkno();
//		okno.addMouseListener(new myszkaListener());
		okno.setSize(200,100);
		okno.setVisible(true);
	}
	
	private void ustawOkno() {
		okno.setLocation(this.getX()+10, this.getY()+40);
	}
	
	private void wyborTrasy() {
		panelMapa.setLayout(new BorderLayout());
		mojaMapa = new MyMap();
		panelMapa.add(mojaMapa, BorderLayout.CENTER);
		menuWyboru();
	}
	
	//Klasa do obslugi obliczania zmienionej trasy
	class celListener implements ActionListener {
		@Override
		//Po wpisanu nowych zmiennych do pól wyznacza nową trase
		public void actionPerformed(ActionEvent e) {
			mojaMapa.calculateDirection(skad, dokad);
		}
	}
	//TODO Docelowo Klasa, która ma zmieniać położenie okna w ramce
	class myszkaListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			ustawOkno();
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			ustawOkno();
		}
		@Override
		public void mouseExited(MouseEvent e) {
			ustawOkno();
		}
		@Override
		public void mousePressed(MouseEvent e) {
			ustawOkno();
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			ustawOkno();
		}
	}
	//Klasa do sterowania przyciskiem z panelu Dane
	//Klasa definiuje jak działa przyciskDalej
	class przyciskListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			boolean pojemnoscPrawda = false, spalaniePrawda = false, zawartoscPrawda = false;
			
			
			//Pola Pojemność silnika oraz Średnie spalanie są polami wymaganymi, sprawdzam czy są podane, jeśli nie odpalam błąd
			if (polePojemnosc.getText().isEmpty() || poleSpalanie.getText().isEmpty()) {
				wymaganaWartosc();
			}
			else {
				//Zapisuje wartości z pól do składowych obiektu samochód, jeśli wartości nie są liczbowe odpalam błąd
				try {
					samochod.setPojemnoscZbiornika(Integer.parseInt(polePojemnosc.getText()));
					pojemnoscPrawda = true;
				} catch (NumberFormatException e) {
					zlaWartosc("Pojemność zbiornika");
					pojemnoscPrawda = false;
				}
				try {
					samochod.setSrednieSpalanie(Double.parseDouble(poleSpalanie.getText()));
					spalaniePrawda = true;
				} catch (NumberFormatException e) {
					zlaWartosc("Średnie spalanie");
					spalaniePrawda = false;
				}
			}
			//Jedno z tych pól jest wymagane, jesli nie będzie wpisane to błąd
			if (poleIleKm.getText().isEmpty() && poleIleWZbiorniku.getText().isEmpty()) {
				wymaganaWartosc2();
			}
			else {
				//Sprawdzam czy ile w zbiorniku ma jakąś wpisaną wartość, jeśli nie to sprawdzam ile zostało km
				if (!poleIleWZbiorniku.getText().isEmpty()) {
					//Zapisuje wartości z pól do składowych obiektu samochód, jeśli wartości nie są liczbowe odpalam błąd
					try {
						samochod.setIloscPaliwa(Double.parseDouble(poleIleWZbiorniku.getText()));
						samochod.setIleKmZostaloObliczone();
						zawartoscPrawda = true;
					} catch (NumberFormatException e) {
						zlaWartosc("Ile paliwa w zbiorniku");
						zawartoscPrawda = false;
					}	
				}
				else {
					//Zapisuje wartości z pól do składowych obiektu samochód, jeśli wartości nie są liczbowe odpalam błąd
					try {
						samochod.setIleKmZostalo(Double.parseDouble(poleIleKm.getText()));
						samochod.setIloscPaliwaObliczone();
						zawartoscPrawda = true;
					} catch (NumberFormatException e) {
						zlaWartosc("Ile kilometrów zostało do przejechania");
						zawartoscPrawda = false;
					}
				}
			}
			//Jeśli wartości zostaną przypisane to przełączamy na panel mapy
			if(pojemnoscPrawda==true && spalaniePrawda==true && zawartoscPrawda==true) {
				CardLayout cardLayout = (CardLayout)(panelGlowny.getLayout());
				cardLayout.show(panelGlowny, "Panel Mapa");
				wyborTrasy();
			}
		}		
		//Wyskakuje okienko dialogowe jeśli wprowadzono niepoprawną wartość
		private void zlaWartosc(String nazwa) {
			JOptionPane.showMessageDialog(rootPane, "Proszę wprowadzić wartość liczbową", nazwa, JOptionPane.ERROR_MESSAGE);
		}
		//Wyskakuje okienko dialogowe jeśli nie wprowadzono wartości
		private void wymaganaWartosc() {
			JOptionPane.showMessageDialog(rootPane, "Pole 'Pojemność slinika' oraz 'Średnie spalanie' są wymagane'", "Brak wartości", JOptionPane.ERROR_MESSAGE);
		}
		//Wyskakuje okienko dialogowe jeśli nie wprowadzono wartości
		private void wymaganaWartosc2() {
			JOptionPane.showMessageDialog(rootPane, "Pole 'Ile jest akutalnie paliwa w zbiorniku' lub pole 'Ile kilometrów zostało do przejechania' jest wymagane", "Brak wartości", JOptionPane.ERROR_MESSAGE);
		}
	}
}
