package nurczynski.mateusz.java.projekt.logic.organizmy.rosliny;

import nurczynski.mateusz.java.projekt.logic.*;
import nurczynski.mateusz.java.projekt.logic.organizmy.Organizm;
import nurczynski.mateusz.java.projekt.logic.organizmy.zwierzeta.Zwierze;

public class Guarana extends Roslina {
    public final static char SYMBOL = 'C';
    public final static int SILA = 0;
    public final static int ZWIEKSZANIE_SILY = 3;

    @Override
    public boolean kolizja(Organizm przeciwnik) throws DwaOrganizmyNaPoluException, InterruptedException {
        boolean guarana_pokonana = super.kolizja(przeciwnik);
        if(guarana_pokonana && do_usuniecia)
        {
            if(przeciwnik instanceof Zwierze)
            {
                Zwierze zwierzecy_przeciwnik = (Zwierze) przeciwnik;
                zwierzecy_przeciwnik.zwieksz_sile(ZWIEKSZANIE_SILY);
            }
        }
        return guarana_pokonana;
    }

    public Guarana(Pole pozycja, Swiat swiat)
    {
        super(pozycja, SYMBOL, SILA, swiat);
    }
}
