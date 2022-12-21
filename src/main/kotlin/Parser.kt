class Parser {
    fun mainInput(input: String): Triple<String, String, String> {
        require(input.isNotEmpty()) {"You must pass grid size, robot position and robot moves"}

        val splitMainInput = input.split("\n").toMutableList()

        require(splitMainInput.size == 3) {"Incorrect input"}

        val gridSize = splitMainInput[0]
        val robotPosition = splitMainInput[1]
        val moves = splitMainInput[2]

        require(gridSize.split(" ").size == 2 && gridSize.toList()[1] == ' ') {"Incorrect grid size"}
        require(robotPosition.split(" ").size == 3 && robotPosition.toList()[1] == ' ' && robotPosition.toList()[3] == ' ' && robotPosition.toList()[4].isLetter()) {"Incorrect robot position"}
        require(moves.toList().all { it.isLetter() && it.isUpperCase()}) {"Incorrect moves"}

        return Triple(gridSize, robotPosition, moves)
    }

    fun splitGridSize(gridSize: String): Pair<Int, Int> {
        val splitGridSize = gridSize.split(" ")
        val width = splitGridSize.first().toInt()
        val height = splitGridSize.last().toInt()

        require(width in 0..50 && height in 0..50) { "Grid is rectangle: 51>x>0 and 51>y>0" }
        return Pair(width, height)
    }

    fun splitRobotPosition(robotPosition: String): Triple<Int, Int, Direction> {
        val splitRobotPosition = robotPosition.split(" ")

        val x = splitRobotPosition[0].toInt()
        val y = splitRobotPosition[1].toInt()

        val direction = Direction.valueOf(splitRobotPosition[2])

        return Triple(x, y, direction)
    }

    fun splitMoves(moves: String, robot: Robot, grid: Grid): List<Command> {
        require(moves.length < 101) { "Too long instruction" }

        val splitMoves = moves.split("")
            .map {
                when (it) {
                    "F" -> MoveForwardCommand(robot, grid)
                    "R" -> MoveRightCommand(robot)
                    "L" -> MoveLeftCommand(robot)
                    else -> NoCommand()
                }
            }.toMutableList()

        splitMoves.removeFirst()
        splitMoves.removeLast()
        splitMoves.toList()

        return splitMoves
    }

}