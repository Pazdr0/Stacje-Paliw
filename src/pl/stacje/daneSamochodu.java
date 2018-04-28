package pl.stacje;

public class daneSamochodu {

	private int pojemnoscZbiornika;
	//km/h
	private double srednieSpalanie;
	//ilosc paliwa w zbiorniku
	private double iloscPaliwa;
	//ile km zostalo jeszcze do przejechania
	private double ileKmZostalo;
	

	public double getIleAktuyalniePrzejedzie() {
		return iloscPaliwa/srednieSpalanie;
	}
	public double getIleMaxPrzejedzie() {
		return pojemnoscZbiornika/srednieSpalanie;
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
	}
	public void setIleKmZostalo(double ileKmZostalo) {
		this.ileKmZostalo = ileKmZostalo;
	}
	public double getIleKmZostalo() {
		return ileKmZostalo;
	}
}
