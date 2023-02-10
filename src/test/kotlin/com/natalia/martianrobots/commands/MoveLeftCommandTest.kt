package com.natalia.martianrobots.commands

import com.natalia.martianrobots.Direction
import com.natalia.martianrobots.Robot
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

internal class MoveLeftCommandTest{
    private val robot = mockk<Robot>()
    private val sut = MoveLeftCommand()

    @Test
    fun `change direction from W to S`(){
        // given
        every { robot.robotDirection} returns Direction.W
        justRun { robot.robotDirection = any() }

        // when
        sut.execute(robot)

        // then
        verify { robot.robotDirection = Direction.S }
    }

}