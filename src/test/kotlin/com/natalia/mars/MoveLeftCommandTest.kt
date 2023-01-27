package com.natalia.mars

import com.natalia.mars.Direction
import com.natalia.mars.MoveLeftCommand
import com.natalia.mars.Robot
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

internal class MoveLeftCommandTest{
    private val robot = mockk<Robot>()
    private val sut = MoveLeftCommand

    @Test
    fun `class MoveLeftCommand correctly change direction from W to S`(){
        // given
        every { robot.robotDirection} returns Direction.W
        justRun { robot.robotDirection = any() }

        // when
        sut.execute(robot)

        // then
        verify { robot.robotDirection = Direction.S }
    }

}