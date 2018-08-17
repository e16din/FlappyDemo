package com.flappydemo.game.sprites

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.utils.Disposable

class Bird(x: Float, y: Float) : Disposable {

    companion object {
        private const val MOVEMENT = 100f

        private const val GRAVITY = -15f
    }

    val position = Vector3(x, y, 0f)
    val velocity = Vector3()

    val birdAnimationTexture = Texture("birdanimation.png")

    val birdAnimation = Animation(TextureRegion(birdAnimationTexture), 3, 0.5f)
    val bounds = Rectangle(x, y, birdAnimationTexture.width / 3f, birdAnimationTexture.height.toFloat())

    fun getTexture() = birdAnimation.getFrame()

    fun update(delta: Float) {
        birdAnimation.update(delta)
        if (position.y > 0) {
            velocity.add(0f, GRAVITY, 0f)
        }
        velocity.scl(delta)
        position.add(MOVEMENT * delta, velocity.y, 0f)
        if (position.y < 0) {
            position.y = 0f
        }

        velocity.scl(1 / delta)
        bounds.setPosition(position.x, position.y)
    }

    override fun dispose() {
        birdAnimationTexture.dispose()
    }

    fun jump() {
        velocity.y = 250f
    }
}