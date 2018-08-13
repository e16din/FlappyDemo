package com.flappydemo.game.states

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector3
import com.flappydemo.game.GameStateManager

abstract class BaseState(val gsm: GameStateManager) {
    var camera = OrthographicCamera()
    var mouse = Vector3()


    abstract fun handleInput()
    abstract fun update(delta: Float)
    abstract fun render(batch: SpriteBatch)
    abstract fun dispose()

}