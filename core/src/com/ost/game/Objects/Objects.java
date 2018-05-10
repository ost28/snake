/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ost.game.Objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/** Объекты на поле
 * @author Dmitriy
 */
public abstract class Objects {
    protected Vector2 position;
    protected Texture image;
    protected Rectangle form;
    
    /** Создает экземпляр объекта на поле
     * @param x - координата по горизонтали
     * @param y - коорзината по вертикали
     */
    public Objects(int x, int y){
        position = new Vector2(x,y);
        //image=new Texture("data/obj.png");
        form = new Rectangle(x+2,y+2,24-4,24-4);
    }
    /** Возвращает позицию объекта
     * @return позиция объекта
     */
    public Vector2 getPosition(){
        return this.position;
    } 
    
    public void setPosition(int x, int y){
        this.position.x = x;
        this.position.y = y;
    }
    /** Возвращает изображение объекта
     * @return изображение объекта
     */
    public Texture getImage(){
        return this.image;
    } 
    
    public void setImage(Texture t){
        this.image = t;
    }
    /** Возвращает форму объекта
     * @return форма объекта
     */
    public Rectangle getForm(){
        return this.form;
    } 
}
