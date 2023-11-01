package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.game.SbakeGame;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		if (args.length > 0 && args[0].replace("-", "").equalsIgnoreCase("version")) {
			try {
				InputStream is = DesktopLauncher.class.getClassLoader().getResourceAsStream("version.md");
				String version = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8)).lines()
						.collect(Collectors.joining("\n"));
				System.out.println(version);
				System.exit(0);
			} catch (Exception e) {
				System.out.println("Fail to get version in DesktopLauncher.");
			}
		}

		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setWindowedMode(20 * 27, 20 * 27);
		
		config.setTitle("Sbake");
		new Lwjgl3Application(new SbakeGame(), config);
	}
}
