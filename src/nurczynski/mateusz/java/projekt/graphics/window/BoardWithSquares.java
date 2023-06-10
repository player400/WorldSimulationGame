package nurczynski.mateusz.java.projekt.graphics.window;

import nurczynski.mateusz.java.projekt.Koordynaty;
import nurczynski.mateusz.java.projekt.logic.Swiat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardWithSquares extends Board {
    BoardWithSquares(Koordynaty grid_size, int margin_top, Koordynaty size, int parent_element_width, Swiat swiat)
    {
        super(grid_size, size, margin_top, parent_element_width);
        int single_field_width = (int) ((size.getWidth() - ((grid_size.getWidth()+1)*field_spacing)) / (grid_size.getWidth()));
        int single_field_height = (int) ((size.getHeight() - ((grid_size.getHeight()+1)*field_spacing)) / (grid_size.getHeight()));
        single_field_size = Math.min(single_field_height, single_field_width);
        int general_horizontal_offset = (int)(size.getWidth() - grid_size.getWidth()*(single_field_size+field_spacing) )/2 - 2*field_spacing;
        Koordynaty field_location = new Koordynaty(general_horizontal_offset, field_spacing);
        for(int i=0;i<grid_size.getHeight();i++)
        {
            for(int j=0;j<grid_size.getWidth();j++)
            {
                Shape field = new Square(single_field_size, new Koordynaty(field_location.getWidth(), field_location.getHeight()), swiat.getPole(new Koordynaty(j, i)));
                field.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        setShape_selected((Shape)e.getSource());
                    }
                });
                add(field);
                fields.add(field);
                field_location.setWidth(field_location.getWidth() + single_field_size+field_spacing);
            }
            field_location.setWidth(general_horizontal_offset);
            field_location.setHeight(field_location.getHeight()+single_field_size+field_spacing);
        }
    }
}
