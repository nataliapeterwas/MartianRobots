package com.natalia.martianrobots.parser

import com.natalia.martianrobots.Direction
import com.natalia.martianrobots.Position
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

internal class PositionAndDirectionConfigParserTest{

    @Test
    fun `throw exception when we pass incorrect robotPosition (it length is 1)`() {
        // given
        val input = "1"

        // when
        val actual = { PositionAndDirectionConfigParser().parse(input)}

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Incorrect robot position"
    }

    @Test
    fun `throw exception when we pass incorrect robotPosition (incorrect format - 1 E 1)`() {
        // given
        val input = "1 E 1"

        // when
        val actual = { PositionAndDirectionConfigParser().parse(input)}

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Incorrect robot position"
    }

    @Test
    fun `transform string '30 5 E' to robotPosition and robotDirection`() {
        // given
        val robotPosition = "30 5 E"

        // when
        val actual = PositionAndDirectionConfigParser().parse(robotPosition)

        // then
        actual.position shouldBeEqualTo Position(30, 5)
        actual.direction shouldBeEqualTo Direction.E
    }

    @Test
    fun `throw exception when its width is 60 and its bigger than grid size`() {
        // given
        val robotPosition = "60 5 K"

        // when
        val actual = { PositionAndDirectionConfigParser().parse(robotPosition)}

        // then
        actual shouldThrow Exception::class withMessage "Incorrect direction"
    }
}