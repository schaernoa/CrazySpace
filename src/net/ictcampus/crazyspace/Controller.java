package net.ictcampus.crazyspace;

import javax.swing.JOptionPane;

/*
 * Controller ist zuständig, zwei Hindernisse auf das Frame zu machen
 * Alle Hindernisse des Hindernis-Arrays werden auf einer fixen Spalte dargestellt
 * Die Hindernisse werden in einer while-Schleife immer weiter nach untern fallen,
 * bis sie schlussendlich verschwinden und wieder beginnen von oben nach unten zu gehen
 */
public class Controller {

    Hindernis[] hindernisse;
    CrazySpaceFrame frame;
    Spielfigur spieler;

    private boolean spielen;
    private float counter;
    private int punkte;
    private int score;
    private String name;

    // Konstruktor
    public Controller(CrazySpaceFrame fr, Spielfigur sp) {
        this.frame = fr;
        this.spieler = sp;

        frame.setResizable(false);

        hindernisse = new Hindernis[2];
        initHindernisse();
        frame.add(spieler);
        name = eingabeName();
        play();
        score = stoppen();
        scoreEintragen(name, score);
    }

    /*
     *  erstellt neue Hindernis-Objekte, welche anschliessend dem Frame hinzugefügt werden
     */
    public void initHindernisse() {
        for (int i = 0; i < hindernisse.length; i++) {
            hindernisse[i] = new Hindernis();
            frame.add(hindernisse[i]);
        }
    }

    /*
     * Regelt den Fluss des Spiels ("fall"-Effekt der Hindernisse)
     * jeweils kurzer "Sleep", dass die Hindernisse nicht undendlich schnell fallen
     * ist beendet, wenn while-Schleife "false" ist
     */
    public void play() {
        counter = 2;
        spielen = true;
        while (spielen) {
            update();

            for (Hindernis h : hindernisse) {
                h.repaint();
            }

            spieler.repaint();

            spielen = loss();

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            counter = increaseCounter(counter);
        }
    }

    /*
     * 
     */
    public void update() {

        for (Hindernis h : hindernisse) {
            h.update(counter);
        }
    }

    public float increaseCounter(float zahl) {

        if (zahl < 5) {
            zahl += 0.001;
        } else if (zahl > 5 && zahl < 7) {
            zahl += 0.0007;
        } else {
            zahl += 0.0005;
        }
        return zahl;
    }

    public String eingabeName() {
        return JOptionPane.showInputDialog("Bitte Name eingeben");
    }

    public boolean loss() {

        for (Hindernis h : hindernisse) {
            if (h.getyPos() + 100 >= spieler.getyPos() && h.getyPos() - 100 <= spieler.getyPos()
                    && h.getxPos() == spieler.getxPos()) {
                return false;
            }
        }
        return true;
    }

    public int stoppen() {

        for (Hindernis h : hindernisse) {
            h.setVisible(false);
        }

        punkte = (int) ((counter * 100) - 229);
        return punkte;
    }

    public void scoreEintragen(String name, int score) {
    	if (name.length() == 0) {
    		if (score <= 300) {
                JOptionPane.showMessageDialog(frame,
                		"Du hast " + score + " Punkte erreicht, da muss noch geübt werden.");
            } else if (score > 300 && score <= 500) {
                JOptionPane.showMessageDialog(frame,
                		"Du hast " + score + " Punkte erreicht, dass lässt sich doch sehen.");
            } else if (score > 500) {
                JOptionPane.showMessageDialog(frame,
                        "Du hast " + score + " Punkte erreicht, dass ist eine gute Leistung.");
            }
    	}
    	else {
    		if (score <= 300) {
                JOptionPane.showMessageDialog(frame,
                        name + " hat " + score + " Punkte erreicht, da muss noch geübt werden.");
            } else if (score > 300 && score <= 500) {
                JOptionPane.showMessageDialog(frame,
                        name + " hat " + score + " Punkte erreicht, dass lässt sich doch sehen.");
            } else if (score > 500) {
                JOptionPane.showMessageDialog(frame,
                        name + " hat " + score + " Punkte erreicht, dass ist eine gute Leistung.");
            }
    	}
    }
}
