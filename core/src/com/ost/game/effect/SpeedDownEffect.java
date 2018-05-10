/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ost.game.effect;

import com.ost.game.snake.Snake;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Dmitriy
 */
public class SpeedDownEffect extends Effect{
    
    private long time;
    
    public SpeedDownEffect(Snake snake, long time){
        super(snake);
        this.time = time;
    }
    
    @Override
    public void activate(){
        snake.getHead().setTail();
        snake.setColor(Snake.Colors.BLUE);
        if(Snake.getVelocity()+Snake.getBoost()>Snake.maxVelocity){
            Snake.setBoost(Snake.getBoost()-1);
            Timer t = new Timer();
            TimerTask mtt = new TimerTask() {
                @Override
                public void run() {
                    Snake.setBoost(Snake.getBoost()+1);
                }
            };
            t.schedule(mtt, time);
        }
    }
}
