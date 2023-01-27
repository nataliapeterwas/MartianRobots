package com.natalia.mars.factory

import com.natalia.mars.parser.*

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