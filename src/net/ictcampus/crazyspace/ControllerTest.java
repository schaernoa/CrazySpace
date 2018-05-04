package net.ictcampus.crazyspace;

import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ControllerTest {

    private static Controller ch;
    private static CrazySpaceFrame frame;
    private static Spielfigur figur;
    
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        frame = new CrazySpaceFrame();
        figur = new Spielfigur();
        ControllerTest.ch = new Controller(frame, figur);
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
        ControllerTest.ch = null;
    }

    @Test
    public void increaseCouterTest() {
        final Float float1 = ch.increaseCounter(3.333f);
        Assert.assertEquals(3.334f, float1, 0.0001);
    }
    
    @Test
    public void stoppenTest() {
        int punkte = ch.stoppen();
        Assert.assertNotNull(punkte);
    }
}
