import io.mockk.*
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class GameTest {
//    private fun defaultSettings() {
//        every { robot.robotDirection } returns null
//        every { robot.robotPosition } returns Position(0, 0)
//        every { robot.robotStatus } returns null
//
//        every { grid.height } returns 0
//        every { grid.width } returns 0
//        every { grid.pollutedList } returns mutableListOf()
////
//        every { parser.mainInput("") } returns Triple("", "", "")
//        every { parser.splitGridSize("") } returns Pair(0,0)
//        every { parser.splitRobotPosition("") } returns Pair(Position(0,0), Direction.W)
//        every { parser.splitMoves("", robot, grid) } returns listOf(command)
//
//        every { command.execute() } just Runs
//    }

    @Test
    fun `startGame sets robot on correct position after changes`() {
        // given
        val robot = mockk<Robot>()
        every { robot.robotDirection } returns Direction.E
        every { robot.robotPosition } returns Position(1, 1)
        every { robot.robotStatus } returns Status.ALIVE
        every { robot.setRobotPosition(Position(1,1), Direction.E) } just Runs

        val grid = mockk<Grid>()
        every { grid.height } returns 3
        every { grid.width } returns 5
        every { grid.pollutedList } returns mutableListOf()
        every { grid.setGrid(5,3) } just Runs
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
        verify (exactly = 3) { command.execute() }
    }


//    @Test
//    fun `startGame works correctly - it sets robot in correct direction after changes`() {
//        // given
//
//
//        // when
//        sut.startGame(
//            """
//             5 3
//             1 1 E
//             RFRFRFRF
//        """.trimIndent()
//        )
//
//        // then
//        robot.robotDirection shouldBeEqualTo Direction.E
//    }
//
//
//    // ??????
//    @Test
//    fun `startGame works correctly - it breaks app when robot has been lost`() {
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
//        verify(exactly = 1) {
//            grid.drawGrid(robot)
//        }
//    }
//
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