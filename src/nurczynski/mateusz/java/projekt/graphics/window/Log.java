package nurczynski.mateusz.java.projekt.graphics.window;

import nurczynski.mateusz.java.projekt.Koordynaty;
import nurczynski.mateusz.java.projekt.logic.Swiat;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class Log extends TextArea {
    private final int font_size = 10;

    public void refresh(Vector<String>logs)
    {
        setText(null);
        for (String log : logs)
        {
            append(log + '\n');
        }
        repaint();
    }

    public Log(Koordynaty size, Koordynaty location)
    {
        super("");
        setFont(new Font("Arial", Font.PLAIN, font_size));
        setFocusable(false);
        setBounds(location.getWidth(), location.getHeight(), size.getWidth(), size.getHeight());
        setEditable(false);
    }
}
