package nurczynski.mateusz.java.projekt.logic;

import nurczynski.mateusz.java.projekt.BasicEventListener;
import nurczynski.mateusz.java.projekt.Koordynaty;
import nurczynski.mateusz.java.projekt.logic.organizmy.Organizm;
import nurczynski.mateusz.java.projekt.logic.organizmy.rosliny.*;
import nurczynski.mateusz.java.projekt.logic.organizmy.zwierzeta.*;

import java.util.Random;
import java.util.Vector;

public class Swiat {
    final static private int ILOSC_ORGANIZMOW = 4;
    final static public int HEX = 1;
    final static public int SQR = 0;
    final private int typ;
    private Vector<Organizm>organizmy;
    private BasicEventListener tura_zakonczona=null;
    private Czlowiek czlowiek = null;
    private Vector<String>logi;
    private Koordynaty rozmiar;
    private Pole plansza[][];
    private int tura;

    private void usun_martwe_organizmy()
    {
        for(int i=0;i<organizmy.size();i++)
        {
            if(organizmy.get(i).czyUsunac())
            {
                organizmy.remove(i);
                i--;
            }
        }
    }

    private void sortuj_organizmy()
    {
        for(int i=0;i<organizmy.size();i++)
        {
            boolean sorted = true;
            for(int j=0;j<organizmy.size()-1-i;j++)
            {
                if(organizmy.get(j).getInicjatywa() < organizmy.get(j+1).getInicjatywa())
                {
                    sorted=false;
                    Organizm temp = organizmy.get(j);
                    organizmy.set(j, organizmy.get(j+1));
                    organizmy.set(j+1, temp);
                }
            }
            if(sorted)
            {
                break;
            }
        }
    }

    private void wypisz_logi()
    {
        for (String s : logi)
        {
            System.out.println(s);
        }
    }

    private void wyczysc_logi()
    {
        logi.clear();
    }

    private void buduj_plansze_kwadratowa()
    {
        for(int i=0;i< rozmiar.getHeight();i++)
        {
            for(int j=0; j<rozmiar.getWidth();j++)
            {
                Pole obecne_pole = plansza[i][j];
                if(i>0)
                {
                    obecne_pole.addSasiednie_pole(plansza[i-1][j]);
                }
                if(i+1 < rozmiar.getHeight())
                {
                    obecne_pole.addSasiednie_pole(plansza[i+1][j]);
                }
                if(j>0)
                {
                    obecne_pole.addSasiednie_pole(plansza[i][j-1]);
                }
                if(j+1 < rozmiar.getWidth())
                {
                    obecne_pole.addSasiednie_pole(plansza[i][j+1]);
                }
            }
        }
    }

    private void buduj_plansze_hexagonalna()
    {
        for(int i=0;i< rozmiar.getHeight();i++)
        {
            boolean przesuniety_wiersz = i % 2 == 1;
            for(int j=0; j<rozmiar.getWidth();j++)
            {
                Pole obecne_pole = plansza[i][j];
                if(i>0)
                {
                    obecne_pole.addSasiednie_pole(plansza[i-1][j]);
                    if(przesuniety_wiersz)
                    {
                        if(j!=rozmiar.getWidth()-1)
                        {
                            obecne_pole.addSasiednie_pole(plansza[i-1][j+1]);
                        }
                    }
                    else
                    {
                        if(j!=0)
                        {
                            obecne_pole.addSasiednie_pole(plansza[i-1][j-1]);
                        }
                    }
                }
                if(i+1 < rozmiar.getHeight())
                {
                    obecne_pole.addSasiednie_pole(plansza[i+1][j]);
                    if(przesuniety_wiersz)
                    {
                        if(j!=rozmiar.getWidth()-1)
                        {
                            obecne_pole.addSasiednie_pole(plansza[i+1][j+1]);
                        }
                    }
                    else
                    {
                        if(j!=0)
                        {
                            obecne_pole.addSasiednie_pole(plansza[i+1][j-1]);
                        }
                    }
                }
                if(j>0)
                {
                    obecne_pole.addSasiednie_pole(plansza[i][j-1]);
                }
                if(j+1 < rozmiar.getWidth())
                {
                    obecne_pole.addSasiednie_pole(plansza[i][j+1]);
                }
            }
        }
    }

    private Koordynaty find_focusedPole()
    {
        for(int i=0;i<rozmiar.getHeight();i++)
        {
            for(int j=0;j<rozmiar.getWidth();j++)
            {
                if(plansza[i][j].getFocus()==Pole.DOUBLE_FOCUS)
                {
                    return new Koordynaty(j, i);
                }
            }
        }
        return null;
    }

    public void focus_right() throws DwaOrganizmyNaPoluException {
        Koordynaty pole = find_focusedPole();
        if(pole==null)
        {
            return;
        }
        if(pole.getWidth() < rozmiar.getWidth()-1)
        {
            if(plansza[pole.getHeight()][pole.getWidth()+1].getFocus()==Pole.FOCUS)
            {
                plansza[pole.getHeight()][pole.getWidth()].setFocus(Pole.FOCUS);
                plansza[pole.getHeight()][pole.getWidth()+1].setFocus(Pole.DOUBLE_FOCUS);
            }
        }
    }

    public void focus_left() throws DwaOrganizmyNaPoluException {
        Koordynaty pole = find_focusedPole();
        if(pole==null)
        {
            return;
        }
        if(pole.getWidth() > 0 )
        {
            if(plansza[pole.getHeight()][pole.getWidth()-1].getFocus()==Pole.FOCUS)
            {
                plansza[pole.getHeight()][pole.getWidth()].setFocus(Pole.FOCUS);
                plansza[pole.getHeight()][pole.getWidth()-1].setFocus(Pole.DOUBLE_FOCUS);
            }
        }
    }

    public void focus_down() throws DwaOrganizmyNaPoluException {
        Koordynaty pole = find_focusedPole();
        if(pole==null)
        {
            return;
        }
        if(pole.getHeight() < rozmiar.getHeight()-1)
        {
            if(plansza[pole.getHeight()+1][pole.getWidth()].getFocus()==Pole.FOCUS)
            {
                plansza[pole.getHeight()][pole.getWidth()].setFocus(Pole.FOCUS);
                plansza[pole.getHeight()+1][pole.getWidth()].setFocus(Pole.DOUBLE_FOCUS);
            }
        }
    }

    public void focus_up() throws DwaOrganizmyNaPoluException {
        Koordynaty pole = find_focusedPole();
        if(pole==null)
        {
            return;
        }
        if(pole.getHeight() > 0 )
        {
            if(plansza[pole.getHeight()-1][pole.getWidth()].getFocus()==Pole.FOCUS)
            {
                plansza[pole.getHeight()][pole.getWidth()].setFocus(Pole.FOCUS);
                plansza[pole.getHeight()-1][pole.getWidth()].setFocus(Pole.DOUBLE_FOCUS);
            }
        }
    }

    public void carry_onCzlowiek()
    {
        if(czlowiek!=null)
        {
            czlowiek.carry_on();
        }
    }

    public void uruchom_umiejetnosc()
    {
        if(czlowiek!=null)
        {
            czlowiek.uruchom_umiejetnosc();
        }
    }

    public Pole getPole(Koordynaty pozycja)
    {
        return plansza[pozycja.getHeight()][pozycja.getWidth()];
    }

    public void dodaj_organizm(Pole pozycja, char symbol) throws DwaOrganizmyNaPoluException, InterruptedException {
        Organizm nowy_organizm;
        switch (symbol)
        {
            case Antylopa.SYMBOL: nowy_organizm = new Antylopa(pozycja, this); break;
            case Barszcz.SYMBOL: nowy_organizm = new Barszcz(pozycja, this); break;
            case Guarana.SYMBOL: nowy_organizm = new Guarana(pozycja,  this); break;
            case Lis.SYMBOL: nowy_organizm = new Lis(pozycja, this); break;
            case Mlecz.SYMBOL: nowy_organizm = new Mlecz(pozycja, this); break;
            case Owca.SYMBOL: nowy_organizm = new Owca(pozycja, this); break;
            case Trawa.SYMBOL: nowy_organizm = new Trawa(pozycja, this); break;
            case WilczeJagody.SYMBOL: nowy_organizm = new WilczeJagody(pozycja, this); break;
            case Wilk.SYMBOL: nowy_organizm = new Wilk(pozycja, this); break;
            case Zolw.SYMBOL: nowy_organizm = new Zolw(pozycja, this); break;
            case Czlowiek.SYMBOL: nowy_organizm = new Czlowiek(pozycja, this); break;
            default: return;
        }
        if(nowy_organizm instanceof Czlowiek)
        {
            if(czlowiek!=null)
            {
                return;
            }
            czlowiek = (Czlowiek) nowy_organizm;
        }
        nowy_organizm.sprawdz_kolizje();
        organizmy.add(nowy_organizm);
    }

    public void dodaj_log(String log)
    {
        logi.add(log);
    }

    public void generuj_organizmy() throws DwaOrganizmyNaPoluException, InterruptedException {
        Random random = new Random();
        Pole pos = plansza[random.nextInt(rozmiar.getHeight())][random.nextInt(rozmiar.getWidth())];
        dodaj_organizm(pos, Czlowiek.SYMBOL);
        char[] rodzaje_organizmow = {Antylopa.SYMBOL, Barszcz.SYMBOL, Guarana.SYMBOL, Lis.SYMBOL, Mlecz.SYMBOL, Owca.SYMBOL, Trawa.SYMBOL, WilczeJagody.SYMBOL, Zolw.SYMBOL, Wilk.SYMBOL};
        for (char c : rodzaje_organizmow) {
            for (int j = 0; j < ILOSC_ORGANIZMOW; j++) {
                Pole pozycja = plansza[random.nextInt(rozmiar.getHeight())][random.nextInt(rozmiar.getWidth())];
                if(pozycja.getOrganizm_na_polu() == null)
                {
                    dodaj_organizm(pozycja, c);
                }
            }
        }
        wypisz_logi();
    }

    public void setTura_zakonczona(BasicEventListener tura_zakonczona)
    {
        this.tura_zakonczona = tura_zakonczona;
    }

    public void akcja() throws DwaOrganizmyNaPoluException, InterruptedException {
        dodaj_log("Logi z tury "+Integer.toString(tura)+":");
        usun_martwe_organizmy();
        sortuj_organizmy();
        int ilosc_organizmow = organizmy.size();
        for(int i=0;i<ilosc_organizmow;i++)
        {
            if(!organizmy.get(i).czyUsunac())
            {
                organizmy.get(i).akcja();
            }
        }
        wypisz_logi();
        if(tura_zakonczona!=null)
        {
            tura_zakonczona.Trigger();
        }
        wyczysc_logi();
        tura++;
    }

    public boolean umiejetnosc_do_uruchomienia()
    {
        if(czlowiek==null)
        {
            return false;
        }
        return czlowiek.umiejetnosc_do_uruchomienia();
    }

    public Vector<String> zapisz()
    {
        Vector<String>dane = new Vector<String>();
        usun_martwe_organizmy();
        dane.add(Integer.toString(typ));
        dane.add(Integer.toString(rozmiar.getWidth()));
        dane.add(Integer.toString(rozmiar.getHeight()));
        dane.add(Integer.toString(organizmy.size()));
        dane.add(Integer.toString(tura));
        for(int i=0;i<organizmy.size();i++)
        {
            dane.add(""+organizmy.get(i).getSymbol());
            dane.add(Integer.toString(organizmy.get(i).getPozycja().getPozycja_na_planszy().getWidth()));
            dane.add(Integer.toString(organizmy.get(i).getPozycja().getPozycja_na_planszy().getHeight()));
            dane.add(Integer.toString(organizmy.get(i).getSila()));
        }
        if(czlowiek!=null)
        {
            dane.add(Integer.toString(czlowiek.getCzas_od_umiejetnosci()));
        }
        else
        {
            dane.add("0");
        }
        return dane;
    }

    public void wczytaj(Vector<String>dane) throws DwaOrganizmyNaPoluException, InterruptedException {
        int liczba_organizmow = Integer.parseInt(dane.get(0));
        int tura = Integer.parseInt(dane.get(1));
        this.tura = tura;
        for(int i=2;i<dane.size()-1;i+=4)
        {
            char symbol = dane.get(i).charAt(0);
            Koordynaty pozycja = new Koordynaty(Integer.parseInt(dane.get(i+1)), Integer.parseInt(dane.get(i+2)));
            int sila = Integer.parseInt(dane.get(i+3));
            Pole pole = getPole(pozycja);
            dodaj_organizm(pole, symbol);
            organizmy.get(organizmy.size()-1).setSila(sila);
        }
        if(czlowiek!=null)
        {
            czlowiek.setCzas_od_umiejetnosci(Integer.parseInt(dane.get(dane.size()-1)));
        }
    }

    public Vector<String> getLogi()
    {
        return logi;
    }

    public void removeCzlowiek()
    {
        czlowiek=null;
    }

    public Swiat(Koordynaty rozmiar, int typ)
    {
        this.typ = typ;
        this.rozmiar = rozmiar;
        tura = 1;
        organizmy = new Vector<Organizm>();
        logi = new Vector<String>();
        plansza = new Pole[rozmiar.getHeight()][rozmiar.getWidth()];
        for(int i=0;i< rozmiar.getHeight();i++)
        {
            for(int j=0; j<rozmiar.getWidth();j++)
            {
                plansza[i][j] = new Pole(new Koordynaty(j, i));
            }
        }
        if(typ == HEX)
        {
            buduj_plansze_hexagonalna();
        }
        else
        {
            buduj_plansze_kwadratowa();
        }
    }
}
