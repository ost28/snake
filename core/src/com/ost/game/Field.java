/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ost.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.ost.game.Objects.Block;
import com.ost.game.Objects.Food;
import com.ost.game.Objects.Area;
import com.ost.game.Objects.Areas;
import com.ost.game.Objects.Objects;
import com.ost.game.effect.Flashing;
import com.ost.game.effect.SpeedDownEffect;
import com.ost.game.effect.SpeedUpEffect;
import com.ost.game.effect.TailDownEffect;
import com.ost.game.effect.TailUpEffect;
import com.ost.game.snake.Head;
import com.ost.game.snake.Segment;
import com.ost.game.snake.Segment.Navigate;
import com.ost.game.snake.Snake;
import com.ost.modules.Module;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/** Игровое поле
 * @author Dmitriy
 */
public class Field implements IField{
    
    /** Ширина экрана */
    public static final int WIDTH = 960;
    /** Высота экрана */
    public static final int HEIGHT = 624;
    /** Фон */
    public Texture background;
    /** Змейка */
    private Snake snake;
    /** Блоки */
    public ArrayList<Block> blocks;
    /** Еда */
    public ArrayList<Food> food;
    /** Области */
    public ArrayList<Areas> areas;
    /** Создает экземпляр поля
     */
    public Field(){
        this.background = new Texture("data/wb.jpg");
        blocks = new ArrayList<Block>();
        areas = new ArrayList<Areas>();
        food = new ArrayList<Food>();
        generateSnake(1,6);
        generateField();
    }
    
    private void generateSnake(int velocity,int length){
        snake = new Snake(new Head(WIDTH/2,HEIGHT/2,Navigate.RIGHT),velocity,length);
    }
    
    private void generateField(){
        generateBlocks();
        generateAreas();
        generateFood();
    }
    /** Генерирует блоки на поле
     */
    private void generateBlocks() {
        Block o;
        int y=168;
        int x=240;
        for(int i=0;i<44;i++){
            if(i==22){
                y=432;
                x=240;
            }
            o=new Block(x,y);
            blocks.add(o);
            x+=24;
        }
    }
    /** Генерирует области на поле
     */
    private void generateAreas() {
        ArrayList<Area> area = new ArrayList<Area>();
        int y=192;
        int x=24;
        int n=10;
        for(int i=0;i<5;i++){
            for(int j=0; j<n; j++){
                area.add(new Area(x,y,true));
                y+=24;
            }
            x+=24;
            y-=(n-1)*24;
            n-=2;
        }
        Areas a = new Areas(area,5);
        areas.add(a);
        
        area = new ArrayList<Area>();
        y=192;
        x=912;
        n=10;
        for(int i=0;i<5;i++){
            for(int j=0; j<n; j++){
                area.add(new Area(x,y,false));
                y+=24;
            }
            x-=24;
            y-=(n-1)*24;
            n-=2;
        }
        a = new Areas(area,-3);
        areas.add(a);
    }
    /** Генерирует еду на поле
     */
    private void generateFood() {
        Vector2 v = generatePosition();
        food.add(new Food((int)v.x,(int)v.y,new TailDownEffect(snake,4)));
        v = generatePosition();
        food.add(new Food((int)v.x,(int)v.y,new SpeedUpEffect(snake,10000)));
        v = generatePosition();
        food.add(new Food((int)v.x,(int)v.y,new TailUpEffect(snake,2)));
        v = generatePosition();
        food.add(new Food((int)v.x,(int)v.y,new SpeedDownEffect(snake,10000)));
        food.add(new Food(24,24,new Flashing(snake,2000)));
        flash();
    }
    /** Генерирует позицию на поле
     * @return - свободная позиция
     */
    public Vector2 generatePosition(){
        Vector2 position = new Vector2();
        Random rand = new Random();
        do{
            position.x = rand.nextInt(WIDTH/snake.getHead().getImage().getWidth())*snake.getHead().getImage().getWidth();
            position.y = rand.nextInt(HEIGHT/snake.getHead().getImage().getHeight())*snake.getHead().getImage().getWidth();
        }while(!checkPosition(position));
        
        return position;
    }
    /** Проверить, свободна ли позиция
     * @param position - проверяемая позиция
     */
    private boolean checkPosition(Vector2 position){
        boolean freePosition=true;
        for(Block block : blocks){
            if(block.getPosition().equals(position)){
                freePosition=false;
                break;
            }
        }
        for(Food f : food){
            if(f.getPosition().equals(position)){
                freePosition=false;
                break;
            }
        }
        return freePosition;
    }
    /** Обновление
     * @param dt - время между кадрами
     */
    public void update(float dt){ 
        snake.update();
    }
    
    public void render(SpriteBatch batch){
        for(Objects o : this.blocks)
            batch.draw(o.getImage(),o.getPosition().x,o.getPosition().y);
        for(Areas a : this.areas)
            a.render(batch);
        for(Food f : this.food){
            if(f.getEffect() instanceof Flashing && visible || !(f.getEffect() instanceof Flashing))
                batch.draw(f.getImage(), f.getPosition().x, f.getPosition().y);
        }
        Segment tail=this.getSnake().getHead();
        while(tail!=null){
            batch.draw(tail.getImage(), tail.getPosition().x, tail.getPosition().y);
            tail=tail.tail;
        }
    }
    /** Очистка поля
     */
    public void dispose(){
        background.dispose();
    }
    /** Возвращает змейку
     * @return змейка
     */
    public Snake getSnake(){
        return snake;
    }

    @Override
    public boolean checkNavigate(Navigate nav) {
        Rectangle rect = null;
        int posX = (int) snake.getHead().getPosition().x;
        int posY = (int) snake.getHead().getPosition().y;
        //Создать квадрат по следующему направлению змейки
        if(nav==Navigate.UP){
            rect = new Rectangle(posX+4,posY+24+4,16,16);}
        else if(nav==Navigate.DOWN){
            rect = new Rectangle(posX+4,posY-24+4,16,16);}
        else if(nav==Navigate.LEFT){
            rect = new Rectangle(posX-24+4,posY+4,16,16);}
        else if(nav==Navigate.RIGHT){
            rect = new Rectangle(posX+24+4,posY+4,16,16);}
        if(posX>WIDTH)
            posX-=WIDTH;
        else if(posX<0)
            posX+=WIDTH;
        if(posY>HEIGHT)
            posY-=HEIGHT;
        else if(posY<0)
            posY+=HEIGHT;
        //Проверить коллизию со следующей позицией
        if(!collisionTail(rect) && !collisionBlocks(rect)){
            return true;
        }
        
        return false;
    }
    
    /** Проверка столкновений с хвостом
     */
    public boolean collisionTail(Rectangle form){
        Segment t;
        if(getSnake().getLength()<5)
            return false;
        else
            t = getSnake().getHead().tail.tail.tail;
        while(t!=null){
            if(Intersector.overlaps(form, t.form)){
                return true;
            }
            t=t.tail;
        }
        return false;
    }
    
    /** Проверка столкновений с блоками
     */
    public boolean collisionBlocks(Rectangle form){
        for(Objects o : blocks){
            if(Intersector.overlaps(o.getForm(),form)){
                return true;
            }
        }
        return false;
    }
    public int collisionAreas(){
        Segment head = getSnake().getHead();
        for(Areas o : areas){
            if(o.collisionArea(head.form)==true){
                return o.getBoost();
            }
        }
        return 0;
    }
    /** Проверка столкновений с блоками
     */
    public void collisionFood(){
        Head head = getSnake().getHead();
        
        for(Food f : food){
            if(f.getEffect() instanceof Flashing && visible || !(f.getEffect() instanceof Flashing)){
                if(Intersector.overlaps(head.form,f.getForm())){
                    head.setTail();
                    GameModel.eat++;
                    GameModel.score+=Snake.getVelocity()/10.0*snake.getLength();
                    if(GameModel.eat!=0 && GameModel.eat%10==0)
                        Snake.setVelocity(Snake.getVelocity()+1);
                    f.activate();
                    Vector2 v = generatePosition();
                    switch (food.indexOf(f)) {
                        case 0:
                            food.set(0,new Food((int)v.x,(int)v.y,new TailDownEffect(snake,4)));
                            break;
                        case 1:
                            food.set(1,new Food((int)v.x,(int)v.y,new SpeedUpEffect(snake,10000)));
                            break;
                        case 2:
                            food.set(2,new Food((int)v.x,(int)v.y,new TailUpEffect(snake,2)));
                            break;
                        case 3:
                            food.set(3,new Food((int)v.x,(int)v.y,new SpeedDownEffect(snake,10000)));
                            break;
                        case 4:
                            food.set(4,new Food(24,24,new Flashing(snake,5000)));
                            visible = false;
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }
    
    boolean visible;
    
    private void flash(){
        Timer t = new Timer();
        TimerTask mtt = new TimerTask() {
            @Override
            public void run() {
                for(Food f : food){
                    if(f.getEffect() instanceof Flashing){
                        if(visible){
                            visible = false;
                        }
                        else{
                            visible = true;
                        }
                    }
                }
            }
        };
        t.schedule(mtt, Flashing.timeFlashing,Flashing.timeFlashing);
    }
}
