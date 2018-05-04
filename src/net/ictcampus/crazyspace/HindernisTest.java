package net.ictcampus.crazyspace;

import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class HindernisTest {

    private static Hindernis h;
    
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        HindernisTest.h = new Hindernis();
    }
    
    @AfterAll
    static void tearDownAfterClass() throws Exception {
        HindernisTest.h = null;
    }

    // Kann nicht explizit getestet werden, da 3 verschiedene Resultate ausgegeben werden können 
    @Test
    public void generateXLocationTest() {
        long zahl = h.generateXLocation();
        System.out.println(zahl);
        Assert.assertNotNull(zahl);
    }
    
    @Test
    public void updateTest() {
        h.update(5.342f);
        int erhoeht = h.getyPos();
        Assert.assertEquals(5, erhoeht);
    }

}
