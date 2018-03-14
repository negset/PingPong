package org.neggly.pingpong.android

import org.mini2Dx.android.AndroidMini2DxConfig
import com.badlogic.gdx.backends.android.AndroidMini2DxGame
import android.os.Bundle
import org.neggly.pingpong.Main

class AndroidLauncher : AndroidMini2DxGame() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val config = AndroidMini2DxConfig(Main.GAME_IDENTIFIER)
        initialize(Main(), config)
    }
}
