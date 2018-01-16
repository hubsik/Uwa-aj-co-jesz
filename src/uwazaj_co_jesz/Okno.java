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
import javax.swing.*;
// parametry okna rozgrywki//
//

/**
 * Klasa, JFrame do ktorego dodawane sa 3 panele, przechowywane sa tu wymiary okna.
 */
public class Okno extends JFrame{

    /** Wysokosc okna rozgrywki*/
    final public static int wysokosc_okna = 1280;
    /**Szerokosc okna rozgrywki*/
    final public static int szerokosc_okna = 1240;
    /** Wysokosc panelu menuPANEL*/
    final public static  int wysokosc_menuPANEL = 300;

    /**
     * Konstruktor Okna rozgrywki
     * @param nazwa nazwa okna
     */
    public Okno(String nazwa){
        super(nazwa);   //metoda super wywołuje konstruktor nadklasy
        this.setResizable(false);   //zablokowanie możliwości zmiay okna
        this.setUndecorated(true);  //ukrywa ramke okna i przyciski w prawym górnym rogu
        this.setSize(szerokosc_okna,wysokosc_okna); //ustawienie wymiarów okna
        this.setLocationRelativeTo(null);   //niezależne połozenie okna(na środku)
        this.setLayout(null);   //brak layoutu

        graPANEL graPANEL = new graPANEL(); //Tworzy obiekt klasy graPANEL
        menuPANEL menuPANEL = new menuPANEL();  //Tworzy obiekt klasy menuPANEL
        tloPANEL tloPANEL = new tloPANEL(); //Tworzy obiekt klasy tloPANEL

        // DODANIE 3 PANELI
        this.add(graPANEL);
        this.add(tloPANEL);
        this.add(menuPANEL);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Możliwość wyłączenia aplikacji przyciskając krzyzyk
        validate();
    }//koniec Okno()
}//koniec class Okno