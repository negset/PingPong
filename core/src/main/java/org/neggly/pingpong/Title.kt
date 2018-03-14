package org.neggly.pingpong

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.Align
import org.mini2Dx.core.graphics.Graphics

class Title {
    var next: State = State.UNSET

    fun init() {
        next = State.UNSET
    }

    fun update() {
        if (Gdx.input.justTouched()) {
            next = State.PLAY
        }
    }

    fun render(g: Graphics) {
        g.color = Color.WHITE
        g.drawString("PingPong", 0f, (HEIGHT / 2 - 200).toFloat(), WIDTH.toFloat(), Align.center)
        g.drawString("Tap to PLAY", 0f, (HEIGHT / 2 + 200).toFloat(), WIDTH.toFloat(), Align.center)
        g.drawString("© 2018 negset", 0f, (HEIGHT - 200).toFloat(), WIDTH.toFloat(), Align.center)
    }
}
