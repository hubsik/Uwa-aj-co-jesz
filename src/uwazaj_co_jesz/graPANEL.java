package uwazaj_co_jesz;

import java.awt.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JPanel;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

/**
 * Klasa odpowiedzialna za wyswietlanie produktow i instrukcji pojawiajacyh sie na ekranie
 * W zaleznosci od aktualnego stanu gry, wyswietlane sa rozne komendy
 * @author Hubert
 */
public class graPANEL extends JPanel  {

    /** Numer elementu z listy, ktory jest poza obaszarem panelu*/
    int index_do_usuniecia;
    /** Ustawiany gdy element jest poza obszarem*/
    boolean usunac = false;
    /** Ilosc wykonan metody paint()*/
    int licznik_przebiegow = 0;
    /** Ile razy probowano dodac elementy do listy*/
    int ilosc_wykonan = 0;
    /** Wynik dzielenia (PoziomGry +1) przez 2, do stoponiowania szybkosci opadania elementow listy*/
    int czesc_calkowita = 0;

    /**
     * Obraz insuliny do wyswietlenia na pierwszym ekranie
     */
    Image insulina = Toolkit.getDefaultToolkit().getImage("grafiki\\insulina.png");
    /**
     * Obraz jablka do wyswietlenia na pierwszym ekranie
     */
    Image jablko = Toolkit.getDefaultToolkit().getImage("grafiki\\jablko.png");
    /**
     * Obraz coli do wyswietlenia na pierwszym ekranie
     */
    Image cola = Toolkit.getDefaultToolkit().getImage("grafiki\\cola.png");
    /**
     * Obraz Wskaznika do wyswietlenia podczas rozgrywki
     */
    Image WskaznikIMG = Toolkit.getDefaultToolkit().getImage("grafiki\\twarz.png");

    /**
     * Szerokosc wyswietlania wskaznika, ktorym sterujemy
     */
    int xWskaznik = (Okno.szerokosc_okna / 2) ;
    /**
     * Wysokosc wyswietlania wskaznika, ktorym sterujemy
     */
    int yWskaznik = (Okno.wysokosc_okna - Okno.wysokosc_menuPANEL);  // wysokość między menuPanel a graPANEL
    /**
     * Lista produktow do wyswietlania
     */
    ArrayList<Produkt> Produkty;    //lista produtków pojawiającyh się w grze

    /**
     * Uzywany do obslugi klawiszy
     */
    KeyListener Klawiatura;
    /**
     * Uzywany do obslugi poruszania myszka
     */
    MouseMotionListener Myszka;
    /**
     * Uzywany do klikania na graPANEL
     */
    MouseListener MyszkaKlik;

    /**
     * Konstruktor klasy graPANELl, dzialanie przyciskow i myszki(zmiany widocznosci przyciskow), zmiana przesuniecia znacznika do zbierana produktow
     * Powstaja Listenery, dodawane jest ich dzialanie i wplyw na polozenie wskaznika
     */
    public graPANEL()
    {
        this.setBounds(0,0,Okno.szerokosc_okna,Okno.wysokosc_okna - Okno.wysokosc_menuPANEL);
        this.setOpaque(false);
        this.setFocusable(true);
        this.requestFocusInWindow();
        Klawiatura = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    xWskaznik = xWskaznik - 35;
                    if (xWskaznik < (WskaznikIMG.getWidth(null) / 2)) {
                        xWskaznik = 50;
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    xWskaznik = xWskaznik + 35;
                    if (xWskaznik > (Okno.szerokosc_okna - (WskaznikIMG.getWidth(null) / 2))) {
                        xWskaznik = (Okno.szerokosc_okna - (WskaznikIMG.getWidth(null) / 2));
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                UstawPrzyciski2();
                }
            }
        };

        Myszka = new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                //System.out.println(e.getX());
                xWskaznik = e.getX();
                if (xWskaznik < (WskaznikIMG.getWidth(null) / 2)) {
                    xWskaznik = 50;
                }
                if (xWskaznik > (Okno.szerokosc_okna - (WskaznikIMG.getWidth(null) / 2))) {
                    xWskaznik = (Okno.szerokosc_okna - (WskaznikIMG.getWidth(null) / 2));
                }
            }
        };

        MyszkaKlik = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                UstawPrzyciski2();
            }

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        };
        DodajObsluge();
        this.requestFocusInWindow();

        Produkty = new ArrayList<Produkt>();    //Stworzenie Listy produktów
    }//koniec class graPANEL

    /**
     * Metoda paint, odpowiedzialna za wyswietlanie wskaznika, produktow oraz informacji
     * O zawartosci wyswietlanej decyduje stan w jakim znajduje sie gra.
     * @param g Obiekt typu Graphics
     */
    public void paint (Graphics g)
    {
        czesc_calkowita = ((graSTATUS.getPoziomGry() + 1)/2);   //+1 zeby wyniki były od 1 do 3
        int wysokosc_wyswietlania = 100;    //Zwiększana jest przy wyświetlaniu zawartości pliku, żeby gracze byli jeden pod drugim

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Serif", Font.BOLD, 60));  //ustawienie czcionki
        if (graSTATUS.getGraRozpoczeta() == true) //Po naciśnięciu przycisku "START"
        {
            if(graSTATUS.getKontuuj() == true)
            {
                SpadekCukru();
                GenerujObiekty(Produkt.DetekcjaListyProduktow());

                //rysowanie i zmiana połozenia wskaźnika
                g2.drawImage(WskaznikIMG, xWskaznik - (WskaznikIMG.getWidth(null)/2), yWskaznik - WskaznikIMG.getHeight(null), WskaznikIMG.getWidth(null), WskaznikIMG.getHeight(null), this);

                for (Produkt p : Produkty) //Wyświetlanie listy produktów na ekranie
                {
                    g2.drawImage(p.Img, p.width, p.height, p.Img.getWidth(null), p.Img.getHeight(null), this);
                    Kolizja(xWskaznik - WskaznikIMG.getWidth(null), yWskaznik - WskaznikIMG.getHeight(null), WskaznikIMG.getWidth(null), WskaznikIMG.getHeight(null), p);

                    DetekcjaPolozenia(p);
                    p.height = p.height + czesc_calkowita;
                }
                UsuwanieElementow();
                licznik_przebiegow++;
            }
            else if(graSTATUS.getKontuuj() == false)    //Wyświetlanie komunikatów, oraz zapisanych wyników
            {
                if(graSTATUS.getOdczyt() == true)
                {

                    //g2.drawString("Powinno sie wczytac !!!!", 500, 500);
                    for(String s: menuPANEL.OdczytaneLinie)
                    {
                        g2.drawString(s, 400,  wysokosc_wyswietlania );
                        wysokosc_wyswietlania = wysokosc_wyswietlania + 65;
                    }
                }
                else if(graSTATUS.getWyswietlPoziom() == true)
                {
                    g2.drawString("Posilek: " + graSTATUS.getPoziomGry(), 400, 500);
                }
                else if(graSTATUS.getWygrales() == true)
                {
                    g2.drawString("WYGRALES!!!!", 400, 500);
                    UstawPrzyciski();
                    WyzerujParametry();
                }
                else if(graSTATUS.getPrzegrales() == true)
                {
                    g2.drawString("PRZEGRALES !!!!", 400, 500);
                    WyzerujParametry();
                }
                else if(graSTATUS.getPauza() == true)
                {
                    g2.drawString("Pauza !!!!", 400, 500);
                }
            }
        }
        else if(graSTATUS.getGraRozpoczeta() == false)
        {
            WyzerujParametry();
            if(graSTATUS.getOdczyt() == true)
            {
                for(String s: menuPANEL.OdczytaneLinie) //wyświetlanie kolejnych graczy
                {
                    g2.drawString(s, 400,  wysokosc_wyswietlania );
                    wysokosc_wyswietlania = wysokosc_wyswietlania + 65;
                }
            }
            else
            {
                g2.setFont(new Font("Serif", Font.BOLD, 50));
                g2.drawString("Gra polega na zbieraniu produktow spozywczych", 10, 50);
                g2.drawString("kazdy inaczej wplywa na poziom cukru:", 10, 105);
                g2.drawString("- insulina: -50", 10, 160);
                g2.drawImage(insulina, 400, 130, insulina.getWidth(null), insulina.getHeight(null), this);
                g2.drawString("- jablko: 4", 10, 300);
                g2.drawImage(jablko, 400, 270, jablko.getWidth(null), jablko.getHeight(null), this);
                g2.drawString("- cola: 55", 10, 440);
                g2.drawImage(cola, 400, 410, cola.getWidth(null), cola.getHeight(null), this);
                g2.drawString("Aby wygrac musisz utrzymac cukier między 20 a 240", 10, 650);
                g2.drawString("Powodzenia !!!!", 10, 715);
            }
        }
        repaint();  //ponowne wywoływanie metody paint, odpowiedzialne za poruszanie sie elementów
    }//koniec paint()

    /**
     * Pseudolosowe generowanie obiektow do wyswietlenia,
     * Rand czy chcemy dodac elementy w tym podejsciu,
     * Rand ile chcemy dodac
     * @param TablicaObietkow   lista produktow z ktorej wybierane sa
     */
    void GenerujObiekty(String[] TablicaObietkow)
    {
        if((ilosc_wykonan < 30) && (licznik_przebiegow == 225)) {
                ilosc_wykonan = ilosc_wykonan + 1;
                if (new Random().nextBoolean() == true) {
                    for (int i = 0; i < 3; i++) {
                        if (new Random().nextBoolean() == true) {
                            Produkty.add(new Produkt(new Random().nextInt((Okno.szerokosc_okna - 100)), TablicaObietkow[new Random().nextInt(TablicaObietkow.length)]));
                        }
                    }
                }
                licznik_przebiegow = 0;
            }
        else if(ilosc_wykonan >= 30)    //Ponad 21 generowań obiektów i pusta lista skutkuje ustawieniem WyswietlPoziom i przejsciem do nastepnego
        {
            if(Produkty.isEmpty())
            {
                if (graSTATUS.getPoziomGry() < 6) {
                    int PoziomGry = graSTATUS.getPoziomGry() + 1;
                    graSTATUS.setPoziomGry(PoziomGry);
                    menuPANEL.poziomLABEL.setText("POSILEK: " + graSTATUS.getPoziomGry());


                    graSTATUS.setWyswietlPoziom(true);
                }
                else if(graSTATUS.getPoziomGry() == 6)  //wyjątek, ukończenie 6 poziomu daje wygraną
                {
                    graSTATUS.setWygrales(true);
                    OdtworzDźwiek("wygrana");
                }
                graSTATUS.setKontynuuj(false);
                ilosc_wykonan = 0;
                licznik_przebiegow = 0;
            }
        }
    }// koniec GenerujObiekty

    /**
     * Sprawdzamy polozenie w jakim rysowany jest produkt, jezeli jest ponizej panelu to usuwamy go z listy
     * @param p Produkt, wraz z polozeniem, obrazem i punktami
     */
    void DetekcjaPolozenia(Produkt p)   //Wykrywanie elementów poza obszarem gry, niepotrzebne jest rysowanie elementów poza panelem
    {
        if (p.height > (Okno.wysokosc_okna -Okno.wysokosc_menuPANEL))//usuniecie elementów listy jezeli sa poza panelem
        {
            index_do_usuniecia = Produkty.indexOf(p);
            usunac = true;
        }
    }//koniec DetekcjaPolozenia()

    /**
     * Usuniecie produktu znajdujacego sie poza panelem
     */
    void UsuwanieElementow()    //Jeżeli funckja wyżej wykryje, to ta usuwa element z listy.
    {
        if(usunac == true)
        {
            Produkty.remove(index_do_usuniecia);
        }
        usunac = false;
    }//koniec UsuwanieElementow()

    /**
     * Wskaznik i elementy listy "staja sie" prostokatami potrzebnymi do detekcji kolizji
     * @param x Lewy góony rog, szerokosc
     * @param y Lewy gorny rog, wysokość
     * @param width Szerokosc elementu
     * @param height Wysokosc elementu
     * @return  zwracany jest prostokat
     */
    public Rectangle getKwadrat(int x, int y, int width, int height) // otrzymujemy kwadraty do intersekcji
    {
        return (new Rectangle (x, y, width, height));
    }//koniec Rectangle

    /**
     * Sprawdzanie czy wskaznik i produkt dotykaja sie, prawda skutkuje naliczeniem punktu i odtworzeniem dzwieku
     * Dodatkowo jest sprawdzany poziom cukru
     * @param xW    Lewy gorny rog wskaznika x
     * @param yW    lewy gorny rog wskaznika y
     * @param widthW    szerokosc wskaznika
     * @param heightW   wysokosc wskaznika
     * @param p produkt ktorego kolizje sprawdzamy
     */
    public void Kolizja(int xW, int yW, int widthW, int heightW, Produkt p) //czy wskaznik i produkt nachodza na siebie
    {
        Rectangle Wskaznik = getKwadrat(xW, yW, widthW, heightW);
        Rectangle Produkt = getKwadrat(p.width, p.height , p.Img.getWidth(null), p.Img.getHeight(null));

        if (Wskaznik.intersects(Produkt))//sprawdzenie czy kolidują
        {
            usunac = true;
            index_do_usuniecia = Produkty.indexOf(p);

            int PoziomCukru = graSTATUS.getPoziomCukru() + p.Punkty;
            graSTATUS.setPoziomCukru(PoziomCukru);
            menuPANEL.cukierLABEL.setText("GLUKOZA: " + graSTATUS.getPoziomCukru());
            OdtworzDźwiek("punkty");    //dźwięk zdobywania punktu
            if(graSTATUS.getPrzegrales())
            {
                graPANEL.OdtworzDźwiek("przegrana");
            }
        }

    }// koniec Kolizja()

    /**
     * Funckja zmniejszajaca poziom cukru, co 75 wykonan metody paint() i ustawiajaca nowy poziom w etykiecie
     */
    public void SpadekCukru()
    {
        if((licznik_przebiegow % 75) == 0)// co 75 powtórzeń, odejmuje j od poziomu cukru
        {
            int PoziomCukru = (graSTATUS.getPoziomCukru() - 1); // kara za bezczynnosc
            graSTATUS.setPoziomCukru(PoziomCukru);
            menuPANEL.cukierLABEL.setText("GLUKOZA: " + graSTATUS.getPoziomCukru());
        }
        if(graSTATUS.getPrzegrales())
        {
            graPANEL.OdtworzDźwiek("przegrana");
        }
    }//koniec SpadekCukru()

    /**
     * Ustawienie przyciskow gdy wygramy, glownie zeby poprawic przejzystosc metody paint
     */
    public void UstawPrzyciski()
    {
        menuPANEL.poziomLABEL.setVisible(false);
        menuPANEL.cukierLABEL.setVisible(false);

        menuPANEL.zapiszBUTTON.setVisible(true);
        menuPANEL.imieTEXTFIELD.setVisible(true);
        menuPANEL.imieLABEL.setVisible(true);
    }//koniec UstawPrzyciski()

    /**
     * Odpowiada za wyjscie z menu i odpowiednie ustawienie stanow i przyciskow
     * Uzywana przez myszke i klawiature
     */
    public void UstawPrzyciski2()
    {
        graSTATUS.setWyswietlPoziom(false);
        graSTATUS.setKontynuuj(true);
        if (graSTATUS.getWygrales() == true || graSTATUS.getPrzegrales() == true || graSTATUS.getOdczyt() == true) {
            graSTATUS.setGraRozpoczeta(false);
        }
        graSTATUS.setPauza(false);
        graSTATUS.setOdczyt(false);

        menuPANEL.wczytajBUTTON.setVisible(false);
        menuPANEL.klawiaturaBUTTON.setVisible(false);
        menuPANEL.myszkaBUTTON.setVisible(false);
        menuPANEL.zapiszBUTTON.setVisible(false);
        menuPANEL.restartBUTTON.setVisible(false);
        menuPANEL.opcjeBUTTON.setVisible(false);
        menuPANEL.wyjscieBUTTON.setVisible(false);
        menuPANEL.poziomLABEL.setVisible(false);
        menuPANEL.imieLABEL.setVisible(false);
        menuPANEL.imieTEXTFIELD.setVisible(false);

        menuPANEL.menuBUTTON.setVisible(true);
        menuPANEL.cukierLABEL.setVisible(true);
        if (graSTATUS.getGraRozpoczeta() == true) {
            menuPANEL.poziomLABEL.setVisible(true);
        } else if (graSTATUS.getGraRozpoczeta() == false) {
            menuPANEL.startBUTTON.setVisible(true);
        }
        DodajObsluge();
    }//koniec UstawPrzyciski2()

    /**
     * Funkcja odtwarzajaca dzwieki przy kolizji, wygranej oraz w wypadku przegranej
     * @param nazwa nazwa dzwieku jaki ma zostac odtworzony
     */
    public static void OdtworzDźwiek(String nazwa)  //Funkcja odpowiadająca za odtwarzanie wszystkich dźwięków
    {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("dzwieki\\"+nazwa+".wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }//koniec OdtworzDzwiek

    /**
     * Zerowanie parametrow i czyszczenie listy, gdy przegramy/wygramy lub wczytamy plik
     */
    public void WyzerujParametry()  //Jeżeli przerwiemy gre/przegramy i zaczniemy od nowa ustawia zmienne które liczą ilości wykonań
    {
        Produkty.removeAll(Produkty);
        ilosc_wykonan = 0;
        licznik_przebiegow = 0;

    }//koniec WyzerujParametry()

    /**
     * Funkcja odpowiedzialna za wybor rodzaju sterowania, na poczatku usuwane sa wszystkie nasluchiwania i pozniej w zaleznosci
     * od naszych wyborow ustawiane nowe
     */
    public void DodajObsluge()
    {
        this.removeMouseMotionListener(Myszka);
        this.removeMouseListener(MyszkaKlik);
        this.removeKeyListener(Klawiatura);

        if(graSTATUS.getSterowanieMyszka() == false) {
            this.addKeyListener(Klawiatura);
        }
        else if(graSTATUS.getSterowanieMyszka() == true) {
            this.addMouseMotionListener(Myszka);
            this.addMouseListener(MyszkaKlik);
        }
    }//konie DodajObsługe()
}//koniec class graPANEL