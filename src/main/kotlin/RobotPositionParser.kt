class RobotPositionParser(private val robotPositionInput: String) {
    fun robotPositionParser(): Pair<Position, Direction> {
        val robotPositionSplitAsChar = robotPositionInput.toList()
        val robotPositionSplitWithSpace = robotPositionInput.split(" ")

        require(robotPositionSplitWithSpace.size == 3 &&  robotPositionSplitAsChar.last().isLetter()) { "Incorrect robot position" }

        val x = robotPositionSplitWithSpace[0].toInt()
        val y = robotPositionSplitWithSpace[1].toInt()
        val robotPosition = Position(x, y)

        val direction = Direction.valueOf(robotPositionSplitWithSpace[2])

        return Pair(robotPosition, direction)
    }
}