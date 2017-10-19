package MyGdxGame.pack;

/**
 * Created by LucasRezende on 03/08/2017.
 */

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;

import MyGdxGame.pack.ModelsPack.Assets;
import MyGdxGame.pack.ScreensPack.MenuScreen;

public class GameMain extends Game {
    private static final java.lang.String Tag = GameMain.class.getName();

    @Override
    public void create () {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        Assets.instance.init(new AssetManager());
        setScreen(new MenuScreen(this));
    }

}
