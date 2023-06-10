package nurczynski.mateusz.java.projekt.graphics;

import nurczynski.mateusz.java.projekt.BasicEventListener;
import nurczynski.mateusz.java.projekt.Koordynaty;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Selection extends JFrame {

    private final static Koordynaty size=new Koordynaty(400, 400);
    private final static int element_width=300;

    private final static int grid_layers=8;
    private final static int font_size=30;

    public void init_button(String option, BasicEventListener action, int height)
    {
        JButton button = new JButton(option);
        button.setSize(element_width, size.getHeight()/grid_layers);
        button.setLocation((size.getWidth()-element_width)/2, height);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action.Trigger();
            }
        });
        add(button);
    }
    public void init_label(String prompt)
    {
        JLabel label = new JLabel(prompt);
        label.setSize(element_width, size.getHeight()/grid_layers);
        label.setLocation((size.getWidth()-element_width)/2, size.getHeight()/grid_layers);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Arial",Font.PLAIN, font_size ));
        add(label);
    }
    public Selection(String prompt, String option1, String option2, BasicEventListener action1, BasicEventListener action2)
    {
        setFocusable(true);
        setResizable(false);
        setLayout(null);
        setSize(size.getWidth(), size.getHeight());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init_button( option1, action1, (size.getHeight()/grid_layers)*3);
        init_button( option2, action2, (size.getHeight()/grid_layers)*5);
        init_label(prompt);
        setVisible(true);
    }
}
