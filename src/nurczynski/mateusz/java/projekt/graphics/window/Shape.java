package nurczynski.mateusz.java.projekt.graphics.window;

import nurczynski.mateusz.java.projekt.Koordynaty;
import nurczynski.mateusz.java.projekt.logic.organizmy.Organizm;
import nurczynski.mateusz.java.projekt.logic.Pole;

import javax.swing.*;
import java.awt.*;

public abstract class Shape extends JButton
{
    private boolean selected;
    final private Pole pole;
    protected int size;
    protected Polygon figure;

    void select()
    {
        selected=true;
    }

    void deselect()
    {
        selected=false;
    }

    public Pole getPole()
    {
        return pole;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        g.setColor(Color.WHITE);
        if(pole.getFocus()==Pole.FOCUS)
        {
            g.setColor(Color.YELLOW);
        }
        if(pole.getFocus()==Pole.DOUBLE_FOCUS)
        {
            g.setColor(Color.RED);
        }
        g.drawPolygon(figure);
        g.fillPolygon(figure);
        if(selected)
        {
            setText("_");
        }
        else
        {
            setText("");
        }
        Organizm organism_to_paint = pole.getOrganizm_na_polu();
        if(organism_to_paint!=null && !(organism_to_paint.czyUsunac()))
        {
            setText(""+organism_to_paint.getSymbol());
        }
        super.paintComponent(g);
    }

    Shape(int size, Koordynaty location, Pole pole)
    {
        super("");
        setMargin(new Insets(0,0,0,0));
        setBounds(location.getWidth(), location.getHeight(), size, size);
        setFocusPainted(true);
        setFocusable(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        this.pole = pole;
        pole.powiadom_o_zmianie_stanu(this::repaint);
        this.size=size;
        figure = new Polygon();
        selected = false;
    }
}
