package com.flappydemo.game.sprites

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Disposable
import java.util.*

class Tube(x: Float) : Disposable {

    companion object {
        const val TUBE_WIDTH = 52f

        const val FLUCTUATION = 130
        const val TUBE_GAP = 100f
        const val LOWEST_OPENNING = 120f
    }

    private val random = Random()

    val topTube = Texture("toptube.png")
    val bottomTube = Texture("bottomtube.png")

    private val topTubeY = random.nextInt(FLUCTUATION).toFloat() + TUBE_GAP + LOWEST_OPENNING
    val posTopTube = Vector2(x, topTubeY)

    private val bottomTubeY = topTubeY - TUBE_GAP - bottomTube.height
    val posBottomTube = Vector2(x, bottomTubeY)

    val boundsTop = Rectangle(posTopTube.x, posTopTube.y, topTube.width.toFloat(), topTube.height.toFloat())
    val boundsBottom = Rectangle(posBottomTube.x, posBottomTube.y, bottomTube.width.toFloat(), bottomTube.height.toFloat())

    fun reposition(x: Float) {
        posTopTube.set(x, topTubeY)
        boundsTop.setPosition(posTopTube)

        posBottomTube.set(x, bottomTubeY)
        boundsBottom.setPosition(posBottomTube)
    }

    fun collides(player: Rectangle): Boolean {
        return player.overlaps(boundsTop) || player.overlaps(boundsBottom)
    }

    override fun dispose() {
        topTube.dispose()
        bottomTube.dispose()
    }
}