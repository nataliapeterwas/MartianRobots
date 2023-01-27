import factory.GridFactory
import factory.ConfigParserFactory
import factory.RobotFactory
import io.mockk.*
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test
import parser.*

internal class GameTest {
    private val configParserFactory = mockk<ConfigParserFactory>()
    private val robotFactory = mockk<RobotFactory>()
    private val gridFactory = mockk<GridFactory>()
    private val commandProcessor = mockk<CommandProcessor>()
    private val mainInputConfigParser = mockk<MainInputConfigParser>()
    private val positionAndDirectionConfigParser = mockk<PositionAndDirectionConfigParser>()
    private val gridConfigParser = mockk<GridConfigParser>()
    private val commandsConfigParser = mockk<CommandsConfigParser>()

    private val sut = Game(configParserFactory, robotFactory, gridFactory, commandProcessor)

    @Test
    fun `robot was created with a correct date`() {
        //given
        val input = "input"

        val direction = Direction.N
        val position = Position(2, 3)

        val config = mockk<Config>()
        every { config.robotPosition } returns position
        every { config.robotDirection } returns direction

        val configParser = mockk<ConfigParser>()

        every { configParserFactory.create() } returns configParser
        every {
            configParser.parse(
                input,
                mainInputConfigParser,
                positionAndDirectionConfigParser,
                gridConfigParser,
                commandsConfigParser
            )
        } returns config

        justRun { robotFactory.create(direction, position) }

        //when
        sut.startGame(input)

        //then
        verify {
            robotFactory.create(direction, position)
        }
    }

    @Test
    fun `startGame throws exception when width is bigger than 50`() {
        //given
        val input = "input"
        val position = Position(1, 1)

        val config = mockk<Config>()
        every { config.robotPosition } returns position
        every { config.gridWidth } returns 54
        every { config.gridHeight } returns 3
        every { config.robotDirection } returns Direction.E
        every { config.commands } returns listOf()

//        every { mainInputConfigParser.parse(any()) } returns

        val configParser = mockk<ConfigParser>()

        every {
            configParserFactory.create() } returns configParser

        every { configParser.parse(
            input,
            mainInputConfigParser,
            positionAndDirectionConfigParser,
            gridConfigParser,
            commandsConfigParser
        ) } returns config

        val robot = mockk<Robot>()
        every { robot.robotPosition } returns position
        every { robotFactory.create(Direction.E, position) } returns robot

        val grid = mockk<Grid>()
        every { grid.width } returns 54
        every { grid.height } returns 3
        every { gridFactory.create(54, 3, mutableListOf()) } returns grid

        val commandProcessor = mockk<CommandProcessor>()
        justRun { commandProcessor.processCommands(robot, grid, any()) }


//        justRun {
//            configParserFactory.create().parse(
//                input,
//                mainInputConfigParser,
//                positionAndDirectionConfigParser,
//                gridConfigParser,
//                commandsConfigParser
//            )
//        }

        //when
//        val actual = { sut.startGame(input) }
        sut.startGame(input)

    //then
//        actual shouldThrow IllegalArgumentException::class withMessage "Grid is rectangle: 51>x>0 and 51>y>0"

}
    @Test
    fun `startGame throws exception when robotPosition is bigger than gridSize`() {
        //given
        every { robotFactory.create(Direction.E, Position(10, 1)) } returns Robot(Direction.E, Position(1, 1))

        every { gridFactory.create(4, 3, mutableListOf()) } returns Grid(4, 3)

        val input = "input"

        justRun {
            configParserFactory.create().parse(
                input,
                mainInputConfigParser,
                positionAndDirectionConfigParser,
                gridConfigParser,
                commandsConfigParser
            )
        }

        //when
        //val actual = { sut.startGame(input) }
        sut.startGame(input)

        //then
//        actual shouldThrow IllegalArgumentException::class withMessage "Incorrect position: 0 <= x <= width and 0 <= y <= height"
    }

    @Test
    fun `processCommands was called correctly`() {
        //given
        val input = "input"

        justRun { commandProcessor.processCommands(any(), any(), any()) }

        val config = mockk<Config>()
        every { config.commands } returns listOf(MoveRightCommand)

        val robot = mockk<Robot>()
        every { robot.robotPosition } returns Position(2, 3)

        val grid = mockk<Grid>()
        every { grid.width } returns 5
        every { grid.height } returns 4

        every {
            configParserFactory.create().parse(
                input,
                mainInputConfigParser,
                positionAndDirectionConfigParser,
                gridConfigParser,
                commandsConfigParser
            )
        } returns config

        every { robotFactory.create(any(), any()) } returns robot

        every { gridFactory.create(any(), any()) } returns grid

        //when
        sut.startGame(input)

        //then
        verify {
            commandProcessor.processCommands(robot, grid, config.commands)
        }
    }
}