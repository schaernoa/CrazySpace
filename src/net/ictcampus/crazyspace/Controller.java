package net.ictcampus.crazyspace;

import javax.swing.JOptionPane;

/*
 * Controller ist zust�ndig, zwei Hindernisse auf das Frame zu machen
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
     *  erstellt neue Hindernis-Objekte, welche anschliessend dem Frame hinzugef�gt werden
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
     * Ruft f�r jedes Hindernis im Hindernis-Array die Update-Methode auf,
     * in welcher die y-Koordinate ver�ndert wird
     */
    public void update() {

        for (Hindernis h : hindernisse) {
            h.update(counter);
        }
    }
    
    /*
     * erh�ht den Counter, bei jedem durchlauf (wird sp�ter f�r Score ben�tigt)
     * gibt Zahl als float zur�ck
     */
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

    /*
     * mit einem Input-Dialog wird ein Name verlangt
     * gibt String mit Name zur�ck
     */
    public String eingabeName() {
        return JOptionPane.showInputDialog("Bitte Name eingeben");
    }

    /*
     * �berpr�ft Kollision der Rakete mit den Planeten
     * gibt true oder false zur�ck
     */
    public boolean loss() {

        for (Hindernis h : hindernisse) {
            if (h.getyPos() + 100 >= spieler.getyPos() && h.getyPos() - 100 <= spieler.getyPos()
                    && h.getxPos() == spieler.getxPos()) {
                return false;
            }
        }
        return true;
    }

    /*
     * Macht alle Hindernisse unsichtbar und gibt den Punktestand (int) zur�ck
     * Dabei wird ber�cksichtigt, dass wenn beim ersten Hindernis gecrasht wird
     * der Score 0 ist
     */
    public int stoppen() {

        for (Hindernis h : hindernisse) {
            h.setVisible(false);
        }

        punkte = (int) ((counter * 100) - 229);
        return punkte;
    }

    /*
     * �ffnet eine MessageBox, in welcher wenn vorhanden der Name mit dem Score
     * und einem kleinen Kommentar zum Punktestand
     * @name wurde zuvor eingelesen
     * @score wurde mit der stoppen-Methode zur�ckgegeben
     */
    public void scoreEintragen(String name, int score) {
    	if (name.length() == 0) {
    		if (score <= 300) {
                JOptionPane.showMessageDialog(frame,
                		"Du hast " + score + " Punkte erreicht, da muss noch ge�bt werden.");
            } else if (score > 300 && score <= 500) {
                JOptionPane.showMessageDialog(frame,
                		"Du hast " + score + " Punkte erreicht, dass l�sst sich doch sehen.");
            } else if (score > 500) {
                JOptionPane.showMessageDialog(frame,
                        "Du hast " + score + " Punkte erreicht, dass ist eine gute Leistung.");
            }
    	}
    	else {
    		if (score <= 300) {
                JOptionPane.showMessageDialog(frame,
                        name + " hat " + score + " Punkte erreicht, da muss noch ge�bt werden.");
            } else if (score > 300 && score <= 500) {
                JOptionPane.showMessageDialog(frame,
                        name + " hat " + score + " Punkte erreicht, dass l�sst sich doch sehen.");
            } else if (score > 500) {
                JOptionPane.showMessageDialog(frame,
                        name + " hat " + score + " Punkte erreicht, dass ist eine gute Leistung.");
            }
    	}
    }
}
