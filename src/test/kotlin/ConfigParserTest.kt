import io.mockk.*
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

internal class ConfigParserTest {
    @Test
    fun `Parser correctly transforms '5 3   1 1 E   RFLR' to list contains of commandList moveRight, moveForward, moveLeft, moveRight`() {
        // given
        val robot = mockk<Robot>()
//        every { robot.robotDirection } returns null
        every { robot.robotPosition } returns Position(0, 0)
        every { robot.robotStatus } returns null
        every { robot.setRobotPosition(Position(1,1), Direction.E) } just Runs

        val grid = mockk<Grid>()
        every { grid.height } returns 5
        every { grid.width } returns 3
        every { grid.pollutedList } returns mutableListOf()
        every { grid.setSize(5,3) } just Runs

        val gridRobotLogger = mockk<GridRobotLogger>()
        justRun { gridRobotLogger.toString() }

        val moveForwardCommand = MoveForwardCommand(robot, grid)
        val moveRightCommand = MoveRightCommand(robot)
        val moveLeftCommand = MoveLeftCommand(robot)

        val input = """
            5 3
            1 1 E
            RFLR
    """.trimIndent()

        val configParser = ConfigParser(grid, robot)

        // when
        val actual = configParser.parse(input)

        // then
        actual shouldBeEqualTo listOf(moveRightCommand, moveForwardCommand, moveLeftCommand, moveRightCommand)
    }
}