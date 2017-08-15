package MyGdxGame.pack.ModelsPack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;
import MyGdxGame.pack.UtilsPack.Constants;

/**
 * Created by LucasRezende on 03/08/2017.
 */

public class Assets implements Disposable, AssetErrorListener {

    public static  final String Tag = Assets.class.getName();
    public  static final  Assets instance = new Assets();

    private AssetManager assetManager;
    public  AssetFonts fontes;
    public AssetWhiteBall whiteball;
    public AssetBlueBall blueball;
    public AssetBorda borda;
    public AssetGoldCoin goldCoin;

    private Assets(){};

    public void init(AssetManager assetManager){
        this.assetManager = assetManager;
        assetManager.setErrorListener(this);
        assetManager.load(Constants.TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
        assetManager.finishLoading();
        Gdx.app.debug(Tag,"# assets carregados: " + assetManager.getAssetNames().size);
        for(String a : assetManager.getAssetNames()) {
            Gdx.app.debug(Tag, "asset: " + a);
        }
        TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS_OBJECTS);
        for (Texture t : atlas.getTextures()){
            t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
        fontes = new AssetFonts();
    }

    @Override
    public void dispose() {
        assetManager.dispose();
        fontes.defaultBig.dispose();
        fontes.defaultNormal.dispose();
        fontes.defaultSmall.dispose();
    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error(Tag, "Asset nao foi carregada ",(Exception)throwable);
    }

    public class AssetWhiteBall{
        public final TextureAtlas.AtlasRegion whiteball;

        public  AssetWhiteBall (TextureAtlas atlas){
            whiteball = atlas.findRegion("white_ball");
        }
    }

    public class AssetBlueBall{
        public final TextureAtlas.AtlasRegion blueball;

        public  AssetBlueBall (TextureAtlas atlas){
            blueball = atlas.findRegion("blue_ball");
        }
    }

    public class AssetBorda{
        public final TextureAtlas.AtlasRegion corner;
        public final TextureAtlas.AtlasRegion middle;

        public  AssetBorda(TextureAtlas atlas){
            corner = atlas.findRegion("borda_corner");
            middle = atlas.findRegion("borda_middle");
        }
    }

    public class AssetGoldCoin{
        public final TextureAtlas.AtlasRegion goldCoin;

        public  AssetGoldCoin (TextureAtlas atlas){
            goldCoin = atlas.findRegion("item_gold_coin");
        }
    }
}

