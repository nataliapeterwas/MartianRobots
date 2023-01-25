package factory

import Grid
import ConfigParser
import Robot

class ParserFactory {
    fun create(grid: Grid, robot: Robot) = ConfigParser(grid, robot)
}