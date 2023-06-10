package nurczynski.mateusz.java.projekt.logic.organizmy.zwierzeta;

import nurczynski.mateusz.java.projekt.logic.DwaOrganizmyNaPoluException;
import nurczynski.mateusz.java.projekt.logic.Pole;
import nurczynski.mateusz.java.projekt.logic.Swiat;
import nurczynski.mateusz.java.projekt.logic.organizmy.Organizm;

public abstract class Zwierze extends Organizm {

    protected void wykonaj_ruch(Pole pozycja) throws DwaOrganizmyNaPoluException, InterruptedException {
        Organizm przeciwnik = pozycja.getOrganizm_na_polu();
        if(przeciwnik==null || przeciwnik.czyUsunac())
        {
            przenies(pozycja);
        }
        else if (przeciwnik.getSymbol() == symbol)
        {
            rozmnoz();
        }
        else
        {
            if(przeciwnik.kolizja(this))
            {
                przenies(pozycja);
            }
        }
    }

    void odstrasz(Pole pozycja) throws DwaOrganizmyNaPoluException, InterruptedException {
        wykonaj_ruch(pozycja);
        swiat.dodaj_log("Organizm "+symbol+" zostal odstraszony i znajduje sie na pozycji "+pozycja.getPozycja_na_planszy().toString());
    }

    public void akcja() throws DwaOrganizmyNaPoluException, InterruptedException {
        Pole nowa_pozycja = pole();
        if(nowa_pozycja!=null)
        {
            wykonaj_ruch(nowa_pozycja);
        }
    }

    public final void zwieksz_sile(int wartosc)
    {
        swiat.dodaj_log("Organizm "+symbol+" otrzymal dodatkowa sile w wysokosci "+Integer.toString(wartosc));
        sila += wartosc;
    }
    Zwierze(Pole pozycja, char symbol, int sila, int inicjatywa, Swiat swiat)
    {
        super(pozycja, symbol, sila, inicjatywa, swiat);
    }
}
