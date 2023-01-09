import io.mockk.*
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

internal class GameTest {
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
        justRun { grid.drawGrid(robot) }

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
//        robot.robotPosition shouldBeEqualTo Position(1, 1)
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
        every { grid.drawGrid(robot) } just Runs

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

//    @Test
//    fun `startGame works correctly - robot status is lost when he went outside grid`() {
//        // given
//
//
//        // when
//        sut.startGame(
//            """
//             5 3
//             0 0 S
//             FFFFFF
//        """.trimIndent()
//        )
//
//        // then
//        robot.robotStatus shouldBeEqualTo Status.LOST
//    }
//

}