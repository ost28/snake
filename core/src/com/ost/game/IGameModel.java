/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ost.game;

import com.badlogic.gdx.math.Vector2;
import com.ost.game.Objects.Block;
import com.ost.game.Objects.Food;
import com.ost.game.snake.Segment.Navigate;
import java.util.ArrayList;

/**
 *
 * @author Dmitriy
 */
public interface IGameModel {
    /*
    Управление змейкой
    */
    public void setNavigate(Navigate nav);
    /*
    Проверка направления
    */
    public boolean checkNavigate(Navigate nav);
    /*
    Получение позиции змейки (головы)
    */
    public Vector2 getSnakePosition();
    /*
    Получение направления движения змейки (головы)
    */
    public Navigate getSnakeNavigate();
    /*
    Получение следующего направления движения змейки (головы)
    */
    public Navigate getSnakeNextNavigate();
    /*
    Получение еды
    */
    public ArrayList<Food> getFood();
    /*
    Получение блоков
    */
    public ArrayList<Block> getBlocks();
}
