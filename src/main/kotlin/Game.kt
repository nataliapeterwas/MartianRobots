data class Position(var x: Int, var y: Int)

class Game(private val grid: Grid, private val robot: Robot) {
    private fun makeMove(movements: List<Command?>, grid: Grid) {
        movements.forEach { command ->
            if (robot.robotStatus == Status.ALIVE) {
                grid.drawGrid(robot)
                command?.execute()
            }
        }

        if (robot.robotStatus == Status.ALIVE) grid.drawGrid(robot)
    }

    fun startGame(input: String) {
        val parser = Parser(input, grid, robot)
        makeMove(parser.parse(), grid)
    }
}

fun main() {
    val game = Game(Grid(), Robot())

    val s = """
      5 3
      1 1 E
      RFRFRFRF
    """.trimIndent()

    val s2 = """
        5 3
        3 2 N
        FRRFLLFFRRFLL
    """.trimIndent()

    val s3 = """
        5 3
        0 3 W
        LLFFFLFLFL
    """.trimIndent()

    val x = """
             5 3
             0 0 S
             FFFFFF
        """.trimIndent()

    game.apply {
//        startGame(s)
//        startGame(s2)
//        startGame(s3)
        startGame(x)
        startGame(x)

    }
}
