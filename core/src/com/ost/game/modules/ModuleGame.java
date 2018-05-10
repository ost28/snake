/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ost.game.modules;

import com.ost.game.GameModel;
import com.ost.game.Objects.Food;
import com.ost.game.snake.Segment.Navigate;
import com.ost.modules.Module;
import java.util.Random;

/**
 *
 * @author Dmitriy
 */
public class ModuleGame implements Module{

    GameModel gm;
    
    @Override
    public void load(GameModel gm, Module batch) {
        if(gm == null || batch==null){
            throw new NullPointerException(); 
        }
        this.gm = gm;
        this.gm.setModule(batch);
    }

    @Override
    public int run() {
        
        for(Food f : gm.getFood()){
            if(Math.abs(f.getPosition().x-gm.getSnakePosition().x)<23){
                if(f.getPosition().y-gm.getSnakePosition().y>0 && gm.checkNavigate(Navigate.UP)){
                    gm.setNavigate(Navigate.UP);
                }
                else if(f.getPosition().y-gm.getSnakePosition().y<0 && gm.checkNavigate(Navigate.DOWN)){
                    gm.setNavigate(Navigate.DOWN);
                }
                break;
            }
            else if(Math.abs(f.getPosition().y-gm.getSnakePosition().y)<23){
                if(f.getPosition().x-gm.getSnakePosition().x>0 && gm.checkNavigate(Navigate.RIGHT)){
                    gm.setNavigate(Navigate.RIGHT);
                }
                else if(f.getPosition().x-gm.getSnakePosition().x<0 && gm.checkNavigate(Navigate.LEFT)){
                    gm.setNavigate(Navigate.LEFT);
                }
                break;
            }
            else if(f==gm.getFood().get(3)){
                Random r = new Random();
                int a = r.nextInt(40);
                switch(a){
                    case 0:
                        if(gm.checkNavigate(Navigate.UP))
                            gm.setNavigate(Navigate.UP);
                        break;
                    case 1:
                        if(gm.checkNavigate(Navigate.DOWN))
                            gm.setNavigate(Navigate.DOWN);
                        break;
                    case 2:
                        if(gm.checkNavigate(Navigate.LEFT))
                            gm.setNavigate(Navigate.LEFT);
                        break;
                    case 3:
                        if(gm.checkNavigate(Navigate.RIGHT))
                            gm.setNavigate(Navigate.RIGHT);
                        break;
                }
            }
        }
        checkAndSetNavigate();
        return 0;
    }
    
    public void checkAndSetNavigate(){
        if(!gm.checkNavigate(gm.getSnakeNextNavigate())){
            gm.setNavigate(gm.getSnakeNavigate());
        }
        if(!gm.checkNavigate(gm.getSnakeNavigate())){
            if(gm.checkNavigate(Navigate.DOWN) && gm.getSnakeNavigate()!=Navigate.UP)
                gm.setNavigate(Navigate.DOWN);
            else if(gm.checkNavigate(Navigate.UP) && gm.getSnakeNavigate()!=Navigate.DOWN)
                gm.setNavigate(Navigate.UP);
            else if(gm.checkNavigate(Navigate.LEFT) && gm.getSnakeNavigate()!=Navigate.RIGHT)
                gm.setNavigate(Navigate.LEFT);
            else if(gm.checkNavigate(Navigate.RIGHT) && gm.getSnakeNavigate()!=Navigate.LEFT)
                gm.setNavigate(Navigate.RIGHT);
        }
    }
    
    @Override
    public void unload() {
        System.out.println("unload");
    }
   
}
