package com.flappydemo.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.flappydemo.game.FlappyDemo;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = (int) FlappyDemo.SCREEN_WIDTH;
        config.height = (int) FlappyDemo.SCREEN_HEIGHT;
        config.title = FlappyDemo.GAME_TITLE;
        new LwjglApplication(new FlappyDemo(), config);
    }
}
