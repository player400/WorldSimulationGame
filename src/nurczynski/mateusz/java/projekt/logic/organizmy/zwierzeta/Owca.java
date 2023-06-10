package nurczynski.mateusz.java.projekt.logic.organizmy.zwierzeta;

import nurczynski.mateusz.java.projekt.logic.Pole;
import nurczynski.mateusz.java.projekt.logic.Swiat;

public class Owca extends Zwierze {
    public final static char SYMBOL = '2';
    public final static int SILA = 4;
    public final static int INICJATYWA = 4;

    public Owca(Pole pozycja, Swiat swiat)
    {
        super(pozycja, SYMBOL, SILA, INICJATYWA, swiat);
    }
}
