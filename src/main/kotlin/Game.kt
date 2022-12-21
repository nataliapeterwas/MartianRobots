data class Position(var x: Int, var y: Int)

class Game(private val grid: Grid, private val parser: Parser, private val robot: Robot) {
    private fun makeMove(movements: List<Command>, grid: Grid) {

        movements.forEach { command ->
            if (robot.robotStatus == Status.ALIVE) {
                grid.drawGrid(robot)
                command.execute()
            }
        }
        if(robot.robotStatus == Status.ALIVE) grid.drawGrid(robot)
    }

    fun startGame(gridSize: String, robotPosition: String, moves: String) {
        robot.robotStatus = Status.ALIVE
        val gridSizeParser = parser.splitGridSize(gridSize)
        grid.setGrid(gridSizeParser.first, gridSizeParser.second)

        val robotPositionParser = parser.splitRobotPosition(robotPosition)
        robot.setRobotPosition(robotPositionParser.first, robotPositionParser.second, robotPositionParser.third, grid)

        val movesParser = parser.splitMoves(moves, robot, grid)

        makeMove(movesParser, grid)
    }
}

fun main() {
    val game = Game(Grid(), Parser(), Robot())

    val s = """
        5 3
        1 1 E
        RFRFRFRF
    """.trimIndent()

    game.apply {
//        startGame("5 3", "0 0 S", "F")
//        startGame("5 3", "0 0 S", "F")

//        startGame("5 3", "1 1 E", "RFRFRFRF")
//        startGame("3 4", "3 4 W", "FRF")
//        startGame("3 4", "3 4 N", "FRF")
        startGame("5 3", "3 2 N", "FRRFLLFFRRFLL")
        startGame("5 3", "0 3 W", "LLFFFLFLFL")
    }
}
