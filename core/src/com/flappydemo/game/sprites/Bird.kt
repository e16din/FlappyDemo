package com.flappydemo.game.sprites

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.utils.Disposable

class Bird(x: Float, y: Float) : Disposable {

    companion object {
        private const val MOVEMENT = 100f

        private const val GRAVITY = -15f
    }

    val position = Vector3(x, y, 0f)
    val velocity = Vector3()

    val texture = Texture("bird.png")

    fun update(delta: Float) {
        if (position.y > 0) {
            velocity.add(0f, GRAVITY, 0f)
        }
        velocity.scl(delta)
        position.add(MOVEMENT * delta, velocity.y, 0f)
        if (position.y < 0) {
            position.y = 0f
        }

        velocity.scl(1 / delta)
    }

    override fun dispose() {
        texture.dispose()
    }

    fun jump() {
        velocity.y = 250f
    }
}