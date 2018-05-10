/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ost.game.Objects;

import com.badlogic.gdx.graphics.Texture;

/** Блоки
 * @author Dmitriy
 */
public class Block extends Objects{
    
    public Block(int x, int y) {
        super(x, y);
        image=new Texture("data/block.png");
    }
}
