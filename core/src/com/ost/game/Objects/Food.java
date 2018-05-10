/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ost.game.Objects;

import com.badlogic.gdx.graphics.Texture;
import com.ost.game.effect.Effect;
import com.ost.game.effect.Flashing;
import com.ost.game.effect.SpeedDownEffect;
import com.ost.game.effect.SpeedUpEffect;
import com.ost.game.effect.TailDownEffect;
import com.ost.game.effect.TailUpEffect;

/** Еда
 * @author Dmitriy
 */
public class Food extends Objects{
    
    private Effect effect; 
    
    public Food(int x, int y, Effect effect) {
        super(x, y);
        this.effect = effect;
        if(effect instanceof SpeedUpEffect)
            this.image = new Texture("data/speedupfood.png");
        else if(effect instanceof SpeedDownEffect)
            this.image = new Texture("data/speeddownfood.png");
        else if(effect instanceof TailUpEffect)
            this.image = new Texture("data/tailupfood.png");
        else if(effect instanceof TailDownEffect)
            this.image = new Texture("data/taildownfood.png");
        else if(effect instanceof Flashing)
            this.image = new Texture("data/taildownfood.png");
    }
    
    public void activate(){
        effect.activate();
    }
    
    public Effect getEffect(){
        return effect;
    }
}
