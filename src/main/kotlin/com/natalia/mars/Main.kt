package com.natalia.mars
import com.natalia.mars.factory.ConfigParserFactory
import com.natalia.mars.factory.GridFactory
import com.natalia.mars.factory.RobotFactory
import com.natalia.mars.parser.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

var module = module {
    singleOf(::Game)
    singleOf(::CommandProcessor)
    singleOf(::GridRobotLogger)

    // parser
    singleOf(::CommandsConfigParser)
    singleOf(::ConfigParser)
    singleOf(::GridConfigParser)
    singleOf(::MainInputConfigParser)
    singleOf(::PositionAndDirectionConfigParser)

    //factory
    singleOf(::ConfigParserFactory)
    singleOf(::GridFactory)
    singleOf(::RobotFactory)
}

class MainApp : KoinComponent {

    private val game: Game by inject()

    init {
        val s = """
            5 3
            1 1 E
            RFRFRFRF
        """.trimIndent()

        game.startGame(s)
    }

}

fun main() {
    startKoin {
        modules(module)
    }
    val mainApp = MainApp()

    val s = """
      5 3
      1 1 E
      RFRFRFRF
    """.trimIndent()
//
//    val s2 = """
//        5 3
//        3 2 N
//        FRRFLLFFRRFLL
//    """.trimIndent()
//
//    val s4 = """
//        9 9
//        3 3 N
//        FFF
//    """.trimIndent()
//
//    val s3 = """
//        5 3
//        0 3 W
//        LLFFFLFLFL
//    """.trimIndent()
//
//    val x = """
//             5 3
//             0 0 S
//             FFFFFF
//        """.trimIndent()
}
