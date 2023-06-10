package nurczynski.mateusz.java.projekt.graphics.window;

import nurczynski.mateusz.java.projekt.Koordynaty;
import nurczynski.mateusz.java.projekt.logic.Pole;

public class Square extends Shape {
    public Square(int size, Koordynaty location, Pole pole)
    {
        super(size, location, pole);
        figure.addPoint(0, 0);
        figure.addPoint(0, size);
        figure.addPoint(size, size);
        figure.addPoint(size, 0);
    }
}
