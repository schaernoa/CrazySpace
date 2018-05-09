package net.ictcampus.crazyspace;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Spielfigur extends JPanel {
    
    // Instanz Variablen
    private int xPos = 250;
    private int yPos = 700;
    private int width = 100;
    private int height = 100;
    private ImageIcon icon;
    

    public Spielfigur() {
        icon = new ImageIcon(getClass().getResource("rocket.png"));
        JLabel label = new JLabel(icon);
        this.add(label);
        this.setOpaque(false);
        this.setSize(width, height);
        this.setBounds(xPos, yPos, width, height);
    }
    
    /*
     * Setzt Spielfigur auf vorgesehene Position
     */
    public void bewegen(String richtung) {
        if (richtung.equals("Rechts")) {
            if(xPos <= 250) {
                xPos += 200;
            }
        }
        else if (richtung.equals("Links")) {
            if(xPos >= 250) {
                xPos -= 200;
            }
        }
        setBounds(xPos, yPos, width, height);
    }
    
    /*
     * Getter Methoden
     */
    public int getxPos() {
        return xPos;
    }
    
    public int getyPos() {
        return yPos;
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
}
