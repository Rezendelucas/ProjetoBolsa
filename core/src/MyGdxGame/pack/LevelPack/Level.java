package MyGdxGame.pack.LevelPack;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.backends.gwt.preloader.Preloader;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

import MyGdxGame.pack.GUIPack.Assets;
import MyGdxGame.pack.UtilsPack.Constants;


/**
 * Created by LucasRezende on 08/08/2017.
 */

public class Level extends ScreenAdapter {
    private static final String TAG = Level.class.getName();


    private Stage stage = new Stage();
    public final List<String> inventory;
    public final List<String> sell;
    public final Table table;
    public final Skin skin;

    public Level(){ //resolver o problema da skin
        skin = Assets.manager.get(Assets.uiskin, Skin.class);
        //skin = new Skin(Gdx.files.internal(Constants.uiskin), new TextureAtlas(Constants.TEXTURE_ATLAS_UI));
        //new Skin(Gdx.files.internal(Constants.SKIN_GAME_UI),new TextureAtlas(Constants.TEXTURE_ATLAS_UI));
        //Assets.manager.get(Assets.uiskin, Skin.class);
        stage.setDebugAll(true);

        inventory = new List<String>(skin);
        sell = new List<String>(skin);
        inventory.setItems("Axe", "Fuel", "Helmet", "Flux Capacitor", "Shoes", "Hammer", "Trash Can", "The Hitchhiker's Guide To The Galaxy", "Cucumber");

        table = new Table(skin);
        table.setFillParent(true);
        stage.addActor(table);

        table.defaults();
        table.add("Inventory");
        table.add("Merchant").row();
        table.add(inventory).expand().fill();
        table.add(sell).expand().fill();

        /////////mecanismo de arrastar e soltar/////////

        DragAndDrop dnd = new DragAndDrop(); //cria o ator de arrastar
        dnd.addSource(new DragAndDrop.Source(inventory) {  //a origem de onde e possivel arrastar
            final DragAndDrop.Payload payload = new DragAndDrop.Payload();  // cria o objeto carga para conter o item arrastado
            @Override
            public DragAndDrop.Payload dragStart(InputEvent event, float x, float y, int pointer) {
                String item = inventory.getSelected(); // pega o item escolhido do inventario
                payload.setObject(item);  //   add no objeto de carga
                inventory.getItems().removeIndex(inventory.getSelectedIndex());  // remove o objeto da lista de inventario, isto e desnecessario para nosso projeto
                payload.setDragActor(new Label(item, skin));
                payload.setInvalidDragActor(new Label(item + " (\"No thanks!\")", skin));
                payload.setValidDragActor(new Label(item + " (\"I'll buy this!\")", skin));
                return payload;
            }

            @Override
            public void dragStop(InputEvent event, float x, float y, int pointer, DragAndDrop.Payload payload, DragAndDrop.Target target) {
                if(target == null)
                    inventory.getItems().add((String) payload.getObject()); // caso o objeto seja solto em area invalida retorna para lista de inventario
            }
        });
        //criaçao de um alvo para o ator de arrastar
        dnd.addTarget(new DragAndDrop.Target(sell) {
            @Override
            public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                return !"Cucumber".equals(payload.getObject());
            }

            @Override
            public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                sell.getItems().add((String) payload.getObject());
            }
        });
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}



