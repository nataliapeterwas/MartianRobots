package com.natalia.martianrobots.commands

import com.natalia.martianrobots.*
import io.mockk.*
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

internal class MoveForwardCommandTest2 {
    private val robot = mockk<Robot>()

    private val sut = MoveForwardCommand()

    @Test
    fun `changes robot position when his direction is S`() {
        // given
        every { robot.robotDirection } returns Direction.S
        every { robot.robotPosition } returns Position(1, 3)

        // when
        sut.execute(robot)

        // then
        robot.robotPosition shouldBeEqualTo Position(1, 4)
    }

    @Test
    fun `doing nothing when robot's position is polluted`() {
        // given
        every { robot.robotDirection } returns Direction.N
        every { robot.robotPosition } returns Position(0, 0)

        // when
        sut.execute(robot)

        // then
        robot.robotPosition shouldBeEqualTo Position(0, -1)
    }
}