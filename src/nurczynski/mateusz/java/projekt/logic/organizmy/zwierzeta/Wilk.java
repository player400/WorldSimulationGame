package nurczynski.mateusz.java.projekt.logic.organizmy.zwierzeta;

import nurczynski.mateusz.java.projekt.logic.Pole;
import nurczynski.mateusz.java.projekt.logic.Swiat;

public class Wilk extends Zwierze
{
    public final static char SYMBOL = '1';
    public final static int SILA = 9;
    public final static int INICJATYWA = 5;

    public Wilk(Pole pozycja, Swiat swiat)
    {
        super(pozycja, SYMBOL, SILA, INICJATYWA, swiat);
    }
}
