package nurczynski.mateusz.java.projekt.graphics.window;

import javax.swing.*;

import nurczynski.mateusz.java.projekt.BasicEventListener;
import nurczynski.mateusz.java.projekt.Koordynaty;
import nurczynski.mateusz.java.projekt.graphics.window.*;
import nurczynski.mateusz.java.projekt.logic.Pole;
import nurczynski.mateusz.java.projekt.logic.Swiat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class Window extends JFrame {
    private final static Koordynaty size = new Koordynaty(800, 700);
    private final static int board_height = 500;
    private final static int text_area_height = 200;
    private final static int button_grid_height= 100;
    private final static Koordynaty button_size = new Koordynaty(150, 50);
    private final static int button_spacing = 10;
    private final static int legend_width=310;
    private final static int log_width = size.getWidth()-legend_width;
    private Vector<JButton>buttons;
    private JButton next_turn_button;
    private JButton skill_button;
    private JButton save_button;
    private JButton load_button;
    private Board board;
    Legend legend;
    Log log;
    BasicEventListener next_turn;
    BasicEventListener skill;
    BasicEventListener save;
    BasicEventListener load;

    private void initialize_window()
    {
        setFocusable(true);
        setResizable(false);
        setLayout(null);
        setSize(size.getWidth(), size.getHeight());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    private void place_buttons()
    {
        for(int i=0;i<buttons.size();i++)
        {
            buttons.get(i).setSize(button_size.getWidth(), button_size.getHeight());
            buttons.get(i).setLocation(button_spacing + i*(button_size.getWidth()+button_spacing), size.getHeight() - button_grid_height);
        }
    }
    private void initialize_buttons()
    {
        next_turn_button = new JButton("Kolejna tura");
        next_turn_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    next_turn.Trigger();
            }
        });
        skill_button = new JButton("Umiejetnosc");
        skill_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    skill.Trigger();
            }
        });
        save_button = new JButton("Zapisz");
        save_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save.Trigger();
            }
        });
        load_button = new JButton("Wczytaj");
        load_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                load.Trigger();
            }
        });
        buttons.add(next_turn_button);
        buttons.add(skill_button);
        buttons.add(save_button);
        buttons.add(load_button);
        place_buttons();
        add(skill_button);
        add(next_turn_button);
        add(save_button);
        add(load_button);
    }
    private void initialize_text_areas()
    {
        legend = new Legend(new Koordynaty(legend_width, text_area_height), new Koordynaty(0, size.getHeight()-(text_area_height+button_grid_height)));
        log = new Log(new Koordynaty(log_width, text_area_height), new Koordynaty(legend_width, size.getHeight()-(text_area_height+button_grid_height)));
        add(log);
        add(legend);
    }
    public void setSkillButtonEnabled(boolean bool)
    {
        skill_button.setEnabled(bool);
    }
    public void enableButtons(boolean bool)
    {
        for (JButton button : buttons) {
            button.setEnabled(bool);
        }
    }
    public void setNext_turnListener(BasicEventListener eventListener)
    {
        next_turn = eventListener;
    }
    public void setSkillListener(BasicEventListener eventListener)
    {
        skill = eventListener;
    }
    public void setLoadListener(BasicEventListener eventListener)
    {
        load = eventListener;
    }
    public void setSaveListener(BasicEventListener eventListener)
    {
        save = eventListener;
    }
    public void create_square_board(Koordynaty grid_size, Swiat swiat)
    {
        if(board == null)
        {
            board = new BoardWithSquares(grid_size, 0 , new Koordynaty(size.getWidth(), size.getHeight()-300), size.getWidth(), swiat);
            add(board);
            repaint();
        }

    }
    public void create_hexagonal_board(Koordynaty grid_size, Swiat swiat)
    {
        if(board==null)
        {
            board = new BoardWithHexagons(grid_size, 0 , new Koordynaty(size.getWidth(), size.getHeight()-300), size.getWidth(), swiat);
            add(board);
            repaint();
        }
    }
    public Pole get_selected_field()
    {
        if(board.getShape_selected()==null)
        {
            return null;
        }
        return board.getShape_selected().getPole();
    }

    public void refresh_logs(Vector<String>logs)
    {
        log.refresh(logs);
    }
    public Window()
    {
        super("Mateusz Nurczynski, 193053");
        buttons = new Vector<JButton>();
        board=null;
        initialize_window();
        initialize_buttons();
        initialize_text_areas();
    }
}
