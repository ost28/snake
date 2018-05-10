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
public class Snake implements ISnake{
    
    private Head head;
    private static int velocity;
    public static final int maxVelocity = 12;
    public static final int minVelocity = 12;
    private static int boost;

    @Override
    public void setNextNavigate(Segment.Navigate nav) {
        if(nav==Navigate.UP && head.nav!=Navigate.DOWN
                || nav==Navigate.DOWN && head.nav!=Navigate.UP
                || nav==Navigate.RIGHT && head.nav!=Navigate.LEFT
                || nav==Navigate.LEFT && head.nav!=Navigate.RIGHT)
            ((Segment)head).nextNav = nav;
    }

    @Override
    public Segment.Navigate getNavigate() {
        return ((Segment)head).nav;
    }
    
    public Segment.Navigate getNextNavigate(){
        return ((Segment)head).nextNav;
    }

    public enum Colors{BLACK, RED, GREEN, BLUE, ORANGE}
    public Snake(Head head, int velocity, int length){
        this.head = head;
        this.velocity = 12;
        this.boost = 0;
        for(int i=0;i<length;i++)
            this.head.setTail();
    }
    
    public Head getHead(){
        return this.head;
    }
    /** Устанавливает скорость змейки
     * @param vel - новая скорость
     */
    public static void setVelocity(int vel){
        if(vel>maxVelocity){
            Snake.velocity = maxVelocity;
        }
        else if(vel<minVelocity){
            Snake.velocity = minVelocity;
        }
        else{
            Snake.velocity = vel;
            if(velocity+boost>maxVelocity)
                boost = maxVelocity-velocity;
            if(velocity+boost<minVelocity)
                boost = velocity - minVelocity;
        }
    }
    
    /** Получить скорость змейки
     * @return - скорость
     */
    public static int getVelocity(){
        return Snake.velocity;
    }
    
    /** Получить ускорение змейки
     * @return - скорость
     */
    public static int getBoost(){
        return Snake.boost;
    }
    
    /** Устанавливает ускорение змейки
     * @param boost - новое ускорение
     */
    public static void setBoost(int boost){
        if(boost+velocity<=maxVelocity && boost+velocity>=minVelocity)
            Snake.boost=boost;
    }
    
    public void setColor(Colors color){
        Segment seg = head;
        while(seg!=null){
            seg.setColor(color);
            seg=seg.tail;
        }
    }
    
    /** Возвращает длину змеи
     * @return количество сегментов змеи 
     */
    public int getLength(){
        int length = 0;
        Segment t = head;
        while(t!=null){
            ++length;
            t=t.tail;
        }
        return length;
    }
    
    public void update(){
        head.update();
    }
}
