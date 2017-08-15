package MyGdxGame.pack;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;

import MyGdxGame.pack.LevelPack.Level;
import MyGdxGame.pack.ScreensPack.MenuScreen;


/**
 * Created by LucasRezende on 03/08/2017.
 */

public class WorldController extends InputAdapter {

    private static final String TAG = WorldController.class.getName();

    public World b2world;
    public OrthographicCamera camera;
    public Level currenteLevel;
    public int score;
    public Game game;
    public boolean isCameraMode = false;


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
        initPhysics();
        //camera.setTarget(level.whiteBall.body);
    }

    ////////////////////////////////////////inicia a fisica////////////////////////////////////////////////

    private void initPhysics () {
        if (b2world != null) b2world.dispose();
        b2world = new World(new Vector2(0, 0), true);//-9.81f gravidade padrao
        Vector2 origin = new Vector2();
    }


    ////////////////////////////////////// Update //////////////////////////////////////////////

    public void update(float delta){
        // handleDebugInput(delta);//Funçao de controle do cena para debuger
        handleInputGame(delta);
        //b2world.step(delta,8,3);
        camera.update();
    }

    //////////////////////////////////////  Classes de debug  /////////////////////////////////////

    private void handleInputGame (float deltaTime) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            if(!isCameraMode) {
                isCameraMode = true;
                Gdx.app.log(TAG, "Modo Camera 'ON'");
            }else{
                isCameraMode = false;
                Gdx.app.log(TAG, "Modo Camera 'OFF'");
            }
        }
        if(!isCameraMode) {
            // movimento
            if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
                //movimentaçao para esquerda
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
                //movimentaçao para direita
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
                //movimentaçao para cima
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
                //movimentaçao para baixo
            }
        }else{
            moveCamera();
        }
    }

    //////////////////////////////////////  Funções de controle  /////////////////////////////////////

    private void backToMenu () {
        // retornar para o menu screen
        game.setScreen(new MenuScreen(game));
    }

    private void  moveCamera(){
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            camera.translate(-100, 0);
            Gdx.app.log(TAG, "Camera: Press Esquerda");
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            camera.translate(100, 0);
            Gdx.app.log(TAG, "Camera: Press Direita");
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            camera.translate(0, 100);
            Gdx.app.log(TAG, "Camera: Press Alto");
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            camera.translate(0, -100);
            Gdx.app.log(TAG, "Camera: Press Baixo");
        }
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

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 ponto = new Vector3(screenX, screenY, 0);
        //.camera.unproject(ponto);

        Gdx.app.debug(TAG,"Toque Tela :" + ponto.x + "/" + ponto.y  );
        Gdx.app.debug(TAG,"Centro Camera : " + camera.position.x + " / " + camera.position.y);
        Gdx.app.debug(TAG,"Toque gambs: clique em " + (ponto.x-400) + " / " + (ponto.y-230));
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return super.touchUp(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return super.touchDragged(screenX, screenY, pointer);
    }

}

