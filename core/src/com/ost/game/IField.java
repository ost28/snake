/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ost.game;

import com.ost.game.snake.Segment.Navigate;

/**
 *
 * @author Dmitriy
 */
public interface IField {
    public boolean checkNavigate(Navigate nav);
}
