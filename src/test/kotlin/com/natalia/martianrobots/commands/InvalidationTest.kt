package com.natalia.martianrobots.commands

import com.natalia.martianrobots.Direction
import com.natalia.martianrobots.Grid
import com.natalia.martianrobots.Position
import com.natalia.martianrobots.Robot
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verifyOrder
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

internal class InvalidationTest {
    val sut = Invalidation()

    @Test
    fun `return true when robot is on the grid`() {
        // given
        val robot = mockk<Robot>()
        every { robot.position } returns Position(2, 3)

        val grid = mockk<Grid>()
        every { grid.width } returns 5
        every { grid.height } returns 4

        //when
        val actual = sut.invalidatePosition(robot, grid)

        //then
        actual shouldBeEqualTo true
    }

    @Test
    fun `return false when robot is on DeadPoint`() {
        // given
        val robot = mockk<Robot>()
        every { robot.position } returns Position(10, 3)

        val grid = mockk<Grid>()
        every { grid.width } returns 5
        every { grid.height } returns 4

        every { grid.isDeadPoint(10, 3) } returns true

        //when
        val actual = sut.invalidatePosition(robot, grid)

        //then
        actual shouldBeEqualTo false
    }

    @Test
    fun `return false when robot is out the grid`() {
        // given
        val robot = mockk<Robot>()
        every { robot.position } returns Position(10, 3)
        every { robot.direction } returns Direction.N

        val grid = mockk<Grid>()
        every { grid.width } returns 5
        every { grid.height } returns 4

        every { grid.isDeadPoint(10, 3) } returns false
        justRun { grid.addDeadPoint(10, 3) }
        justRun { robot.updateRobotStatus(false) }

        //when
        val actual = sut.invalidatePosition(robot, grid)

        //then
        actual shouldBeEqualTo false
    }

    @Test
    fun `call all instruction when robot is out the grid`() {
        // given
        val robot = mockk<Robot>()
        every { robot.position } returns Position(10, 3)
        every { robot.direction } returns Direction.N

        val grid = mockk<Grid>()
        every { grid.width } returns 5
        every { grid.height } returns 4

        every { grid.isDeadPoint(10, 3) } returns false
        justRun { grid.addDeadPoint(10, 3) }
        justRun { robot.updateRobotStatus(false) }

        //when
        sut.invalidatePosition(robot, grid)

        //then
        verifyOrder {
            grid.addDeadPoint(10, 3)
            robot.updateRobotStatus(false)
        }
    }
}