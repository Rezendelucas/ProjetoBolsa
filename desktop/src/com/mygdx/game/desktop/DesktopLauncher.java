package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2;

import MyGdxGame.pack.GameMain;

public class DesktopLauncher {
	private static  boolean rebuildAtlas = false;
	private static  boolean drawDebugOutline = false;

	public static void main (String[] arg) {
		if(rebuildAtlas){
			TexturePacker2.Settings settings = new TexturePacker2.Settings();
			settings.maxWidth = 1024;
			settings.maxHeight = 1024;
			settings.debug = drawDebugOutline;
			TexturePacker2.process(settings,"assets_raw","assets_atlas","GameTexture.pack");
			TexturePacker2.process(settings,"assets_raw_ui","assets_atlas_ui","GameUI.pack");
		}
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "GameMain";
		config.useGL30 = false;
		config.width  = 800;
		config.height = 480;
		new LwjglApplication(new GameMain(), config);
	}
}
