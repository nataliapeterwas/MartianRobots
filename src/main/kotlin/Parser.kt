class Parser(
    private val input: String,
    private val grid: Grid,
    private val robot: Robot
) {
    fun parse(): List<Command?> {
        val splitInput = MainInputParser(input).mainInput()
        val gridSizeParser = GridSizeParser(splitInput.first).gridSizeParser()

        grid.setGrid(gridSizeParser.first, gridSizeParser.second)

        val robotPositionParser = RobotPositionParser(splitInput.second).robotPositionParser()
        robot.setRobotPosition(robotPositionParser.first, robotPositionParser.second)

        return MovesParser(splitInput.third, robot, grid).movesParser()
    }
}