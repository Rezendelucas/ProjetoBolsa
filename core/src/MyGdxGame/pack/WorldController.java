package MyGdxGame.pack;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import MyGdxGame.pack.LevelPack.Level;
import MyGdxGame.pack.ScreensPack.MenuScreen;


/**
 * Created by LucasRezende on 03/08/2017.
 */

public class WorldController extends InputAdapter {

    private static final String TAG = WorldController.class.getName();

    public OrthographicCamera camera;
    public Level currenteLevel;
    public int score;
    public Game game;


    public WorldController(Game game){
        this.game = game;
        init();
    }

    private void init(){
        Gdx.input.setInputProcessor(this);
        camera = new OrthographicCamera();
        initLevel();
    }

    private void initLevel(){
        score = 0;
        currenteLevel = new Level();
    }

    ////////////////////////////////////// Update //////////////////////////////////////////////

    public void update(float delta){
        currenteLevel.update(delta);
        camera.update();
    }

    //////////////////////////////////////  Classes de debug  /////////////////////////////////////

    private void backToMenu () {
        // retornar para o menu screen
        game.setScreen(new MenuScreen(game));
    }

    public boolean keyUp(int keycode){//reseta o world
        if(keycode == Input.Keys.R){
            init();
            Gdx.app.debug(TAG,"Game world resetado");
        }else if (keycode == Input.Keys.ESCAPE) {// back to menu
            backToMenu();
        }
        return false;
    }



}

