package nurczynski.mateusz.java.projekt.logic.organizmy.rosliny;

import nurczynski.mateusz.java.projekt.logic.DwaOrganizmyNaPoluException;
import nurczynski.mateusz.java.projekt.logic.Pole;
import nurczynski.mateusz.java.projekt.logic.Swiat;
import nurczynski.mateusz.java.projekt.logic.organizmy.zwierzeta.Zwierze;
import nurczynski.mateusz.java.projekt.logic.organizmy.Organizm;

public class WilczeJagody extends Roslina {
    public final static char SYMBOL = 'D';
    public final static int SILA = 99;

    @Override
    public boolean kolizja(Organizm przeciwnik) throws DwaOrganizmyNaPoluException, InterruptedException {
        boolean jagody_pokonane = super.kolizja(przeciwnik);
        if(jagody_pokonane && do_usuniecia)
        {
            if(przeciwnik instanceof Zwierze)
            {
                Zwierze zwierzecy_przeciwnik = (Zwierze) przeciwnik;
                zwierzecy_przeciwnik.zabij();
                swiat.dodaj_log("Wilcze Jegody na polu "+pozycja.getPozycja_na_planszy().toString()+" otruly organizm "+zwierzecy_przeciwnik.getSymbol());
            }
        }
        return jagody_pokonane;
    }
    public WilczeJagody(Pole pozycja, Swiat swiat)
    {
        super(pozycja, SYMBOL, SILA, swiat);
    }
}
