import io.mockk.*
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

internal class GameTest {
    @Test
    fun `startGame calls three commands`(){
        //given
        val robot = mockk<Robot>()
        every { robot.robotStatus } returns Status.ALIVE

        val grid = mockk<Grid>()

        val gridRobotLogger = mockk<GridRobotLogger>()
        every { gridRobotLogger.toString() } returns ""

        val input = """
             5 3
             1 1 E
             RFL
        """.trimIndent()

        val parserFactory = mockk<ParserFactory>()
        val parser = mockk<Parser>()

        every { parserFactory.create(input, grid, robot) } returns parser
        val moveForwardCommand = mockk<MoveForwardCommand>()
        every { moveForwardCommand.execute() } just Runs
        val moveRightCommand = mockk<MoveRightCommand>()
        every { moveRightCommand.execute() } just Runs
        val moveLeftCommand = mockk<MoveLeftCommand>()
        every { moveLeftCommand.execute() } just Runs

        every { parser.parse() } returns listOf(moveRightCommand, moveForwardCommand, moveLeftCommand)

        val sut = Game(grid, robot, parserFactory, gridRobotLogger)

        //when
        sut.startGame(input)

        verifyOrder {
            moveRightCommand.execute()
            moveForwardCommand.execute()
            moveLeftCommand.execute()
        }
    }

    @Test
    fun `startGame() calls two commands and the last is not called`(){
        //given
        val robot = mockk<Robot>()
        every { robot.robotStatus } returnsMany listOf(Status.ALIVE, Status.ALIVE, Status.LOST)

        val grid = mockk<Grid>()

        val gridRobotLogger = mockk<GridRobotLogger>()
        every { gridRobotLogger.toString() } returns ""

        val input = """
             5 3
             1 1 E
             RFF
        """.trimIndent()

        val parserFactory = mockk<ParserFactory>()
        val parser = mockk<Parser>()

        every { parserFactory.create(input, grid, robot) } returns parser
        val moveForwardCommand = mockk<MoveForwardCommand>()
        every { moveForwardCommand.execute() } just Runs
        val moveRightCommand = mockk<MoveRightCommand>()
        every { moveRightCommand.execute() } just Runs
        val moveLeftCommand = mockk<MoveLeftCommand>()
        every { moveLeftCommand.execute() } just Runs

        every { parser.parse() } returns listOf(moveRightCommand, moveForwardCommand, moveLeftCommand)

        val sut = Game(grid, robot, parserFactory, gridRobotLogger)

        //when
        sut.startGame(input)

        verify(exactly = 0) { moveLeftCommand.execute()}
    }

    /*
    @Test
    fun `startGame sets robot on correct position after changes`() {
        // given
        val robot = mockk<Robot>()
        every { robot.robotDirection } answers { Direction.E }
        every { robot.robotPosition } answers { Position(1, 1) }
        every { robot.robotStatus } answers { Status.ALIVE }
        justRun { robot.setRobotPosition(any(), any()) }
        justRun { robot.robotDirection = any()}
        justRun { robot.robotPosition = any() }

        val grid = mockk<Grid>()
        every { grid.width } returns 5
        every { grid.height } returns 3
        every { grid.pollutedList } answers { mutableListOf() }
        justRun { grid.setGrid(any(), any()) }

        val command = mockk<Command>()
        every { command.execute() } just Runs

        val moveForwardCommand = mockk<MoveForwardCommand>()
        every { moveForwardCommand.execute() } just Runs
        val moveRightCommand = mockk<MoveRightCommand>()
        every { moveRightCommand.execute() } just Runs
        val moveLeftCommand = mockk<MoveLeftCommand>()
        every { moveLeftCommand.execute() } just Runs

        val parser = mockk<Parser>()
        every { parser.parse() } returns listOf(moveRightCommand, moveForwardCommand, moveLeftCommand)

        val sut = Game(grid, robot)

        // when
        sut.startGame(
            """
             5 3
             1 1 E
             RFL
        """.trimIndent()
        )

        // then
        verifyOrder {
            moveRightCommand.execute()
            moveForwardCommand.execute()
            moveLeftCommand.execute()
        }
    }

    @Test
    fun `startGame works correctly - it sets robot in correct direction after changes`() {
        // given
        val robot = spyk<Robot>()
        every { robot.robotDirection } answers { Direction.E }
        every { robot.robotPosition } answers { Position(1, 1) }
        every { robot.robotStatus } answers { Status.ALIVE }

        val grid = spyk<Grid>()
        every { grid.width } answers { 5 }
        every { grid.height } answers { 3 }
        every { grid.pollutedList } answers { mutableListOf() }

        val command = mockk<Command>()
        every { command.execute() } just Runs

        val moveForwardCommand = mockk<MoveForwardCommand>()
        every { moveForwardCommand.execute() } just Runs
        val moveRightCommand = mockk<MoveRightCommand>()
        every { moveRightCommand.execute() } just Runs
        val moveLeftCommand = mockk<MoveLeftCommand>()
        every { moveLeftCommand.execute() } just Runs

        val parser = mockk<Parser>()
        every { parser.parse() } returns listOf(moveRightCommand, moveForwardCommand, moveLeftCommand)
        val sut = Game(grid, robot)

        // when
        sut.startGame(
            """
             5 3
             1 1 E
             RFRFRFRF
        """.trimIndent()
        )

        // then
        robot.robotDirection shouldBeEqualTo Direction.E
    }

    // ??????
    @Test
    fun `startGame doesn't go to next moves when robot has been lost`() {
        // given
        val robot = spyk(Robot(), recordPrivateCalls = true)
        every { robot.robotDirection } answers { Direction.S }
        every { robot.robotPosition } answers { Position(0, 0) }
        every { robot.robotStatus } answers { Status.ALIVE }

        val grid = spyk(Grid(), recordPrivateCalls = true)
        every { grid.width } answers { 5 }
        every { grid.height } answers { 3 }
        every { grid.pollutedList } answers { mutableListOf() }

        every { grid.setGrid(5, 3) } just Runs

        val command = mockk<Command>()
        every { command.execute() } just Runs

        val moveForwardCommand = mockk<MoveForwardCommand>()
        every { moveForwardCommand.execute() } just Runs
        val moveRightCommand = mockk<MoveRightCommand>()
        every { moveRightCommand.execute() } just Runs
        val moveLeftCommand = mockk<MoveLeftCommand>()
        every { moveLeftCommand.execute() } just Runs

        val parser = mockk<Parser>()
        every { parser.parse() } returns listOf(
            moveForwardCommand,
            moveForwardCommand,
            moveForwardCommand,
            moveForwardCommand,
            moveForwardCommand,
            moveForwardCommand
        )
        val sut = Game(grid, robot)

        // when
        sut.startGame(
            """
             5 3
             0 0 S
             FFFFFF
        """.trimIndent()
        )

        // then
        robot.robotStatus shouldBeEqualTo Status.LOST
    }

    @Test
    fun `startGame works correctly - robot status is lost when he went outside grid`() {
        // given


        // when
        sut.startGame(
            """
             5 3
             0 0 S
             FFFFFF
        """.trimIndent()
        )

        // then
        robot.robotStatus shouldBeEqualTo Status.LOST
    }

     */
}