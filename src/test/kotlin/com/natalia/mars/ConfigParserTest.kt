package com.natalia.mars

import com.natalia.mars.parser.*
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

internal class ConfigParserTest {
    private val mainInputConfigParser = mockk<MainInputConfigParser>()
    private val positionAndDirectionConfigParser = mockk<PositionAndDirectionConfigParser>()
    private val gridConfigParser = mockk<GridConfigParser>()
    private val commandsConfigParser = mockk<CommandsConfigParser>()

    private val sut =
        ConfigParser(
            mainInputConfigParser,
            positionAndDirectionConfigParser,
            gridConfigParser,
            commandsConfigParser
        )

    @Test
    fun `Parser correctly transforms input to list contains of commandList moveRight, moveForward, moveLeft, moveRight`() {
        // given
        val position = Position(1, 1)
        val gridSize = GridSize(5, 3)

        val gridRobotLogger = mockk<GridRobotLogger>()
        justRun { gridRobotLogger.toString() }

        val moveForwardCommand = MoveForwardCommand()
        val moveRightCommand = MoveRightCommand()
        val moveLeftCommand = MoveLeftCommand()

        val input = "input"

        every { mainInputConfigParser.parse(input) } returns InputParsedToThreeLines(input, input, input)
        every { gridConfigParser.parse(input) } returns gridSize
        every { positionAndDirectionConfigParser.parse(input) } returns RobotPositionAndDirection(position, Direction.E)
        every { commandsConfigParser.parse(input) } returns listOf(
            moveRightCommand,
            moveForwardCommand,
            moveLeftCommand,
            moveRightCommand
        )

        // when
        val actual = sut.parse(input).commands

        // then
        actual shouldBeEqualTo listOf(moveRightCommand, moveForwardCommand, moveLeftCommand, moveRightCommand)
    }
}