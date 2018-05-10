/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ost.game.modules;

import com.ost.game.GameModel;
import com.ost.game.snake.Segment;
import com.ost.modules.Module;

/**
 *
 * @author Dmitriy
 */
public class ModuleGameOver implements Module{

    GameModel gm;
    
    @Override
    public void load(GameModel gm, Module batch) {
        this.gm = gm;
        this.gm.setModule(batch);
    }

    @Override
    public int run() {
        gm.setNavigate(Segment.Navigate.UP);
        return 0;
    }

    @Override
    public void unload() {
        
    }
    
}
