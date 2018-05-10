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
public abstract class Effect {
    protected Snake snake;
    
    public Effect(Snake snake){
        this.snake = snake;
    }
    
    public void activate(){};
}
