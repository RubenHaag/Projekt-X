package grafik;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameWindow extends JFrame {
    //TODO 6.1 EInstellungsmenu mit Verschiedenen Maps fehlt


    public GameWindow() {
        this.registerWindowListener();
        this.createTopMenu();

        this.setTitle("Battle of the Olymp");
        this.setLocation(10, 10);
        this.setResizable(false);

        this.setVisible(true);
    }

    private void createTopMenu() {
        JMenuBar topMenu = new JMenuBar();
        this.setJMenuBar(topMenu);

        JMenu fileMenu = new JMenu("Datei");
        JMenu gameMenu = new JMenu("Spiel");
        JMenu prefMenu = new JMenu("Preferences");

        topMenu.add(fileMenu);
        topMenu.add(gameMenu);
        topMenu.add(prefMenu);

        addFileMenuItems(fileMenu);
        addGameMenuItems(gameMenu);
    }

    private void addFileMenuItems(JMenu fileMenu) {
        JMenuItem quitItem = new JMenuItem("Beenden");
        fileMenu.add(quitItem);
        quitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void registerWindowListener() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

            @Override
            public void windowActivated(WindowEvent e) {
                //TODO playerGamePanel.continueGAME();
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                //TODO playerGamePanel.pauseGame();
            }
        });
    }

    private void addGameMenuItems(JMenu gameMenu) {
        //TODO vermutlich vieles UNbrauchbar da wir doch kein Pause haben
        JMenuItem pauseItem = new JMenuItem("Pause");
        gameMenu.add(pauseItem);
        pauseItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });

        JMenuItem continueItem = new JMenuItem("Continue");
        gameMenu.add(continueItem);
        continueItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });

        gameMenu.addSeparator();
        JMenuItem restartItem = new JMenuItem("Restart");
        gameMenu.add(restartItem);
        restartItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });
    }


}
