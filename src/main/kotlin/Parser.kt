class Parser(
    private val grid: Grid,
    private val robot: Robot
) {
    fun parse(input: String): List<Command?> {
        val splitInput = MainInputParser(input).mainInput()
        val gridSizeParser = GridConfigParser(splitInput.first).gridSizeParser()

        grid.setSize(gridSizeParser.first, gridSizeParser.second)

        val robotPositionParser = RobotPositionParser(splitInput.second).robotPositionParser()
        robot.setRobotPosition(robotPositionParser.first, robotPositionParser.second)

        return CommandsConfigParser(splitInput.third, robot, grid).movesParser()
    }

    /*
    parse(input) = Config(
    parseGridConfig(input),
    ...
    )
     */
}