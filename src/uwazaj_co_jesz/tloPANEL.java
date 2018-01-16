/**
 * Created by Hubert on 2016-12-17.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uwazaj_co_jesz;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.*;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
/**
 *  Klasa, tlo dla glownego okna rozgrywki
 */
public class tloPANEL extends JPanel{
    /**
     * Obraz panelu tloPANEL
     */
    ImageIcon tloBG = new ImageIcon("grafiki\\tlo.png"); //Obraz tła rozgrywki

    /**
     * Konstruktor klasy tloPANEL, dodanie etykiety i ustawienie tla
     */
    public tloPANEL()
    {
        JLabel tlo = new JLabel();  //Tworzenie nowej etykiety
        tlo.setIcon(tloBG); //Przypisanie tła etykiecie, skutkuje to tym, że jest wyświetlane jako tło całego panelu

        this.setBounds(0,0,Okno.szerokosc_okna,Okno.wysokosc_okna - Okno.wysokosc_menuPANEL); //Ustawienie położenia etykiety
        this.setOpaque(false);  //Ustawienie etykiety jako przzeźroczystą
        this.add(tlo);  //  Dodanie etykiety do panelu
    }//koniec tloPANEL()
}//koniec class tloPANEL