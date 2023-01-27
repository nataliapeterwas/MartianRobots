package com.natalia.martianrobots
import com.natalia.martianrobots.factory.ConfigParserFactory
import com.natalia.martianrobots.factory.GridFactory
import com.natalia.martianrobots.factory.RobotFactory

class Game(
    private val configParserFactory: ConfigParserFactory,
    private val robotFactory: RobotFactory,
    private val gridFactory: GridFactory,
    private val commandProcessor: CommandProcessor,
) {
    private var deadPoints = mutableListOf<Position>()
    fun startGame(input: String,) {
        val config = configParserFactory.create().parse(input)
        val robot = robotFactory.create(config.robotDirection, config.robotPosition)
        val grid = gridFactory.create(config.gridWidth, config.gridHeight, deadPoints)

        require(robot.robotPosition.x in 0..grid.width && robot.robotPosition.y in 0..grid.height) { "Incorrect position: 0 <= x <= width and 0 <= y <= height" }
        require(grid.width in 1..50 && grid.height in 1..50) { "Grid is rectangle: 51>x>0 and 51>y>0" }

        commandProcessor.processCommands(robot, grid, config.commands)
        deadPoints = grid.deadPoints
    }

//    fun createNewRobot(input: String) {
//        val parser = parserFactory.create().parse(input)
//        val robot = robotFactory.create(parser.robotDirection, parser.robotPosition)
//        require(robot.robotPosition.x in 0..grid.width && robot.robotPosition.y in 0..grid.height) { "Incorrect position: 0 <= x <= width and 0 <= y <= height" }
//
//
//    }
}
