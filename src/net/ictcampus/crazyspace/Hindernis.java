package net.ictcampus.crazyspace;

import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Hindernis extends JPanel {

    // Instanz Variablen
    private Random rand = new Random();
    private int xPos;
    private int yPos;
    private int width = 100;
    private int height = 100;
    private int speed = 2;
    private ImageIcon icon;

    // Konstruktor
    public Hindernis() {
        icon = new ImageIcon(getClass().getResource("meteor.png"));
        JLabel label = new JLabel(icon);
        this.add(label);
        this.setOpaque(false);
        this.setBounds(xPos, yPos, width, height);
        xPos = generateXLocation();
    }
    
    /*
     * Gibt random einer der folgenden drei werte zurück: 50, 250, 450
     */
    public int generateXLocation() {
        return ((200 * rand.nextInt(3)) + 50);
    }
    
    /*
     * Setzt Hindernisse auf vorgesehene Position
     */
    public void update(float counter){

        speed = (int) counter;
        if(yPos >= 880){
            xPos = generateXLocation();
            yPos = 0;
            setBounds(xPos, yPos, width, height);
        }
        
        else if(yPos <= 880){
            yPos += speed;
            setBounds(xPos, yPos, width, height);
        }
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
