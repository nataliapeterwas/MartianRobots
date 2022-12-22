class RobotPositionParser(private val robotPositionInput: String) {
    fun robotPositionParser(): Pair<Position, Direction> {
        require(robotPositionInput.split(" ").size == 3 && robotPositionInput.toList()[1] == ' ' && robotPositionInput.toList()[3] == ' ' && robotPositionInput.toList()[4].isLetter()) { "Incorrect robot position" }

        val splitRobotPosition = robotPositionInput.split(" ")

        val x = splitRobotPosition[0].toInt()
        val y = splitRobotPosition[1].toInt()
        val robotPosition = Position(x, y)

        val direction = Direction.valueOf(splitRobotPosition[2])

        return Pair(robotPosition, direction)
    }
}