package org.neggly.pingpong

import com.badlogic.gdx.graphics.Color
import org.mini2Dx.core.graphics.Graphics

class Ball : GameObject() {
    private val r = 20f
    private var dir = 45
    private val speed = 15
    private var deltaX = 0.0
    private var deltaY = 0.0

    val left: Float
        get() = x - r
    val right: Float
        get() = x + r
    val top: Float
        get() = y - r
    val bottom: Float
        get() = y + r

    override fun init(x: Float, y: Float) {
        super.init(x, y)

        deltaX = speed * Math.cos(Math.toRadians(dir.toDouble()))
        deltaY = speed * Math.sin(Math.toRadians(dir.toDouble()))
    }

    override fun update() {
        x += deltaX.toFloat()
        y += deltaY.toFloat()

        if (left < 0 || right > WIDTH) {
            bounceX()
        }
    }

    override fun render(g: Graphics) {
        g.color = Color.WHITE
        g.fillCircle(x, y, r)
    }

    fun bounceX() {
        deltaX = -deltaX
    }

    fun bounceY() {
        deltaY = -deltaY
    }
}
