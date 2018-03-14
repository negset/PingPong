package org.neggly.pingpong

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import org.mini2Dx.core.graphics.Graphics

class Player : GameObject() {
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

    private var firstTouch = false
    private var baseX = 0f
    private var touchBaseX = 0f

    override fun init(x: Float, y: Float) {
        super.init(x, y)

        passed = false

        firstTouch = true
    }

    override fun update() {
        if (Gdx.input.isTouched) {
            if (firstTouch) {
                baseX = x
                touchBaseX = Gdx.input.x.toFloat()
                firstTouch = false
            }
            else {
                x = baseX + (Gdx.input.x - touchBaseX)
                if (left < 0) {
                    x = (0 + w / 2).toFloat()
                }
                else if (right > WIDTH) {
                    x = (WIDTH - w / 2).toFloat()
                }
            }
        }
        else {
            firstTouch = true
        }
    }

    fun collision(ball: Ball) {
        if (!passed && ball.bottom >= top) {
            if (ball.x > left && ball.x < right) {
                ball.bounceY()
            }
            else {
                passed = true
            }
        }
    }

    override fun render(g: Graphics) {
        g.color = Color.BLUE
        g.fillRect(x - w / 2, y - h / 2, w.toFloat(), h.toFloat())
    }
}