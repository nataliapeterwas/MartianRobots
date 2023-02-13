package com.natalia.martianrobots.commands

import com.natalia.martianrobots.Grid
import com.natalia.martianrobots.GridRobotLogger
import com.natalia.martianrobots.Position
import com.natalia.martianrobots.Robot
import io.mockk.*
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

internal class CommandProcessorTest {
    private val gridRobotLogger = mockk<GridRobotLogger>()
    private val invalidation = mockk<Invalidation>()
    private val moveRightCommand = mockk<MoveRightCommand>()
    private val moveLeftCommand = mockk<MoveLeftCommand>()
    private val moveForwardCommand = mockk<MoveForwardCommand>()

    private val sut = CommandProcessor(
        gridRobotLogger,
        invalidation
    )

    @Test
    fun `nothing happens when robot status is Lost`() {
        //given
        val robot = mockk<Robot>()
        every { robot.isAlive } returns false

        val grid = mockk<Grid>()

        val commands = listOf<Command>()

        justRun { gridRobotLogger.log(robot, grid) }

        //when
        sut.processCommands(robot, grid, commands)

        //then
        verify(exactly = 0) {
            gridRobotLogger.log(robot, grid)
        }
    }

    @Test
    fun `MoveRightCommand is called with correct parameters`() {
        //given
        val robot = mockk<Robot>()
        every { robot.isAlive } returns true
        every { robot.robotPosition } returns Position(2, 3)

        val grid = mockk<Grid>()

        justRun { moveRightCommand.execute(robot) }

        val commands = listOf(
            moveRightCommand
        )

        justRun { gridRobotLogger.log(robot, grid) }

        //when
        sut.processCommands(robot, grid, commands)

        // then
        verify {
            moveRightCommand.execute(robot)
        }
    }

    @Test
    fun `MoveLeftCommand is called with correct parameters`() {
        //given
        val robot = mockk<Robot>()
        every { robot.isAlive } returns true
        every { robot.robotPosition } returns Position(2, 3)

        val grid = mockk<Grid>()

        justRun { moveLeftCommand.execute(robot) }

        val commands = listOf(
            moveLeftCommand
        )

        justRun { gridRobotLogger.log(robot, grid) }

        //when
        sut.processCommands(robot, grid, commands)

        //then
        verify {
            moveLeftCommand.execute(robot)
        }
    }

    @Test
    fun `MoveForwardCommand is called with correct parameters`() {
        //given
        val robot = mockk<Robot>()
        every { robot.robotPosition } returns Position(2, 3)
        every { robot.isAlive } returns true

        val grid = mockk<Grid>()
        every { grid.width } returns 4
        every { grid.height } returns 5

        justRun { moveForwardCommand.execute(robot) }
        every { invalidation.invalidatePosition(robot, grid) } returns true

        val commands = listOf(
            moveForwardCommand
        )

        justRun { gridRobotLogger.log(robot, grid) }

        //when
        sut.processCommands(robot, grid, commands)

        //then
        verify {
            moveForwardCommand.execute(robot)
        }
    }

    @Test
    fun `processCommands calls three commands when robot status is Alive`() {
        //given
        val robot = mockk<Robot>()
        every { robot.robotPosition } returns Position(2, 3)

        every { robot.isAlive } returns true

        val grid = mockk<Grid>()
        every { grid.width } returns 4
        every { grid.height } returns 5

        justRun { gridRobotLogger.log(robot, grid) }

        justRun { moveForwardCommand.execute(robot) }
        justRun { moveRightCommand.execute(robot) }
        justRun { moveLeftCommand.execute(robot) }

        every { invalidation.invalidatePosition(robot, grid) } returns true

        val commands = listOf(
            moveRightCommand,
            moveForwardCommand,
            moveLeftCommand
        )

        //when
        sut.processCommands(robot, grid, commands)

        //then
        verifyOrder {
            moveRightCommand.execute(robot)
            moveForwardCommand.execute(robot)
            moveLeftCommand.execute(robot)
        }
    }

    @Test
    fun `processCommands calls command when robot status is Alive and not call when the status is Lost`() {
        //given
        val robot = mockk<Robot>()
        every { robot.isAlive } returns true andThen false
        every { robot.robotPosition } returns Position(0, 0)

        val grid = mockk<Grid>()

        justRun { gridRobotLogger.log(robot, grid) }

        justRun { moveForwardCommand.execute(robot) }
        justRun { moveRightCommand.execute(robot) }
        justRun { moveLeftCommand.execute(robot) }

        val commands = listOf(
            moveRightCommand,
            moveForwardCommand,
            moveLeftCommand
        )

        //when
        sut.processCommands(robot, grid, commands)

        //then
        verify {
            moveRightCommand.execute(robot)
        }
        verify(exactly = 0) {
            moveForwardCommand.execute(robot)
            moveLeftCommand.execute(robot)
        }
    }

    @Test
    fun `logger is called when robot status is Alive`() {
        //given
        val robot = mockk<Robot>()
        every { robot.isAlive } returns true
        every { robot.robotPosition } returns Position(2, 3)

        val grid = mockk<Grid>()

        justRun { moveRightCommand.execute(robot) }

        val commands = listOf(
            moveRightCommand
        )

        justRun { gridRobotLogger.log(robot, grid) }

        //when
        sut.processCommands(robot, grid, commands)

        //then
        verify {
            gridRobotLogger.log(robot, grid)
        }
    }

    @Test
    fun `robotPosition changes to the previous when invalidation returns false`() {
        //given
        val robot = mockk<Robot>()
        every { robot.isAlive } returns true
        every { robot.robotPosition } returns Position(2, 3)

        val beforeRobotPosition =  Position(2,3)

        val grid = mockk<Grid>()

        justRun { moveForwardCommand.execute(robot) }
        every { invalidation.invalidatePosition(robot, grid) } returns false

        val commands = listOf(
            moveForwardCommand
        )

        justRun { gridRobotLogger.log(robot, grid) }
        justRun { robot.robotPosition = beforeRobotPosition }

        //when
        sut.processCommands(robot, grid, commands)

        //then
        verify {
            robot.robotPosition = beforeRobotPosition
        }
    }
}
