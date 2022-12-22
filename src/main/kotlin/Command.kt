interface Command {
    fun execute()
}

data class MoveRightCommand(private val robot: Robot) : Command {
    override fun execute() {
        robot.robotDirection = when (robot.robotDirection) {
            Direction.N -> Direction.E
            Direction.S -> Direction.W
            Direction.E -> Direction.S
            Direction.W -> Direction.N
            else -> null
        }
    }
}

data class MoveLeftCommand(private val robot: Robot) : Command {
    override fun execute() {
        robot.robotDirection = when (robot.robotDirection) {
            Direction.N -> Direction.W
            Direction.S -> Direction.E
            Direction.E -> Direction.N
            Direction.W -> Direction.S
            else -> null
        }
    }
}

data class MoveForwardCommand(private val robot: Robot, private val grid: Grid) : Command {
    override fun execute() {
        var temporaryX = robot.robotPosition.x
        var temporaryY = robot.robotPosition.y
        when (robot.robotDirection) {
            Direction.N -> temporaryY += 1
            Direction.S -> temporaryY -= 1
            Direction.E -> temporaryX += 1
            Direction.W -> temporaryX -= 1
            else -> null
        }

        if (temporaryX in 0..grid.width && temporaryY in 0..grid.height) {
            robot.robotPosition = Position(temporaryX, temporaryY)
        } else if (grid.pollutedList.contains(Position(temporaryX, temporaryY))) {
            Unit
        } else {
            grid.pollutedList.add(Position(temporaryX, temporaryY))
            println("${robot.robotPosition.x} ${robot.robotPosition.y} ${robot.robotDirection} LOST \n")
            robot.robotStatus = Status.LOST
        }
    }
}

