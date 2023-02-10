package com.natalia.martianrobots.commands

import com.natalia.martianrobots.Direction
import com.natalia.martianrobots.Robot
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

internal class MoveRightCommandTest {
    private val robot = mockk<Robot>()
    private val sut = MoveRightCommand()

    @Test
    fun `change direction from N to E`() {
        // given
        every { robot.robotDirection } returns Direction.N
        justRun { robot.robotDirection = any()}

        // when
        sut.execute(robot)

        // then
        verify { robot.robotDirection = Direction.E }
    }
}