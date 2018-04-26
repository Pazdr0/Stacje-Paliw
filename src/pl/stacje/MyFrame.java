package pl.stacje;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	
	private daneSamochodu samochod = new daneSamochodu();
	private Trasa trasa = new Trasa();
//	private MyMap mojaMapa = new MyMap();
	
	public MyFrame() {
		informacjeOkno();
	}
	
	private void informacjeOkno() {
		//TODO do poprawienia jest cały układ graficzny, narazie jest dosyć chujowy, ale działa
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
		
		//Dodaje panel przycisk i drugi panel do ramki
		this.getContentPane().add(przyciskDalej, BorderLayout.SOUTH);
		this.getContentPane().add(panelInfo2, BorderLayout.NORTH);
		this.getContentPane().add(panelInfo, BorderLayout.CENTER);
	}
	
	private void wyborTrasy() {
		
	}
	
	//Klasa definiuje jak działa przyciskDalej
	class przyciskListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			//Pola Pojemność silnika oraz Średnie spalanie są polami wymaganymi, sprawdzam czy są podane, jeśli nie odpalam błąd
			if (polePojemnosc.getText().isEmpty() || poleSpalanie.getText().isEmpty()) {
				wymaganaWartosc();
			}
			else {
				//Zapisuje wartości z pól do składowych obiektu samochód, jeśli wartości nie są liczbowe odpalam błąd
				try {
					samochod.setPojemnoscZbiornika(Integer.parseInt(polePojemnosc.getText()));
				} catch (NumberFormatException e) {
					zlaWartosc("Pojemność zbiornika");
				}
				try {
					samochod.setSrednieSpalanie(Double.parseDouble(poleSpalanie.getText()));
				} catch (NumberFormatException e) {
					zlaWartosc("Średnie spalanie");
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
					} catch (NumberFormatException e) {
						zlaWartosc("Ile paliwa w zbiorniku");
					}	
				}
				else {
					//Zapisuje wartości z pól do składowych obiektu samochód, jeśli wartości nie są liczbowe odpalam błąd
					try {
						samochod.setIleKmZostalo(Double.parseDouble(poleIleKm.getText()));
					} catch (NumberFormatException e) {
						zlaWartosc("Ile kilometrów zostało do przejechania");
					}
				}
			}
			wyborTrasy();
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
