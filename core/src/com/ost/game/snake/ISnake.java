/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ost.game.snake;

import com.ost.game.snake.Segment.Navigate;

/**
 *
 * @author Dmitriy
 */
public interface ISnake {
    public void setNextNavigate(Navigate nav);
    public Navigate getNavigate();
}
