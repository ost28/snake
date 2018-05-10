/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ost.game.Objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author Dmitriy
 */
public class Areas {
    
    public ArrayList<Area> areas;
    public int boost;
    public static boolean snakeOnArea = false;
    public Areas(ArrayList<Area> areas, int boost){
        this.areas = areas;
        this.boost=boost;
    }
    
    public ArrayList<Area> getAreas(){
        return areas;
    }
    
    public int getBoost(){
        return boost;
    }
    
    public void render(SpriteBatch batch){
        for(Area a : areas)
            batch.draw(a.getImage(),a.getPosition().x,a.getPosition().y);
    }
    
    public boolean collisionArea(Rectangle form){
        for(Area a : areas){
            if(Intersector.overlaps(a.getForm(), form)){
                return true;
            }
        }
        return false;
    }
}
