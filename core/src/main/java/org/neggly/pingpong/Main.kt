package org.neggly.pingpong

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import org.mini2Dx.core.game.BasicGame
import org.mini2Dx.core.graphics.Graphics
import com.badlogic.gdx.graphics.g2d.BitmapFont

const val WIDTH = 1440
const val HEIGHT = 2560

class Main : BasicGame() {
    /** ゲームの状態 */
    private var state: State = State.UNSET

    /** ゲームの画面 */
    private val title = Title()
    private val play = Play()

    /** フォント */
    private var font: BitmapFont? = null

    override fun initialise() {
        state = State.TITLE

        title.init()
        play.init()


        /* フォントの生成および設定 */
        val param = FreeTypeFontGenerator.FreeTypeFontParameter()
        param.size = 100
        param.flip = true
        param.magFilter = Texture.TextureFilter.Linear
        param.minFilter = Texture.TextureFilter.Linear
        param.incremental = true
        val generator = FreeTypeFontGenerator(Gdx.files.internal("Product Sans Bold.otf"))
        font = generator.generateFont(param)
        font?.setUseIntegerPositions(false)
    }

    override fun update(delta: Float) {
        when (state) {
            State.TITLE -> {
                title.update()
                if (title.next != State.UNSET) {
                    state = title.next
                    title.init()
                }
            }

            State.PLAY -> {
                play.update()
                if (play.next != State.UNSET) {
                    state = play.next
                    play.init()
                }
            }
        }
    }

    override fun interpolate(alpha: Float) {
    }

    override fun render(g: Graphics) {
        fitScreen(g)

        g.font = font

        when (state) {
            State.TITLE -> title.render(g)
            State.PLAY -> play.render(g)
        }
    }

    /**
     * 画面サイズの最適化を行う.
     *
     * @param g 描画先
     */
    private fun fitScreen(g: Graphics) {
        val sx = getWidth().toFloat() / WIDTH
        val sy = getHeight().toFloat() / HEIGHT
        if (sx < sy) {
            g.setScale(sx, sx)
            g.setTranslation(0f, -(getHeight() / sx - HEIGHT) / 2)
        } else {
            g.setScale(sy, sy)
            g.setTranslation(-(getWidth() / sy - WIDTH) / 2, 0f)
        }
    }

    companion object {
        val GAME_IDENTIFIER = "org.neggly.pingpong"
    }
}
