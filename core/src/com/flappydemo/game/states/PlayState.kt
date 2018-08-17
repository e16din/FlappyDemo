package com.flappydemo.game.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Array
import com.flappydemo.game.FlappyDemo
import com.flappydemo.game.GameStateManager
import com.flappydemo.game.sprites.Bird
import com.flappydemo.game.sprites.Tube

class PlayState(gsm: GameStateManager) : BaseState(gsm) {

    companion object {
        const val TUBE_SPACING = 125f
        const val TUBE_COUNT = 4

        const val GROUND_Y_OFFSET = -30f
    }

    private var background = Texture("bg.png")
    private var ground = Texture("ground.png")

    private val bird = Bird(50f, 300f)

    private val tubes = Array<Tube>()

    private val groundPos1 = Vector2(calcGroundPos1(), GROUND_Y_OFFSET)

    private val groundPos2 = Vector2(calcGroundPos2(), GROUND_Y_OFFSET)


    init {
        camera.setToOrtho(false,
                FlappyDemo.SCREEN_WIDTH / 2f,
                FlappyDemo.SCREEN_HEIGHT / 2f)

        for (i in 1..TUBE_COUNT) {
            val tubeX = i * (TUBE_SPACING + Tube.TUBE_WIDTH)
            tubes.add(Tube(tubeX))
        }
    }

    fun calcGroundPos1() = camera.position.x - camera.viewportWidth / 2f
    fun calcGroundPos2() = groundPos1.x + ground.width

    override fun handleInput() {
        if (Gdx.input.justTouched()) {
            bird.jump()
        }
    }

    override fun update(delta: Float) {
        updateGround()
        bird.update(delta)
        camera.position.x = bird.position.x + 80

        for (tube in tubes) {

            val cameraLeftPosition = camera.position.x - (camera.viewportWidth / 2)
            val tubeRightPosition = tube.posTopTube.x + tube.topTube.width
            if (cameraLeftPosition > tubeRightPosition) {
                tube.reposition(tube.posTopTube.x + (Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT)
            }

//            if (tube.collides(bird.bounds)) {
//                gsm.set(PlayState(gsm))
//                break
//            }
        }

        camera.update()
    }

    override fun render(batch: SpriteBatch) {
        batch.projectionMatrix = camera.combined
        batch.begin()

        drawBackground(batch)

        drawBird(batch)

        drawTubes(batch)

        drawGround(batch)

        batch.end()
    }

    private fun drawGround(batch: SpriteBatch) {
        batch.draw(ground, groundPos1.x, groundPos1.y)
        batch.draw(ground, groundPos2.x, groundPos2.y)
    }

    private fun drawBackground(batch: SpriteBatch) {
        val cameraLeftPosition = camera.position.x - camera.viewportWidth / 2
        batch.draw(background, cameraLeftPosition, 0f, FlappyDemo.SCREEN_WIDTH / 2f, FlappyDemo.SCREEN_HEIGHT / 2f)
    }

    private fun drawBird(batch: SpriteBatch) {
        batch.draw(bird.getTexture(), bird.position.x, bird.position.y)
    }

    private fun drawTubes(batch: SpriteBatch) {
        tubes.forEach { tube ->
            batch.draw(tube.topTube, tube.posTopTube.x, tube.posTopTube.y)
            batch.draw(tube.bottomTube, tube.posBottomTube.x, tube.posBottomTube.y)
        }
    }

    private fun updateGround() {
        if (calcGroundPos1() > groundPos1.x + ground.width) {
            groundPos1.add(ground.width * 2f, 0f)
        }

        if (calcGroundPos1() > groundPos2.x + ground.width) {
            groundPos2.add(ground.width * 2f, 0f)
        }
    }

    override fun dispose() {
        bird.dispose()
        background.dispose()
        ground.dispose()
        tubes.forEach { tube ->
            tube.dispose()
        }
    }

}