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
	

	//Jeśli użytkownik poda ile zostało km do przejechania to obliczam ile paliwa jest w baku
	public void setIloscPaliwaObliczone() {
		iloscPaliwa = ileKmZostalo*srednieSpalanie/100;
	}
	//Jeśli użytkownik poda ile paliwa jest akutalnie w baku to obliczam ile km zostało do przejechania
	public void setIleKmZostaloObliczone() {
		ileKmZostalo = iloscPaliwa/srednieSpalanie*100;
	}
//	public double getIleAktualniePrzejedzie() {
//		return iloscPaliwa/srednieSpalanie;
//	}
//	public double getIleMaxPrzejedzie() {
//		return pojemnoscZbiornika/srednieSpalanie;
//	}	
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
	}
	public void setIleKmZostalo(double ileKmZostalo) {
		this.ileKmZostalo = ileKmZostalo;
	}
	public double getIleKmZostalo() {
		return ileKmZostalo;
	}
}
