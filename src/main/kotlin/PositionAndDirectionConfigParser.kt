class PositionAndDirectionConfigParser {
    fun parse(input: String): Pair<Position, Direction> {
        val robotPositionSplitAsChar = input.toList()
        val robotPositionSplitWithSpace = input.split(" ")

        require(
            robotPositionSplitWithSpace.size == 3 && robotPositionSplitAsChar.last().isLetter()
        ) { "Incorrect robot position" }

        val x = robotPositionSplitWithSpace[0].toInt()
        val y = robotPositionSplitWithSpace[1].toInt()
        val robotPosition = Position(x, y)

        val direction = Direction.values().firstOrNull { it.letter == robotPositionSplitWithSpace[2] }
            ?: throw Exception("Incorrect direction")

        return Pair(robotPosition, direction)
    }
}

