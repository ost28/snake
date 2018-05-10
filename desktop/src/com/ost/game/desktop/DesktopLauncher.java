package com.ost.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ost.game.Field;
import com.ost.game.GameModel;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                
                config.title="SnakeOst";
                config.height=Field.HEIGHT;
                config.width=Field.WIDTH;
                
		new LwjglApplication(new GameModel(), config);
	}
}
