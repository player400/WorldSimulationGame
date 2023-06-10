package nurczynski.mateusz.java.projekt.logic.organizmy.zwierzeta;

import nurczynski.mateusz.java.projekt.logic.DwaOrganizmyNaPoluException;
import nurczynski.mateusz.java.projekt.logic.Pole;
import nurczynski.mateusz.java.projekt.logic.Swiat;
import nurczynski.mateusz.java.projekt.logic.organizmy.Organizm;

import java.util.Random;

public class Zolw extends Zwierze {
    public final static char SYMBOL = '4';
    public final static int SILA = 2;
    public final static int INICJATYWA = 1;
    public final static int SZANSA_ZMIANY_POLOZENIA = 4;
    public final static int SILA_OBRONNA = 5;
    private boolean moving;

    @Override
    public void akcja() throws DwaOrganizmyNaPoluException, InterruptedException {
        moving = true;
        Random random = new Random();
        if(0==random.nextInt(SZANSA_ZMIANY_POLOZENIA))
        {
            super.akcja();
        }
        moving = false;
    }

    @Override
    public boolean kolizja(Organizm przeciwnik) throws DwaOrganizmyNaPoluException, InterruptedException {
        if(moving)
        {
            return super.kolizja(przeciwnik);
        }
        if(przeciwnik.getSila()>=SILA_OBRONNA)
        {
            zabij();
            loguj_walke(przeciwnik.getSymbol());
            return true;
        }
        else
        {
            if(sila>przeciwnik.getSila())
            {
                przeciwnik.kolizja(this);
            }
            else
            {
                swiat.dodaj_log("Zolw obronil sie przed organizmem "+przeciwnik.getSymbol());
            }
            return false;
        }
    }
    public Zolw(Pole pozycja, Swiat swiat)
    {
        super(pozycja, SYMBOL, SILA, INICJATYWA, swiat);
        moving = false;
    }
}
