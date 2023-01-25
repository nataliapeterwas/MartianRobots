import io.mockk.*
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

internal class MainInputParserTest{
    @Test
    fun `MainInputParser is correctly split  '5 3   1 1 E   RFRFRFRF' to three separate strings`() {
        // given
        val input = """
            5 3
            1 1 E
            RFRFRFRF
        """.trimIndent()

        // when
        val actual = MainInputParser(input).mainInput()

        // then
        actual.first shouldBeEqualTo "5 3"
        actual.second shouldBeEqualTo "1 1 E"
        actual.third shouldBeEqualTo "RFRFRFRF"
    }

    @Test
    fun `mainInputParser throws exception when we pass empty input`() {
        // given
        val input = """
        """.trimIndent()

        // when
        val actual = {MainInputParser(input).mainInput()}

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "You must pass grid size, robot position and robot moves"
    }

    @Test
    fun `mainInput throws exception when we pass incorrect input (two lines instead of three)`() {
        // given
        val input = """
            66
            1
        """.trimIndent()

        // when
        val actual = {MainInputParser(input).mainInput()}

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Incorrect input"
    }

    @Test
    fun `MovesParser correctly transforms 'FRLLL' to list contains of commandList moveForward, moveRight, moveLeft, moveLeft, moveLeft`(){
        // given
        val robot = mockk<Robot>()
        every { robot.robotPosition } returns Position(0, 0)
        every { robot.robotStatus } returns null
        every { robot.setRobotPosition(Position(0,0), Direction.W) } just Runs

        val grid = mockk<Grid>()
        every { grid.height } returns 0
        every { grid.width } returns 0
        every { grid.pollutedList } returns mutableListOf()
        every { grid.setSize(0,0) } just Runs

        val gridRobotLogger = mockk<GridRobotLogger>()
        justRun { gridRobotLogger.toString() }

        val moves = "FRLLL"
        val moveForwardCommand = MoveForwardCommand(robot, grid)
        val moveRightCommand = MoveRightCommand(robot)
        val moveLeftCommand = MoveLeftCommand(robot)

        // when
        val actual = CommandsConfigParser(moves, robot, grid).movesParser()

        // then
        actual shouldBeEqualTo listOf(moveForwardCommand, moveRightCommand, moveLeftCommand, moveLeftCommand, moveLeftCommand)
    }

}