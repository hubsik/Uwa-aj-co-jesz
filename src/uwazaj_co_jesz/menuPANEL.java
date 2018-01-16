/**
 * Created by Hubert on 2016-12-17.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uwazaj_co_jesz;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLBoundOperation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.String;
import java.util.ArrayList;

/**
 * Klasa zawierajaca wszystkie uzywane przyciski w menuPANEL, obrazy, odpowiednie reakcje na ich nacisniecie
 * @author Hubert
 */
public class menuPANEL extends JPanel{

    /** Kolo tla w menuPANEL*/
    final private Color tlo_menu = Color.LIGHT_GRAY;    //Stała z kolorem tła menu

    /**
     * Obraz przycisku MENU
     */
    ImageIcon menuBG = new ImageIcon("grafiki\\MENU.png");
    /**
     * Obraz przycisku START
     */
    ImageIcon startBG = new ImageIcon("grafiki\\START.png");
    /**
     * Obraz przycisku WYJSCIE
     */
    ImageIcon wyjscieBG = new ImageIcon("grafiki\\WYJSCIE.png");
    /**
     * Obraz przycisku OPCJE
     */
    ImageIcon opcjeBG = new ImageIcon("grafiki\\OPCJE.png");
    /**
     * Obraz przycisku RESTART
     */
    ImageIcon restartBG = new ImageIcon("grafiki\\RESTART.png");
    /**
     * Obraz przycisku WCZYTAJ
     */
    ImageIcon wczytajBG = new ImageIcon("grafiki\\WCZYTAJ.png");
    /**
     * Obraz przycisku ZAPISZ
     */
    ImageIcon zapiszBG = new ImageIcon("grafiki\\ZAPISZ.png");
    /**
     * Obraz przycisku MYSZKA
     */
    ImageIcon myszkaBG = new ImageIcon("grafiki\\myszka.png");
    /**
     * Obraz przycisku KLAWIATURA
     */
    ImageIcon klawiaturaBG = new ImageIcon("grafiki\\klawiatura.png");



    public final int odl_od_brzegu = 20;  //Stała, używana do rozmieszczania przycisków

   /** Przycisk z napisem "MENU"*/
    public static JButton menuBUTTON;
    /** Przycisk z napisem "START"*/
    public static JButton startBUTTON;
    /** Etykieta z napisem "Poziom Glukozy"*/
    public static JLabel cukierLABEL;
    /** Etykieta z napisem "Posilek"*/
    public static JLabel poziomLABEL;
    /** Przycisk z napisem "WYJSCIE"*/
    public static JButton wyjscieBUTTON;
    /** Przycisk z napisem "OPCJE"*/
    public static JButton opcjeBUTTON;
    /** Przycisk z napisem "RESTART"*/
    public static JButton restartBUTTON;
    /** Przycisk z napisem "WCZYTAJ"*/
    public static JButton wczytajBUTTON;
    /** Przycisk z napisem "ZAPISZ"*/
    public static JButton zapiszBUTTON;
    /** Pole do wpisania imienia gdy wygramy*/
    public static JTextField imieTEXTFIELD;
    /** Etykieta z napisem "Podaj Imie"*/
    public static JLabel imieLABEL;
    /** Przycisk z napisem "MYSZKA"*/
    public static JButton myszkaBUTTON;
    /** Przycisk z napisem "KLAWIATURA"*/
    public static JButton klawiaturaBUTTON;

    /** Lista odczytanych linii z pliku*/
    public static ArrayList<String> OdczytaneLinie = new ArrayList<String>();

    /** Konstruktor klasy menuPANEL, tworzy przyciski, etykiety i ustawia je w pozadany sposob
     * Przyciski i etykiety bez tla, przezroczyste, poczatkowo widoczne tylko 3 elementy.
     * */
    public menuPANEL()
    {
        this.setBounds(0,Okno.wysokosc_okna - Okno.wysokosc_menuPANEL,Okno.szerokosc_okna,Okno.wysokosc_menuPANEL);
        this.setBackground(tlo_menu);
        this.setLayout(null);

        menuBUTTON = new JButton();
        menuBUTTON.setIcon(menuBG);
        menuBUTTON.setBounds((int)(Okno.szerokosc_okna/3), odl_od_brzegu, menuBG.getIconWidth(), menuBG.getIconHeight());
        menuBUTTON.setContentAreaFilled(false);
        menuBUTTON.setBorder(null);
        menuBUTTON.setFocusable(false);

        startBUTTON = new JButton();
        startBUTTON.setIcon(startBG);
        startBUTTON.setBounds(odl_od_brzegu, odl_od_brzegu, startBG.getIconWidth(), startBG.getIconHeight());
        startBUTTON.setBorder(null);
        startBUTTON.setContentAreaFilled(false);
        startBUTTON.setOpaque(false);
        startBUTTON.setFocusable(false);


        cukierLABEL = new JLabel(); /// aktualizowanie powinno chyba być przy pomocy setText
        cukierLABEL.setText("GLUKOZA: " + graSTATUS.getPoziomCukru() );
        cukierLABEL.setBounds((int)(2*(Okno.szerokosc_okna/3)), odl_od_brzegu, 500, 50);
        cukierLABEL.setFont(new Font("Serif", Font.PLAIN, 60));
        cukierLABEL.setOpaque(false);
        cukierLABEL.setFocusable(false);

        poziomLABEL = new JLabel();
        poziomLABEL.setText("POSILEK: " + graSTATUS.getPoziomGry());
        poziomLABEL.setBounds(odl_od_brzegu, odl_od_brzegu, 500, 50);
        poziomLABEL.setFont(new Font("Serif", Font.PLAIN, 60));
        poziomLABEL.setOpaque(false);
        poziomLABEL.setFocusable(false);
        poziomLABEL.setVisible(false);

        wyjscieBUTTON = new JButton();
        wyjscieBUTTON.setIcon(wyjscieBG);
        wyjscieBUTTON.setBounds((int)(2*(Okno.szerokosc_okna/3)) + 50, odl_od_brzegu, wyjscieBG.getIconWidth(), wyjscieBG.getIconHeight());
        wyjscieBUTTON.setBorder(null);
        wyjscieBUTTON.setContentAreaFilled(false);
        wyjscieBUTTON.setOpaque(false);
        wyjscieBUTTON.setFocusable(false);
        wyjscieBUTTON.setVisible(false);

        opcjeBUTTON = new JButton();
        opcjeBUTTON.setIcon(opcjeBG);
        opcjeBUTTON.setBounds((int)(Okno.szerokosc_okna/3)+100, odl_od_brzegu, opcjeBG.getIconWidth(), opcjeBG.getIconHeight());
        opcjeBUTTON.setBorder(null);
        opcjeBUTTON.setContentAreaFilled(false);
        opcjeBUTTON.setOpaque(false);
        opcjeBUTTON.setFocusable(false);
        opcjeBUTTON.setVisible(false);

        restartBUTTON = new JButton();
        restartBUTTON.setIcon(restartBG);
        restartBUTTON.setBounds(odl_od_brzegu, odl_od_brzegu, restartBG.getIconWidth(), restartBG.getIconHeight());
        restartBUTTON.setBorder(null);
        restartBUTTON.setContentAreaFilled(false);
        restartBUTTON.setOpaque(false);
        restartBUTTON.setFocusable(false);
        restartBUTTON.setVisible(false);

        wczytajBUTTON = new JButton();
        wczytajBUTTON.setIcon(wczytajBG);
        wczytajBUTTON.setBounds((int)(2*(Okno.szerokosc_okna/3)), odl_od_brzegu, wczytajBG.getIconWidth(), wczytajBG.getIconHeight());
        wczytajBUTTON.setBorder(null);
        wczytajBUTTON.setContentAreaFilled(false);
        wczytajBUTTON.setOpaque(false);
        wczytajBUTTON.setFocusable(false);
        wczytajBUTTON.setVisible(false);

        zapiszBUTTON = new JButton();
        zapiszBUTTON.setIcon(zapiszBG);
        zapiszBUTTON.setBounds((int)(2*(Okno.szerokosc_okna/3)) + 50, odl_od_brzegu, zapiszBG.getIconWidth(), zapiszBG.getIconHeight());
        zapiszBUTTON.setBorder(null);
        zapiszBUTTON.setContentAreaFilled(false);
        zapiszBUTTON.setOpaque(false);
        zapiszBUTTON.setFocusable(false);
        zapiszBUTTON.setVisible(false);

        imieTEXTFIELD = new JTextField();
        imieTEXTFIELD.setBounds(odl_od_brzegu, 3*odl_od_brzegu, 300, 30);
        imieTEXTFIELD.setFont(new Font("Serif", Font.PLAIN, 30));
        imieTEXTFIELD.setVisible(false);

        imieLABEL = new JLabel();
        imieLABEL.setText("Podaj Imie:");
        imieLABEL.setBounds(odl_od_brzegu, odl_od_brzegu, 200, 40);
        imieLABEL.setFont(new Font("Serif", Font.PLAIN, 30));
        imieLABEL.setOpaque(false);
        imieLABEL.setFocusable(false);
        imieLABEL.setVisible(false);

        myszkaBUTTON = new JButton();
        myszkaBUTTON.setIcon(myszkaBG);
        myszkaBUTTON.setBounds(odl_od_brzegu, odl_od_brzegu, myszkaBG.getIconWidth(), myszkaBG.getIconHeight());
        myszkaBUTTON.setBorder(null);
        myszkaBUTTON.setContentAreaFilled(false);
        myszkaBUTTON.setOpaque(false);
        myszkaBUTTON.setFocusable(false);
        myszkaBUTTON.setVisible(false);

        klawiaturaBUTTON = new JButton();
        klawiaturaBUTTON.setIcon(klawiaturaBG);
        klawiaturaBUTTON.setBounds(odl_od_brzegu, odl_od_brzegu, klawiaturaBG.getIconWidth(), klawiaturaBG.getIconHeight());
        klawiaturaBUTTON.setBorder(null);
        klawiaturaBUTTON.setContentAreaFilled(false);
        klawiaturaBUTTON.setOpaque(false);
        klawiaturaBUTTON.setFocusable(false);
        klawiaturaBUTTON.setVisible(false);

        this.add(startBUTTON);
        this.add(menuBUTTON);
        this.add(cukierLABEL);
        this.add(poziomLABEL);
        this.add(wyjscieBUTTON);
        this.add(opcjeBUTTON);
        this.add(restartBUTTON);
        this.add(wczytajBUTTON);
        this.add(zapiszBUTTON);
        this.add(imieTEXTFIELD);
        this.add(imieLABEL);
        this.add(myszkaBUTTON);
        this.add(klawiaturaBUTTON);

        //DODANIE nasłuchu dla przyciskow i ich reakcje na klikniecie//
        startBUTTON.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                graSTATUS.setGraRozpoczeta( true);
                graSTATUS.setPoziomCukru (0);
                graSTATUS.setPoziomGry(1);
                graSTATUS.setPrzegrales(false);
                graSTATUS.setWyswietlPoziom(false);
                graSTATUS.setKontynuuj(true);
                graSTATUS.setWygrales(false);
                graSTATUS.setPauza(false);
                graSTATUS.setOdczyt(false);

                startBUTTON.setVisible(false);
                poziomLABEL.setVisible(true);
                poziomLABEL.setText("POSILEK: " + graSTATUS.getPoziomGry());
                graSTATUS.PoczatkowyPoziomCukru();

            }
        });//koniec nasłuchu

        menuBUTTON.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                graSTATUS.setKontynuuj(false);
                graSTATUS.setPauza(true);
                graSTATUS.setWygrales(false);
                poziomLABEL.setVisible(false);
                cukierLABEL.setVisible(false);
                menuBUTTON.setVisible(false);
                startBUTTON.setVisible(false);
                zapiszBUTTON.setVisible(false);
                imieLABEL.setVisible(false);
                imieTEXTFIELD.setVisible(false);

                restartBUTTON.setVisible(true);
                opcjeBUTTON.setVisible(true);
                wyjscieBUTTON.setVisible(true);
            }
        });

        opcjeBUTTON.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartBUTTON.setVisible(false);
                opcjeBUTTON.setVisible(false);
                wyjscieBUTTON.setVisible(false);

                wczytajBUTTON.setVisible(true);
                if(graSTATUS.getSterowanieMyszka() == true)
                {
                    klawiaturaBUTTON.setVisible(true);
                }
                else if (graSTATUS.getSterowanieMyszka() == false)
                {
                    myszkaBUTTON.setVisible(true);
                }

            }
        });

        wyjscieBUTTON.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        zapiszBUTTON.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                zapiszBUTTON.setVisible(false);
                ZapiszWynik();
            }
        });

        restartBUTTON.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                graSTATUS.setGraRozpoczeta(false);
                graSTATUS.setPoziomCukru (0);
                graSTATUS.setPoziomGry(1);
                graSTATUS.setPrzegrales(false);
                graSTATUS.setWyswietlPoziom(false);
                graSTATUS.setKontynuuj(true);
                graSTATUS.setWygrales(false);
                graSTATUS.setPauza(false);
                graSTATUS.setOdczyt(false);

                restartBUTTON.setVisible(false);
                wyjscieBUTTON.setVisible(false);
                opcjeBUTTON.setVisible(false);

                startBUTTON.setVisible(true);
                menuBUTTON.setVisible(true);
                cukierLABEL.setVisible(true);
            }
        });

        wczytajBUTTON.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OdczytajWyniki();
                graSTATUS.setOdczyt(true);
            }
        });

        myszkaBUTTON.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graSTATUS.setSterowanieMyszka(true);
                myszkaBUTTON.setVisible(false);
                klawiaturaBUTTON.setVisible(true);
            }
        });

        klawiaturaBUTTON.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graSTATUS.setSterowanieMyszka(false);
                klawiaturaBUTTON.setVisible(false);
                myszkaBUTTON.setVisible(true);
            }
        });

        validate();

    }//koniec menuPANEL()

    /**
     * Funckja, ktorej mozna uzyć po ukonczeniu gry, dodaje gracza do listy zwyciezcow.
     * Gracze dodawani sa w kolejnych linijkach, int wynik jest zapisywany jako String.
     * Odpowiednie ustawienie przyciskow i stanow gry
     */
    void ZapiszWynik()
    {
        String imie = "Anonim";
        String PoziomCukruSTRING = toString();


        if((imieTEXTFIELD.getText().equals("")) == false) {
            imie = imieTEXTFIELD.getText(); //Pobranie wartości wpisanej przez użytkownika

            try {
                BufferedWriter zapis = new BufferedWriter(new FileWriter(".\\wyniki.txt", true));

                zapis.write(imie + "   ");
                zapis.write(PoziomCukruSTRING);
                zapis.newLine();
                zapis.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            graSTATUS.setGraRozpoczeta(false);
            graSTATUS.setWygrales(false);
            imieTEXTFIELD.setVisible(false);
            imieLABEL.setVisible(false);

            startBUTTON.setVisible(true);
            cukierLABEL.setVisible(true);

        }
    }// koniec ZapiszWynik()

    /**
     *  Wczytanie wynikow  i dodanie ich do listy, ktora pozniej wyswietlana jest w graPANEL
     *  Przy kazdym wywolaniu lista jest czyszczona, a nastepnie dodawani sa tam gracze
     */
    void OdczytajWyniki()
    {
        String linia;
        OdczytaneLinie.removeAll(OdczytaneLinie);
        try{
            BufferedReader odczyt = new BufferedReader(new FileReader(".\\wyniki.txt"));
            while((linia = odczyt.readLine()) != null)  //Odczytywanie pliku "wyniki.txt" linia po linii i dodawanie do listy
            {
                OdczytaneLinie.add(linia);
            }
            odczyt.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        graSTATUS.setOdczyt(true);
    }//koniec OdczytajWyniki()

    /**
     * Konwersja zmiennej typu int na zmienna typu String
     * @return zwracana jest zmienna typu String, potrzebna do zapisu do pliku
     */
    public String toString()
    {
        return String.format("%d", graSTATUS.getPoziomCukru());
    }//koniec toString()

}//koniec class menuPANEL