package org.neggly.pingpong

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.Align
import org.mini2Dx.core.graphics.Graphics

class Play {
    var next: State = State.UNSET

    private enum class Scene {
        READY,
        PLAYING,
        GAME_OVER
    }
    private var scene: Scene = Scene.READY

    private val player: Player = Player()
    private val enemy: Enemy = Enemy()
    private val ball: Ball = Ball()
    private val score: Score = Score()

    fun init() {
        next = State.UNSET

        score.init()

        initGame()
    }

    private fun initGame() {
        scene = Scene.READY

        player.init((WIDTH / 2).toFloat(), (HEIGHT - 400).toFloat())
        enemy.init((WIDTH / 2).toFloat(), 400f)
        ball.init((WIDTH / 2).toFloat(), (HEIGHT / 2).toFloat())
    }

    fun update() {
        when (scene) {
            Scene.READY -> if (Gdx.input.justTouched()) {
                scene = Scene.PLAYING
            }

            Scene.PLAYING -> {
                ball.update()
                if (ball.top < 0) {
                    score.addScore(true)
                    scene = Scene.GAME_OVER
                }
                else if (ball.bottom > HEIGHT) {
                    score.addScore(false)
                    scene = Scene.GAME_OVER
                }

                player.update()
                enemy.update(ball)

                player.collision(ball)
                enemy.collision(ball)
            }

            Scene.GAME_OVER -> if (Gdx.input.justTouched()) {
                if (Gdx.input.y < HEIGHT / 2) {
                    initGame()
                }
                else {
                    next = State.TITLE
                }
            }
        }
    }

    fun render(g: Graphics) {
        player.render(g)
        enemy.render(g)
        ball.render(g)
        score.render(g)

        when (scene) {
            Scene.READY -> {
                g.color = Color.WHITE
                g.drawString("Tap to START", 0f, (HEIGHT / 2 - 200).toFloat(), WIDTH.toFloat(), Align.center)
            }

            Scene.PLAYING -> {
            }

            Scene.GAME_OVER -> {
                g.color = Color.WHITE
                g.drawString("CONTINUE", 0f, (HEIGHT / 2 - 200).toFloat(), WIDTH.toFloat(), Align.center)
                g.drawString("or", 0f, (HEIGHT / 2).toFloat(), WIDTH.toFloat(), Align.center)
                g.drawString("EXIT ?", 0f, (HEIGHT / 2 + 200).toFloat(), WIDTH.toFloat(), Align.center)
            }
        }
    }
}