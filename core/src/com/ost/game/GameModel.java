package com.ost.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.ost.game.Objects.Areas;
import com.ost.game.Objects.Block;
import com.ost.game.Objects.Food;
import com.ost.game.snake.Segment;
import com.ost.game.snake.Snake;
import com.ost.modules.Module;
import com.ost.modules.ModuleEngine;
import java.util.ArrayList;
/** Игровая модель
 * @author Dmitriy
 */
public class GameModel extends ApplicationAdapter implements IGameModel{
    /** Контейнер для объектов на экране */
    private SpriteBatch batch;
    /** Игровое поле */
    public Field field;
    public static int eat = 0;
    public static int score = 0;
    /** Время с прошлого нажатия паузы */
    private long pauseTime;
    /** Время между нажатиями пауз */
    private static final long pauseDeltaTime = 300;
    /** Шрифт */
    private BitmapFont infoFont;
    /** Текущее состояние игры */
    public State State;

    public Module mg;
    /** Создает элементы игры
     */
    
    public void create() {
        
        field = new Field();
        batch = new SpriteBatch();
        
        infoFont = new BitmapFont();
        infoFont.setColor(Color.BLACK);
        infoFont.getData().setScale(2, 2);
        
        pauseTime=0;
        
        State = State.PLAY;
        
        Gdx.gl.glClearColor(1,1,1,1);
        mg = null;
    }
    
    public void setModule(Module module){
        mg = module;
    }
    /** Обновляет экран игры
     */
    @Override
    public void render() {
        
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(isPlay())
            update();
        batch.begin();
        switch (State){
            case PLAY:
                if(Gdx.input.isKeyPressed(Keys.B)){
                    String[] arr = new String[1];
                    arr[0] = "..\\build\\classes\\main\\com\\ost\\game\\modules\\";
                    ModuleEngine.main(arr,this);
                }
                if(mg!=null)
                    mg.run();
                batch.draw(field.background,0,0,field.background.getWidth(),field.background.getHeight());
                field.render(batch);
                break;
            case PAUSE:
                batch.draw(field.background,0,0,field.background.getWidth(),field.background.getHeight());
                infoFont.draw(batch, "GAME PAUSED\nYour score: "+score, 250, (float) 400);
                break;
            case GAMEOVER:
                batch.draw(field.background,0,0,field.background.getWidth(),field.background.getHeight());
                infoFont.draw(batch, "GAME OVER\nYour score: "+score+"\nEat "+eat+" balls"+"\n\nPRESS ENTER", 250, (float) 400);
                if(Gdx.input.isKeyPressed(Keys.ENTER)){
                    State=State.PLAY;
                    score=0;
                    eat=0;
                    mg = null;
                }
                break;
        }
        batch.end();
    }
    /** Проверяет, идет ли игра
     * @return true - идет игра, false - не идет
     */
    public boolean isPlay(){
        if(Gdx.input.isKeyPressed(Keys.SPACE) && TimeUtils.nanosToMillis(TimeUtils.nanoTime()) - this.pauseTime > GameModel.pauseDeltaTime){
            if(State==State.PLAY) pause();
            else if(State==State.PAUSE) play();
        }
        return State==State.PLAY;
    }
    /** Устанавливает состояние паузы
     */
    @Override
    public void pause(){
        State=State.PAUSE;
        pauseTime = TimeUtils.nanosToMillis(TimeUtils.nanoTime());
    }
    /** Устанавливает состояние игры
     */
    public void play(){
        State=State.PLAY;
        pauseTime = TimeUtils.nanosToMillis(TimeUtils.nanoTime());
    }
    /** Проверка столкновений объектов на поле
     */
    public void update(){
        field.update(Gdx.graphics.getDeltaTime());
        if(field.collisionTail(field.getSnake().getHead().form)==true || field.collisionBlocks(field.getSnake().getHead().form)==true){
            field.dispose();
            field = new Field();
            Snake.setVelocity(1);
            State=State.GAMEOVER;
        }
        int incBoost=field.collisionAreas();
        if(incBoost!=0 && Areas.snakeOnArea==false){
            Areas.snakeOnArea=true;
            Snake.setBoost(Snake.getBoost()+incBoost);}
        else if(Areas.snakeOnArea==true){
            Areas.snakeOnArea=false;
            Snake.setBoost(0);
        }
        field.collisionFood();    
    }
    
    @Override
    public void dispose() {
        super.dispose();
    }   
    
    @Override
    public void setNavigate(Segment.Navigate nav) {
        field.getSnake().setNextNavigate(nav);
    }

    @Override
    public boolean checkNavigate(Segment.Navigate nav) {
        return field.checkNavigate(nav);
    }

    @Override
    public Vector2 getSnakePosition() {
        return field.getSnake().getHead().getPosition();
    }

    @Override
    public ArrayList<Food> getFood() {
        return field.food;
    }

    @Override
    public ArrayList<Block> getBlocks() {
        return field.blocks;
    }

    @Override
    public Segment.Navigate getSnakeNavigate() {
        return field.getSnake().getNavigate();
    }

    @Override
    public Segment.Navigate getSnakeNextNavigate() {
        return field.getSnake().getNextNavigate();
    }
    /** Состояния игры */
    public enum State{PLAY, PAUSE, GAMEOVER};
    
}
