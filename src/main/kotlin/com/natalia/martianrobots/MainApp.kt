package com.natalia.martianrobots

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainApp : KoinComponent {
    private val game: Game by inject()
    init {
        val s = """
            5 3
            0 0 N
            F
        """.trimIndent()

        game.startGame(s)
        game.startGame(s)

    }
}