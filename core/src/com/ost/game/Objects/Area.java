/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ost.game.Objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author Dmitriy
 */
public class Area extends Objects{
    public Area(int x, int y, boolean up) {
        super(x, y);
        if(up)
            image=new Texture("data/areaup.png");
        else
            image=new Texture("data/areadown.png");
        form = new Rectangle(x,y,image.getWidth(),image.getHeight());
    }
    
    
}
