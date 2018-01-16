package uwazaj_co_jesz;

import javafx.geometry.Pos;

import java.awt.*;

/**
 * Klasa przechowywujaca Punkty, polozenie i obraz kazdego z rysowanych produktow
 *
 */
public class Produkt {

    /**
     * Wplyw na poziom cukru
     */
    int Punkty;
    /**
     * Szerokosc wyswietlania
     */
    int width;
    /**
     * Wysokosc wyswietlania / -150 dla plynniejszego efektu
     */
    int height;
    /**
     * Obraz produktu
     */
    Image Img;
    /**
     * Produkty na sniadanie
     */
    public static String[] Posilek1 = {"insulina", "donut", "herbata", "kanapka", "kiełbaski", "woda", "czekolada", "ciastko", "chleb", "kawa"};
    /**
     * Produkty na drugie sniadanie
     */
    public static String[] Posilek2 = {"insulina", "donut", "kanapka", "czipsy", "cola", "burger", "papryka"};
    /**
     * Produkty na obiad
     */
    public static String[] Posilek3 = {"insulina", "burger", "cola", "czekolada", "udko", "ziemniak", "zupa", "woda", "salatka", "mieso", "frytki"};
    /**
     * Produkty na podwieczorek
     */
    public static String[] Posilek4 = {"insulina", "sok", "czekolada", "piwo", "jablko", "burger", "kanapka", "papryka"};
    /**
     * Produkty na kolacje
     */
    public static String[] Posilek5 = {"insulina", "papryka", "ogorek", "marchewka", "pomidor", "piwo", "jablko", "gruszka", "czipsy", "ciastko", "herbata", "cola"};
    /**
     * Produkty na druga kolacje
     */
    public static String[] Posilek6 = {"insulina", "cola", "ciastko", "czekolada", "cola", "mleko", "kielbaski", "pomidor", "chleb"};

    /**
     * Konstruktor klasy Produkt
     * @param width szerokosc na jakiej jest rysowany produkt
     * @param Nazwa nazwa, na jej podstawie dobierany jest obrazek produktu
     */
    Produkt(int width, String Nazwa)
    {
        this.Punkty = DetekcjaPunktow(Nazwa);
        this.Img = Toolkit.getDefaultToolkit().getImage("grafiki\\"+Nazwa+".png");
        this.width = width;
        this.height = -150;     //Obrazy powstają poza panelem, "wjeżdżają" na obszar przez nas widoczny
    }//koniec Produkt()

    /**
     *  Na podstawie nazwy dobierana jest odpowiednia ilosc punktow przypisana do produktu
     * @param Nazwa nazwa produktu, na jej podstawie dobierana jest ilosc punktów
     * @return  zwraca ilosc puntkow przypisana do produktu
     */
    int DetekcjaPunktow(String Nazwa)
    {
        /**
         * Poczatkowa wartosc puntkow, przed detekcja
         */
        int Punkty = 0;
        switch(Nazwa)
        {
            case "chleb": Punkty = 10;
                break;
            case "sok": Punkty = 30;
                break;
            case "burger": Punkty = 40;
                break;
            case "ciastko": Punkty = 25;
                break;
            case "cola": Punkty = 55;
                break;
            case "czekolada": Punkty = 50;
                break;
            case "czipsy": Punkty = 30;
                break;
            case "donut": Punkty = 35;
                break;
            case "frytki": Punkty = 40;
                break;
            case "gruszka": Punkty = 5;
                break;
            case "herbata": Punkty = 3;
                break;
            case "insulina": Punkty = -50;
                break;
            case "jablko": Punkty = 4;
                break;
            case "kanapka": Punkty = 15;
                break;
            case "kawa": Punkty = 3;
                break;
            case "kielbaski": Punkty = 10;
                break;
            case "mieso": Punkty = 8;
                break;
            case "mleko": Punkty = 12;
                break;
            case "ogorek": Punkty = 4;
                break;
            case "papryka": Punkty = 4;
                break;
            case "piwo": Punkty = 25;
                break;
            case "pomidor": Punkty = 4;
                break;
            case "salatka": Punkty = 17;
                break;
            case "udko": Punkty = 9;
                break;
            case "woda": Punkty = 1;
                break;
            case "ziemniak": Punkty = 10;
                break;
            case "zupa": Punkty = 19;
                break;
            default: Punkty = 0;
                break;
        }
        return Punkty;
    }//koniec DetekcjaPunktow()

    /**
     * Pobierany jest poziomGry i na jego podstawie wybierane sa produktu do wyswietlenia
     * @return  Do kazdego posilku przypisana jest inna tablica nazw produktow,
     */
    public static String[] DetekcjaListyProduktow()
    {
        String[] TablicaObiektow = Posilek1;
        switch(graSTATUS.getPoziomGry())
        {
            case 2: TablicaObiektow = Posilek2;
                break;
            case 3: TablicaObiektow = Posilek3;
                break;
            case 4: TablicaObiektow = Posilek4;
                break;
            case 5: TablicaObiektow = Posilek5;
                break;
            case 6: TablicaObiektow = Posilek6;
                break;
            default: TablicaObiektow = Posilek1;
                break;
        }
        return TablicaObiektow;
    }// koniec DetekcjaListyProduktow()
}//koniec class Produkt