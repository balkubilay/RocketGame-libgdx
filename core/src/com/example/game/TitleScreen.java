package com.example.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class TitleScreen extends ScreenAdapter {
    RocketGameTest game;
    private Stage stage;
    Texture readypng;
    Texture background;

    int row_height = Gdx.graphics.getWidth() / 12;
    int col_width = Gdx.graphics.getWidth() / 12;

    public TitleScreen(RocketGameTest game) {
        this.game = game;
    }

    @Override
    public void show(){
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        readypng = new Texture("fbs-31.png");
        background = new Texture("background.png");
        int Help_Guides = 12;

        Skin mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        ImageButton button2 = new ImageButton(mySkin);
        button2.setSize(col_width*3,(row_height*1.5f));
        button2.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("fbs-13.png"))));
        button2.getStyle().imageUp.setMinHeight(row_height*2);
        button2.getStyle().imageUp.setMinWidth(col_width*4);
        button2.setPosition( Gdx.graphics.getWidth() * .35f, Gdx.graphics.getHeight() /2.3f);
        button2.addListener(new ClickListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                game.setScreen(new GameScreen(game));

            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        stage.addActor(button2);

//        Skin mySkin2 = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
//        ImageButton button3 = new ImageButton(mySkin2);
//        button3.setSize(col_width*3,(row_height*1.5f));
//        button3.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("fbs-14.png"))));
//        button3.getStyle().imageUp.setMinHeight(row_height*2);
//        button3.getStyle().imageUp.setMinWidth(col_width*4);
//        button3.setPosition(Gdx.graphics.getWidth() * .35f, Gdx.graphics.getHeight() /5);
//        button3.addListener(new ClickListener(){
//            @Override
//            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
//                super.touchUp(event, x, y, pointer, button);
//                game.setScreen(new HighScores(game));
//
//            }
//
//            @Override
//            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//                return super.touchDown(event, x, y, pointer, button);
//            }
//        });
//        stage.addActor(button3);


    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        game.batch.begin();

        game.batch.draw(readypng,Gdx.graphics.getWidth() * .35f, Gdx.graphics.getHeight() /1.5f,col_width*3,row_height*1.5f);

        game.batch.end();
    }

    @Override
    public void hide(){
        Gdx.input.setInputProcessor(null);
    }
}
