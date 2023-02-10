package com.natalia.martianrobots

import com.natalia.martianrobots.commands.CommandProcessor
import com.natalia.martianrobots.factory.ConfigParserFactory
import com.natalia.martianrobots.factory.GridFactory
import com.natalia.martianrobots.factory.RobotFactory
import com.natalia.martianrobots.parser.ConfigParser
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

internal class GameTest {
    private val configParserFactory = mockk<ConfigParserFactory>()
    private val robotFactory = mockk<RobotFactory>()
    private val gridFactory = mockk<GridFactory>()
    private val commandProcessor = mockk<CommandProcessor>()

    private val sut = Game(configParserFactory, robotFactory, gridFactory, commandProcessor)

    @Test
    fun `robot was created with a correct data`() {
        //given
        val input = "input"

        val direction = Direction.N
        val position = Position(2, 3)

        val config = mockk<Config>()
        every { config.robotPosition } returns position
        every { config.robotDirection } returns direction
        every { config.gridHeight } returns 5
        every { config.gridWidth } returns 4

        val configParser = mockk<ConfigParser>()

        every { configParserFactory.create() } returns configParser

        every { configParser.parse(input) } returns config

        val robot = mockk<Robot>()
        every { robot.robotDirection } returns direction
        every { robot.robotPosition } returns position
        every { robotFactory.create(direction, position) } returns robot

        val grid = mockk<Grid>()
        every { grid.width } returns 4
        every { grid.height } returns 5
        every { grid.deadPoints } returns mutableListOf()
        every { gridFactory.create(4, 5) } returns grid

        justRun { commandProcessor.processCommands(robot, grid, config.commands) }

        //when
        sut.startGame(input)

        //then
        verify {
            robotFactory.create(direction, position)
        }
    }

    @Test
    fun `grid was created with a correct data`() {
        //given
        val input = "input"

        val direction = Direction.N
        val position = Position(2, 3)

        val config = mockk<Config>()
        every { config.robotPosition } returns position
        every { config.robotDirection } returns direction
        every { config.gridHeight } returns 5
        every { config.gridWidth } returns 4

        val configParser = mockk<ConfigParser>()

        every { configParserFactory.create() } returns configParser

        every { configParser.parse(input) } returns config

        val robot = mockk<Robot>()
        every { robot.robotDirection } returns direction
        every { robot.robotPosition } returns position
        every { robotFactory.create(direction, position) } returns robot

        val grid = mockk<Grid>()
        every { grid.width } returns 4
        every { grid.height } returns 5
        every { grid.deadPoints } returns mutableListOf()
        every { gridFactory.create(4, 5) } returns grid

        justRun { commandProcessor.processCommands(robot, grid, config.commands) }

        //when
        sut.startGame(input)

        //then
        verify {
            gridFactory.create(4, 5)
        }
    }

    @Test
    fun `startGame throws exception when width is bigger than 50`() {
        //given
        val input = "input"

        val position = Position(1, 1)
        val direction = Direction.N

        val config = mockk<Config>()
        every { config.robotPosition } returns position
        every { config.robotDirection } returns direction
        every { config.gridHeight } returns 3
        every { config.gridWidth } returns 54

        val configParser = mockk<ConfigParser>()

        every { configParserFactory.create() } returns configParser

        every { configParser.parse(input) } returns config

        val robot = mockk<Robot>()
        every { robot.robotPosition } returns position
        every { robotFactory.create(direction, position) } returns robot

        val grid = mockk<Grid>()
        every { grid.width } returns 54
        every { grid.height } returns 3
        every { gridFactory.create(54, 3) } returns grid

        justRun { commandProcessor.processCommands(robot, grid, config.commands) }

        //when
        val actual = { sut.startGame(input) }

        //then
        actual shouldThrow IllegalArgumentException::class withMessage "Grid is rectangle: 51>x>0 and 51>y>0"
    }

    @Test
    fun `startGame throws exception when robotPosition is bigger than gridSize`() {
        //given
        val input = "input"

        val position = Position(10, 1)
        val direction = Direction.N

        val config = mockk<Config>()
        every { config.robotPosition } returns position
        every { config.robotDirection } returns direction
        every { config.gridHeight } returns 3
        every { config.gridWidth } returns 5

        val configParser = mockk<ConfigParser>()

        every { configParserFactory.create() } returns configParser

        every { configParser.parse(input) } returns config

        val robot = mockk<Robot>()
        every { robot.robotPosition } returns position
        every { robotFactory.create(direction, position) } returns robot

        val grid = mockk<Grid>()
        every { grid.width } returns 5
        every { grid.height } returns 3
        every { gridFactory.create(5, 3) } returns grid

        justRun { commandProcessor.processCommands(robot, grid, config.commands) }

        //when
        val actual = { sut.startGame(input) }

        //then
        actual shouldThrow IllegalArgumentException::class withMessage "Incorrect position: 0 <= x <= width and 0 <= y <= height"
    }

    @Test
    fun `processCommands was called correctly`() {
        //given
        val input = "input"

        val position = Position(1, 1)
        val direction = Direction.N

        val config = mockk<Config>()
        every { config.robotPosition } returns position
        every { config.robotDirection } returns direction
        every { config.gridHeight } returns 3
        every { config.gridWidth } returns 5

        val configParser = mockk<ConfigParser>()

        every { configParserFactory.create() } returns configParser

        every { configParser.parse(input) } returns config

        val robot = mockk<Robot>()
        every { robot.robotPosition } returns position
        every { robotFactory.create(direction, position) } returns robot

        val grid = mockk<Grid>()
        every { grid.width } returns 5
        every { grid.height } returns 3
        every { grid.deadPoints } returns mutableListOf()
        every { gridFactory.create(5, 3) } returns grid

        justRun { commandProcessor.processCommands(robot, grid, config.commands) }

        //when
        sut.startGame(input)

        //then
        verify {
            commandProcessor.processCommands(robot, grid, config.commands)
        }
    }
}