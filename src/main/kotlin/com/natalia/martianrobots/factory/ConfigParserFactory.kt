package com.natalia.martianrobots.factory

import com.natalia.martianrobots.parser.*

class ConfigParserFactory (
    private val mainInputConfigParser: MainInputConfigParser,
    private val positionAndDirectionConfigParser: PositionAndDirectionConfigParser,
    private val gridConfigParser: GridConfigParser,
    private val commandsConfigParser: CommandsConfigParser,
) {
    fun create() = ConfigParser(
        mainInputConfigParser,
        positionAndDirectionConfigParser,
        gridConfigParser,
        commandsConfigParser
    )
}