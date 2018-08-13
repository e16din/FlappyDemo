package com.flappydemo.game

import com.badlogic.gdx.graphics.Texture

object Utils {
    fun centerHorizontal(texture: Texture) = FlappyDemo.SCREEN_WIDTH / 2f - texture.width / 2f
    fun centerVertical(texture: Texture) = FlappyDemo.SCREEN_HEIGHT / 2f - texture.height / 2f
}