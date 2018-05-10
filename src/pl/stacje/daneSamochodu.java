package pl.stacje;

public class daneSamochodu {
	//l
	private int pojemnoscZbiornika;
	//km/h
	private double srednieSpalanie;
	//Ilosc paliwa w zbiorniku
	private double iloscPaliwa;
	//Ile km zostalo jeszcze do przejechania
	private double ileKmZostalo;
	//Jakie paliwo
	private String rodzajPaliwa;
	
	public String getRodzajPaliwa() {
		return rodzajPaliwa;
	}
	public void setRodzajPaliwa(String rodzajPaliwa) {
		this.rodzajPaliwa = rodzajPaliwa;
	}
	//Jeśli użytkownik poda ile zostało km do przejechania to obliczam ile paliwa jest w baku
	private void setIloscPaliwaObliczone() {
		iloscPaliwa = ileKmZostalo*srednieSpalanie/100;
	}
	//Jeśli użytkownik poda ile paliwa jest akutalnie w baku to obliczam ile km zostało do przejechania
	private void setIleKmZostaloObliczone() {
		ileKmZostalo = iloscPaliwa/srednieSpalanie*100;
	}
	public int getPojemnoscZbiornika() {
		return pojemnoscZbiornika;
	}
	public void setPojemnoscZbiornika(int pojemnoscZbiornika) {
		this.pojemnoscZbiornika = pojemnoscZbiornika;
	}
	public double getSrednieSpalanie() {
		return srednieSpalanie;
	}
	public void setSrednieSpalanie(double srednieSpalanie) {
		this.srednieSpalanie = srednieSpalanie;
	}
	public double getIloscPaliwa() {
		return iloscPaliwa;
	}
	public void setIloscPaliwa(double iloscPaliwa) {
		this.iloscPaliwa = iloscPaliwa;
		setIleKmZostaloObliczone();
	}
	public double getIleKmZostalo() {
		return ileKmZostalo;
	}
	public void setIleKmZostalo(double ileKmZostalo) {
		this.ileKmZostalo = ileKmZostalo;
		setIloscPaliwaObliczone();
	}
}
