package pl.stacje;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.SwingConstants;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;


public class MyFrame extends JFrame{
	
	//TODO składowe, które nie muszą być składowymi, a mogą być zmiennymi lokalnymi wrzycić do funckji, ale to może być na koniec
	private static final long serialVersionUID = 672L;
	//Panele glowny sluzy do przelączania między resztą
	private JPanel panelGlowny;
	private JPanel panelMapa;
	private JPanel panelInfo;
	private JPanel panelInfo2;
	private JPanel panelDane;
	//Przyciski
	private JButton przyciskDalej;
	//Etykiety
	private JLabel etykietkaPojemnosc;
	private JLabel etykietkaSpalanie;
	private JLabel etykietkaIleWZbiorniku;
	private JLabel etykietaJakaMaxOdleglosc;
	private JLabel etykietkaInfo;
	private JLabel etykietkaInfo2; 
	private JLabel etykietkaIleKm;
	private JLabel etykietaRodzajPaliwa;
	//Pola tekstowe
	private JTextField polePojemnosc;
	private JTextField poleSpalanie;
	private JTextField poleIleWZbiorniku;
	private JTextField poleIleKm;
	private JTextField poleJakaMaksOdleglosc;
	//Okno wyboru punktu miejsca wyjazdy i docelowego
	private JWindow okno;
	//Komponenty JWindow
	private JTextField skad;
	private JTextField dokad;
	//Combobox
	private JComboBox<String> comboBox;
	//SessionFactory - Hibernate
	private SessionFactory factory;
	//Obiekty moich klas
	private daneSamochodu samochod;
	private Trasa trasa;
	private MyMap mojaMapa;
//	private DBConnection myConn;
	private List<DaneStacji> stacje;
	
	public MyFrame() {
		//Panel główny, który zarządza pozostałymi komponentami, CardLayout, dodaje do niego pozostałe panele
		//TODO jeszcze dodać w panelu z Danymi ile max kilometrów chce odjechać od trasy etykieta i textfield
		panelGlowny = new JPanel();
		panelMapa = new JPanel();
		panelDane = new JPanel();
		samochod = new daneSamochodu();
		trasa = new Trasa();
		mojaMapa = new MyMap();
		//SessionFactory
		factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(DaneStacji.class)
				.buildSessionFactory();

		pobierzDaneZBazy();
		
		informacjeOkno();
		
		wyswietlDaneZBazy();
		
//		zlokalizujStacje();
		
		panelGlowny.setLayout(new CardLayout());
		panelGlowny.add(panelDane, "Panel Dane");
		panelGlowny.add(panelMapa, "Panel Mapa");
		this.getContentPane().add(panelGlowny, BorderLayout.CENTER);
	}
	
	public void zlokalizujStacje() {
		Session sesja = factory.getCurrentSession();
		for (DaneStacji stacja : stacje) {
//			stacja.setWspolrzedne(mojaMapa.lokalizujStacje(stacja.getMiejscowosc() + ", "+ stacja.getAdres()));
//			System.out.println("a");
//			try {
//				TimeUnit.MILLISECONDS.sleep(200);
//				System.out.println(stacja.getWspolrzedne());
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
			stacja.setWspolrzedne(mojaMapa.lokalizujStacje(stacja.getMiejscowosc() + ", " + stacja.getAdres()));
			
			try {
				sesja.beginTransaction();
				sesja.saveOrUpdate(stacja);
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				factory.close();
			}
			
			
		}
	}
	//Tak jak nazwa, wyswietla wszystkie stacje
	public void wyswietlDaneZBazy() {
		for (DaneStacji stacja : stacje) {
			System.out.println("ID: " + stacja.getId() +
								",\tNazwa Stacji: " + stacja.getNazwaStacji() +
								",\tMiejscowość: " + stacja.getMiejscowosc() +
								",\tAdres: " + stacja.getAdres() + 
								",\t\tCena benzyny 95: " + stacja.getCenaBenzyny95() + 
								",\tCena benzyny 98: " + stacja.getCenaBenzyny98() + 	
								",\tCena oleju napędowego: " + stacja.getCenaON());
		}
	}
	//Tworze sesje, tworze zapytanie i pobieram wszystkie dane z basy do kolekcji List<>
	public void pobierzDaneZBazy() {
		Session sesja = factory.getCurrentSession();
		try {
			sesja.beginTransaction();
			stacje = castList(DaneStacji.class, sesja.createQuery("from DaneStacji").list());
			sesja.getTransaction().commit();
		} finally {
//			factory.close();
			sesja.close();
			zlokalizujStacje();
		}
		try {
			TimeUnit.MILLISECONDS.sleep(200);
			zlokalizujStacje();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//Funkcja do castowania obiektów klasy DaneStacji do kolekcji List, 
	//bez tego w metodzie pobierzDaneZBazy wyrzuca warning przy pobieraniu stacji z bazy
	public static <T> List<T> castList(Class<? extends T> clazz, Collection<?> c) {
	    List<T> r = new ArrayList<T>(c.size());
	    for(Object o: c)
	      r.add(clazz.cast(o));
	    return r;
	}
	//Pierwszy panel z informacjami o samochodzie i o trasie
	private void informacjeOkno() {
		//TODO do poprawienia jest cały układ graficzny, narazie jest dosyć chujowy, ale działa
		panelInfo = new JPanel();
		panelInfo2 = new JPanel();
		przyciskDalej = new JButton("Dalej");
		//Tworze i nazywam etykiety
		etykietkaPojemnosc = new JLabel("Pojemność zbiornika: ");
		etykietkaSpalanie = new JLabel("Średnie spalanie samochodu: ");
		etykietkaIleWZbiorniku = new JLabel("Ile jest akutalnie paliwa w zbiorniku: ");
		etykietkaInfo = new JLabel("Informacje na temat pojazdu oraz trasy.");
		etykietkaInfo2 = new JLabel("Podaj ile aktualnie jest paliwa w zbiorniku, bądż ile kilometów zostało do przejechania, pozostałe parametry obowiązkowe"); 
		etykietkaIleKm = new JLabel("Ile kilometrów zostało do przejechania: ");
		etykietaJakaMaxOdleglosc = new JLabel("Maksymalnie jak daleko chciałbyś odjechać od trasy:");
		etykietaRodzajPaliwa = new JLabel("Rodzaj paliwa:");
		//Pola tekstowe
		polePojemnosc = new JTextField();
		poleSpalanie = new JTextField();
		poleIleWZbiorniku = new JTextField();
		poleIleKm = new JTextField();
		poleJakaMaksOdleglosc = new JTextField();
		//Combobox
		String[] rodzajePaliw = {"Wybierz", "Benzyna 95", "Benzyna 98", "Olej Napędowy"};
		comboBox = new JComboBox<String>(rodzajePaliw);
		samochod.setRodzajPaliwa(comboBox.getItemAt(0));
		comboBox.addItemListener(new ComboListener());
		//Zmieniam obramowanie na niewidoczne
		poleIleKm.setBorder(BorderFactory.createEmptyBorder());
		poleSpalanie.setBorder(BorderFactory.createEmptyBorder());
		polePojemnosc.setBorder(BorderFactory.createEmptyBorder());
		poleIleWZbiorniku.setBorder(BorderFactory.createEmptyBorder());
		poleJakaMaksOdleglosc.setBorder(BorderFactory.createEmptyBorder());
		//Większa czcionka
		etykietkaInfo.setFont(new Font("Serif", Font.PLAIN, 26));
		etykietkaInfo2.setFont(new Font("Serif", Font.PLAIN, 20));
		etykietkaInfo.setVerticalAlignment(SwingConstants.CENTER);
		
//		Dimension poleDim = new Dimension(100, 15);
//		polePojemnosc.setMaximumSize(poleDim);
		
		//Ustawienie Layoutu
		GridLayout gridLayout = new GridLayout(6, 2);
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
		panelInfo.add(etykietaJakaMaxOdleglosc);
		panelInfo.add(poleJakaMaksOdleglosc);
		panelInfo.add(etykietaRodzajPaliwa);
		panelInfo.add(comboBox);
		//Dodaje wszystkie komponenty do panelu 2
		panelInfo2.add(etykietkaInfo);
		panelInfo2.add(etykietkaInfo2);
		
		//Po klinieciu przycisku wpisuje dane z okienek do obieku samochod i przechodze do okna wybierania trasy
		przyciskDalej.addActionListener(new PrzyciskListener());
		
		//Dodaje panel przycisk i drugi panel do panelu glównego
		panelDane.setLayout(new BorderLayout());
		panelDane.add(przyciskDalej, BorderLayout.SOUTH);
		panelDane.add(panelInfo2, BorderLayout.NORTH);
		panelDane.add(panelInfo, BorderLayout.CENTER);
	}
	//JWindow z 2 JTextField do wybory trasy
	private void menuWyboru() {
		skad = new JTextField();
		dokad = new JTextField();
		//Przezroczystość
		skad.setOpaque(false);
		dokad.setOpaque(false);
		skad.addActionListener(new CelListener());
		dokad.addActionListener(new CelListener());
//		skad.setBackground(Color.BLUE);
//		dokad.setBackground(Color.BLUE);
		okno = new JWindow(this);
		okno.setLayout(new GridLayout(2, 1));
		okno.setBackground(Color.BLUE);
		okno.getContentPane().add(skad);
		okno.getContentPane().add(dokad);
		ustawOkno();
		okno.setSize(150,100);
		okno.setVisible(true);
		this.addComponentListener(new RamkaListener());
	}
	//Tworzy obiekt MyMap i wyświetla mape z centrum we Wro
	private void wyborTrasy() {
		panelMapa.setLayout(new BorderLayout());
		panelMapa.add(mojaMapa, BorderLayout.CENTER);
		menuWyboru();
//		mojaMapa.getWspolrzedne();
		
	}
	//Metoda używana do ustawiania okienka do wpisywania trasy, wywoływana w ramkaListener
	private void ustawOkno() {
		okno.setLocation(this.getX()+10, this.getY()+40);
	}
	//Metoda używana do zebrania informacji o trasie, wywoływana w celListener
	private void pobieranieDanychTrasy() {
		trasa = new Trasa();
		trasa.setPunktA(skad.getText());
		trasa.setPunktB(dokad.getText());
		trasa.setDlugoscTrasy(mojaMapa.getDystans());
//		System.out.println("Dystans: " + trasa.getDlugoscTrasy());
	}
	
	//Klasa storzona do obslugi zmiany rodzaju paliwa
	class ComboListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			samochod.setRodzajPaliwa(e.getItem().toString());
		}
	}
	
	//Klasa stworzona po to, aby JWindow poruszało się razem z JFrame
	class RamkaListener implements ComponentListener {
		//Jeśli ramka się porusza to okno do wczytywania trasy też
		@Override
		public void componentMoved(ComponentEvent e) {
			ustawOkno();		
		}
		//Niepotrzebna, ale trzeba przesłonić
		@Override
		public void componentHidden(ComponentEvent e) {	}
		//Niepotrzebna, ale trzeba przesłonić
		@Override
		public void componentResized(ComponentEvent e) { }
		//Niepotrzebna, ale trzeba przesłonić
		@Override
		public void componentShown(ComponentEvent e) { }
	}
	
	//Klasa do obslugi obliczania zmienionej trasy
	class CelListener implements ActionListener {
		@Override
		//Po wpisanu nowych zmiennych do pól wyznacza nową trase
		public void actionPerformed(ActionEvent e) {
			mojaMapa.calculateDirection(skad, dokad);	
			
			try	{
				TimeUnit.MILLISECONDS.sleep(200);
				pobieranieDanychTrasy();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	//Klasa definiuje jak działa przyciskDalej
	class PrzyciskListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			boolean pojemnoscPrawda = false, spalaniePrawda = false, zawartoscPrawda = false, rodzajPaliwa = false;
			
			//Pola Pojemność silnika oraz Średnie spalanie są polami wymaganymi, sprawdzam czy są podane, jeśli nie odpalam błąd
			if (polePojemnosc.getText().isEmpty() || poleSpalanie.getText().isEmpty()) {
				wymaganaWartosc();
			} else {
				//Zapisuje wartości z pól do składowych obiektu samochód, jeśli wartości nie są liczbowe odpalam błąd
				try {
					samochod.setPojemnoscZbiornika(Integer.parseInt(polePojemnosc.getText()));
					pojemnoscPrawda = true;
				} catch (NumberFormatException e) {
					zlaWartosc("Pojemność zbiornika");
//					pojemnoscPrawda = false;
				}
				try {
					samochod.setSrednieSpalanie(Double.parseDouble(poleSpalanie.getText()));
					spalaniePrawda = true;
				} catch (NumberFormatException e) {
					zlaWartosc("Średnie spalanie");
//					spalaniePrawda = false;
				}
			}
			//Jedno z tych pól jest wymagane, jesli nie będzie wpisane to błąd
			if (poleIleKm.getText().isEmpty() && poleIleWZbiorniku.getText().isEmpty()) {
				wymaganaWartosc2();
			} else {
				//Sprawdzam czy ile w zbiorniku ma jakąś wpisaną wartość, jeśli nie to sprawdzam ile zostało km
				if (!poleIleWZbiorniku.getText().isEmpty()) {
					//Zapisuje wartości z pól do składowych obiektu samochód, jeśli wartości nie są liczbowe odpalam błąd
					try {
						samochod.setIloscPaliwa(Double.parseDouble(poleIleWZbiorniku.getText()));
						zawartoscPrawda = true;
					} catch (NumberFormatException e) {
						zlaWartosc("Ile paliwa w zbiorniku");
//						zawartoscPrawda = false;
					}	
				} else {
					//Zapisuje wartości z pól do składowych obiektu samochód, jeśli wartości nie są liczbowe odpalam błąd
					try {
						samochod.setIleKmZostalo(Double.parseDouble(poleIleKm.getText()));
						zawartoscPrawda = true;
					} catch (NumberFormatException e) {
						zlaWartosc("Ile kilometrów zostało do przejechania");
//						zawartoscPrawda = false;
					}
				}
			}
			//Pole opcjonalne, moze byc bez wartości
			if (!poleJakaMaksOdleglosc.getText().isEmpty()) {
				try {
					trasa.setDlugoscTrasy(Double.parseDouble(poleJakaMaksOdleglosc.getText()));
				} catch (NumberFormatException e) {
					zlaWartosc("Jaka maks odleglosc");
				}
			}
			//Pole wymagane, należy wybrać rodzaj paliwa
			if (samochod.getRodzajPaliwa() == "Wybierz") {
				wymaganaWartosc3();
			} else {
				rodzajPaliwa = true;
				System.out.println(samochod.getRodzajPaliwa());
			}
			//Jeśli wartości zostaną przypisane to przełączamy na panel mapy
			if(pojemnoscPrawda==true && spalaniePrawda==true && zawartoscPrawda==true && rodzajPaliwa==true) {
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
			JOptionPane.showMessageDialog(rootPane, "Pole 'Pojemność zbiornika' oraz 'Średnie spalanie' są wymagane'", "Brak wartości", JOptionPane.ERROR_MESSAGE);
		}
		//Wyskakuje okienko dialogowe jeśli nie wprowadzono wartości
		private void wymaganaWartosc2() {
			JOptionPane.showMessageDialog(rootPane, "Pole 'Ile jest akutalnie paliwa w zbiorniku' lub pole 'Ile kilometrów zostało do przejechania' jest wymagane", "Brak wartości", JOptionPane.ERROR_MESSAGE);
		}
		//Wyskakuje okienko dialogowe jeśli nie wprowadzono wartości
		private void wymaganaWartosc3() {
			JOptionPane.showMessageDialog(rootPane, "Wybór paliwa jest wymagany", "Wybierz paliwo", JOptionPane.ERROR_MESSAGE);
		}
	}
}