package nurczynski.mateusz.java.projekt.logic.organizmy.rosliny;

import nurczynski.mateusz.java.projekt.logic.DwaOrganizmyNaPoluException;
import nurczynski.mateusz.java.projekt.logic.Pole;
import nurczynski.mateusz.java.projekt.logic.Swiat;
import nurczynski.mateusz.java.projekt.logic.organizmy.Organizm;

import java.util.Random;



public abstract class Roslina extends Organizm {
    public final static int INICJATYWA = 0;
    public final static int SZANSA_ROZROSTU = 10;

    public void akcja() throws DwaOrganizmyNaPoluException, InterruptedException {
        Random random = new Random();
        if(0==(random.nextInt(SZANSA_ROZROSTU)))
        {
            rozmnoz();
        }
    }

    Roslina(Pole pozycja, char symbol, int sila, Swiat swiat)
    {
        super(pozycja, symbol, sila, INICJATYWA, swiat);
    }
}
