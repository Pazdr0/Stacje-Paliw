package pl.stacje;

public class DaneStacji {
	//Nazwy stacji użyję tylko do zaznaczenia stacji na mapie
	private Integer id;
	private String nazwaStacji;
	//Na podstawie adresu i miejscowości znajde stacje i przypisze jej współrzędne
	private String miejscowosc;
	private String adres;
	//Robie tylko jedną zmienną cena paliwa, bo nie ma potrzeby robić 3
	//Użytkownik podaje spalanie, więc obliczanie kosztu będzie takie samo niezależnie od rodzaju, 
	//trzeba jedynie wybrać odpowiednią rubryke z bazy, aby została tu wpisana, a to nie problem, 
	//jeśli tylko mi się wydaje, że nie potrzeba, a okaże się, że tak to się doda
	private Double cenaPaliwa;
	
}
