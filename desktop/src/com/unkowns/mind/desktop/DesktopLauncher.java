package com.unkowns.mind.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.unkowns.mind.MindGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
        System.out.println("[INIT] Starting with DesktopLauncher.class");
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Mind game by the team unkowns";
        config.height = 768;
        config.width = 1366;
        config.fullscreen = true;
        System.out.println("[OK] Intializing done... Starting in 5 seconds...");
        new LwjglApplication(new MindGame(), config);
	}
}
