import io.mockk.*
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import parser.ConfigParser

internal class ConfigParserTest {
    @Test
    fun `Parser correctly transforms '5 3   1 1 E   RFLR' to list contains of commandList moveRight, moveForward, moveLeft, moveRight`() {
        // given
        val robot = mockk<Robot>()
        every { robot.robotPosition } returns Position(0, 0)
        every { robot.robotStatus } returns null

        val grid = mockk<Grid>()
        every { grid.height } returns 5
        every { grid.width } returns 3
        every { grid.pollutedList } returns mutableListOf()

        val gridRobotLogger = mockk<GridRobotLogger>()
        justRun { gridRobotLogger.toString() }

        val moveForwardCommand = MoveForwardCommand
        val moveRightCommand = MoveRightCommand
        val moveLeftCommand = MoveLeftCommand

        val input = """
            5 3
            1 1 E
            RFLR
    """.trimIndent()

        val configParser = ConfigParser()

        // when
        val actual = configParser.parse(input).commands

        // then
        actual shouldBeEqualTo listOf(moveRightCommand, moveForwardCommand, moveLeftCommand, moveRightCommand)
    }
}