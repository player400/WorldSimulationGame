package nurczynski.mateusz.java.projekt.graphics.window;

import nurczynski.mateusz.java.projekt.Koordynaty;
import nurczynski.mateusz.java.projekt.logic.Pole;

public class Hexagon extends Shape {
    private int side_length;

    @Override
    public boolean contains(int x, int y) {
        if(x>getWidth() || y>getHeight())
        {
            return false;
        }
        if(x<0 || y< 0)
        {
            return false;
        }
        if(figure.contains(x, y))
        {
            return true;
        }
        return false;
    }

    public int get_slope_size()
    {
        double correction = ((double)size/2)/(double)side_length;
        return (int)((side_length/2.0)*correction);
    }

    public Hexagon(int size, Koordynaty location, Pole pole)
    {
        super(size, location, pole);
        side_length=(int)(size/Math.sqrt(3));
        double correction = ((double)size/2)/(double)side_length;
        for (int i = 0; i < 6; i++) {
            int vertical = (int) (size/2 + correction * side_length * Math.sin(Math.PI / 2 + i * 2 * Math.PI / 6));
            int horizontal = (int) (size/2 + side_length * Math.cos(Math.PI / 2 + i * 2 * Math.PI / 6));
            figure.addPoint(horizontal, vertical);
        }
    }
}
