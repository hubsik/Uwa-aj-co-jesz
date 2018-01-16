/**
 * Created by Hubert on 2016-12-17.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uwazaj_co_jesz;
import java.awt.*;
import java.util.*;
/**
 * Glowna klasa, tworzy okno gry i ustawia jego widocznosc.
 */
public class Uwazaj_co_jesz {

    //Ustawia nazwe stworzonego okna
    public static final String nazwa = "Uważaj co jesz";

    /** Konstruktor klasy main
     * @param args ustawienia poczatkowe
     */
    public static void main(String[] args)
    {
        //Utworzono obiekt klasy okno
        Okno okno = new Okno(nazwa);
        okno.setVisible(true);
    }//koniec main()
}//koniec class Uwazaj_co_jesz