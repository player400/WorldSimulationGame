package nurczynski.mateusz.java.projekt.graphics.window;

import nurczynski.mateusz.java.projekt.Koordynaty;
import nurczynski.mateusz.java.projekt.logic.organizmy.zwierzeta.*;
import nurczynski.mateusz.java.projekt.logic.organizmy.rosliny.*;


import java.awt.*;


public class Legend extends TextArea {
    private final int font_size = 10;

    public Legend(Koordynaty size, Koordynaty location)
    {
        super("");
        setFont(new Font("Arial", Font.PLAIN, font_size));
        setFocusable(false);
        setBounds(location.getWidth(), location.getHeight(), size.getWidth(), size.getHeight());
        setEditable(false);

        append("Mateusz Nurczynski, 193053, symulator swiata.\n");
        append(Czlowiek.SYMBOL + " - Czlowiek\n");
        append(Wilk.SYMBOL + " - Wilk\n");
        append(Owca.SYMBOL + " - Owca\n");
        append(Lis.SYMBOL + " - Lis\n");
        append(Zolw.SYMBOL + " - Zolw\n");
        append(Antylopa.SYMBOL + " - Antylopa\n");
        append(Trawa.SYMBOL + " - Trawa\n");
        append(Mlecz.SYMBOL + " - Mlecz\n");
        append(Guarana.SYMBOL + " - Guarana\n");
        append(WilczeJagody.SYMBOL + " - Wilcze jagody\n");
        append(Barszcz.SYMBOL + " - Barszcz sosnowskiego\n");
    }
}
