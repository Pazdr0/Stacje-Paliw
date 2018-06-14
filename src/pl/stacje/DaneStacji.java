package pl.stacje;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.teamdev.jxmaps.LatLng;


@Entity
@Table(name="stacje")
public class DaneStacji {
	//Nazwy stacji użyję tylko do zaznaczenia stacji na mapie
	@Id
	@Column(name="idstacje")
	private Integer id;
	@Column(name="nazwa_stacji")
	private String nazwaStacji;
	//Na podstawie adresu i miejscowości znajde stacje i przypisze jej współrzędne
	@Column(name="miejscowosc")
	private String miejscowosc;
	@Column(name="adres")
	private String adres;
	//Robie tylko jedną zmienną cena paliwa, bo nie ma potrzeby robić 3
	//Użytkownik podaje spalanie, więc obliczanie kosztu będzie takie samo niezależnie od rodzaju, 
	//trzeba jedynie wybrać odpowiednią rubryke z bazy, aby została tu wpisana, a to nie problem, 
	//jeśli tylko mi się wydaje, że nie potrzeba, a okaże się, że tak to się doda
	//Jednak chuj do hibernate chyba potrzebuje różnych, ale narazie zostawie komentarz
	@Column(name="cena_benzyny_95")
	private Double cenaBenzyny95;
	@Column(name="cena_benzyny_98")
	private Double cenaBenzyny98;
	@Column(name="cena_oleju_napedowego")
	private Double cenaON;
//	private Double cenaPaliwa;
	@Transient
	private LatLng wspolrzedne;
	@Column(name="")
	private String dlugoscGeo;
	@Column(name="")
	private String szerokoscGeo;
	
	
	public DaneStacji() {}
	public DaneStacji(String nazwaStacji, String miejscowosc, String adres, Double cenaBenzyny95, Double cenaBenzyny98,
			Double cenaON) {
		super();
		this.nazwaStacji = nazwaStacji;
		this.miejscowosc = miejscowosc;
		this.adres = adres;
		this.cenaBenzyny95 = cenaBenzyny95;
		this.cenaBenzyny98 = cenaBenzyny98;
		this.cenaON = cenaON;
	}
	
	public String getWspolrzedne() {
		return wspolrzedne.toString();
	}
	public void setWspolrzedne(String[] wspolrzedne) {
		this.dlugoscGeo = wspolrzedne[0];
		this.szerokoscGeo = wspolrzedne[1];
	}
	@Override
	public String toString() {
		return "DaneStacji [id=" + id + ", nazwaStacji=" + nazwaStacji + ", miejscowosc=" + miejscowosc + ", adres="
				+ adres + ", cenaBenzyny95=" + cenaBenzyny95 + ", cenaBenzyny98=" + cenaBenzyny98 + ", cenaON=" + cenaON
				+ "]";
	}
	public Integer getId() {
		return id;
	}	
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNazwaStacji() {
		return nazwaStacji;
	}
	public void setNazwaStacji(String nazwaStacji) {
		this.nazwaStacji = nazwaStacji;
	}
	public String getMiejscowosc() {
		return miejscowosc;
	}
	public void setMiejscowosc(String miejscowosc) {
		this.miejscowosc = miejscowosc;
	}
	public String getAdres() {
		return adres;
	}
	public void setAdres(String adres) {
		this.adres = adres;
	}
	public Double getCenaBenzyny95() {
		return cenaBenzyny95;
	}
	public void setCenaBenzyny95(Double cenaBenzyny95) {
		this.cenaBenzyny95 = cenaBenzyny95;
	}
	public Double getCenaBenzyny98() {
		return cenaBenzyny98;
	}
	public void setCenaBenzyny98(Double cenaBenzyny98) {
		this.cenaBenzyny98 = cenaBenzyny98;
	}
	public Double getCenaON() {
		return cenaON;
	}
	public void setCenaON(Double cenaON) {
		this.cenaON = cenaON;
	}
}