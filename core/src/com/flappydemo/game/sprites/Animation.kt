package com.flappydemo.game.sprites

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Array

class Animation(region: TextureRegion, val frameCount: Int, cycleTime: Float) {
    val frames = Array<TextureRegion>()
    var maxFrameTime = cycleTime / frameCount
    var currentFrameTime = 0f
    var frame = 0

    init {
        val frameWith = region.regionWidth / frameCount
        for (i in 0 until frameCount) {
            frames.add(TextureRegion(region, i * frameWith, 0, frameWith, region.regionHeight))
        }
    }

    fun update(delta: Float) {
        currentFrameTime += delta
        if (currentFrameTime > maxFrameTime) {
            frame += 1
            currentFrameTime = 0f
        }

        if (frame >= frameCount) {
            frame = 0
        }
    }

    fun getFrame() = frames[frame]
}