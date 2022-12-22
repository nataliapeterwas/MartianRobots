import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.*
import org.junit.jupiter.api.Test

internal class ParserTest {
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
    fun `gridSizeParser throws exception when we pass incorrect gridSize (it length is 2)`() {
        // given
        val input = "66"

        // when
        val actual = {GridSizeParser(input).gridSizeParser()}

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Incorrect grid size"
    }

    @Test
    fun `gridSizeParser throws exception when we pass incorrect gridSize (it has blank space at beginning)`() {
        // given
        val input = " 66"

        // when
        val actual = {GridSizeParser(input).gridSizeParser()}

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Incorrect grid size"
    }

    @Test
    fun `gridSizeParser throws exception when we pass incorrect robotPosition (it length is 1)`() {
        // given
        val input = "1"

        // when
        val actual = {RobotPositionParser(input).robotPositionParser()}

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Incorrect robot position"
    }

    @Test
    fun `robotPositionParser throws exception when we pass incorrect robotPosition (incorrect format - 1 E 1)`() {
        // given
        val input = "1 E 1"

        // when
        val actual = {RobotPositionParser(input).robotPositionParser()}

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Incorrect robot position"
    }

    @Test
    fun `MovesParser throws exception when we pass incorrect moves (contains Digit)`() {
        // given
        val input = "RR1"
        val robot = Robot()
        val grid = Grid()

        // when
        val actual = {MovesParser(input, robot, grid)}

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Incorrect moves"
    }

    @Test
    fun `mainInput throws exception when we pass incorrect moves (contains blank space)`() {
        // given
        val input = "RL "

        // when
        val actual = {MainInputParser(input).mainInput()}

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Incorrect moves"
    }

    @Test
    fun `mainInput throws exception when we pass incorrect moves (contains small letter)`() {
        // given
        val input = "LrF"

        // when
        val actual = {MainInputParser(input).mainInput()}

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Incorrect moves"
    }

    @Test
    fun `splitGridSize transforms string '30 5' to grid width and grid height`() {
        // given
        val gridSize = "30 5"

        // when
        val actual = GridSizeParser(gridSize).gridSizeParser()

        // then
        actual.first shouldBeEqualTo 30
        actual.second shouldBeEqualTo 5
    }

    @Test
    fun `splitGridSize throws exception when width is 60 (it is too big)`() {
        // given
        val gridSize = "60 5"

        // when
        val actual = {GridSizeParser(gridSize).gridSizeParser()}

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Grid is rectangle: 51>x>0 and 51>y>0"
    }

    @Test
    fun `splitRobotPosition correctly transforms string '30 5 E' to robotPosition and robotDirection`() {
        // given
        val robotPosition = "30 5 E"

        // when
        val actual = RobotPositionParser(robotPosition).robotPositionParser()

        // then
        actual.first shouldBeEqualTo Position(30, 5)
        actual.second shouldBeEqualTo Direction.E
    }

    @Test
    fun `splitRobotPosition throw exception when its width is 60 and its bigger than grid size`() {
        // given
        val robotPosition = "60 5 K"

        // when
        val actual = { RobotPositionParser(robotPosition).robotPositionParser()}

        // then
        actual shouldThrow IllegalArgumentException::class
    }

    @Test
    fun `MovesParser correctly transforms 'FRLLL' to list contains of commandList moveForward, moveRight, moveLeft, moveLeft, moveLeft`(){
        // given
        val robot = mockk<Robot>()
        every { robot.robotDirection } returns null
        every { robot.robotPosition } returns Position(0, 0)
        every { robot.robotStatus } returns null
        every { robot.setRobotPosition(Position(0,0), Direction.W) } just Runs

        val grid = mockk<Grid>()
        every { grid.height } returns 0
        every { grid.width } returns 0
        every { grid.pollutedList } returns mutableListOf()
        every { grid.setGrid(0,0) } just Runs
        every { grid.drawGrid(robot) } just Runs

        val moves = "FRLLL"
        val moveForwardCommand = MoveForwardCommand(robot, grid)
        val moveRightCommand = MoveRightCommand(robot)
        val moveLeftCommand = MoveLeftCommand(robot)

        // when
        val actual = MovesParser(moves, robot, grid).movesParser()

        // then
        actual shouldBeEqualTo listOf(moveForwardCommand, moveRightCommand, moveLeftCommand, moveLeftCommand, moveLeftCommand)
    }

    @Test
    fun `Parser correctly transforms '5 3   1 1 E   RFLR' to list contains of commandList moveRight, moveForward, moveLeft, moveRight`() {
        // given
        val robot = mockk<Robot>()
        every { robot.robotDirection } returns null
        every { robot.robotPosition } returns Position(0, 0)
        every { robot.robotStatus } returns null
        every { robot.setRobotPosition(Position(1,1), Direction.E) } just Runs

        val grid = mockk<Grid>()
        every { grid.height } returns 5
        every { grid.width } returns 3
        every { grid.pollutedList } returns mutableListOf()
        every { grid.setGrid(5,3) } just Runs
        every { grid.drawGrid(robot) } just Runs

        val moveForwardCommand = MoveForwardCommand(robot, grid)
        val moveRightCommand = MoveRightCommand(robot)
        val moveLeftCommand = MoveLeftCommand(robot)

        val input = """
            5 3
            1 1 E
            RFLR
    """.trimIndent()

        val parser = Parser(input, grid, robot)

        // when
        val actual = parser.parse()

        // then
        actual shouldBeEqualTo listOf(moveRightCommand, moveForwardCommand, moveLeftCommand, moveRightCommand)
    }
}