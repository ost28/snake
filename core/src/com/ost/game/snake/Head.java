/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ost.game.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

/** Голова змеи
 * @author Dmitriy
 */
public class Head extends Segment{
    
    /** Создает экземпляр головы
    * @param x - координата левого угла изображения
    * @param y - координата нижнего угла изображения
     * @param nav - направление головы
    */
    public Head(int x,int y, Navigate nav){
        super(x,y,nav);
    }    
    /** Двигает змейку
     */
    @Override
    protected void setNavigate(){
        //Для каждой единицы скорости
        for(int i=0; i<Snake.getVelocity()+Snake.getBoost(); i++){
            //Если позиция, на которой нужно повенуть
            if(position.x%image.getWidth()==0 && position.y%image.getHeight()==0 && nextNav!=nav){
                //Если направление изменилось и есть хвостик
                if(nav!=nextNav && tail!=null){
                    //Предупреждаем о повороте
                    tail.nextPos=new Vector2(position);
                    tail.nextNav=nextNav;
                }
                //Повернуть
                nav=nextNav;
            }
            else
                //Проверить следующее направление
                setNextNav();
            //Сдвинуться на единицу расстояния
            makeMove();
        }
    }
    /** Устанавливает следующее направление
     */
    private void setNextNav() {
        if((Gdx.input.isKeyPressed(Keys.UP)||(Gdx.input.isKeyPressed(Keys.W))) && nav!=Navigate.DOWN)
            nextNav=Navigate.UP;
        else if((Gdx.input.isKeyPressed(Keys.DOWN)||(Gdx.input.isKeyPressed(Keys.S))) && nav!=Navigate.UP)
            nextNav=Navigate.DOWN;
        else if((Gdx.input.isKeyPressed(Keys.RIGHT)||(Gdx.input.isKeyPressed(Keys.D))) && nav!=Navigate.LEFT)
            nextNav=Navigate.RIGHT;
        else if((Gdx.input.isKeyPressed(Keys.LEFT)||(Gdx.input.isKeyPressed(Keys.A))) && nav!=Navigate.RIGHT)
            nextNav=Navigate.LEFT;
    }
}
