package net.ictcampus.crazyspace;

import javax.swing.JOptionPane;

public class Controller {

    Hindernis[] hindernisse;
    CrazySpaceFrame frame;
    Spielfigur spieler;

    private boolean spielen;
    private float counter;
    private int punkte;
    private int score;
    private String name;

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

    public void initHindernisse() {
        for (int i = 0; i < hindernisse.length; i++) {
            hindernisse[i] = new Hindernis();
            frame.add(hindernisse[i]);
        }
    }

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
