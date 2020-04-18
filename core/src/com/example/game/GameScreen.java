package com.example.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class GameScreen extends ScreenAdapter {
    RocketGameTest game;
    Texture background;
    Texture rocket;
    Texture ufo;
    Texture ufo2;
    Texture ufo3;
    float rocketX;
    float rocketY;
    int gameState = 0;
    public static float velocity = 0 ;
    float gravity = 0.4f;
    float enemyVelocity = 6;
    Random random;
    public static int score = 0;
    public static int scoredEnemy = 0;
    BitmapFont font;
    BitmapFont font2;

    Circle rocketCircle;

    int numberOfEnemies = 4;
    float [] enemyX = new float[numberOfEnemies];
    float [] enemyOffSet = new float[numberOfEnemies];
    float [] enemyOffSet2 = new float[numberOfEnemies];
    float [] enemyOffSet3 = new float[numberOfEnemies];
    float distance = 0;
    Circle[] enemyCircle;
    Circle[] enemyCircle2;
    Circle[] enemyCircle3;

    public GameScreen(RocketGameTest game) {
        this.game = game;
    }
    @Override
    public void show() {
        background = new Texture("background.png");
        rocket = new Texture("rocket.png");
        ufo = new Texture("ufo.png");
        ufo2 = new Texture("ufo.png");
        ufo3 = new Texture("ufo.png");

        distance = Gdx.graphics.getWidth()/4;
        random = new Random();


        rocketX = Gdx.graphics.getWidth() / 3 - rocket.getHeight() / 2;
        rocketY = Gdx.graphics.getHeight() / 2;

        rocketCircle = new Circle();
        enemyCircle = new Circle[numberOfEnemies];
        enemyCircle2= new Circle[numberOfEnemies];
        enemyCircle3 = new Circle[numberOfEnemies];

        font=new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(4);

        font2 = new BitmapFont();
        font2.setColor(Color.WHITE);
        font2.getData().setScale(7);

        for (int i = 0; i<numberOfEnemies; i++){


            enemyOffSet[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight()-200);
            enemyOffSet2[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight()-200);
            enemyOffSet3[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight()-200);
            enemyX[i] = Gdx.graphics.getWidth() - ufo.getWidth()/2 + i*distance;

            enemyCircle[i] = new Circle();
            enemyCircle2[i] = new Circle();
            enemyCircle3[i] = new Circle();
        }

    }

    @Override
    public void render(float delta) {
        game.batch.begin();
        game.batch.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        if (gameState == 1){

            if (enemyX[scoredEnemy] < Gdx.graphics.getWidth() / 3 - rocket.getHeight() / 2){
                score++;
                if (scoredEnemy<numberOfEnemies-1){
                    scoredEnemy++;

                }else {
                    scoredEnemy = 0;
                }
            }

            if (Gdx.input.justTouched()){
                velocity = -7; }


            for (int i = 0; i<numberOfEnemies; i++){
                if (enemyX[i] < Gdx.graphics.getWidth()/15){
                    enemyX[i] = enemyX[i] + numberOfEnemies * distance;

                    enemyOffSet[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight()-200);
                    enemyOffSet2[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight()-200);
                    enemyOffSet3[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight()-200);
                }else {
                    enemyX[i] = enemyX[i] - enemyVelocity;
                }
                game.batch.draw(ufo,enemyX[i],Gdx.graphics.getHeight() / 2 + enemyOffSet[i],Gdx.graphics.getWidth()/14,Gdx.graphics.getHeight()/10);
                game.batch.draw(ufo2,enemyX[i],Gdx.graphics.getHeight()/ 2 + enemyOffSet2[i],Gdx.graphics.getWidth()/14,Gdx.graphics.getHeight()/10);
                game.batch.draw(ufo3,enemyX[i],Gdx.graphics.getHeight() / 2 + enemyOffSet3[i],Gdx.graphics.getWidth()/14,Gdx.graphics.getHeight()/10);

                enemyCircle[i] = new Circle(enemyX[i] + Gdx.graphics.getWidth()/28,Gdx.graphics.getHeight() / 2 + enemyOffSet[i] +Gdx.graphics.getHeight() / 20, Gdx.graphics.getWidth()/28);
                enemyCircle2[i] = new Circle(enemyX[i] + Gdx.graphics.getWidth()/28,Gdx.graphics.getHeight() / 2 + enemyOffSet2[i] +Gdx.graphics.getHeight() / 20, Gdx.graphics.getWidth()/28);
                enemyCircle3[i] = new Circle(enemyX[i] + Gdx.graphics.getWidth()/28,Gdx.graphics.getHeight() / 2 + enemyOffSet3[i] +Gdx.graphics.getHeight() / 20, Gdx.graphics.getWidth()/28);


            }

            if (rocketY > 0 ){
                velocity = velocity + gravity;
                rocketY = rocketY - velocity;
            }else {
                gameState = 2;
            }


        }else if (gameState == 0){
            if (Gdx.input.justTouched()){
                gameState = 1;}
        }else if (gameState == 2){
            game.setScreen(new EndScreen(game));

//            font.draw(game.batch,"Your Score is: " +  score,100,Gdx.graphics.getHeight()/3);
//            font2.draw(game.batch,"Game Over... Tap To Play Again",100,Gdx.graphics.getHeight()/2 );
//            if (Gdx.input.justTouched()){
//                velocity = 0;
//                scoredEnemy = 0;
//                score =0;
//
//                gameState = 1;}
//
//            rocketY = Gdx.graphics.getHeight() / 2;
//
//            for (int i = 0; i<numberOfEnemies; i++){
//
//
//                enemyOffSet[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight()-200);
//                enemyOffSet2[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight()-200);
//                enemyOffSet3[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight()-200);
//                enemyX[i] = Gdx.graphics.getWidth() - ufo.getWidth()/2 + i*distance;
//
//                enemyCircle[i] = new Circle();
//                enemyCircle2[i] = new Circle();
//                enemyCircle3[i] = new Circle();
//            }


        }


        game.batch.draw(rocket,rocketX,rocketY,Gdx.graphics.getWidth()/14,Gdx.graphics.getHeight()/10);
        font.draw(game.batch,String.valueOf(score),100,200);
        game.batch.end();

        rocketCircle.set(rocketX+Gdx.graphics.getWidth()/28,rocketY+Gdx.graphics.getHeight()/20,Gdx.graphics.getWidth()/28);


        for (int i = 0; i<numberOfEnemies; i++){

            if (Intersector.overlaps(rocketCircle,enemyCircle[i]) || Intersector.overlaps(rocketCircle,enemyCircle2[i]) || Intersector.overlaps(rocketCircle,enemyCircle3[i])){
                gameState = 2;
            }
        }

    }
}
