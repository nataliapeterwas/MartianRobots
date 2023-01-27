import factory.GridFactory
import factory.ConfigParserFactory
import factory.RobotFactory
import parser.CommandsConfigParser
import parser.GridConfigParser
import parser.MainInputConfigParser
import parser.PositionAndDirectionConfigParser

class Game(
    private val configParserFactory: ConfigParserFactory,
    private val robotFactory: RobotFactory,
    private val gridFactory: GridFactory,
    private val commandProcessor: CommandProcessor,
) {
    private var deadPoints = mutableListOf<Position>()
    fun startGame(
        input: String,
        mainInputConfigParser: MainInputConfigParser,
        positionAndDirectionConfigParser: PositionAndDirectionConfigParser,
        gridConfigParser: GridConfigParser,
        commandsConfigParser: CommandsConfigParser,
    ) {
        val config = configParserFactory.create().parse(
            input,
            mainInputConfigParser,
            positionAndDirectionConfigParser,
            gridConfigParser,
            commandsConfigParser,
        )
        val robot = robotFactory.create(config.robotDirection, config.robotPosition)
        val grid = gridFactory.create(config.gridWidth, config.gridHeight, deadPoints)

        require(robot.robotPosition.x in 0..grid.width && robot.robotPosition.y in 0..grid.height) { "Incorrect position: 0 <= x <= width and 0 <= y <= height" }
        require(grid.width in 1..50 && grid.height in 1..50) { "Grid is rectangle: 51>x>0 and 51>y>0" }

        commandProcessor.processCommands(robot, grid, config.commands)
        deadPoints = grid.deadPoints
    }

//    fun createNewRobot(input: String) {
//        val parser = parserFactory.create().parse(input)
//        val robot = robotFactory.create(parser.robotDirection, parser.robotPosition)
//        require(robot.robotPosition.x in 0..grid.width && robot.robotPosition.y in 0..grid.height) { "Incorrect position: 0 <= x <= width and 0 <= y <= height" }
//
//
//    }
}


//fun main() {
//    val game = Game()
//
//    val s = """
//      5 3
//      1 1 E
//      RFRFRFRF
//    """.trimIndent()
////
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
//
//    game.apply {
//        startGame(s)
//        startGame(s2)
//        startGame(s4)
//        startGame(s3)
//        startGame(x)
//        startGame(x)
//
//    }
//}