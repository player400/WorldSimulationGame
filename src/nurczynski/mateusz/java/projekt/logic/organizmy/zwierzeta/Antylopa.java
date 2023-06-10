package nurczynski.mateusz.java.projekt.logic.organizmy.zwierzeta;

import nurczynski.mateusz.java.projekt.logic.*;
import nurczynski.mateusz.java.projekt.logic.organizmy.Organizm;

import java.util.Random;
import java.util.Vector;

public class Antylopa extends Zwierze {
    public final static char SYMBOL = '5';
    public final static int SILA = 4;
    public final static int INICJATYWA = 4;
    public final static int SZANSA_UCIECZKI = 2;

    @Override
    public void akcja() throws DwaOrganizmyNaPoluException, InterruptedException {
        Pole nowa_pozycja = pole();
        if(nowa_pozycja!=null)
        {
            Pole nowa_nowa_pozycja = pole(nowa_pozycja);
            if(nowa_nowa_pozycja!=null)
            {
                wykonaj_ruch(nowa_nowa_pozycja);
            }
        }
    }

    @Override
    public boolean kolizja(Organizm przeciwnik) throws DwaOrganizmyNaPoluException, InterruptedException {
        Random random = new Random();
        if(przeciwnik.getSila()>=sila && 0==random.nextInt(SZANSA_UCIECZKI))
        {
            Vector<Pole>sasiednie_pola = pozycja.getSasiednie_pola();
            Vector<Pole>wolne_pola = new Vector<Pole>();
            for(int i=0;i<sasiednie_pola.size();i++)
            {
                if(sasiednie_pola.get(i).getOrganizm_na_polu()==null)
                {
                    wolne_pola.add(sasiednie_pola.get(i));
                }
            }
            if(wolne_pola.size()>0)
            {
                przenies(wolne_pola.get(random.nextInt(wolne_pola.size())));
                swiat.dodaj_log("Antylopa uciekla przed atakiem organizmu "+przeciwnik.getSymbol());
                return true;
            }
        }
        return super.kolizja(przeciwnik);
    }

    public Antylopa(Pole pozycja, Swiat swiat)
    {
        super(pozycja, SYMBOL, SILA, INICJATYWA, swiat);
    }

}
