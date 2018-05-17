Iformacje potrzebne do odpalenia bazy danych:
	zapytanie sql za pomocą, którego można dodawać stacje oraz bazę danych dodałem w folderze db
	myśle, że dodawanie stacji za pomocą tego zapytania jest w maire wygodne, ale jeśli ma tam być sporo tych stacji
	to można wykminić coś bardziej efektywnego.

	żeby program połączył się z baza danych trzeba zmienić czas servera, defaultowo pobieraniy jest z systemu
	tylko wtedy w aplikacji wyskakuje błąd: 
	"The server time zone value 'Central European Daylight Tim' is unrecognized or represents more than one time zone. You must configure either the server or JDBC driver"
	wystarczy w pliku my.ini dodać tą linie i działa default_time_zone='+00:00' (jeśli mieliście włączony server należy go zrestartować)
	ścieżka do pliku: C:\ProgramData\MySQL\MySQL Server 5.7
	i w sumie tyle.
	
	Tabela jest do dopracowania, zrobiłem tylko taką, żebym mógł przetestować działanie w programie
	
	ja używam MySQL, także ta stworznona baza jest oraclowa. Myśle, że można używać phpmyadmin czy czegoś
	ale polecam zaintalować cały pakiet stąd: https://dev.mysql.com/downloads/installer/ wystarczy się za darmo 
	zarejestrować, przeglądałem regulamin mniej więcej i do użytku własnego jest za darmo.
	potrzebny jest chyba tylko Server, Workbench i Connector J, ale ja zainstalowałem cały pakiet.

Co do aplikacji to musze jeszcze opracować w jaki sposób sprawnie pobierać dane z tabeli, jest w javie fajne
narzędzie do tego, ale jeszcze go nie używałem, więc nie wiem jak, ale myśle, że to będzie proste do oganięcia

Baza będzie pewnie dosyć pracochłonna do stworzenia, ale to tylko kwestia wklepania danych, nie muszą być nawet prwdziwe

Problemem jest jak narazie algorytm, narazie w ogóle się nad nim nie zastanawiałem, trzeba się zastanowić jak to będzie działać
Michał miał jakąś propozycje z tego co pamiętam
Myśle, że jak nic nie wymyślimy to trzeba by się do niego przejść i coś podpowie.
Tak na pierwszą myśl wydaje mi się, że problematyczny będzie wybór stacji paliw w okolicach wybranej trasy
nie wiem jakby to można zaimplementować.
Można by niby wyznaczyć każdą trase z każdą stacją i porównać wyniki kosztów, ale byłoby to troche kosztowne,
ale może na taki projekt na zajęcia mogłoby być.
	