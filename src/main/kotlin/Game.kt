import factory.GridFactory
import factory.ParserFactory
import factory.RobotFactory

class Game(
    private val parserFactory: ParserFactory,
    private val robotFactory: RobotFactory,
    private val gridFactory: GridFactory,
    private val gridRobotLogger: GridRobotLogger,

    ) {
    private fun processCommands(robot: Robot, grid: Grid, commands: List<Command>) {
        commands.forEach { command ->
            if (robot.robotStatus == RobotStatus.ALIVE) {
                gridRobotLogger.log(robot, grid)
                when(command){
                    is MoveForwardCommand -> command.execute(robot, grid)
                    is MoveLeftCommand -> command.execute(robot)
                    is MoveRightCommand -> command.execute(robot)
                }
            }
        }

        if (robot.robotStatus == RobotStatus.ALIVE) {
            gridRobotLogger.log(robot, grid)
        }
    }

    fun startGame(input: String) {
        val parser = parserFactory.create().parse(input)
        val robot = robotFactory.create(parser.robotDirection, parser.robotPosition)
        val grid = gridFactory.create(parser.gridWidth, parser.gridHeight)

        require(robot.robotPosition.x in 0..grid.width && robot.robotPosition.y in 0..grid.height) { "Incorrect position: 0 <= x <= width and 0 <= y <= height" }
        require(grid.width in 1..50 && grid.height in 1..50) { "Grid is rectangle: 51>x>0 and 51>y>0" }

        processCommands(robot, grid, parser.commands)
    }
}

//fun main() {
//    val game = Game(Grid(), Robot(Direction.S), ParserFactory(), GridRobotLogger(Grid(), Robot()))
//
//    val s = """
//      5 3
//      1 1 E
//      RFRFRFRF
//    """.trimIndent()
//
//    val s2 = """
//        5 3
//        3 2 N
//        FRRFLLFFRRFLL
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
//        startGame(s3)
////        startGame(x)
////        startGame(x)
//
//    }
//}


