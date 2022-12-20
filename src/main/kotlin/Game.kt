data class Position(var x: Int, var y: Int)

class Game(val grid: Grid, val parser: Parser) {
    private var x = 0
    private var y = 0
    private var width = 0
    private var height = 0
    var robotDirection = Direction.W
    var robotPosition = Position(0, 0)
    val pollutedList = mutableListOf<Position>()

    private fun setGrid(width: Int, height: Int) {
        require(width in 1..50 && height in 1..50) { "Grid is rectangle: 51>x>0 and 51>y>0" }
        this.width = width
        this.height = height
    }

    private fun setRobotPosition(x: Int, y: Int, direction: Direction) {
        this.x = x
        this.y = y
        robotDirection = direction
        robotPosition = Position(x, y)
    }

    private fun makeMove(movements: List<Movement>, grid: Grid) {
        movements.forEach {
            if (it == Movement.R) {
                robotDirection = when (robotDirection) {
                    Direction.N -> Direction.E
                    Direction.S -> Direction.W
                    Direction.E -> Direction.S
                    Direction.W -> Direction.N
                    Direction.None -> Direction.None
                }
            } else if (it == Movement.L) {
                robotDirection = when (robotDirection) {
                    Direction.N -> Direction.W
                    Direction.S -> Direction.E
                    Direction.E -> Direction.N
                    Direction.W -> Direction.S
                    Direction.None -> Direction.None
                }
            } else if (it == Movement.F) {
                var temporaryX = x
                var temporaryY = y
                when (robotDirection) {
                    Direction.N -> temporaryY += 1
                    Direction.S -> temporaryY -= 1
                    Direction.E -> temporaryX += 1
                    Direction.W -> temporaryX -= 1
                    Direction.None -> Direction.None
                }

                if (temporaryX in 0..width && temporaryY in 0..height) {
                    x = temporaryX
                    y = temporaryY
                    robotPosition = Position(x, y)
                } else if (pollutedList.contains(Position(temporaryX, temporaryY))) {
                    Unit
                } else {
                    pollutedList.add(Position(temporaryX, temporaryY))
                    println("${robotPosition.x} ${robotPosition.y} $robotDirection LOST")
                    return
                }
            }
            grid.drawGrid(width, height, x, y, robotDirection)
        }
        println("${robotPosition.x} ${robotPosition.y} $robotDirection")
    }

    fun startGame(gridSize: String, robotPosition: String, moves: String) {
        val gridSizeParser = parser.splitGridSize(gridSize)
        setGrid(gridSizeParser.first, gridSizeParser.second)

        val robotPositionParser = parser.splitRobotPosition(robotPosition)
        setRobotPosition(robotPositionParser.first, robotPositionParser.second, robotPositionParser.third)

        val movesParser = parser.splitMoves(moves)

        makeMove(movesParser, Grid())
    }
}

fun main() {
    val game = Game(Grid(), Parser())
    game.apply {
//        startGame("5 3", "1 1 E", "RFRFRFRF")
        startGame("3 4", "3 4 W", "FRF")
        println(pollutedList)
        startGame("3 4", "3 4 N", "FRF")
        println(pollutedList)
//        startGame("5 3", "3 2 N", "FRRFLLFFRRFLL")
//        startGame("5 3", "0 3 W", "LLFFFLFLFL")
    }
}
