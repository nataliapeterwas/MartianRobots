package com.natalia.martianrobots.parser

import com.natalia.martianrobots.commands.MoveForwardCommand
import com.natalia.martianrobots.commands.MoveLeftCommand
import com.natalia.martianrobots.commands.MoveRightCommand
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

internal class MainInputConfigParserTest {
    @Test
    fun `correctly split input to three separate strings`() {
        // given
        val input = """
            5 3
            1 1 E
            RFRFRFRF
        """.trimIndent()

        // when
        val actual = MainInputConfigParser().parse(input)

        // then
        actual.gridSizeInfo shouldBeEqualTo "5 3"
        actual.robotInfo shouldBeEqualTo "1 1 E"
        actual.commandsInfo shouldBeEqualTo "RFRFRFRF"
    }

    @Test
    fun `throws exception when we pass empty input`() {
        // given
        val input = """
        """.trimIndent()

        // when
        val actual = { MainInputConfigParser().parse(input) }

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "You must pass grid size, robot position and robot moves"
    }

    @Test
    fun `throws exception when we pass incorrect input (two lines instead of three)`() {
        // given
        val input = """
            66
            1
        """.trimIndent()

        // when
        val actual = { MainInputConfigParser().parse(input) }

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Incorrect input"
    }

    @Test
    fun `transforms 'FRLLL' to list contains commandList moveForward, moveRight, moveLeft, moveLeft, moveLeft`() {
        // given
        val input = "FRLLL"
        val moveForwardCommand = mockk<MoveForwardCommand>()
        val moveRightCommand = mockk<MoveRightCommand>()
        val moveLeftCommand = mockk<MoveLeftCommand>()

        // when
        val actual = CommandsConfigParser(
            moveForwardCommand,
            moveRightCommand,
            moveLeftCommand
        ).parse(input)

        // then
        actual shouldBeEqualTo listOf(
            moveForwardCommand,
            moveRightCommand,
            moveLeftCommand,
            moveLeftCommand,
            moveLeftCommand
        )
    }

}