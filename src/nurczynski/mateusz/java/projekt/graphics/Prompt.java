package nurczynski.mateusz.java.projekt.graphics;

import nurczynski.mateusz.java.projekt.Koordynaty;
import nurczynski.mateusz.java.projekt.StringEventListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Prompt extends JFrame {
    private final static Koordynaty size=new Koordynaty(400, 400);
    private final static int element_width=300;

    private final static int grid_layers=8;
    private final static int font_size=30;
    private final StringEventListener event;
    private JTextField text_field;

    private void initText_field()
    {
        text_field = new JTextField();
        text_field.setSize(element_width, size.getHeight()/grid_layers);
        text_field.setLocation((size.getWidth()-element_width)/2, (size.getHeight()/grid_layers)*3);
        add(text_field);
    }

    private void initButton()
    {
        JButton button = new JButton("Zatwierdz");
        button.setSize(element_width, size.getHeight()/8);
        button.setLocation((size.getWidth()-element_width)/2, (size.getHeight()/grid_layers)*5);
        button.setEnabled(true);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                event.Trigger(text_field.getText());
            }
        });
        add(button);
    }

    private void initLabel(String prompt)
    {
        JLabel label = new JLabel(prompt);
        label.setSize(element_width, size.getHeight()/grid_layers);
        label.setLocation((size.getWidth()-element_width)/2, size.getHeight()/grid_layers);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Arial",Font.PLAIN, font_size ));
        add(label);
    }

    public Prompt(String prompt, StringEventListener event)
    {
        setFocusable(true);
        setResizable(false);
        setLayout(null);
        setSize(size.getWidth(), size.getHeight());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.event = event;
        initButton();
        initText_field();
        initLabel(prompt);
        setVisible(true);
    }
}
