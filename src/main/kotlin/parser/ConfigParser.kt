package parser

import Config

class ConfigParser {
    fun parse(
        input: String,
        mainInputConfigParser: MainInputConfigParser = MainInputConfigParser(),
        positionAndDirectionConfigParser: PositionAndDirectionConfigParser = PositionAndDirectionConfigParser(),
        gridConfigParser: GridConfigParser = GridConfigParser(),
        commandsConfigParser: CommandsConfigParser = CommandsConfigParser()
    ): Config {
        val splitInput = mainInputConfigParser.parse(input)
        val gridSize = gridConfigParser.parse(splitInput.first)

        val positionAndDirection = positionAndDirectionConfigParser.parse(splitInput.second)

        val commands = commandsConfigParser.parse(splitInput.third)
        return Config(
            robotPosition = positionAndDirection.first,
            robotDirection = positionAndDirection.second,
            gridWidth = gridSize.first,
            gridHeight = gridSize.second,
            commands = commands
        )
    }
}