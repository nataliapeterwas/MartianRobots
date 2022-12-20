class Parser {
    fun splitGridSize(gridSize: String): Pair<Int, Int> {
        val splitGridSize = gridSize.split(" ")
        val width = splitGridSize.first().toInt()
        val height = splitGridSize.last().toInt()
        return Pair(width, height)
    }

    fun splitRobotPosition(robotPosition: String): Triple<Int, Int, Direction> {
        val splitRobotPosition = robotPosition.split(" ")

        val x = splitRobotPosition[0].toInt()
        val y = splitRobotPosition[1].toInt()
        val direction = when (splitRobotPosition[2]) {
            "N" -> Direction.N
            "S" -> Direction.S
            "E" -> Direction.E
            "W" -> Direction.W
            else -> Direction.None
        }
        return Triple(x, y, direction)
    }

    fun splitMoves(moves: String): List<Movement> {
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
        return splitMoves
    }

}