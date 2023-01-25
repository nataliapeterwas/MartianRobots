sealed interface Command

object MoveRightCommand : Command {
    fun execute(robot: Robot) {
        robot.robotDirection = when (robot.robotDirection) {
            Direction.N -> Direction.E
            Direction.S -> Direction.W
            Direction.E -> Direction.S
            Direction.W -> Direction.N
        }
    }
}

object MoveLeftCommand : Command {
    fun execute(robot: Robot) {
        robot.robotDirection = when (robot.robotDirection) {
            Direction.N -> Direction.W
            Direction.S -> Direction.E
            Direction.E -> Direction.N
            Direction.W -> Direction.S
        }
    }
}

object MoveForwardCommand : Command {
    fun execute(robot: Robot, grid: Grid) {
        var temporaryX = robot.robotPosition.x
        var temporaryY = robot.robotPosition.y

        when (robot.robotDirection) {
            Direction.N -> temporaryY += 1
            Direction.S -> temporaryY -= 1
            Direction.E -> temporaryX += 1
            Direction.W -> temporaryX -= 1
        }

        if (temporaryX in 0..grid.width && temporaryY in 0..grid.height) {
            robot.updatePosition(temporaryX, temporaryY)
        } else if (!grid.hasPositionInPollutedList(temporaryX, temporaryY)) {
            grid.addPositionToPollutedList(temporaryX, temporaryY)
            println("${robot.robotPosition.x} ${robot.robotPosition.y} ${robot.robotDirection} LOST \n")
            robot.updateRobotStatus(RobotStatus.LOST)
        }
    }
}