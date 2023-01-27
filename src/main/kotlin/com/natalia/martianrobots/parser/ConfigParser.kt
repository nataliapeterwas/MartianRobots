package com.natalia.martianrobots.parser

import com.natalia.martianrobots.Config

class ConfigParser(
    private val mainInputConfigParser: MainInputConfigParser,
    private val positionAndDirectionConfigParser: PositionAndDirectionConfigParser,
    private val gridConfigParser: GridConfigParser,
    private val commandsConfigParser: CommandsConfigParser,
) {
    fun parse(input: String): Config {
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