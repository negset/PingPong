package org.neggly.pingpong

import org.mini2Dx.core.graphics.Graphics

abstract class GameObject {
    var x = 0f
    var y = 0f

    open fun init(x: Float, y: Float) {
        this.x = x
        this.y = y
    }

    abstract fun update()

    abstract fun render(g: Graphics)
}
