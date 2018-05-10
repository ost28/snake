/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ost.game.effect;

import com.ost.game.Objects.Food;
import com.ost.game.snake.Snake;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Dmitriy
 */
public class Flashing extends Effect{
    
    private long time;
    public static long timeFlashing = 2000;
    
    public Flashing(Snake snake, long time) {
        super(snake);
        this.time = time;
    }
    
    @Override
    public void activate(){
        snake.getHead().setTail();
        snake.setColor(Snake.Colors.BLACK);
        timeFlashing = time;
        Timer t = new Timer();
        TimerTask mtt = new TimerTask() {
            @Override
            public void run() {
                timeFlashing = 2000;
            }
        };
        t.schedule(mtt, time);
        
    }
    
}
