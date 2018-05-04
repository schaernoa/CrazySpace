package net.ictcampus.crazyspace;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class MyKeyListener implements KeyListener {

    // Instanz Variablen
    CrazySpaceFrame frame;
    Spielfigur figur;
    
    // Konstruktor
    public MyKeyListener(Spielfigur sf, CrazySpaceFrame f) {
        frame = f;
        figur  = sf;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            figur.bewegen("Rechts");
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            figur.bewegen("Links");
        }
        figur.repaint();
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        // TODO Auto-generated method stub
        
    }
}
