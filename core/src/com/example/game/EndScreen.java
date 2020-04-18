package com.example.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import static com.example.game.GameScreen.score;
import static com.example.game.GameScreen.scoredEnemy;
import static com.example.game.GameScreen.velocity;

public class EndScreen extends ScreenAdapter {
    RocketGameTest game;
    private Stage stage;
    public EndScreen(RocketGameTest game) {
        this.game = game;
    }
    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        int Help_Guides = 12;
        int row_height = Gdx.graphics.getWidth() / 12;
        int col_width = Gdx.graphics.getWidth() / 12;
        Skin mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));



        Button button3 = new TextButton("Play Again",mySkin,"small");
        button3.setSize(col_width*4,row_height);
        button3.getStyle().up.setMinWidth(col_width*4);
        button3.setPosition(Gdx.graphics.getWidth() * .35f, Gdx.graphics.getHeight() /2);
        button3.addListener(new ClickListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                    velocity = 0;
                    scoredEnemy = 0;
                    score =0;
                    game.setScreen(new TitleScreen(game));
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return super.touchDown(event, x, y, pointer, button);
            }

        });
        stage.addActor(button3);

        game.font.getData().setScale(5);
        game.font.setColor(Color.WHITE);
        game.font.draw(game.batch, "Your Score is " + score, Gdx.graphics.getWidth() * .35f, Gdx.graphics.getHeight() /1.2f);

    }
    @Override
    public void render(float delta) {
        stage.act();
        stage.draw();
        game.batch.begin();



        game.batch.end();

    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
}
