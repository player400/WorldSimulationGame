package nurczynski.mateusz.java.projekt.logic.organizmy.rosliny;

import nurczynski.mateusz.java.projekt.logic.*;
import nurczynski.mateusz.java.projekt.logic.organizmy.Organizm;
import nurczynski.mateusz.java.projekt.logic.organizmy.zwierzeta.Zwierze;

import java.util.Vector;

public class Barszcz extends Roslina {
    public final static char SYMBOL = 'E';
    public final static int SILA = 10;

    @Override
    public boolean kolizja(Organizm przeciwnik) throws DwaOrganizmyNaPoluException, InterruptedException {
        boolean barszcz_pokonany = super.kolizja(przeciwnik);
        if(barszcz_pokonany && do_usuniecia)
        {
            if(przeciwnik instanceof Zwierze)
            {
                Zwierze zwierzecy_przeciwnik = (Zwierze) przeciwnik;
                zwierzecy_przeciwnik.zabij();
                swiat.dodaj_log("Barszcz na polu "+pozycja.getPozycja_na_planszy().toString()+" otrul organizm "+zwierzecy_przeciwnik.getSymbol());
            }
        }
        return barszcz_pokonany;
    }

    @Override
    public void akcja() throws DwaOrganizmyNaPoluException, InterruptedException {
        Vector<Pole>sasiednie_pola = pozycja.getSasiednie_pola();
        for(int i=0;i<sasiednie_pola.size();i++)
        {
            Organizm przeciwnik = sasiednie_pola.get(i).getOrganizm_na_polu();
            if(przeciwnik instanceof Zwierze)
            {
                przeciwnik.zabij();
                swiat.dodaj_log("Barszcz na polu "+pozycja.getPozycja_na_planszy().toString()+" zabil organizm "+przeciwnik.getSymbol());
            }
        }
        super.akcja();
    }
    public Barszcz(Pole pozycja, Swiat swiat)
    {
        super(pozycja, SYMBOL, SILA, swiat);
    }
}
