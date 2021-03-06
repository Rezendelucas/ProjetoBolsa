package MyGdxGame.pack.LevelPack;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.List;

import java.util.ArrayList;

import MyGdxGame.pack.ModelsPack.AbstractGameObject;
import MyGdxGame.pack.ModelsPack.Assets;
import MyGdxGame.pack.ModelsPack.Ground;
import MyGdxGame.pack.ModelsPack.Player;
import MyGdxGame.pack.ModelsPack.Wall;
import MyGdxGame.pack.ScreensPack.GuiScreen;

import static MyGdxGame.pack.ScreensPack.GuiScreen.isStart;
import static MyGdxGame.pack.ScreensPack.GuiScreen.setStart;


/**
 * Created by LucasRezende on 08/08/2017.
 */

public class Level extends ScreenAdapter {

    private static final String TAG = Level.class.getName();

    private final Stage stage;
    private int[][] matriz;
    private java.util.List<Ground> tilesGround;
    private java.util.List<Wall> tilesWall;
    private float sizeX;
    private float sizeY;
    private int totalComandos = 0;
    private int comandosRealizados = 0;
    private AbstractGameObject obj;
    private Player player;
    private TextureAtlas atlas;

    public Level(){
        stage = new Stage();
        tilesGround = new ArrayList<Ground>();
        tilesWall = new ArrayList<Wall>();
        sizeX = 1f;
        sizeY = 1f;
        matriz = new int[][]{
                {5, 1, 1, 1, 1, 1, 1, 1, 1, 6},
                {2, 9, 0, 0, 0, 0, 0, 0, 0, 3},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                {7, 4, 4, 4, 4, 4, 4, 4, 4, 8},
        };
        initMap();
    }

    public void initMap() {
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                switch (matriz[x][y]) {
                    case 0:
                        obj = new Ground(Assets.instance.ground.ground_rock);
                        obj.position.set(x - 5 , y - 7);
                        obj.dimension.set(sizeX,sizeY);
                        tilesGround.add((Ground) obj);
                        break;
                    case 1:
                        obj = new Ground(Assets.instance.ground.tile_ground_rock);
                        obj.position.set(x - 5 , y - 7);
                        obj.dimension.set(sizeX,sizeY);
                        tilesGround.add((Ground) obj);
                        obj = new Wall(Assets.instance.wall.side_wall_rock);
                        obj.position.set(x - 5 , y - 7);
                        obj.dimension.set(sizeX,sizeY);
                        tilesWall.add((Wall) obj);
                        break;
                    case 2:
                        obj = new Ground(Assets.instance.ground.tile_ground_rock);
                        obj.position.set(x - 5 , y - 7);
                        obj.dimension.set(sizeX,sizeY);
                        tilesGround.add((Ground) obj);
                        obj = new Wall(Assets.instance.wall.side_wall_rock);
                        obj.position.set(x - 5 , y - 7);
                        obj.dimension.set(sizeX,sizeY);
                        obj.rotation = 90;
                        tilesWall.add((Wall) obj);
                        break;
                    case 3:
                        obj = new Ground(Assets.instance.ground.tile_ground_rock);
                        obj.position.set(x - 5 , y - 7);
                        obj.dimension.set(sizeX,sizeY);
                        tilesGround.add((Ground) obj);
                        obj = new Wall(Assets.instance.wall.up_wall_rock);
                        obj.position.set(x - 5 , y - 7);
                        obj.dimension.set(sizeX,sizeY);
                        tilesWall.add((Wall) obj);
                        break;
                    case 4:
                        obj = new Ground(Assets.instance.ground.tile_ground_rock);
                        obj.position.set(x - 5 , y - 7);
                        obj.dimension.set(sizeX,sizeY);
                        tilesGround.add((Ground) obj);
                        obj = new Wall(Assets.instance.wall.side_wall_rock);
                        obj.position.set(x - 5 , y - 7);
                        obj.dimension.set(sizeX,sizeY);
                        obj.rotation = 180;
                        tilesWall.add((Wall) obj);
                        break;
                    case 5:
                        obj = new Ground(Assets.instance.ground.tile_ground_rock);
                        obj.position.set(x - 5 , y - 7);
                        obj.dimension.set(sizeX,sizeY);
                        tilesGround.add((Ground) obj);
                        obj = new Wall(Assets.instance.wall.cornerLB_wall_rock);
                        obj.position.set(x - 5 , y - 7);
                        obj.dimension.set(sizeX,sizeY);
                        tilesWall.add((Wall) obj);
                        break;
                    case 6:
                        obj = new Ground(Assets.instance.ground.tile_ground_rock);
                        obj.position.set(x - 5 , y - 7);
                        obj.dimension.set(sizeX,sizeY);
                        tilesGround.add((Ground) obj);
                        obj = new Wall(Assets.instance.wall.cornerLT_wall_rock);
                        obj.position.set(x - 5 , y - 7);
                        obj.dimension.set(sizeX,sizeY);
                        tilesWall.add((Wall) obj);
                        break;
                    case 7:
                        obj = new Ground(Assets.instance.ground.tile_ground_rock);
                        obj.position.set(x - 5 , y - 7);
                        obj.dimension.set(sizeX,sizeY);
                        tilesGround.add((Ground) obj);
                        obj = new Wall(Assets.instance.wall.cornerRB_wall_rock);
                        obj.position.set(x - 5 , y - 7);
                        obj.dimension.set(sizeX,sizeY);
                        tilesWall.add((Wall) obj);
                        break;
                    case 8:
                        obj = new Ground(Assets.instance.ground.tile_ground_rock);
                        obj.position.set(x - 5 , y - 7);
                        obj.dimension.set(sizeX,sizeY);
                        tilesGround.add((Ground) obj);
                        obj = new Wall(Assets.instance.wall.cornerRT_wall_rock);
                        obj.position.set(x - 5 , y - 7);
                        obj.dimension.set(sizeX,sizeY);
                        tilesWall.add((Wall) obj);
                        break;
                    case 9:
                        obj = new Ground(Assets.instance.ground.ground_rock);
                        obj.position.set(x - 5 , y - 7);
                        obj.dimension.set(sizeX,sizeY);
                        tilesGround.add((Ground) obj);
                        obj = new Player();
                        obj.position.set(x-5,y-7);
                        player = (Player) obj;
                        break;
                    default:
                        //nothing
                        break;
                }

            }
        }

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    public void update (float deltaTime) {
        List<Object> list;
            try {
                list = GuiScreen.pullComands();
                totalComandos = list.getItems().size;
                if(isStart()) {
                //for (int i = 0; i < list.getItems().size; i++) {
                    int wait = 50000;
                    while (wait > 0) {
                        if (wait == 50000) {
                            player.comandos(player.Parse.get(list.getItems().get(comandosRealizados)));
                            comandosRealizados++;
                            //list.getItems().removeIndex(1);
                            wait--;
                            //render(deltaTime,batch);
                        }else{
                            System.out.print("Em espera! \n");
                            wait--;
                        }
                    }
                    if(comandosRealizados == totalComandos){
                        setStart();
                        comandosRealizados = 0;
                        System.out.print("Final de comando \n");
                    }
                }
            }catch (Exception e){
                System.out.print("Erro ao capturar fila de comandos \n");
            }
        }

    public void render(SpriteBatch batch) {
        renderMap(batch);
        player.render(batch);
    }

    public void renderMap(SpriteBatch batch) {
        for(Ground m: tilesGround)
            m.render(batch);
        for(Wall w: tilesWall)
            w.render(batch);
        }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
