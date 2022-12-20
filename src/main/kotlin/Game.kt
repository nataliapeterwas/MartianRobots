data class Position(var x: Int, var y: Int)

class Game {
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
                }
            } else if (it == Movement.L) {
                robotDirection = when (robotDirection) {
                    Direction.N -> Direction.W
                    Direction.S -> Direction.E
                    Direction.E -> Direction.N
                    Direction.W -> Direction.S
                }
            } else if (it == Movement.F) {
                var temporaryX = x
                var temporaryY = y
                when (robotDirection) {
                    Direction.N -> temporaryY += 1
                    Direction.S -> temporaryY -= 1
                    Direction.E -> temporaryX += 1
                    Direction.W -> temporaryX -= 1
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

//    private fun drawGrid(
//        width: Int,
//        height: Int,
//        x: Int,
//        y: Int,
//        direction: Direction
//    ) {
//        require(x in 0..width && y in 0..height) { "Incorrect position: 0 <= x <= width and 0 <= y <= height" }
//        val currentPlace = when (direction) {
//            Direction.N -> "↑ "
//            Direction.S -> "↓ "
//            Direction.E -> "→ "
//            Direction.W -> "← "
//        }
//        repeat(height + 1) { yAxis ->
//            repeat(width + 1) { xAxis ->
//                if (xAxis == x && yAxis == height - y) {
//                    print(currentPlace)
//                } else {
//                    print("□ ")
//                }
//            }
//            println()
//        }
//        println()
//    }

    fun startGame(gridSize: String, robotPosition: String, moves: String) {

        val splitGridSize = gridSize.split(" ")

        setGrid(splitGridSize.first().toInt(), splitGridSize.last().toInt())

        val splitRobotPosition = robotPosition.split(" ")

        val x = splitRobotPosition[0].toInt()
        val y = splitRobotPosition[1].toInt()
        val direction = when (splitRobotPosition[2]) {
            "N" -> Direction.N
            "S" -> Direction.S
            "E" -> Direction.E
            "W" -> Direction.W
            else -> return
        }

        setRobotPosition(x, y, direction)

        require(moves.length < 101) { "Too long instruction" }

        val splitMoves = moves.split("")
            .map {
                when (it) {
                    "F" -> Movement.F
                    "R" -> Movement.R
                    "L" -> Movement.L
                    else -> Movement.Nothing
                }
            }
//
//        drawGrid(
//            splitGridSize.first().toInt(),
//            splitGridSize.last().toInt(),
//            x,
//            y,
//            direction
//        )
        makeMove(splitMoves, Grid())
    }
}

fun main() {
    val game = Game()
    game.apply {
        startGame("5 3", "1 1 E", "RFRFRFRF")
//        startGame("5 3", "1 1 E", "FFFFFFFFFF")
//        startGame("5 3", "1 1 E", "FFFFFFFFFF")
//        startGame("5 3", "3 2 N", "FRRFLLFFRRFLL")
//        startGame("5 3", "0 3 W", "LLFFFLFLFL")
    }
}
