package factory

import Grid
import Parser
import Robot

class ParserFactory {
    fun create(grid: Grid, robot: Robot) = Parser(grid, robot)
}