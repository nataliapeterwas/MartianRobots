class MovesParser(private val moves: String, private val robot: Robot, private val grid: Grid) {
    fun movesParser(): List<Command?> {
        require(moves.length < 101) { "Too long instruction" }
        require(robot.robotPosition.x in 0..grid.width && robot.robotPosition.y in 0..grid.height) { "Incorrect position: 0 <= x <= width and 0 <= y <= height" }
        require(moves.toList().all { it.isLetter() && it.isUpperCase() }) { "Incorrect moves" }

        val splitMoves = moves.split("")
            .map {
                when (it) {
                    "F" -> MoveForwardCommand(robot, grid)
                    "R" -> MoveRightCommand(robot)
                    "L" -> MoveLeftCommand(robot)
                    else -> null
                }

//                checkNotNull(command) { "Unknown command character $it" }
            }.toMutableList()

        splitMoves.removeIf { it == null }

        return splitMoves.toList()
    }
}