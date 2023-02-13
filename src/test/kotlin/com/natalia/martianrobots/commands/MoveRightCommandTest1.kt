package com.natalia.martianrobots.commands

import com.natalia.martianrobots.Direction
import com.natalia.martianrobots.Position
import com.natalia.martianrobots.Robot
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class MoveRightCommandTest {
    private var sut = MoveRightCommand()

    @ParameterizedTest(name = "given {1}x{2}, direction {0} when execute then position {2}x{3}")
    @MethodSource("provideValues")
    fun `execute`(
        startDirection: Direction,
        endDirection: Direction,
        position: Position
    ) {
        // given
        val robot = Robot(
            direction = startDirection,
            position = position
        )

        // when
        sut.execute(robot)

        // then
        robot.direction shouldBeEqualTo endDirection
    }

    companion object {
        private val position = Position(2,3)

        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            Arguments.arguments(
                Direction.N,
                Direction.E,
                position
            ),
            Arguments.arguments(
                Direction.S,
                Direction.W,
                position
            ),
            Arguments.arguments(
                Direction.E,
                Direction.S,
                position
            ),
            Arguments.arguments(
                Direction.W,
                Direction.N,
                position
            ),
        )
    }
}