package com.natalia.martianrobots.commands

import com.natalia.martianrobots.Direction
import com.natalia.martianrobots.Position
import com.natalia.martianrobots.Robot
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class MoveForwardCommandTest {
    private var sut = MoveForwardCommand()

    @ParameterizedTest(name = "given {1}x{2}, direction {0} when execute then position {2}x{3}")
    @MethodSource("provideValues")
    fun `execute`(
        direction: Direction,
        startPositionX: Int,
        startPositionY: Int,
        endPositionX: Int,
        endPositionY: Int,
    ) {
        // given
        val robot = Robot(
            direction = direction,
            position = Position(startPositionX, startPositionY)
        )

        // when
        sut.execute(robot)

        // then
        robot.position.x shouldBeEqualTo endPositionX
        robot.position.y shouldBeEqualTo endPositionY
    }

    companion object {
        private const val START_COORDINATE_X = 2
        private const val START_COORDINATE_Y = 4

        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments(
                Direction.N,
                START_COORDINATE_X,
                START_COORDINATE_Y,
                START_COORDINATE_X,
                START_COORDINATE_Y - 1,
            ),
            arguments(
                Direction.S,
                START_COORDINATE_X,
                START_COORDINATE_Y,
                START_COORDINATE_X,
                START_COORDINATE_Y + 1,
            ),
            arguments(
                Direction.E,
                START_COORDINATE_X,
                START_COORDINATE_Y,
                START_COORDINATE_X + 1,
                START_COORDINATE_Y,
            ),
            arguments(
                Direction.W,
                START_COORDINATE_X,
                START_COORDINATE_Y,
                START_COORDINATE_X - 1,
                START_COORDINATE_Y,
            ),
        )
    }
}