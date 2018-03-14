package org.neggly.pingpong

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.Align
import org.mini2Dx.core.graphics.Graphics

class Score internal constructor() {
    private var playerPoint: Int = 0
    private var enemyPoint: Int = 0

    fun init() {
        playerPoint = 0
        enemyPoint = 0
    }

    fun render(g: Graphics) {
        g.color = Color.WHITE
        g.drawString("YOU: " + playerPoint, 50f, 50f, (WIDTH - 100).toFloat(), Align.left)
        g.drawString("NPC: " + enemyPoint, 50f, 50f, (WIDTH - 100).toFloat(), Align.right)
    }

    fun addScore(isPlayer: Boolean) {
        if (isPlayer) {
            playerPoint++
        } else {
            enemyPoint++
        }
    }
}