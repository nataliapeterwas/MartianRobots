class ConfigParser(
    private val grid: Grid,
    private val robot: Robot
) {
    fun parse(input: String): List<Command?> {
        val splitInput = MainInputConfigParser().parse(input)
        val gridSizeParser = GridConfigParser().parse(splitInput.first)

        grid.setSize(gridSizeParser.first, gridSizeParser.second)

        val positionAndDirectionConfigParser = PositionAndDirectionConfigParser().parse(splitInput.second)
        robot.setRobotPosition(positionAndDirectionConfigParser.first, positionAndDirectionConfigParser.second)

        return CommandsConfigParser(splitInput.third, robot, grid).movesParser()
    }

    /*
    parse(input) = Config(
    parseGridConfig(input),
    ...
    )
     */
}