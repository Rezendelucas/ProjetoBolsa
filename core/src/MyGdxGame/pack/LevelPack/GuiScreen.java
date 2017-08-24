package MyGdxGame.pack.LevelPack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

import MyGdxGame.pack.UtilsPack.Constants;

public class GuiScreen extends ScreenAdapter {
    private static final String TAG = Level.class.getName();


    private Stage stage = new Stage();
    private final List<String> grimoire;
    private final List<String> spell;
    private final Table table;
    private final Skin skin;
    private final Label lblGrimoire;
    private final Label lblspell;

    public GuiScreen(){ //resolver o problema da skin
        skin = new Skin(Gdx.files.internal(Constants.UISKIN));
        stage.setDebugAll(true);
        Gdx.input.setInputProcessor(stage);

        grimoire = new List<String>(skin);
        grimoire.setItems("Fireball", "Magic Missile", "Great Shield", "Concentration", "Shadows Step", "God Hammer", "Azure Wrath", "Invisibility", "Touch of Death");
        spell = new List<String>(skin);
        spell.setItems("Begin");

        table = new Table(skin);
        table.setFillParent(true);
        table.left();
        stage.addActor(table);

        table.defaults();
        lblGrimoire = new Label("Grimorio", skin);
        lblspell = new Label("Spell",skin);

        table.add(lblGrimoire).width(150).left().fill();
        table.add(lblspell).width(150).right().fill().row();

        table.add(grimoire).width(150).left().expand().fill();
        table.add(spell).width(150).right().expand().fill();

        //TextButton btn = new TextButton("menu", skin);
        //btn.setPosition(100,100);
        //stage.addActor(btn);
        
        /////////mecanismo de arrastar e soltar/////////


        //-------------------Grimoire para spell-----------------------//

        DragAndDrop dnd = new DragAndDrop(); //cria o ator de arrastar
        dnd.addSource(new DragAndDrop.Source(grimoire) {  //a origem de onde e possivel arrastar
            final DragAndDrop.Payload payload = new DragAndDrop.Payload();  // cria o objeto carga para conter o item arrastado
            @Override
            public DragAndDrop.Payload dragStart(InputEvent event, float x, float y, int pointer) {
                String item = grimoire.getSelected(); // pega o item escolhido do inventario
                payload.setObject(item);  //   add no objeto de carga
                //grimoire.getItems().removeIndex(Grimoire.getSelectedIndex());  // remove o objeto da lista de inventario, isto e desnecessario para nosso projeto
                payload.setDragActor(new Label(item, skin));
                payload.setInvalidDragActor(new Label(item + " (\"voce nao tem mana suficiente!\")", skin));
                payload.setValidDragActor(new Label(item + " (\"Adcionar spell\")", skin));
                return payload;
            }

            @Override
            public void dragStop(InputEvent event, float x, float y, int pointer, DragAndDrop.Payload payload, DragAndDrop.Target target) {
                // if(target == null)
                //Grimoire.getItems().add((String) payload.getObject()); // caso o objeto seja solto em area invalida retorna para lista de inventario
            }
        });
        //criaçao de um alvo para o ator de arrastar
        dnd.addTarget(new DragAndDrop.Target(spell) {
            @Override
            public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                return !"Touch of Death".equals(payload.getObject());
            }

            @Override
            public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                spell.getItems().add((String) payload.getObject());
            }
        });

        //-------------------spell para grimoire------------------------//

        DragAndDrop dnd2 = new DragAndDrop();
        dnd2.addSource(new DragAndDrop.Source(spell) {  //a origem de onde e possivel arrastar
            final DragAndDrop.Payload payload1 = new DragAndDrop.Payload();  // cria o objeto carga para conter o item arrastado
            @Override
            public DragAndDrop.Payload dragStart(InputEvent event, float x, float y, int pointer) {
                String item = spell.getSelected(); // pega o item escolhido do inventario
                payload1.setObject(item);  //   add no objeto de carga
                spell.getItems().removeIndex(spell.getSelectedIndex());  // remove o objeto da lista de inventario, isto e desnecessario para nosso projeto
                payload1.setDragActor(new Label(item, skin));
                return payload1;
            }

            @Override
            public void dragStop(InputEvent event, float x, float y, int pointer, DragAndDrop.Payload payload, DragAndDrop.Target target) {
                if(target == null)
                    spell.getItems().add((String) payload.getObject()); // caso o objeto seja solto em area invalida retorna para lista de inventario
            }
        });
        dnd2.addTarget(new DragAndDrop.Target(grimoire) {
            @Override
            public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                return !"Begin".equals(payload.getObject());
            }

            @Override
            public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                //spell.getItems().removeIndex(spell.getSelectedIndex());
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


