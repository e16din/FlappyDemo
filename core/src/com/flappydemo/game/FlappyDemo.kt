package com.flappydemo.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.flappydemo.game.states.MenuState

class FlappyDemo : ApplicationAdapter() {

    companion object {
        const val SCREEN_WIDTH = 480f
        const val SCREEN_HEIGHT = 800f

        const val GAME_TITLE = "FlappyDemo"
    }

    private lateinit var gsm: GameStateManager
    private lateinit var batch: SpriteBatch

    override fun create() {
        gsm = GameStateManager()
        batch = SpriteBatch()
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        gsm.push(MenuState(gsm))
    }

    override fun render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        gsm.update(Gdx.graphics.deltaTime)
        gsm.render(batch)
    }

    override fun dispose() {
        batch.dispose()
    }
}
