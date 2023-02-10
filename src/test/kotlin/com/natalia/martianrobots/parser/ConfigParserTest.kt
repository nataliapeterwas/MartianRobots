package com.natalia.martianrobots.parser

import com.natalia.martianrobots.*
import com.natalia.martianrobots.commands.MoveForwardCommand
import com.natalia.martianrobots.commands.MoveLeftCommand
import com.natalia.martianrobots.commands.MoveRightCommand
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
    fun `transforms input to list contains commandList moveRight, moveForward, moveLeft, moveRight`() {
        // given
        val position = Position(1, 1)
        val gridSize = GridSize(5, 3)

        val gridRobotLogger = mockk<GridRobotLogger>()
        justRun { gridRobotLogger.toString() }

        val moveForwardCommand = MoveForwardCommand()
        val moveRightCommand = MoveRightCommand()
        val moveLeftCommand = MoveLeftCommand()

        val input = "input"

        every { mainInputConfigParser.parse(input) } returns InputParsedToThreeLines("first", "second", "third")
        every { gridConfigParser.parse("first") } returns gridSize
        every { positionAndDirectionConfigParser.parse("second") } returns RobotPositionAndDirection(position, Direction.E)
        every { commandsConfigParser.parse("third") } returns listOf(
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