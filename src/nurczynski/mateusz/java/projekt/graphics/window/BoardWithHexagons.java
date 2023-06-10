package nurczynski.mateusz.java.projekt.graphics.window;

import nurczynski.mateusz.java.projekt.Koordynaty;
import nurczynski.mateusz.java.projekt.logic.Swiat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardWithHexagons extends Board
{
    public BoardWithHexagons(Koordynaty grid_size, int margin_top, Koordynaty size, int parent_element_width, Swiat swiat) {
        super(grid_size, size, margin_top, parent_element_width);
        int field_width = (int) ((size.getWidth() - ((grid_size.getWidth() + 2)*field_spacing)) / ((double)grid_size.getWidth()+0.5));
        int field_height = (int) ((size.getHeight() - ((grid_size.getHeight() + 2)*field_spacing)) / (((grid_size.getHeight()-1)*0.75)+1));
        single_field_size = Math.min(field_height, field_width);
        int general_horizontal_offset = (int)(size.getWidth() - (grid_size.getWidth()+0.5)*(single_field_size + field_spacing))/2 - 2*field_spacing;
        Koordynaty field_location = new Koordynaty(general_horizontal_offset, field_spacing);
        for(int i=0;i<grid_size.getHeight();i++)
        {
            int hexagon_slope_size = 0;
            int horizontal_offset = (i%2)*(single_field_size/2+1);
            for(int j=0;j<grid_size.getWidth();j++)
            {
                Hexagon field = new Hexagon(single_field_size, new Koordynaty(field_location.getWidth()+horizontal_offset, field_location.getHeight()), swiat.getPole(new Koordynaty(j, i)));
                field.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        setShape_selected((Shape)e.getSource());
                    }
                });
                add(field);
                fields.add(field);
                field_location.setWidth(field_location.getWidth() + single_field_size+field_spacing);
                hexagon_slope_size = field.get_slope_size();
            }
            field_location.setWidth( general_horizontal_offset);
            field_location.setHeight (field_location.getHeight() + (single_field_size+field_spacing)-hexagon_slope_size);
        }
    }
}
