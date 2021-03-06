package com.flappydemo.game.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.flappydemo.game.FlappyDemo
import com.flappydemo.game.GameStateManager

class MenuState(gsm: GameStateManager) : BaseState(gsm) {

    init {
        camera.setToOrtho(false, FlappyDemo.SCREEN_WIDTH / 2f, FlappyDemo.SCREEN_HEIGHT / 2f)
    }

    var background = Texture("bg.png")
    var playButton = Texture("playbtn.png")

    override fun handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.set(PlayState(gsm))
        }
    }

    override fun update(delta: Float) {
    }

    override fun render(batch: SpriteBatch) {
        batch.projectionMatrix = camera.combined

        batch.begin()
        batch.draw(background, 0f, 0f, FlappyDemo.SCREEN_WIDTH, FlappyDemo.SCREEN_HEIGHT)
        batch.draw(playButton,
                camera.position.x - playButton.width / 2f,
                camera.position.y - playButton.height / 2f)

        batch.end()
    }

    override fun dispose() {
        background.dispose()
        playButton.dispose()
    }

}