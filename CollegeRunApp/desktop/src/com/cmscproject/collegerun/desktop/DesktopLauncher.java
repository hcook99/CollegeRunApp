package com.cmscproject.collegerun.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.cmscproject.collegerun.CollegeRun;

class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 2000;
		config.height = 1000;
		new LwjglApplication(new CollegeRun(), config);
	}
}
