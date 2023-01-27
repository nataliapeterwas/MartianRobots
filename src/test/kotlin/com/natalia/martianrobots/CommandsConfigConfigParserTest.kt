package com.natalia.martianrobots

import com.natalia.martianrobots.parser.CommandsConfigParser
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

internal class CommandsConfigConfigParserTest {
    private val moveRightCommand = mockk<MoveRightCommand>()
    private val moveLeftCommand = mockk<MoveLeftCommand>()
    private val moveForwardCommand = mockk<MoveForwardCommand>()

    @Test
    fun `MovesParser throws exception when we pass incorrect moves (contains Digit)`() {
        // given
        val input = "RR1"
        val robot = mockk<Robot>()
        every { robot.robotPosition } returns Position(3, 4)
        val grid = mockk<Grid>()
        every { grid.width } returns 6
        every { grid.height } returns 4

        // when
        val actual = {
            CommandsConfigParser(
                moveForwardCommand,
                moveRightCommand,
                moveLeftCommand
            ).parse(input)
        }

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Incorrect moves"
    }

    @Test
    fun `MovesParser throws exception when we pass incorrect moves (contains blank space)`() {
        // given
        val input = "RL "
        val robot = mockk<Robot>()
        every { robot.robotPosition } returns Position(3, 4)
        val grid = mockk<Grid>()
        every { grid.width } returns 6
        every { grid.height } returns 4

        // when
        val actual = {
            CommandsConfigParser(
                moveForwardCommand,
                moveRightCommand,
                moveLeftCommand
            ).parse(input)
        }

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Incorrect moves"
    }

    @Test
    fun `MovesParser throws exception when we pass incorrect moves (contains small letter)`() {
        // given
        val input = "LrF"
        val robot = mockk<Robot>()
        every { robot.robotPosition } returns Position(3, 4)
        val grid = mockk<Grid>()
        every { grid.width } returns 6
        every { grid.height } returns 4

        // when
        val actual = {
            CommandsConfigParser(
                moveForwardCommand,
                moveRightCommand,
                moveLeftCommand
            ).parse(input)
        }

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Incorrect moves"
    }

}