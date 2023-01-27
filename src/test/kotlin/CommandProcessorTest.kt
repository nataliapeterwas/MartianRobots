import io.mockk.*
import org.junit.jupiter.api.Test

internal class CommandProcessorTest {
    private val gridRobotLogger = mockk<GridRobotLogger>()

    private val sut = CommandProcessor(gridRobotLogger)

    @Test
    fun `nothing happens when robot status is Lost`() {
        //given
        val robot = mockk<Robot>()
        every { robot.robotStatus } returns RobotStatus.LOST

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
        every { robot.robotStatus } returns RobotStatus.ALIVE

        val grid = mockk<Grid>()

        val moveRightCommand = mockk<MoveRightCommand>()
        justRun { moveRightCommand.execute(robot) }

        val commands = listOf(
            moveRightCommand
        )

        justRun { gridRobotLogger.log(robot, grid) }

        //when
        sut.processCommands(robot, grid, commands)

        //then
        verify  {
            moveRightCommand.execute(robot)
        }
    }

    @Test
    fun `MoveLeftCommand is called with correct parameters`() {
        //given
        val robot = mockk<Robot>()
        every { robot.robotStatus } returns RobotStatus.ALIVE
        every { robot.robotDirection } returns Direction.N
        every { robot.robotPosition } returns Position(1, 2)

        val grid = mockk<Grid>()
        every { grid.height } returns 3
        every { grid.width } returns 5

        val moveLeftCommand = mockk<MoveLeftCommand>()
        justRun { moveLeftCommand.execute(robot) }

        val commands = listOf(
            moveLeftCommand
        )

        justRun { gridRobotLogger.log(robot, grid) }

        //when
        sut.processCommands(robot, grid, commands)

        //then
        verify  {
            moveLeftCommand.execute(robot)
        }
    }

    @Test
    fun `MoveForwardCommand is called with correct parameters`() {
        //given
        val robot = mockk<Robot>()
        every { robot.robotStatus } returns RobotStatus.ALIVE
        every { robot.robotDirection } returns Direction.N
        every { robot.robotPosition } returns Position(1, 2)

        val grid = mockk<Grid>()
        every { grid.height } returns 3
        every { grid.width } returns 5

        val moveForwardCommand = mockk<MoveForwardCommand>()
        justRun { moveForwardCommand.execute(robot, grid) }

        val commands = listOf(
            moveForwardCommand
        )

        justRun { gridRobotLogger.log(robot, grid) }

        //when
        sut.processCommands(robot, grid, commands)

        //then
        verify  {
            moveForwardCommand.execute(robot, grid)
        }
    }

    @Test
    fun `processCommands calls three commands when robot status is Alive`() {
        //given
        val input = "input"

        val robot = mockk<Robot>()
        every { robot.robotStatus } returns RobotStatus.ALIVE
//        every { robot.robotDirection } returns Direction.N
//        every { robot.robotPosition } returns Position(1, 2)

        val grid = mockk<Grid>()
//        every { grid.height } returns 3
//        every { grid.width } returns 5

        justRun { gridRobotLogger.log(robot, grid) }

        val moveForwardCommand = mockk<MoveForwardCommand>()
        justRun { moveForwardCommand.execute(robot, grid) }
        val moveRightCommand = mockk<MoveRightCommand>()
        justRun { moveRightCommand.execute(robot) }
        val moveLeftCommand = mockk<MoveLeftCommand>()
        justRun { moveLeftCommand.execute(robot) }

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
            moveForwardCommand.execute(robot, grid)
            moveLeftCommand.execute(robot)
        }
    }

    @Test
    fun `processCommands calls command when robot status is Alive and not call when the status is Lost`() {
        //given
        val robot = mockk<Robot>()
        every { robot.robotStatus } returns RobotStatus.ALIVE andThen RobotStatus.LOST

        every { robot.robotDirection } returns Direction.N
        every { robot.robotPosition } returns Position(1, 2)

        val grid = mockk<Grid>()
        every { grid.height } returns 3
        every { grid.width } returns 5

        justRun { gridRobotLogger.log(robot, grid) }

        val moveForwardCommand = mockk<MoveForwardCommand>()
        justRun { moveForwardCommand.execute(robot, grid) }
        val moveRightCommand = mockk<MoveRightCommand>()
        justRun { moveRightCommand.execute(robot) }
        val moveLeftCommand = mockk<MoveLeftCommand>()
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
            moveForwardCommand.execute(robot, grid)
            moveLeftCommand.execute(robot)
        }
    }

    @Test
    fun `logger is called when robot status is Alive`() {
        //given
        val robot = mockk<Robot>()
        every { robot.robotStatus } returns RobotStatus.ALIVE
//        every { robot.robotDirection } returns Direction.N
        every { robot.robotPosition } returns Position(1, 2)

        val grid = mockk<Grid>()
        every { grid.height } returns 3
        every { grid.width } returns 5

        val moveRightCommand = mockk<MoveRightCommand>()
        justRun { moveRightCommand.execute(robot) }

        val commands = listOf(
            moveRightCommand
        )

        justRun { gridRobotLogger.log(robot, grid) }

        //when
        sut.processCommands(robot, grid, commands)

        //then
        verify  {
            gridRobotLogger.log(robot, grid)
        }
    }
}
