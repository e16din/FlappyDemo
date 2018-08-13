package com.flappydemo.game.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.flappydemo.game.FlappyDemo
import com.flappydemo.game.GameStateManager
import com.flappydemo.game.sprites.Bird

class PlayState(gsm: GameStateManager) : BaseState(gsm) {

    var background = Texture("bg.png")

    private val bird = Bird(50f, 300f)

    init {
        camera.setToOrtho(false,
                FlappyDemo.SCREEN_WIDTH / 2f,
                FlappyDemo.SCREEN_HEIGHT / 2f)
    }

    override fun handleInput() {
        if (Gdx.input.justTouched()) {
            bird.jump()
        }
    }

    override fun update(delta: Float) {
        bird.update(delta)
    }

    override fun render(batch: SpriteBatch) {
        batch.projectionMatrix = camera.combined
        batch.begin()
        batch.draw(background, 0f, 0f, FlappyDemo.SCREEN_WIDTH / 2f, FlappyDemo.SCREEN_HEIGHT / 2f)
        batch.draw(bird.texture, bird.position.x, bird.position.y)
        batch.end()
    }

    override fun dispose() {
        bird.dispose()
    }

}