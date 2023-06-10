package nurczynski.mateusz.java.projekt;

import nurczynski.mateusz.java.projekt.graphics.Prompt;
import nurczynski.mateusz.java.projekt.graphics.window.Window;
import nurczynski.mateusz.java.projekt.logic.DwaOrganizmyNaPoluException;
import nurczynski.mateusz.java.projekt.logic.Pole;
import nurczynski.mateusz.java.projekt.logic.Swiat;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class Game {
    final static private String FILE_EXTENTION = ".nurczynski";
    private Prompt filename_prompt;
    private class MyThread extends Thread
    {
        @Override
        public void run()
        {
            try {
                swiat.akcja();
            } catch (DwaOrganizmyNaPoluException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private Koordynaty grid_size;
    private Swiat swiat;
    private Window window;

    private void save(String filename)
    {
        filename+=FILE_EXTENTION;
        Vector<String> data = swiat.zapisz();
        filename_prompt.dispose();
        File file = new File(filename);
        file.delete();
        try {
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            for(int i=0;i<data.size();i++)
            {
                writer.write(data.get(i));
                writer.write('\n');
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void load(String filename)
    {
        filename+=FILE_EXTENTION;
        filename_prompt.dispose();
        try
        {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            String type_ = scanner.nextLine();
            String width_ = scanner.nextLine();
            String height_ = scanner.nextLine();
            int type = Integer.parseInt(type_);
            Koordynaty grid_size = new Koordynaty(Integer.parseInt(width_), Integer.parseInt(height_));
            Vector<String>data = new Vector<String>();
            while(scanner.hasNextLine())
            {
                data.add(scanner.nextLine());
            }
            scanner.close();
            this.grid_size = grid_size;
            swiat=null;
            window.dispose();
            window = new Window();
            if(type == Swiat.HEX)
            {
                init_hex(false);
            }
            else
            {
                init_sqr(false);
            }
            swiat.wczytaj(data);
        }
        catch (FileNotFoundException | InterruptedException | DwaOrganizmyNaPoluException e)
        {
            throw new RuntimeException(e);
        }
    }

    private void init()
    {
        window.setNext_turnListener(()->{
            window.enableButtons(false);
            MyThread thread = new MyThread();
            thread.start();
            window.requestFocus();
        });
        window.setSkillListener(()->{
            swiat.uruchom_umiejetnosc();
            window.setSkillButtonEnabled(false);
            window.requestFocus();
        });
        window.setSaveListener(()->{
            filename_prompt = new Prompt("Nazwa pliku", this::save);
        });
        window.setLoadListener(()->{
            filename_prompt = new Prompt("Nazwa pliku", this::load);
        });
        swiat.setTura_zakonczona(()->{
            window.enableButtons(true);
            window.setSkillButtonEnabled(swiat.umiejetnosc_do_uruchomienia());
            window.refresh_logs(swiat.getLogi());
        });
        window.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {

                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                        if(e.getKeyCode()==KeyEvent.VK_ENTER)
                        {
                            swiat.carry_onCzlowiek();
                        }
                        if(e.getKeyCode()==KeyEvent.VK_UP)
                        {
                            try {
                                swiat.focus_up();
                            } catch (DwaOrganizmyNaPoluException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                        if(e.getKeyCode()==KeyEvent.VK_DOWN)
                        {
                            try {
                                swiat.focus_down();
                            } catch (DwaOrganizmyNaPoluException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                        if(e.getKeyCode()==KeyEvent.VK_RIGHT)
                        {
                            try {
                                swiat.focus_right();
                            } catch (DwaOrganizmyNaPoluException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                        if(e.getKeyCode()==KeyEvent.VK_LEFT)
                        {
                            try {
                                swiat.focus_left();
                            } catch (DwaOrganizmyNaPoluException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                        Pole pole  = window.get_selected_field();
                        if(pole!=null && (pole.getOrganizm_na_polu()==null || pole.getOrganizm_na_polu().czyUsunac()))
                        {
                            try {
                                swiat.dodaj_organizm(pole, e.getKeyChar());
                            } catch (DwaOrganizmyNaPoluException | InterruptedException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );
    }

    void init_hex() throws DwaOrganizmyNaPoluException, InterruptedException
    {
        init_hex(true);
    }

    void init_sqr() throws DwaOrganizmyNaPoluException, InterruptedException
    {
        init_sqr(true);
    }

    private void init_hex(boolean generate) throws DwaOrganizmyNaPoluException, InterruptedException
    {
        if(swiat==null)
        {
            swiat = new Swiat(grid_size, Swiat.HEX);
            if(generate)
            {
                swiat.generuj_organizmy();
            }
            window.create_hexagonal_board(grid_size, swiat);
            init();
        }
    }

    private void init_sqr(boolean generate) throws DwaOrganizmyNaPoluException, InterruptedException {
        if(swiat==null)
        {
            swiat = new Swiat(grid_size, Swiat.SQR);
            if(generate)
            {
                swiat.generuj_organizmy();
            }
            window.create_square_board(grid_size, swiat);
            init();
        }
    }

    Game(Koordynaty grid_size)
    {
        swiat=null;
        this.grid_size = grid_size;
        window = new Window();
    }
}
