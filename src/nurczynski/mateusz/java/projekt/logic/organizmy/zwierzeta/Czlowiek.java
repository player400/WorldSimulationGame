package nurczynski.mateusz.java.projekt.logic.organizmy.zwierzeta;

import nurczynski.mateusz.java.projekt.logic.*;
import nurczynski.mateusz.java.projekt.logic.organizmy.Organizm;

import java.util.Vector;

public class Czlowiek extends Zwierze {
    public final static char SYMBOL = '0';
    public final static int SILA = 5;
    public final static int INICJATYWA = 4;
    public final static int CZAS_TRWANIA_TARCZY = 5;
    public final static int COOLDOWN_TARCZY = 5;
    private int czas_od_umiejetnosci;
    private boolean waiting;
    public void setCzas_od_umiejetnosci(int czas_od_umiejetnosci)
    {
        this.czas_od_umiejetnosci=czas_od_umiejetnosci;
    }
    private boolean moving;
    public int czas_do_umiejetnosci()
    {
        return CZAS_TRWANIA_TARCZY+COOLDOWN_TARCZY-czas_od_umiejetnosci;
    }

    public void uruchom_umiejetnosc()
    {
        if(swiat.umiejetnosc_do_uruchomienia())
        {
            setCzas_od_umiejetnosci(0);
            swiat.dodaj_log("Tarcza alzura aktywowana!");
        }
        else
        {
            swiat.dodaj_log("Nie mozna aktywowac tarczy jeszcze przez "+Integer.toString(czas_do_umiejetnosci()) + " tur!");
        }
    }

    public boolean umiejetnosc_do_uruchomienia()
    {
        return getCzas_od_umiejetnosci() >= COOLDOWN_TARCZY + CZAS_TRWANIA_TARCZY;
    }

    public int getCzas_od_umiejetnosci()
    {
        return czas_od_umiejetnosci;
    }

    public void carry_on()
    {
        waiting = false;
    }

    @Override
    public void akcja() throws DwaOrganizmyNaPoluException, InterruptedException {
        if(czas_od_umiejetnosci + 1 == CZAS_TRWANIA_TARCZY)
        {
            swiat.dodaj_log("Tarcza Alzura dezaktywowana!");
        }
        moving=true;
        Vector<Pole>sasiednie_pola = pozycja.getSasiednie_pola();
        for(int i=0;i<sasiednie_pola.size();i++)
        {
            sasiednie_pola.get(i).setFocus(Pole.FOCUS);
        }
        pozycja.setFocus(Pole.DOUBLE_FOCUS);
        waiting = true;
        while(waiting)
        {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Pole nowa_pozycja=pozycja;
        for(int i=0;i<sasiednie_pola.size();i++)
        {
            if(sasiednie_pola.get(i).getFocus()==Pole.DOUBLE_FOCUS)
            {
                nowa_pozycja = sasiednie_pola.get(i);
            }
        }
        pozycja.setFocus(Pole.NO_FOCUS);
        for(int i=0;i<sasiednie_pola.size();i++)
        {
            sasiednie_pola.get(i).setFocus(Pole.NO_FOCUS);
        }
        if(nowa_pozycja != null && nowa_pozycja!=pozycja)
        {
            wykonaj_ruch(nowa_pozycja);
        }
        czas_od_umiejetnosci++;
        moving = false;
    }

    @Override
    public boolean kolizja(Organizm przeciwnik) throws DwaOrganizmyNaPoluException, InterruptedException {
        if(!moving && przeciwnik instanceof Zwierze && czas_od_umiejetnosci < CZAS_TRWANIA_TARCZY)
        {
            Pole pole = this.pole();
            ((Zwierze)przeciwnik).odstrasz(pole);
            return false;
        }
        return super.kolizja(przeciwnik);
    }

    @Override
    public void zabij() throws DwaOrganizmyNaPoluException {
        super.zabij();
        swiat.removeCzlowiek();
    }

    public Czlowiek(Pole pozycja, Swiat swiat)
    {
        super(pozycja, SYMBOL, SILA, INICJATYWA, swiat);
        czas_od_umiejetnosci = CZAS_TRWANIA_TARCZY+COOLDOWN_TARCZY;
        waiting = false;
        moving = false;
    }
}
