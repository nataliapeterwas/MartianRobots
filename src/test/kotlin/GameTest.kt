import factory.GridFactory
import factory.ParserFactory
import factory.RobotFactory
import io.mockk.*
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

internal class GameTest {
    @Test
    fun `startGame throws exception when width is bigger than 50`() {
        //given
        val robotFactory = mockk<RobotFactory>()
        every { robotFactory.create(Direction.E, Position(1, 1)) } returns Robot(Direction.E, Position(1, 1))

        val gridFactory = mockk<GridFactory>()
        every { gridFactory.create(54, 3, mutableListOf()) } returns Grid(54, 3)

        val input = """
             54 3
             1 1 E
             RFL
        """.trimIndent()

        val parserFactory = mockk<ParserFactory>()
        justRun { parserFactory.create().parse(input) }

        val sut = Game()

        //when
        val actual = { sut.startGame(input) }

        //then
        actual shouldThrow IllegalArgumentException::class withMessage "Grid is rectangle: 51>x>0 and 51>y>0"
    }

    @Test
    fun `startGame throws exception when robotPosition is bigger than gridSize`() {
        //given
        val robotFactory = mockk<RobotFactory>()
        every { robotFactory.create(Direction.E, Position(10, 1)) } returns Robot(Direction.E, Position(1, 1))

        val gridFactory = mockk<GridFactory>()
        every { gridFactory.create(4, 3, mutableListOf()) } returns Grid(4, 3)

        val input = """
             4 3
             10 1 E
             RFL
        """.trimIndent()

        val parserFactory = mockk<ParserFactory>()
        justRun { parserFactory.create().parse(input) }

        val sut = Game()

        //when
        val actual = { sut.startGame(input) }

        //then
        actual shouldThrow IllegalArgumentException::class withMessage "Incorrect position: 0 <= x <= width and 0 <= y <= height"
    }

    @Test
    fun `startGame calls processCommands method`() {
        //given
        val input = """
             5 3
             1 1 E
             RFL
        """.trimIndent()

        val robot = mockk<Robot>()
        every { robot.robotStatus } returns RobotStatus.ALIVE
        val robotFactory = mockk<RobotFactory>()
        every { robotFactory.create(Direction.E, Position(1, 1)) } returns Robot(Direction.E, Position(1, 1))

        val grid = mockk<Grid>()
        val gridFactory = mockk<GridFactory>()
        every { gridFactory.create(5, 3) } returns Grid(5, 3)

        val parserFactory = mockk<ParserFactory>()
        justRun { parserFactory.create().parse(input) }

        val configParser = mockk<parser.ConfigParser>()

        val gridRobotLogger = mockk<GridRobotLogger>()
        justRun { gridRobotLogger.log(robot, grid) }

        every { parserFactory.create() } returns configParser
        val moveForwardCommand = mockk<MoveForwardCommand>()
        justRun { moveForwardCommand.execute(robot, grid) }
        val moveRightCommand = mockk<MoveRightCommand>()
        justRun { moveRightCommand.execute(robot) }
        val moveLeftCommand = mockk<MoveLeftCommand>()
        justRun { moveLeftCommand.execute(robot) }

        every { configParser.parse(any()).commands } returns listOf(
            moveRightCommand,
            moveForwardCommand,
            moveLeftCommand
        )

        val sut = Game()

        //when
        sut.startGame(input)

        //then
        verify {
            moveRightCommand.execute(robot)
            moveForwardCommand.execute(robot, grid)
            moveLeftCommand.execute(robot)
        }
    }

//    @Test
//    fun `startGame() calls only two commands and the last is not called`(){
//        //given
//        val robot = mockk<Robot>()
//        every { robot.robotStatus } returnsMany listOf(RobotStatus.ALIVE, RobotStatus.ALIVE, RobotStatus.LOST)
//
//        val grid = mockk<Grid>()
//
//        val gridRobotLogger = mockk<GridRobotLogger>()
//        justRun { gridRobotLogger.log(robot, grid) }
//
//        val input = """
//             5 3
//             1 1 E
//             RFF
//        """.trimIndent()
//
//        val parserFactory = mockk<ParserFactory>()
//        val configParser = mockk<parser.ConfigParser>()
//
//        every { parserFactory.create() } returns configParser
//        val moveForwardCommand = mockk<MoveForwardCommand>()
//        every { moveForwardCommand.execute(robot, grid) } just Runs
//        val moveRightCommand = mockk<MoveRightCommand>()
//        every { moveRightCommand.execute(robot) } just Runs
//        val moveLeftCommand = mockk<MoveLeftCommand>()
//        every { moveLeftCommand.execute(robot) } just Runs
//
//        every { configParser.parse(any()) } returns listOf(moveRightCommand, moveForwardCommand, moveLeftCommand)
//
//        val sut = Game(grid, robot, parserFactory, gridRobotLogger)
//
//        //when
//        sut.startGame(input)
//
//        verify(exactly = 0) { moveLeftCommand.execute()}
//    }

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