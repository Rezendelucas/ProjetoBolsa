package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2;

import MyGdxGame.pack.ScreensPack.GameMain;

public class DesktopLauncher {
	private static  boolean rebuildAtlas = true;
	private static  boolean drawDebugOutline = false;

	public static void main (String[] arg) {
		if(rebuildAtlas){
			TexturePacker2.Settings settings = new TexturePacker2.Settings();
			settings.maxWidth = 1024;
			settings.maxHeight = 1024;
			settings.debug = drawDebugOutline;
			TexturePacker2.process(settings,"android/assets/assets_raw","android/assets/assets_atlas","GameTexture.pack");
			TexturePacker2.process(settings,"android/assets/assets_raw_ui","android/assets/assets_atlas_ui","GameUI.pack");
		}
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "GameMain";
		config.useGL30 = false;
		config.width  = 1280;
		config.height = 720;
		new LwjglApplication(new GameMain(), config);
	}
}
