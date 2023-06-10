package nurczynski.mateusz.java.projekt.logic.organizmy;

import nurczynski.mateusz.java.projekt.logic.DwaOrganizmyNaPoluException;
import nurczynski.mateusz.java.projekt.logic.Pole;
import nurczynski.mateusz.java.projekt.logic.Swiat;

import java.util.Random;
import java.util.Vector;

public abstract class Organizm {
    protected Swiat swiat;
    protected char symbol;
    protected final int domyslna_sila;
    protected int sila;
    protected int inicjatywa;
    protected boolean do_usuniecia = false;
    protected Pole pozycja;

    protected Pole pole(Pole obecne_pole)
    {
        Vector<Pole>sasiednie_pola = obecne_pole.getSasiednie_pola();
        Random random = new Random();
        return sasiednie_pola.get(random.nextInt(sasiednie_pola.size()));
    }

    final protected Pole pole()
    {
        return pole(this.pozycja);
    }

    public void zabij() throws DwaOrganizmyNaPoluException {
        do_usuniecia = true;
        if(pozycja.getOrganizm_na_polu()==this)
        {
            pozycja.zejdz();
        }
    }

    protected void rozmnoz() throws DwaOrganizmyNaPoluException, InterruptedException {
        Vector<Pole> sasiednie_pola = pozycja.getSasiednie_pola();
        Vector<Pole>puste_pola = new Vector<Pole>();
        for(int i=0;i<sasiednie_pola.size();i++)
        {
            if(sasiednie_pola.get(i).getOrganizm_na_polu()==null || (sasiednie_pola.get(i).getOrganizm_na_polu().getSymbol()!=symbol && sasiednie_pola.get(i).getOrganizm_na_polu().getSila()<=domyslna_sila))
            {
                puste_pola.add(sasiednie_pola.get(i));
            }
        }
        if (puste_pola.size()!=0)
        {
            Random rand = new Random();
            int index = rand.nextInt(puste_pola.size());
            swiat.dodaj_organizm(puste_pola.get(index), symbol);
            loguj_rozmnozenie();
            return;
        }
        swiat.dodaj_log("Organizm "+symbol+" na polu "+pozycja.getPozycja_na_planszy().toString()+" nie rozmnozyl sie z braku miejsca");
    }

    final protected void przenies(Pole pozycja) throws DwaOrganizmyNaPoluException {
        if(this.pozycja.getOrganizm_na_polu()==this)
        {
            this.pozycja.zejdz();
        }
        pozycja.wejdz(this);
        this.pozycja=pozycja;
    }

    final protected void loguj_walke(char symbol_przecniwnika)
    {
        swiat.dodaj_log("Organizm "+ symbol_przecniwnika +" usmiercil organizm " + symbol +" na polu "+pozycja.getPozycja_na_planszy().toString());
    }

    final protected void loguj_rozmnozenie()
    {
        swiat.dodaj_log("Organizm "+symbol+" na polu "+pozycja.getPozycja_na_planszy().toString()+" rozmnozyl sie");
    }

    public final void sprawdz_kolizje() throws DwaOrganizmyNaPoluException, InterruptedException {
        Organizm przeciwnik = pozycja.getOrganizm_na_polu();
        if(przeciwnik!=null)
        {
            if(przeciwnik.kolizja(this))
            {
                przenies(pozycja);
            }
            else
            {
                do_usuniecia=true;
            }
        }
        else
        {
            przenies(pozycja);
        }
    }

    final public Pole getPozycja()
    {
        return pozycja;
    }

    final public boolean czyUsunac()
    {
        return do_usuniecia;
    }

    public final int getSila()
    {
        return sila;
    }

    public final int getInicjatywa()
    {
        return inicjatywa;
    }

    public final char getSymbol()
    {
        return symbol;
    }

    public boolean kolizja(Organizm przeciwnik) throws DwaOrganizmyNaPoluException, InterruptedException {
        if(przeciwnik.getSila()>=sila)
        {
            zabij();
            loguj_walke(przeciwnik.getSymbol());
            return true;
        }
        else
        {
            przeciwnik.kolizja(this);
            return false;
        }
    }

    public void setSila(int sila)
    {
        this.sila = sila;
    }

    public abstract void akcja() throws DwaOrganizmyNaPoluException, InterruptedException;

    public Organizm(Pole pozycja, char symbol, int sila, int inicjatywa, Swiat swiat)
    {
        domyslna_sila = sila;
        this.pozycja = pozycja;
        this.symbol = symbol;
        this.sila = sila;
        this.inicjatywa = inicjatywa;
        this.swiat = swiat;
    }
}
