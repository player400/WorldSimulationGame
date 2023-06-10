package nurczynski.mateusz.java.projekt.logic;

import nurczynski.mateusz.java.projekt.BasicEventListener;
import nurczynski.mateusz.java.projekt.Koordynaty;
import nurczynski.mateusz.java.projekt.logic.organizmy.Organizm;

import java.util.Vector;

public class Pole {
    public static final int NO_FOCUS = 0;
    public static final int FOCUS = 1;
    public static final int DOUBLE_FOCUS = 2;

    private int focus = NO_FOCUS;
    private final Koordynaty pozycja_na_planszy;
    private Vector<Pole>sasiednie_pola;
    private Vector<BasicEventListener>zmiana_stanu_pola;
    private Organizm organizm_na_polu;

    private void trigger_zmiana_stanu_pola() throws DwaOrganizmyNaPoluException {
        for (BasicEventListener basicEventListener : zmiana_stanu_pola)
        {
            basicEventListener.Trigger();
        }
    }

    public Koordynaty getPozycja_na_planszy()
    {
        return pozycja_na_planszy;
    }

    public void powiadom_o_zmianie_stanu(BasicEventListener eventListener)
    {
        zmiana_stanu_pola.add(eventListener);
    }

    public int getFocus()
    {
        return focus;
    }

    public void setFocus(int focus) throws DwaOrganizmyNaPoluException
    {
        this.focus = focus;
        trigger_zmiana_stanu_pola();
    }

    public Vector<Pole>getSasiednie_pola()
    {
        return sasiednie_pola;
    }

    void addSasiednie_pole(Pole pole)
    {
        sasiednie_pola.add(pole);
    }

    public Organizm getOrganizm_na_polu()
    {
        return organizm_na_polu;
    }

    public final void wejdz(Organizm organizm) throws DwaOrganizmyNaPoluException
    {
        if(organizm_na_polu!=null && !organizm_na_polu.czyUsunac())
        {
            throw new DwaOrganizmyNaPoluException("Na polu jest juz organizm "+organizm_na_polu.getSymbol()+", a probuje na nie wejsc organizm "+organizm.getSymbol());
        }
        organizm_na_polu = organizm;
        trigger_zmiana_stanu_pola();
    }

    public final void zejdz() throws DwaOrganizmyNaPoluException {
        organizm_na_polu=null;
        trigger_zmiana_stanu_pola();
    }

    Pole(Koordynaty pozycja_na_planszy)
    {
        sasiednie_pola = new Vector<Pole>();
        organizm_na_polu = null;
        this.pozycja_na_planszy = pozycja_na_planszy;
        zmiana_stanu_pola = new Vector<BasicEventListener>();
    }
}
