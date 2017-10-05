package MyGdxGame.pack.ModelsPack;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by LucasRezende on 12/09/2017.
 */

public class Player extends AbstractGameObject {

    public static final String TAG = Player.class.getName();
    private TextureRegion regPlayer;
    private float movePlayerX = 20;
    private float movePlayerY = 20;
    private SpriteBatch drawBatch = new SpriteBatch();;

    public Player() {
        init();
    }

    public void init () {
        dimension.set(1, 1);
        regPlayer = Assets.instance.player.player;
        bounds.set(0, 0, dimension.x, dimension.y); //seta caixa de colisao
    }

    @Override
    public void render(SpriteBatch batch) {
        TextureRegion reg = null;
        // Draw image
        reg = regPlayer;
        drawBatch.begin();
        batch.draw(reg.getTexture(),
                getPosition().x , getPosition().y,
                origin.x, origin.y,
                dimension.x, dimension.y,
                scale.x, scale.y,
                rotation,
                reg.getRegionX(), reg.getRegionY(),
                reg.getRegionWidth(), reg.getRegionHeight(),
                true, false);
        drawBatch.end();
    }

    public void comandos(float delta, com.badlogic.gdx.scenes.scene2d.ui.List list) {
        list.getItems().add("End");
        for (int i = 0; i < list.getItems().size; i++) {
            int wait = 10;
            while (wait > 0) {
                if (wait == 10) {
                    switch (Parse.get(list.getItems().get(i))) {
                        case 1:
                            movimento_Frente();
                            //render(drawBatch);
                            wait--;
                            break;
                        case 2:
                            movimento_Direita();
                            //render(drawBatch);
                            wait--;
                            break;
                        case 3:
                            movimento_Esquerda();
                            //render(drawBatch);
                            wait--;
                            break;
                        case 4:
                            movimento_Ataque();
                            //render(drawBatch);
                            wait--;
                            break;
                        case 5:
                            //inicia ciclo
                            wait--;
                            break;
                        case 6:
                            //final ciclo
                            wait--;
                            break;
                        default:
                            //nothing
                            wait--;
                            break;
                    }
                } else {
                    wait--;
                }
            }
        }
    }


    public Map<String , Integer> Parse = new HashMap<String , Integer>(){
        {
            put("Siga em frente",1);
            put("Virar a direita",2);
            put("Virar a Esquerda",3);
            put("Golpe simples",4);
            put("Begin",5);
            put("End",6);
        }
    };

    private void movimento_Frente() {
        position.set(getPosition().x + 0,getPosition().y + movePlayerY);
        System.out.print("Movimento a frente! \n");
    }

    private void movimento_Direita() {
        position.set(getPosition().x + movePlayerX,getPosition().y + 0);
        System.out.print("Movimento a direita! \n");
    }

    private void movimento_Esquerda() {
        position.set(getPosition().x - movePlayerX,getPosition().y + 0);
        System.out.print("Movimento a esquerda! \n");
    }

    private void movimento_Ataque() {
        System.out.print("Ataque efetuado \n!");
    }

}
