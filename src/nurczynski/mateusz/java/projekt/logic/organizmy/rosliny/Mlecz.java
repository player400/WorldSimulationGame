package nurczynski.mateusz.java.projekt.logic.organizmy.rosliny;

import nurczynski.mateusz.java.projekt.logic.*;

public class Mlecz extends Roslina {
    public final static char SYMBOL = 'B';
    public final static int SILA = 0;
    public final static int LICZBA_PROB_ROZMNAZANIA = 3;

    @Override
    public void akcja() throws DwaOrganizmyNaPoluException, InterruptedException {
        for(int i = 0; i< LICZBA_PROB_ROZMNAZANIA; i++)
        {
            super.akcja();
        }
    }
    public Mlecz(Pole pozycja, Swiat swiat)
    {
        super(pozycja, SYMBOL, SILA, swiat);
    }
}
