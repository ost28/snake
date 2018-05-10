/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.java.com.ost.game.modules;

import com.ost.game.Field;
import com.ost.game.GameModel;
import com.ost.game.Objects.Food;
import com.ost.game.effect.TailDownEffect;
import com.ost.game.modules.ModuleGame;
import com.ost.game.snake.Head;
import com.ost.game.snake.Segment;
import com.ost.game.snake.Segment.Navigate;
import com.ost.game.snake.Snake;
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

    @org.junit.Test
    public void testCheckAndSetNavigate() {
        
    }

    @org.junit.Test(expected = NullPointerException.class)
    public void testLoadNullModel(){
        System.out.println("load: null gm");
        ModuleGame mg = new ModuleGame();
        mg.load(null,mg);
    }
    
    @org.junit.Test(expected = NullPointerException.class)
    public void testLoadNullModule(){
        System.out.println("load: null module");
        ModuleGame mg = new ModuleGame();
        mg.load(null,mg);
    }
    
    @org.junit.Test
    public void testGetNavigateUp(){
        System.out.println("testGetNavigate: UP");
        ModuleGame mg = new ModuleGame();
        Head h = new Head(Field.WIDTH/2,Field.HEIGHT/2,Navigate.RIGHT);
        Snake s = new Snake(h,1,6);
        TailDownEffect e = new TailDownEffect(s,1);
        Food f = new Food(Field.WIDTH/2,Field.HEIGHT/2+24,e);
        assertEquals(Navigate.UP,mg.getNavigate(s,f));
    }
    
    @org.junit.Test
    public void testGetNavigateDown(){
        System.out.println("testGetNavigate: Down");
        ModuleGame mg = new ModuleGame();
        Head h = new Head(Field.WIDTH/2,Field.HEIGHT/2,Navigate.RIGHT);
        Snake s = new Snake(h,1,6);
        TailDownEffect e = new TailDownEffect(s,1);
        Food f = new Food(Field.WIDTH/2,Field.HEIGHT/2-24,e);
        assertEquals(Navigate.DOWN,mg.getNavigate(s,f));
    }
    
    @org.junit.Test
    public void testGetNavigateRight(){
        System.out.println("testGetNavigate: RIGHT");
        ModuleGame mg = new ModuleGame();
        Head h = new Head(Field.WIDTH/2,Field.HEIGHT/2,Navigate.RIGHT);
        Snake s = new Snake(h,1,6);
        TailDownEffect e = new TailDownEffect(s,1);
        Food f = new Food(Field.WIDTH/2+24,Field.HEIGHT/2,e);
        assertEquals(Navigate.RIGHT,mg.getNavigate(s,f));
    }
    
    @org.junit.Test
    public void testGetNavigateLeft(){
        System.out.println("testGetNavigate: Left");
        ModuleGame mg = new ModuleGame();
        Head h = new Head(Field.WIDTH/2,Field.HEIGHT/2,Navigate.RIGHT);
        Snake s = new Snake(h,1,6);
        TailDownEffect e = new TailDownEffect(s,1);
        Food f = new Food(Field.WIDTH/2-24,Field.HEIGHT/2,e);
        assertEquals(Navigate.LEFT,mg.getNavigate(s,f));
    }
    
}
