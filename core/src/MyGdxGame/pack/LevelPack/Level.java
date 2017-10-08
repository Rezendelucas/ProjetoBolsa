package MyGdxGame.pack.LevelPack;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import MyGdxGame.pack.ModelsPack.AbstractGameObject;
import MyGdxGame.pack.ModelsPack.Player;
import MyGdxGame.pack.ScreensPack.GuiScreen;


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
    private GuiScreen gui;
    private boolean laço = true;



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


    public void render(SpriteBatch batch) {
        //stage.act(delta);
        //stage.draw();
        renderMap(batch);
        player.render(batch);
    }

    public void update (float deltaTime) {
        List<Object> list;
            try {
                list = GuiScreen.pullComands();
                if(list.getItems().size <= 1){laço = false;}
                if(laço) {
                //for (int i = 0; i < list.getItems().size; i++) {
                    int wait = 50000;
                    while (wait > 0) {
                        if (wait == 50000) {
                            player.comandos(player.Parse.get(list.getItems().get(1)));
                            list.getItems().removeIndex(1);
                            wait--;
                            //render(deltaTime,batch);
                        }else{
                            System.out.print("Em espera! \n");
                            wait--;
                        }
                    }
                    System.out.print("Final de comando \n");
                }
            }catch (Exception e){
                System.out.print("Erro ao capturar lista de comandos \n");
            }
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
