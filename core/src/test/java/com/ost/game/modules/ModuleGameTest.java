/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.java.com.ost.game.modules;

import com.ost.game.GameModel;
import com.ost.game.modules.ModuleGame;
import com.ost.modules.Module;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dmitriy
 */
public class ModuleGameTest {
    
    public ModuleGameTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
    }

    GameModel gm;
    Module batch;

    @org.junit.Test
    public void testCheckAndSetNavigate() {
        
    }

    @org.junit.Test
    public void testReturn3(){
        System.out.println("return3");
        assertEquals(0, 1);
    }
}
