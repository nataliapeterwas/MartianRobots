package com.natalia.martianrobots.parser

import com.natalia.martianrobots.commands.MoveForwardCommand
import com.natalia.martianrobots.commands.MoveLeftCommand
import com.natalia.martianrobots.commands.MoveRightCommand
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

internal class CommandsConfigParserTest {
    private val moveRightCommand = mockk<MoveRightCommand>()
    private val moveLeftCommand = mockk<MoveLeftCommand>()
    private val moveForwardCommand = mockk<MoveForwardCommand>()

    private val sut = CommandsConfigParser(
        moveForwardCommand,
        moveRightCommand,
        moveLeftCommand
    )

    @Test
    fun `throw exception when parse input contains digit`() {
        // given
        val input = "RL1"

        // when
        val actual = { sut.parse(input) }

        // then
        actual shouldThrow Exception::class withMessage "Incorrect move"
    }

    @Test
    fun `return list of commands`() {
        // given
        val input = "LFR"

        // when
        val actual = sut.parse(input)

        // then
        actual shouldBeEqualTo listOf(moveLeftCommand, moveForwardCommand, moveRightCommand)
    }
}
