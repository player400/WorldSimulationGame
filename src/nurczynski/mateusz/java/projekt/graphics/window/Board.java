package nurczynski.mateusz.java.projekt.graphics.window;

import nurczynski.mateusz.java.projekt.Koordynaty;
import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public abstract class Board extends JPanel {
    protected final static int field_spacing = 3;
    private Shape shape_selected;
    private final int parent_element_width;
    private final int margin_top;
    private Koordynaty location;
    protected Vector<Shape> fields;
    protected Koordynaty size;
    protected Koordynaty grid_size;
    protected int single_field_size;

    public Shape getShape_selected()
    {
        return shape_selected;
    }

    public void setShape_selected(Shape shape_selected)
    {
        if(this.shape_selected!=null)
        {
            this.shape_selected.deselect();
            this.shape_selected.repaint();
        }
        if(shape_selected!=null)
        {
            shape_selected.select();
            shape_selected.repaint();
        }
        this.shape_selected = shape_selected;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        setBounds((parent_element_width - size.getWidth()) / 2, margin_top, size.getWidth(), size.getHeight());
        setLayout(null);
        super.paintComponent(g);

    }

    public Board(Koordynaty grid_size, Koordynaty size, int margin_top, int parent_element_width)
    {
        super();
        fields= new Vector<Shape>();
        setLayout(null);
        setOpaque(true);
        setBackground(Color.BLACK);
        setBounds((parent_element_width - size.getWidth()) / 2, margin_top, size.getWidth(), size.getHeight());
        this.grid_size = grid_size;
        this.parent_element_width = parent_element_width;
        this.margin_top = margin_top;
        this.size = size;
        shape_selected = null;
    }
}
