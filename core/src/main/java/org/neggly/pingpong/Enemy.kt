package org.neggly.pingpong

import com.badlogic.gdx.graphics.Color
import org.mini2Dx.core.graphics.Graphics

class Enemy : GameObject() {
    private val w = 480
    private val h = 20

    val left: Float
        get() = x - w / 2
    val right: Float
        get() = x + w / 2
    val top: Float
        get() = y - h / 2
    val bottom: Float
        get() = y + h / 2

    private var passed = false

    override fun init(x: Float, y: Float) {
        super.init(x, y)

        passed = false
    }

    override fun update() {
    }

    fun update(ball: Ball) {
        if (ball.x < x - 100) {
            x -= 10
            if (left < 0) {
                x = (0 + w / 2).toFloat()
            }
        }
        else if (ball.x > x - 100) {
            x += 10
            if (right > WIDTH) {
                x = (WIDTH - w / 2).toFloat()
            }
        }
    }

    fun collision(ball: Ball) {
        if (!passed && ball.top <= bottom) {
            if (ball.x > left && ball.x < right) {
                ball.bounceY()
            }
            else {
                passed = true
            }
        }
    }

    override fun render(g: Graphics) {
        g.color = Color.RED
        g.fillRect(x - w / 2, y - h / 2, w.toFloat(), h.toFloat())
    }
}
