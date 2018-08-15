package com.flappydemo.game.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.Array
import com.flappydemo.game.FlappyDemo
import com.flappydemo.game.GameStateManager
import com.flappydemo.game.sprites.Bird
import com.flappydemo.game.sprites.Tube

class PlayState(gsm: GameStateManager) : BaseState(gsm) {

    companion object {
        const val TUBE_SPACING = 125f
        const val TUBE_COUNT = 4
    }

    private var background = Texture("bg.png")

    private val bird = Bird(50f, 300f)

    private val tubes = Array<Tube>()

    init {
        camera.setToOrtho(false,
                FlappyDemo.SCREEN_WIDTH / 2f,
                FlappyDemo.SCREEN_HEIGHT / 2f)

        for (i in 1..TUBE_COUNT) {
            val tubeX = i * (TUBE_SPACING + Tube.TUBE_WIDTH)
            tubes.add(Tube(tubeX))
        }
    }

    override fun handleInput() {
        if (Gdx.input.justTouched()) {
            bird.jump()
        }
    }

    override fun update(delta: Float) {
        bird.update(delta)
        camera.position.x = bird.position.x + 80

        for(tube in tubes){

            val cameraLeftPosition = camera.position.x - (camera.viewportWidth / 2)
            val tubeRightPosition = tube.posTopTube.x + tube.topTube.width
            if (cameraLeftPosition > tubeRightPosition) {
                tube.reposition(tube.posTopTube.x + (Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT)
            }

            if(tube.collides(bird.bounds)){
                gsm.set(PlayState(gsm))
                break
            }
        }

        camera.update()
    }

    override fun render(batch: SpriteBatch) {
        batch.projectionMatrix = camera.combined
        batch.begin()

        drawBackground(batch)

        drawBird(batch)

        drawTubes(batch)

        batch.end()
    }

    private fun drawBackground(batch: SpriteBatch) {
        val cameraLeftPosition = camera.position.x - camera.viewportWidth / 2
        batch.draw(background, cameraLeftPosition, 0f, FlappyDemo.SCREEN_WIDTH / 2f, FlappyDemo.SCREEN_HEIGHT / 2f)
    }

    private fun drawBird(batch: SpriteBatch) {
        batch.draw(bird.texture, bird.position.x, bird.position.y)
    }

    private fun drawTubes(batch: SpriteBatch) {
        tubes.forEach { tube ->
            batch.draw(tube.topTube, tube.posTopTube.x, tube.posTopTube.y)
            batch.draw(tube.bottomTube, tube.posBottomTube.x, tube.posBottomTube.y)
        }
    }

    override fun dispose() {
        bird.dispose()
        background.dispose()
        tubes.forEach { tube ->
            tube.dispose()
        }
    }

}