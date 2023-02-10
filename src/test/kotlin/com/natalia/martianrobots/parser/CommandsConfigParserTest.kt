package com.natalia.martianrobots.parser

import com.natalia.martianrobots.commands.MoveForwardCommand
import com.natalia.martianrobots.commands.MoveLeftCommand
import com.natalia.martianrobots.commands.MoveRightCommand
import io.mockk.mockk
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

internal class CommandsConfigParserTest {
    private val moveRightCommand = mockk<MoveRightCommand>()
    private val moveLeftCommand = mockk<MoveLeftCommand>()
    private val moveForwardCommand = mockk<MoveForwardCommand>()

    @Test
    fun `MovesParser throws exception when we pass incorrect moves (contains Digit)`() {
        // given
        val input = "RR1"

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
    fun `throws exception when we pass incorrect moves (contains blank space)`() {
//    fun `throws exception when parse input contains space`() {
        // given
        val input = "RL "

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
