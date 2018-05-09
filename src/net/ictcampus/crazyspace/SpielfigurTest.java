package net.ictcampus.crazyspace;

import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


class SpielfigurTest {

    private static Spielfigur figur;
    
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        SpielfigurTest.figur = new Spielfigur();
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
        SpielfigurTest.figur = null;
    }

    @Test
    void bewegenTest() {
        figur.bewegen("Rechts");
        int posX = figur.getxPos();
        Assert.assertEquals(450, posX);
    }
}
