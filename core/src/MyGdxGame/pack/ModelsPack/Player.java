package MyGdxGame.pack.ModelsPack;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LucasRezende on 12/09/2017.
 */

public class Player extends AbstractGameObject {

    public static final String TAG = Player.class.getName();
    private TextureRegion regPlayer;
    private float movePlayerX = 1;
    private float movePlayerY = 1;
    //private SpriteBatch drawBatch = new SpriteBatch();;

    public Player() {
        init();
    }

    public void init () {
        dimension.set(1, 1);
        origin.set(dimension.x/2,dimension.y/2);
        regPlayer = Assets.instance.player.player;
        bounds.set(0, 0, dimension.x, dimension.y); //seta caixa de colisao
    }

    @Override
    public void render(SpriteBatch batch) {
        TextureRegion reg = null;
        // Draw image
        reg = regPlayer;
        //drawBatch.begin();
        batch.draw(reg.getTexture(),
                getPosition().x , getPosition().y,
                origin.x, origin.y,
                dimension.x, dimension.y,
                scale.x, scale.y,
                rotation,
                reg.getRegionX(), reg.getRegionY(),
                reg.getRegionWidth(), reg.getRegionHeight(),
                true, false);
        //drawBatch.end();
    }

    public void comandos(int comando) {
                    switch (comando) {
                        case 1:
                            movimento_Frente();
                            //render(drawBatch);
                            break;
                        case 2:
                            girar_Direita();
                            //render(drawBatch);
                            break;
                        case 3:
                            girar_Esquerda();
                            //render(drawBatch);
                            break;
                        case 4:
                            movimento_Ataque();
                            //render(drawBatch);
                            break;
                        case 5:
                            //inicia ciclo
                            break;
                        case 6:
                            //final ciclo
                            break;
                        default:
                            //nothing
                            break;
                    }
    }


    public Map<String , Integer> Parse = new HashMap<String , Integer>(){
        {
            put("Siga em frente",1);
            put("Virar a direita",2);
            put("Virar a Esquerda",3);
            put("Golpe simples",4);
            put("Begin",5);
            put("END",6);
        }
    };

    private void movimento_Frente() {
        if(rotation == 0) {//direita
            position.set(getPosition().x + 0, getPosition().y + movePlayerY);
        }else if(rotation == 90 || rotation == -90) {//cima
            position.set(getPosition().x + movePlayerX, getPosition().y + 0);
        }else if(rotation == 270 || rotation == -270) {//esquerda
            position.set(getPosition().x -  movePlayerY, getPosition().y + 0);
        }else if(rotation == 180 || rotation == -180) {//baixo
            position.set(getPosition().x + 0, getPosition().y - movePlayerX);
        }
        System.out.print("Movimento a frente! \n");
    }

    private void girar_Direita() {
        //position.set(getPosition().x + movePlayerX,getPosition().y + 0);
        setRotation(rotation-90);
        if(rotation == -360){
            setRotation(0);
        }
        System.out.print("giro a direita! \n");
    }

    private void girar_Esquerda() {
        //position.set(getPosition().x - movePlayerX,getPosition().y + 0);
        setRotation(getRotation()+90);
        if(rotation == 360){
            setRotation(0);
        }
        System.out.print("giro a esquerda! \n");
    }

    private void movimento_Ataque() {
        System.out.print("Ataque efetuado \n!");
    }

}
