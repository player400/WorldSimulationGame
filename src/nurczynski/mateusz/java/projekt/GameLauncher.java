package nurczynski.mateusz.java.projekt;

import nurczynski.mateusz.java.projekt.Game;
import nurczynski.mateusz.java.projekt.Koordynaty;
import nurczynski.mateusz.java.projekt.graphics.Prompt;
import nurczynski.mateusz.java.projekt.graphics.Selection;
import nurczynski.mateusz.java.projekt.logic.DwaOrganizmyNaPoluException;

public class GameLauncher {
    private Prompt width_prompt;
    private Prompt height_prompt;
    private Selection type_selection;
    private int width = 20;
    private int height = 10;
    private Game game;

    private void init()
    {
        type_selection.dispose();
    }

    private void ask_type()
    {
        game = new Game(new Koordynaty(width, height));
        height_prompt.dispose();
        type_selection = new Selection("Wybierz rodzaj mapy", "Hexagony", "Kwadraty", ()->{
            try {
                game.init_hex();
                init();
            } catch (DwaOrganizmyNaPoluException e) {
                System.out.println("Dwa organizmy na tym samym polu!");
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, ()->{
            try {
                game.init_sqr();
                init();
            } catch (DwaOrganizmyNaPoluException e) {
                System.out.println("Dwa organizmy na tym samym polu!");
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void ask_height()
    {
        width_prompt.dispose();
        height_prompt = new Prompt("Wysokosc planszy", (String string_height)->{
            try
            {
                int new_height = Integer.parseUnsignedInt(string_height);
                if (new_height > 0) {
                    height = new_height;
                }
            }
            catch (NumberFormatException ignored){}
            ask_type();
        });
    }

    private void ask_width()
    {
        width_prompt = new Prompt("Szerokosc planszy:", (String string_width)-> {
            try
            {
                int new_width = Integer.parseUnsignedInt(string_width);
                if (new_width > 0) {
                    width = new_width;
                }
            }
            catch (NumberFormatException ignored){}
            ask_height();
        });
    }

    public GameLauncher()
    {
        ask_width();
    }
}
