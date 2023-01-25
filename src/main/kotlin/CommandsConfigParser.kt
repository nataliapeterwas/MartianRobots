class CommandsConfigParser(private val moves: String, private val robot: Robot, private val grid: Grid) {
    fun movesParser(): List<Command?> {
        require(moves.length < 101) { "Too long instruction" }
        require(robot.robotPosition.x in 0..grid.width && robot.robotPosition.y in 0..grid.height) { "Incorrect position: 0 <= x <= width and 0 <= y <= height" }
        require(moves.toList().all { it.isLetter() && it.isUpperCase() }) { "Incorrect moves" }

        val splitMovesString = moves.split("").toMutableList()
                splitMovesString.removeIf { it.isEmpty() }

        val splitMoves = splitMovesString.map {
                when (it) {
                    "F" -> MoveForwardCommand(robot, grid)
                    "R" -> MoveRightCommand(robot)
                    "L" -> MoveLeftCommand(robot)
                    else -> throw Exception("Incorrect move")
                }
            }

        return splitMoves.toList()
    }
}