package parser

import Config

class ConfigParser {
    fun parse(
        input: String,
        mainInputConfigParser: MainInputConfigParser,
        positionAndDirectionConfigParser: PositionAndDirectionConfigParser,
        gridConfigParser: GridConfigParser,
        commandsConfigParser: CommandsConfigParser,
    ): Config {
        val splitInput = mainInputConfigParser.parse(input)
        val gridSize = gridConfigParser.parse(splitInput.gridSizeInfo)

        val positionAndDirection = positionAndDirectionConfigParser.parse(splitInput.robotInfo)

        val commands = commandsConfigParser.parse(splitInput.commandsInfo)
        return Config(
            robotPosition = positionAndDirection.position,
            robotDirection = positionAndDirection.direction,
            gridWidth = gridSize.width,
            gridHeight = gridSize.height,
            commands = commands
        )
    }
}