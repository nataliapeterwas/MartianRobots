package com.natalia.mars

import com.natalia.mars.*
import io.mockk.*
import org.junit.jupiter.api.Test

internal class MoveForwardCommandTest {
    private val robot = mockk<Robot>()
    private val grid = mockk<Grid>()
    private val sut = MoveForwardCommand

    @Test
    fun `class MoveForwardCommand correctly change his position when his direction is S`() {
        // given
        every { grid.height } returns 10
        every { grid.width } returns 10
        every { robot.robotDirection } returns Direction.S
        every { robot.robotPosition } returns Position(1, 3)
        justRun { robot.updatePosition(1, 2)  }

        // when
        sut.execute(robot, grid)

        // then
        verify { robot.updatePosition(1, 2) }

    }

    @Test
    fun `class MoveForwardCommand doing nothing when robot's position is polluted`() {
        // given
        every { grid.height } returns 10
        every { grid.width } returns 10
        every { robot.robotDirection } returns Direction.S
        every { grid.isDeadPoint(0, -1) } returns true
        every { robot.robotPosition } returns Position(0, 0)

        // when
        sut.execute(robot, grid)

        // then
        verify(exactly = 0) { robot.updatePosition(any(), any()) }
    }

    @Test
    fun `class MoveForwardCommand makes that robot loses when he went outside grid`() {
        // given
        every { grid.height } returns 10
        every { grid.width } returns 10
        every { grid.deadPoints } returns mutableListOf()
        every { robot.robotDirection } returns Direction.S
        every { robot.robotPosition } returns Position(0, 0)
        every { robot.robotStatus } returns RobotStatus.ALIVE
        every { grid.isDeadPoint(0, -1) } returns false
        justRun { grid.addDeadPoint(0, -1) }
        justRun { robot.updateRobotStatus(RobotStatus.LOST) }

        // when
        sut.execute(robot, grid)

        // then
        verifyOrder {
            grid.addDeadPoint(0, -1)
            robot.updateRobotStatus(RobotStatus.LOST)
        }
    }
}