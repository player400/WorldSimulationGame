package nurczynski.mateusz.java.projekt.logic.organizmy.zwierzeta;

import nurczynski.mateusz.java.projekt.logic.Pole;
import nurczynski.mateusz.java.projekt.logic.Swiat;

import java.util.Random;
import java.util.Vector;

public class Lis extends Zwierze
{
    public final static char SYMBOL = '3';
    public final static int SILA = 3;
    public final static int INICJATYWA = 7;

    @Override
    protected Pole pole(Pole obecne_pole)
    {
        Vector<Pole>sasiednie_pole = obecne_pole.getSasiednie_pola();
        Vector<Pole>wolne_pola = new Vector<Pole>();
        for(int i=0;i<sasiednie_pole.size();i++)
        {
            if(sasiednie_pole.get(i).getOrganizm_na_polu() ==null || sasiednie_pole.get(i).getOrganizm_na_polu().getSymbol()==symbol || sasiednie_pole.get(i).getOrganizm_na_polu().getSila()<=sila)
            {
                wolne_pola.add(sasiednie_pole.get(i));
            }
        }
        if(wolne_pola.size()>0)
        {
            Random random = new Random();
            return wolne_pola.get(random.nextInt(wolne_pola.size()));
        }
        return null;
    }

    public Lis(Pole pozycja, Swiat swiat)
    {
        super(pozycja, SYMBOL, SILA, INICJATYWA, swiat);
    }
}
