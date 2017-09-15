package MyGdxGame.pack.LevelPack;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import MyGdxGame.pack.ModelsPack.AbstractGameObject;
import MyGdxGame.pack.ModelsPack.Player;


/**
 * Created by LucasRezende on 08/08/2017.
 */

public class Level extends ScreenAdapter {

    private static final String TAG = Level.class.getName();

    private final Stage stage;
    private Texture texture;
    private final Sprite[][] sprites;
    private float sizeX;
    private float sizeY;
    private AbstractGameObject obj;
    private Player player;

    public Level(){
        stage = new Stage();
        sprites = new Sprite[10][10];
        sizeX = 1f;
        sizeY = 1f;
        init();
    }

    public void init(){
        texture = new Texture(Gdx.files.internal("tile.jpg"));
        for (int z = 0; z < 10; z++) {
            for (int x = 0; x < 10; x++) {
                sprites[x][z] = new Sprite(texture);
                sprites[x][z].setPosition(x - 5 , z - 7 );
                sprites[x][z].setSize(sizeX, sizeY);
                if(x == 0 & z == 0){
                    obj = new Player();
                    obj.position.set(x-5,z-7);
                    player = (Player) obj;
                }
            }
        }
        //stage.addActor();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    //@Override
    public void render(float delta, SpriteBatch batch) {
        //stage.act(delta);
        //stage.draw();
        renderMap(batch);
        player.render(batch);
    }
    public void update (float deltaTime) {
        player.update(deltaTime);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    public void renderMap(SpriteBatch batch) {
        for(int z = 0; z < 10; z++) {
            for(int x = 0; x < 10; x++) {
                sprites[x][z].draw(batch);
            }
        }
    }
}
