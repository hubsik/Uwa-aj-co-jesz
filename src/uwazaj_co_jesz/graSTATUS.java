package uwazaj_co_jesz;

import java.util.Random;

/**
 * Klasa ze wszystkimi stanami w ktorych gra moze sie znajdowac, w postaci zmiennych boolowskich i intow jezeli to poziomy gry lub cukru
 */
public class graSTATUS {
    /** Zmienna ustawiana przy rozpoczeciu gry przyciskiem "START"*/
    private static boolean GraRozpoczeta = false;
    /** Zmienna przechowywujaca PoziomemCukru*/
    private static int PoziomCukru = 0;
    /** Zmienna z aktualnym PoziomemGry*/
    private static int PoziomGry = 0;
    /** Zmienna ustawiana w momencie gdy przekroczymy dopuszczalne wartosci*/
    private static boolean Przegrales = false;
    /** Zmienna sluzaca do wyswietlania odpowiedniej informacji przy pomocy metody paint w klasie graPANEL*/
    private static boolean WyswietlPoziom = false;
    /** Zmienna umozliwiajaca przerwanie gry wejsciem do menu i dajaca mozliwosc powrocic do rozgrywki z ostatniego miejsca*/
    private static boolean Kontynuuj = true;
    /** Zmienna ustawiana, gdy ukonczymy 6 poziom*/
    private static boolean Wygrales = false;
    /** Zmienna, sterowanie wyswietlaniem w graPANEL, informacja o PAUZIE*/
    private static boolean Pauza = false;
    /** Zmienna, stan kiedy odczytujemy informacje o graczach z pliku*/
    private static boolean Odczyt = false;
    /** Informacja o aktualnie wybranym przez nas sposobie sterowania*/
    private static boolean SterowanieMyszka = true;

    /**
     * Pobieranie wartosci zmiennej GraRozpoczeta
     * @return  zwraca wartosc zmiennej GraRozpoczeta
     */
    static boolean getGraRozpoczeta()
    {
        return GraRozpoczeta;
    }
    /**
     * Ustawianie wartosci zmiennej GraRozpoczeta
     * @param stan_gry  nowa wartosc zmiennej stan_gry
     */
    static void setGraRozpoczeta(boolean stan_gry)
    {
        GraRozpoczeta = stan_gry;
    }
    /**
     * Pobieranie wartosci zmiennej GraRozpoczeta
     * @return  zwraca PoziomCukru
     */
    static int getPoziomCukru()
    {
        return PoziomCukru;
    }
    /**
     * Ustawianie wartosci zmiennej Poziomcukru i sprawdzanie czy jest on w odpowiednim zakresie
     * @param poziomCukru   nowa wartosc PoziomuCukru
     */
    static void setPoziomCukru(int poziomCukru)
    {
        graSTATUS.PoziomCukru = poziomCukru;
        CzyPrzegrales();    //wywołanie funkcji sprawdzającej poziom cukru
    }
    /** Losowanie poczatkowej wartosci od jakiej rozpoczynamy rozgrywke*/
    static void PoczatkowyPoziomCukru()
    {
        setPoziomCukru(new Random().nextInt(200)+40);
    }
    /**
     * Pobieranie wartosci zmiennej PoziomGry
     * @return  zwraca aktualny PoziomGry
     */
    static int getPoziomGry()
    {
        return PoziomGry;
    }
    /**
     * Ustwianie wartosci zmiennej PoziomGry
     * @param poziomGry nowy PoziomGry
     */
    static void setPoziomGry(int poziomGry)
    {
        PoziomGry = poziomGry;
    }
    /**
     * Pobieranie wartosci zmiennej Przegrales
     * @return  zwraca wartosc zmiennej Przegrales
     */
    static boolean getPrzegrales() {return Przegrales;}
    /**
     * Ustwianie wartosci zmiennej Przegrales
     * @param przegrales nowa wartosc zmiennej Przegrales
     */
    public static void setPrzegrales(boolean przegrales) {Przegrales = przegrales;}
    /** Funkcja sprawdzjaca czy nasz poziom cukru znajduje sie w przyjetym zakresie 20-240*/
    static void CzyPrzegrales()
    {
        if((getPoziomCukru() <20) || (getPoziomCukru() > 240))
        {
            setPrzegrales(true);    //ustawienie zmiennej Przegrales
            setKontynuuj(false);    //ustawienie zmiennej Kontynuuj jako false
        }
    }//koniec CzyPrzegrales()
    /**
     * Pobieranie wartosci zmiennej WyswietlPoziom, przerwa miedzy rozgrywka
     * @return zwraca wartosc zmiennej WyswietlPoziom
     */
    static boolean getWyswietlPoziom() { return WyswietlPoziom;}
    /**
     * Ustwianie wartosci zmiennej WyswietlPoziom
     * @param wyswietlPoziom nowa wartosc zmiennej WyswietlPoziom
     */
    static void setWyswietlPoziom(boolean wyswietlPoziom) {WyswietlPoziom = wyswietlPoziom;}
    /**
     * Pobieranie wartosci zmiennej Kontynuuj
     * @return zwraca wartosc zmiennej Kontynuuj
     */
    static boolean getKontuuj() {return Kontynuuj;}
    /**
     * Ustwianie wartosci zmiennej Kontynuuj
     * @param kontynuuj nowa wartosc zmiennej Kontynuuj
     */
    static void setKontynuuj(boolean kontynuuj) {Kontynuuj = kontynuuj;}
    /**
     * Pobieranie wartosci zmiennej Wygrales
     * @return  zwraca Wygrales
     */
    static boolean getWygrales() {return Wygrales;}
    /**
     * Ustwianie wartosci zmiennej Wygrales
     * @param wygrales nowa wartosc Wygrales
     */
    static void setWygrales(boolean wygrales) {Wygrales = wygrales;}
    /**
     * Pobieranie wartosci zmiennej Pauza
     * @return zwraca wartosc zmiennej Pauza
     */
    static boolean getPauza() {return Pauza;}
    /**
     * Ustwianie wartosci zmiennej Pauza
     * @param pauza nowa wartosc zmiennej Pauza
     */
    static void setPauza(boolean pauza) {Pauza = pauza;}
    /**
     * Pobieranie wartosci zmiennej Odczyt
     * @return  zwraca wartosc zmiennej Odczyt
     */
    static boolean getOdczyt() {return Odczyt;}
    /**
     * Ustwianie wartosci zmiennej Odczyt
     * @param odczyt nowa wartosc zmiennej Odczyt
     */
    static void setOdczyt(boolean odczyt){Odczyt = odczyt;}
    /**
     * Pobieranie wartosci zmiennej SterowanieMyszka
     * @return  true - sterowanie myszka, false - klawiatura
     */
    static boolean getSterowanieMyszka() {return SterowanieMyszka;}
    /**
     * Ustwianie wartosci zmiennej SterowanieMyszka
     * @param sterowanieMyszka  nowe sterowanie
     */
    static void setSterowanieMyszka(boolean sterowanieMyszka) {SterowanieMyszka = sterowanieMyszka;}
}//koniec classgraSTATUS