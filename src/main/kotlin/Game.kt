import factory.ParserFactory

class Game(
    private val grid: Grid,
    private val robot: Robot,
    private val parserFactory: ParserFactory,
    private val gridRobotLogger: GridRobotLogger,
) {
    /*
    1. ALIVE true - lista komend + czy kazda komenda wyolana
    2. ALIVE true - 2 komendy wwolane = ALVIE false - 3 komenda nie wywolala

     */

    private fun processCommands(commands: List<Command?>) {
        commands.forEach { command ->
            if (robot.robotStatus == Status.ALIVE) {
                gridRobotLogger.log()
                command?.execute()
            }
        }

        if (robot.robotStatus == Status.ALIVE) {
            gridRobotLogger.log()
        }
    }

    fun startGame(input: String) {
        val parser = parserFactory.create(grid, robot)
        processCommands(parser.parse(input))
    }
}

/*
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
        startGame(s)
        startGame(s2)
        startGame(s3)
//        startGame(x)
//        startGame(x)

    }
}
 */

