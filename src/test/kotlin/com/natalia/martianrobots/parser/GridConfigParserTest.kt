package com.natalia.martianrobots.parser

import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test
import com.natalia.martianrobots.parser.GridConfigParser

internal class GridConfigParserTest {
    private val sut = GridConfigParser()

    @Test
    fun `throws exception when we pass incorrect gridSize (it length is 2)`() {
        // given
        val input = "66"

        // when
        val actual = { sut.parse(input) }

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Incorrect grid size"
    }

    @Test
    fun `throws exception when we pass incorrect gridSize (it has blank space at beginning)`() {
        // given
        val input = " 66"

        // when
        val actual = { sut.parse(input) }

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Incorrect grid size"
    }

    @Test
    fun `transforms string '30 5' to grid width and grid height`() {
        // given
        val input = "30 5"

        // when
        val actual = sut.parse(input)

        // then
        actual.width shouldBeEqualTo 30
        actual.height shouldBeEqualTo 5
    }
}