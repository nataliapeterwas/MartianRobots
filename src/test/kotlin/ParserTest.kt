import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.*
import org.junit.jupiter.api.Test

internal class ParserTest {
    private var parser = Parser()

    @Test
    fun `main input is split to three strings`() {
        //given
        val input = """
            5 3
            1 1 E
            RFRFRFRF
        """.trimIndent()

        //when
        val actual = parser.mainInput(input)

        //then
        actual.first shouldBeEqualTo "5 3"
        actual.second shouldBeEqualTo "1 1 E"
        actual.third shouldBeEqualTo "RFRFRFRF"
    }

    @Test
    fun `mainInput throws exception when we pass empty input`() {
        //given
        val input = """
        """.trimIndent()

        //when
        val actual = {parser.mainInput(input)}

        //then
        actual shouldThrow IllegalArgumentException::class withMessage "You must pass grid size, robot position and robot moves"
    }

    @Test
    fun `mainInput throws exception when we pass incorrect input`() {
        //given
        val input = """
            66
            1
        """.trimIndent()

        //when
        val actual = {parser.mainInput(input)}

        //then
        actual shouldThrow IllegalArgumentException::class withMessage "Incorrect input"
    }

    @Test
    fun `mainInput throws exception when we pass incorrect gridSize (it length is 2)`() {
        //given
        val input = """
            66
            1
            m
        """.trimIndent()

        //when
        val actual = {parser.mainInput(input)}

        //then
        actual shouldThrow IllegalArgumentException::class withMessage "Incorrect grid size"
    }

    @Test
    fun `mainInput throws exception when we pass incorrect gridSize (it has blank space at beginning)`() {
        //given
        val input = """
             66
            1
            m
        """.trimIndent()

        //when
        val actual = {parser.mainInput(input)}

        //then
        actual shouldThrow IllegalArgumentException::class withMessage "Incorrect grid size"
    }

    @Test
    fun `mainInput throws exception when we pass incorrect robotPosition (incorrect length)`() {
        //given
        val input = """
            6 6
            1
            m
        """.trimIndent()

        //when
        val actual = {parser.mainInput(input)}

        //then
        actual shouldThrow IllegalArgumentException::class withMessage "Incorrect robot position"
    }

    @Test
    fun `mainInput throws exception when we pass incorrect robotPosition (incorrect format)`() {
        //given
        val input = """
            6 6
            1 E 1
            m
        """.trimIndent()

        //when
        val actual = {parser.mainInput(input)}

        //then
        actual shouldThrow IllegalArgumentException::class withMessage "Incorrect robot position"
    }

    @Test
    fun `mainInput throws exception when we pass incorrect moves (contains Digit)`() {
        //given
        val input = """
            6 6
            1 1 E
            EE1
        """.trimIndent()

        //when
        val actual = {parser.mainInput(input)}

        //then
        actual shouldThrow IllegalArgumentException::class withMessage "Incorrect moves"
    }

    @Test
    fun `mainInput throws exception when we pass incorrect moves (contains blank space)`() {
        //given
        val input = """
            6 6
            1 1 E
            EE RW
        """.trimIndent()

        //when
        val actual = {parser.mainInput(input)}

        //then
        actual shouldThrow IllegalArgumentException::class withMessage "Incorrect moves"
    }

    @Test
    fun `mainInput throws exception when we pass incorrect moves (contains small letter)`() {
        //given
        val input = """
            6 6
            1 1 E
            EErW
        """.trimIndent()

        //when
        val actual = {parser.mainInput(input)}

        //then
        actual shouldThrow IllegalArgumentException::class withMessage "Incorrect moves"
    }

    @Test
    fun `splitGridSize works correct`() {
        //given
        val gridSize = "30 5"

        //when
        val actual = parser.splitGridSize(gridSize)

        //then
        actual.first shouldBeEqualTo 30
        actual.second shouldBeEqualTo 5
    }

    @Test
    fun `splitGridSize throws exception when width is too long`() {
        //given
        val gridSize = "60 5"

        //when
        val actual = {parser.splitGridSize(gridSize)}

        //then
        actual shouldThrow IllegalArgumentException::class withMessage "Grid is rectangle: 51>x>0 and 51>y>0"
    }

    @Test
    fun `splitRobotPosition works correct`() {
        //given
        val robotPosition = "30 5 E"

        //when
        val actual = parser.splitRobotPosition(robotPosition)

        //then
        actual.first shouldBeEqualTo 30
        actual.second shouldBeEqualTo 5
        actual.third shouldBeEqualTo Direction.E
    }

    @Test
    fun `splitRobotPosition throw exception when it is bigger than grid size`() {
        //given
        val robotPosition = "60 5 K"

        //when
        val actual = {parser.splitRobotPosition(robotPosition)}

        //then
        actual shouldThrow IllegalArgumentException::class
    }

    //??????
    @Test
    fun `splitMoves works correct`(){
        //given
        val robot = Robot()
        val grid = Grid()
        val moves = "FRLLL"

        //when
        val actual = parser.splitMoves(moves, robot, grid)

        //then
        actual shouldBeEqualTo listOf(MoveForwardCommand(robot, grid), MoveRightCommand(robot), MoveLeftCommand(robot), MoveLeftCommand(robot), MoveLeftCommand(robot))
    }

}