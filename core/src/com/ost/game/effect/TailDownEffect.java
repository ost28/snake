/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ost.game.effect;

import com.ost.game.snake.Head;
import com.ost.game.snake.Snake;

/**
 *
 * @author Dmitriy
 */
public class TailDownEffect extends Effect{
    
    private int length;
    
    public TailDownEffect(Snake snake, int length){
        super(snake);
        this.length = length;
    }
    
    @Override
    public void activate(){
        for(int i=0; i<length && snake.getLength()>5; i++){
            snake.getHead().removeTail();
        }
        System.out.println(snake.getLength());
        snake.setColor(Snake.Colors.GREEN);
    }
}
