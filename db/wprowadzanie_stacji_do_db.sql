set @nazwa_stacji = 'Orlen';
set @miejscowosc = 'Wroclaw';
set @adres = 'Rynek';
set @cena_benzyny95 = 5;
set @cena_benzyny98 = 5.5;
set @cena_olejunapedowego = 4.9;

	insert into stacje (nazwa_stacji, miejscowosc, adres, cena_benzyny_95, cena_benzyny_98, cena_oleju_napedowego) 
		VALUES (@nazwa_stacji, @miejscowosc, @adres, @cena_benzyny95, @cena_benzyny98, @cena_olejunapedowego);
	select * from stacje
