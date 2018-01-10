package com.unkowns.mind.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.unkowns.mind.MindGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Mind game by the team unkowns";
        config.height = 768;
        config.width = 1366;
        config.fullscreen = true;
		new LwjglApplication(new MindGame(), config);
	}
}
