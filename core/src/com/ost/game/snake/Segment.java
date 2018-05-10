/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ost.game.snake;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.ost.game.Field;
import com.ost.game.snake.Snake.Colors;
/**
 *
 * @author Dmitriy
 */
public class Segment {

    public static void setBoost(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /** Позиция */
    protected Vector2 position;
    /** Позиция следующего поворота */
    protected Vector2 nextPos;
    /** Изображение */
    protected Texture image; 
    /** Текущее направление */
    public Navigate nav;
    /** Следующее направление */
    public Navigate nextNav;
    /** Следующий сегмент */
    public Segment tail;
    /** Силуэт */
    public Rectangle form;
    /** Список направлений */
    public static enum Navigate{
        UP,
        RIGHT,
        DOWN,
        LEFT
    }
    
    
    /** Создает экземпляр сегмента
    * @param x - координата левого угла изображения
    * @param y - координата нижнего угла изображения
     * @param nav - направление сегмента
    */
    public Segment(int x, int y, Navigate nav){
        //image = new Texture("data/snake.png");
        position = new Vector2(x,y);
        nextPos = null;
        this.nav = nav;
        nextNav = nav;
        //form = new Rectangle(x+4,y+4,image.getWidth()-8,image.getHeight()-8);
        tail=null;
    }
    /** Обновление сегмента
     */
    public void update() {
        //Двигаем текущий сегмент
        setNavigate();
        //Если есть следующий сегмент
        if(tail!=null){
            //Обновляем следующий сегмент
            tail.update();
        }
    }
    /** Двигает змейку в текущем направлении на единицу расстояния
     */
    public void makeMove(){
        //Для каждого направления
        switch (nav){
            //Сделать шаг вверх
            case UP:
                position.add(0,1);
                if(position.y>Field.HEIGHT-image.getHeight()/2)
                    position.y=-image.getHeight()/2;
                break;
            //Сделать шаг вниз
            case DOWN:
                position.add(0,-1);
                if(position.y<-image.getHeight()/2)
                    position.y=Field.HEIGHT-image.getHeight()/2;
                break;
            //Сделать шаг вправо
            case RIGHT:
                position.add(1,0);
                if(position.x>Field.WIDTH-image.getWidth()/2)
                    position.x=-image.getWidth()/2;
                break;
            //Сделать шаг влево
            case LEFT:
                position.add(-1,0);
                if(position.x<-image.getHeight()/2)
                    position.x=Field.WIDTH-image.getHeight()/2;
                break;
        }
        //Двигает форму сегмента за сегментом
        form.x=position.x;
        form.y=position.y;
    }
    /** Двигает змейку
     */
    protected void setNavigate(){
        //Для каждой единицы скорости
        for(int i=0; i<Snake.getVelocity()+Snake.getBoost(); i++){
            //Если нужно повернуть И нужно повернуть сейчас
            if(nextPos!=null && nextPos.equals(position)){
                //Если есть следующий сегмент
                if(tail!=null){
                    //Предупредить о ближайшем повороте
                    tail.nextPos=nextPos;
                    tail.nextNav=nextNav;
                }
                //Повернуть
                nav=nextNav;
                nextPos=null;
            }
            //Сдвинуться на единицу расстрояния
            makeMove();
        }
    }
    /** Отращивает следующий хвост
     */
    public void setTail(){
        //Если есть следующий сегмент
        if(tail!=null){
            //Просим его вырастить хвост
            tail.setTail();
            return;
        }
        //Для каждого направления вырастить хвост
        switch (nav){
            case UP:
                tail=new Segment((int)position.x,(int)position.y-24/2,Navigate.UP);
                break;
            case DOWN:
                tail=new Segment((int)position.x,(int)position.y+24/2,Navigate.DOWN);
                break;
            case RIGHT:
                tail=new Segment((int)position.x-24/2,(int)position.y,Navigate.RIGHT);
                break;
            case LEFT:
                tail=new Segment((int)position.x+24/2,(int)position.y,Navigate.LEFT);
                break;
        }
    }
    
    public void removeTail(){
        if(tail!=null && tail.tail!=null)
            tail.removeTail();
        else if(tail!=null && tail.tail==null)
            tail = null;
    }
    /** Возвращает позицию сегмента
     * @return позиция
     */
    public Vector2 getPosition(){return position;}
    /** Возвращает изображение сегмента
     * @return изображение 
     */
    public Texture getImage(){return image;}
    
    public void setColor(Colors color){
        if(color==Colors.BLACK)
            image=new Texture("data/snake.png");
        else if(color==Colors.BLUE)
            image=new Texture("data/snake_blue.png");
        else if(color==Colors.RED)
            image=new Texture("data/snake_red.png");
        else if(color==Colors.GREEN)
            image=new Texture("data/snake_green.png");
        else if(color==Colors.ORANGE)
            image=new Texture("data/snake_orange.png");
    }
}
