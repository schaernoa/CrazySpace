package net.ictcampus.crazyspace;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * Erstellt neues Frame in Main Methode
 * Zusätzlich wird ein Spieler und ein Controller erstellt (welcher zuständig für die Hindernisse ist)
 * Regelt grösse und Ansicht des Frames und ruft den Controller auf
 */
public class CrazySpaceFrame extends JFrame {

    // Instanz Variablen
    private int width = 600;
    private int height = 880;
    private Spielfigur figur = new Spielfigur();
    private Controller control;

    // Konstruktor
    public CrazySpaceFrame() {

        // CrazyRoadFrame Layout
        setBackgroundImage();
        this.setSize(width, height);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setTitle("CrazySpace");

        // KeyListener auf Figur, Frame
        MyKeyListener k = new MyKeyListener(figur, this);
        this.addKeyListener(k);

        // GUI Anzeigen
        figur.setFocusable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        
        // Spiel erstellen (Hindernisse)
        control = new Controller(this, figur);
        
        // Frame schliessen
        this.dispose();
    }

    
    /*
     *  setBackgroundImage() Methode
     *  Setzt Hintergrund des Frames
     */
    public void setBackgroundImage() {
        try {
            final Image backgroundImage = javax.imageio.ImageIO
                    .read(new File("src/net/ictcampus/crazyspace/space.jpg"));
            setContentPane(new JPanel(new BorderLayout()) {
                @Override
                public void paintComponent(Graphics g) {
                    g.drawImage(backgroundImage, 0, 0, null);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    

    // Main Methode
    public static void main(String[] args) {
        CrazySpaceFrame frame = new CrazySpaceFrame();
    }
}
