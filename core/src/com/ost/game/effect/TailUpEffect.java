/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ost.game.effect;

import com.ost.game.snake.Snake;

/**
 *
 * @author Dmitriy
 */
public class TailUpEffect extends Effect{
    
    private int length;
    
    public TailUpEffect(Snake snake, int length){
        super(snake);
        this.length = length;
    }
    
    @Override
    public void activate(){
        for(int i=0;i<length && i<5;i++)
            snake.getHead().setTail();
        System.out.println(snake.getLength());
        snake.setColor(Snake.Colors.ORANGE);
    }
}
