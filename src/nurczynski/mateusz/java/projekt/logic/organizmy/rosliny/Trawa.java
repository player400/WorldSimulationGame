package nurczynski.mateusz.java.projekt.logic.organizmy.rosliny;

import nurczynski.mateusz.java.projekt.logic.Pole;
import nurczynski.mateusz.java.projekt.logic.Swiat;

public class Trawa extends Roslina {
    public final static char SYMBOL = 'A';
    public final static int SILA = 1;

    public Trawa(Pole pozycja, Swiat swiat)
    {
        super(pozycja, SYMBOL, SILA, swiat);
    }
}
